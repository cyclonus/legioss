package com.faraya.legioss.service.payroll;

import com.faraya.legioss.core.dao.calendar.ICalendarDAO;
import com.faraya.legioss.core.dao.payroll.log.IAttendanceLogDAO;
import com.faraya.legioss.core.entity.calendar.Calendar;
import com.faraya.legioss.core.entity.calendar.CalendarDate;
import com.faraya.legioss.core.entity.calendar.Type;
import com.faraya.legioss.core.entity.common.*;
import com.faraya.legioss.core.entity.payroll.Employee;
import com.faraya.legioss.core.entity.payroll.agreement.Agreements;
import com.faraya.legioss.core.entity.payroll.agreement.HoursAgreement;
import com.faraya.legioss.core.entity.payroll.agreement.PayType;
import com.faraya.legioss.core.entity.payroll.log.DailyAttendance;
import com.faraya.legioss.core.model.payroll.DailySalary;
import com.faraya.legioss.core.model.payroll.EmployeePayment;
import com.faraya.legioss.util.TemporalDifference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * Created by fabrizzio on 10/31/15.
 */

@Service
public class PayrollServiceImpl implements IPayrollService{

    @Autowired
    ICalendarDAO calendarDAO;

    @Autowired
    IAttendanceLogDAO attendanceLogDAO;

    /**
     * TODO Cache this method
     * @param businessId
     * @param period
     * @return
     */
    private Map<LocalDate,CalendarDate> getCalendar(Long businessId, Period period){
        Calendar calendar = calendarDAO.findByBusinessId(businessId);
        Set<CalendarDate> calendarDates = calendar.getCalendarDates();
        return calendarDates.stream().filter(
                calendarDate -> period.isWithinPeriod(calendarDate.getDate())
        ).collect(Collectors.toMap(CalendarDate::getDate, calendarDate -> calendarDate ));
    }

    /**
     * Given an employee, extract all active agreements and group them by payType
     * @param employee
     * @return
     */
    private Map<PayType,List<HoursAgreement>> getActiveHourAgreementsByPayType(Employee employee){
        Agreements agreements = employee.getAgreements();
        Set<HoursAgreement> hoursAgreements = agreements.getHoursAgreements();
        return hoursAgreements.stream().filter(
                hoursAgreement -> hoursAgreement.isActive()
        ).sorted((o1, o2) -> o1.getSchedule().getTimeIn().compareTo(o2.getSchedule().getTimeIn()))
         .collect(Collectors.groupingBy(HoursAgreement::getPayType));
    }

    private List<DailyAttendance> getAttendanceLog(Employee employee, Period period){
        List<DailyAttendance> dailyAttendances = attendanceLogDAO.findAttendance(employee.getId(),period);
        //dailyAttendances.sort((o1, o2) -> o1.getDate().compareTo(o2.getDate()));
        return dailyAttendances;
    }

    /**
     * Given the workedHours extracted from the attendance log, we compute hours against the agreements passed
     * @param agreements
     * @param workedHours
     * @return
     */
    private Map<HoursAgreement,BigDecimal> getHoursPerRate(final List<HoursAgreement> agreements, final DailyWorkedHours workedHours){
        final LocalTime in = workedHours.getTimeIn();
        final LocalTime out = workedHours.getTimeOut();
        BigDecimal checksum = new BigDecimal(0);
        Map<HoursAgreement,BigDecimal> result = new HashMap<>();
        // First iterate over the agreements trying to match the workedHours (a Day of work) occurrence passed
        for(HoursAgreement ha:agreements){
            final DailyWorkSchedule schedule = ha.getSchedule();
            if(schedule.isWithinBoundaries(out)){ 
                if(schedule.isWithinBoundaries(in) && schedule.isWithinBoundaries(out)){
                    // difference between in & out, that's it
                    TemporalDifference td = TemporalDifference.of(in, out);
                    BigDecimal hours = BigDecimal.valueOf(td.getHours() + (td.getMinutes() / 60));
                    result.put(ha,hours);
                    checksum = checksum.add(hours);
                } else {
                    //TODO Review this logic here
                    TemporalDifference td = TemporalDifference.of(workedHours.getTimeIn(), out);
                    BigDecimal hours = BigDecimal.valueOf(td.getHours() + (td.getMinutes() / 60));
                    result.put(ha,hours);
                    checksum = checksum.add(hours);
                }
            } else {
                //accumulate all this time
                int h = workedHours.getHours();
                BigDecimal hours = BigDecimal.valueOf(h);
                result.put(ha,hours);
                checksum = checksum.add(hours);
            }
            //now validate results, they must match
            // TemporalDifference td = new TemporalDifference(in, out);
            // BigDecimal checksumExpectedResult = BigDecimal.valueOf(td.getHours() + (td.getMinutes() / 60));
            // assert (checksumExpectedResult.compareTo(checksum) == 0);

        }
        return result;
    }

