package com.faraya.legioss.service.payroll;

import com.faraya.legioss.core.dao.payroll.log.IAttendanceLogDAO;
import com.faraya.legioss.core.entity.calendar.CalendarDate;
import com.faraya.legioss.core.entity.calendar.Type;
import com.faraya.legioss.core.entity.common.*;
import com.faraya.legioss.core.entity.payroll.Employee;
import com.faraya.legioss.core.entity.payroll.agreement.Agreements;
import com.faraya.legioss.core.entity.payroll.agreement.HoursAgreement;
import com.faraya.legioss.core.entity.payroll.agreement.PayType;
import com.faraya.legioss.core.entity.payroll.log.AttendanceType;
import com.faraya.legioss.core.entity.payroll.log.DailyAttendance;
import com.faraya.legioss.core.model.payroll.PayrollContext;
import com.faraya.legioss.core.model.payroll.attendance.DailyAttendanceSalary;
import com.faraya.legioss.core.model.payroll.attendance.HoursDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * Created by fabrizzio on 12/22/15.
 */

@Service
public class AttendanceSalaryServiceImpl implements IAttendanceSalaryCalculatorService {

    @Autowired
    IAttendanceLogDAO attendanceLogDAO;

    DailyAttendance createDailyAttendance(Long employeeId, LocalDate date, LocalTime timeIn, LocalTime timeOut, AttendanceType attendanceType, String projectRef){
        DailyAttendance attendance = new DailyAttendance(employeeId, date, timeIn, timeOut, attendanceType, projectRef);
        attendanceLogDAO.save(attendance);
        return attendance;
    }

    /**
     *
     * @param employeeId
     * @param period
     * @return
     */
    List<DailyAttendance> getAttendanceLog(Long employeeId, Period period){
        List<DailyAttendance> dailyAttendances = attendanceLogDAO.findAttendance(employeeId,period);
        //dailyAttendances.sort((o1, o2) -> o1.getDate().compareTo(o2.getDate()));
        return dailyAttendances;
    }

    /**
     * For now it'll just multiply rate by two
     * @param baseRate
     * @return
     */
    BigDecimal getHolidayRate(BigDecimal baseRate){
      return baseRate.multiply(BigDecimal.valueOf(2));
    }

    /**
     * Given the workedHours extracted from the attendance log, we compute hours against the agreements passed
     * @param dailyAttendance
     * @param hourAgreements
     * @param holidayOverride
     * @param regularShiftHours
     */

    Set<HoursDetail> getHoursDetail(final List<HoursAgreement> hourAgreements, final DailyAttendance dailyAttendance, final boolean holidayOverride, final int regularShiftHours){
        final String projectRef = dailyAttendance.getProjectRef();
        //dailyAttendance.getAttendanceType();
        final DailyWorkedHours workedHours = dailyAttendance.getWorkedHours();
        final LocalTime in = workedHours.getTimeIn();
        final LocalTime out = workedHours.getTimeOut();
        Set<HoursDetail> result = new HashSet<>();
        // First iterate over the agreements trying to match the workedHours (a Day of work) occurrence passed
        for(HoursAgreement ha:hourAgreements){
            final DailyWorkSchedule schedule = ha.getSchedule();
            if(schedule.isBeforeBoundaries(out)){ //if the time the worked has walked out if before this shift we are analyzing, it does not apply
                break;//we expect them to be sorted, so if time out is not within this schedule it won't be on the others (just break)
            }
            final Duration duration = schedule.getAdjustedTimeBetween(in, out, regularShiftHours); // Applies lunch hour adjustment
            if(!(duration.isZero() || duration.isNegative())){
                final BigDecimal hoursDuration = BigDecimal.valueOf(duration.toMinutes() / 60);
                BasicMoney basicRate = ha.getRate();
                BasicCurrency currency = basicRate.getCurrency();
                BigDecimal rateAmount = (holidayOverride ? getHolidayRate(basicRate.getAmount()) : basicRate.getAmount());
                result.add(new HoursDetail(hoursDuration, rateAmount, currency.toCurrency(), projectRef));//TODO: add the hour agreement id to the 'HoursDetail' here??
            }
        }
        return result;
    }