    /**
     *
     * @param hoursPerRate
     * @return
     */
    private Map<Currency,BigDecimal>computeDailyTotals(final Map<HoursAgreement,BigDecimal> hoursPerRate){
        Map<Currency,BigDecimal> result = new HashMap<>();
        hoursPerRate.forEach((hoursAgreement, hours) -> {
            BasicMoney rate = hoursAgreement.getRate();
            Currency currency = rate.getCurrency().toCurrency();
            result.compute(currency,(k, v) -> v == null ? rate.getAmount().multiply(hours) : v.add(rate.getAmount().multiply(hours)));
        });
        return result;
    }

    /**
     *
     * @param dailyAttendance
     * @param hoursAgreements
     * @return
     */
    private DailySalary computeDailySalary(DailyAttendance dailyAttendance, Map<PayType,List<HoursAgreement>> hoursAgreements, CalendarDate calendarDate){
        final boolean holidayOverride = (calendarDate != null && (calendarDate.getType() == Type.MANDATORY_HOLIDAY));
        final LocalDate date = dailyAttendance.getDate();
        final PayType payType = PayType.from(date);
        final List<HoursAgreement> agreements = hoursAgreements.get(payType);
        final DailyWorkedHours workedHours = dailyAttendance.getWorkedHours();
        final Map<HoursAgreement,BigDecimal> hoursPerRate = getHoursPerRate(agreements, workedHours);
        Map<Currency,BigDecimal> attendanceTotals = computeDailyTotals(hoursPerRate);
        // Compute piecework part
        return new DailySalary(attendanceTotals);
    }

    /**
     * Computes daily salary for a given attendance day
     * @param dailyAttendance
     * @param calendarDate
     * @return
     */
    public DailySalary computeDailySalary(DailyAttendance dailyAttendance, CalendarDate calendarDate){
        Employee employee = dailyAttendance.getEmployee();
        Map<PayType,List<HoursAgreement>> hoursAgreementsByPayType = getActiveHourAgreementsByPayType(employee);
        return this.computeDailySalary(dailyAttendance, hoursAgreementsByPayType, calendarDate);
    }

    /**
     *
     * @param employee
     * @param period
     * @param business
     * @return
     */
    public EmployeePayment computePayroll(Employee employee, Period period, Business business){
       List<DailySalary> dailySalaries = new ArrayList<>();
       // Lets get the global calendar, for the current company
       Map<LocalDate,CalendarDate> calendarDates = getCalendar(business.getId(), period);
       // Get all attendance for a given period, Lets say a month
       List<DailyAttendance> attendances = getAttendanceLog(employee, period);
       // Lets go over everyday in attendance
       for(DailyAttendance dailyAttendance:attendances) {
           CalendarDate calendarDate = calendarDates.get(dailyAttendance.getDate());
           //compute salary for the current entry
           DailySalary dailySalary = computeDailySalary(dailyAttendance, calendarDate);
           dailySalaries.add(dailySalary);
       }

       return new EmployeePayment(dailySalaries);
    }

}