    /**
     *
     * @param hoursPerRate
     * @return
     */
    Map<Currency,BigDecimal> computeDailyTotals(final Set<HoursDetail>  hoursPerRate){
        Map<Currency,BigDecimal> result = new HashMap<>();
        hoursPerRate.forEach(hoursDetail -> {
            BigDecimal rate = hoursDetail.getRate();
            Currency currency = hoursDetail.getCurrency();
            result.compute(currency, (k, v) -> v == null ? rate.multiply(hoursDetail.getHours()) : v.add(rate.multiply(hoursDetail.getHours())));
        });
        return result;
    }

    /**
     * Given an employee, extract all active agreements and group them by payType
     * @param employee
     * @return
     */
    Map<PayType,List<HoursAgreement>> getActiveHourAgreementsByPayType(Employee employee){
        Agreements agreements = employee.getAgreements();
        Set<HoursAgreement> hoursAgreements = agreements.getHoursAgreements();
        return hoursAgreements.stream().filter(
                hoursAgreement -> hoursAgreement.isActive()
        ).sorted((o1, o2) -> o1.getSchedule().getTimeIn().compareTo(o2.getSchedule().getTimeIn()))
                .collect(Collectors.groupingBy(HoursAgreement::getPayType));
    }

    boolean isHolidayOverride(final LocalDate date, PayrollContext context){
         Map<LocalDate, CalendarDate> calendarDates = context.getCalendarDates();
         CalendarDate calendarDate = calendarDates.get(date);
         return (calendarDate != null && (calendarDate.getType() == Type.MANDATORY_HOLIDAY));
    }

    /**
     *
     * @param dailyAttendance
     * @param hoursAgreements
     * @param context
     * @return
     */
     DailyAttendanceSalary computeDailySalary(DailyAttendance dailyAttendance, Map<PayType,List<HoursAgreement>> hoursAgreements, PayrollContext context){
        final boolean holidayOverride = isHolidayOverride(dailyAttendance.getDate(), context);
        final int regularShiftHours = context.getRegularShiftHours();
        final LocalDate date = dailyAttendance.getDate();
        final PayType payType = PayType.from(date);
        final List<HoursAgreement> hourAgreements = hoursAgreements.get(payType);
        final Set<HoursDetail> hourDetails = getHoursDetail(hourAgreements, dailyAttendance, holidayOverride, regularShiftHours);
        Map<Currency,BigDecimal> attendanceTotals = computeDailyTotals(hourDetails);
        return new DailyAttendanceSalary(date, holidayOverride, attendanceTotals, hourDetails);
    }

    /**
     * Computes daily salary for a given attendance day
     * @param dailyAttendance
     * @param context
     * @return
     */
     DailyAttendanceSalary computeDailySalary(DailyAttendance dailyAttendance, PayrollContext context){
        Employee employee = dailyAttendance.getEmployee();
        Map<PayType,List<HoursAgreement>> hoursAgreementsByPayType = getActiveHourAgreementsByPayType(employee);
         if(hoursAgreementsByPayType == null || hoursAgreementsByPayType.isEmpty()){
           throw new RuntimeException("");
         }
        return computeDailySalary(dailyAttendance, hoursAgreementsByPayType, context);
    }

    /**
     *
     * @param employeeId
     * @param period
     * @param context
     * @return
     */
    public List<DailyAttendanceSalary> computeDailySalary(Long employeeId, Period period, PayrollContext context){
        List<DailyAttendanceSalary> dailySalaries = new ArrayList<>();
        List<DailyAttendance> attendances = getAttendanceLog(employeeId, period);
        for(DailyAttendance dailyAttendance:attendances) {
            //compute salary for the current entry
            DailyAttendanceSalary dailyAttendanceSalary = computeDailySalary(dailyAttendance, context);
            dailySalaries.add(dailyAttendanceSalary);
        }
        return dailySalaries;
    }

}
