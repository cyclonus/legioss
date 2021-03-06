package com.faraya.legioss.service.payroll;

import com.faraya.legioss.core.entity.calendar.Calendar;
import com.faraya.legioss.core.entity.calendar.CalendarDate;
import com.faraya.legioss.core.entity.calendar.Type;
import com.faraya.legioss.core.entity.common.*;
import com.faraya.legioss.core.entity.costing.Piecework;
import com.faraya.legioss.core.entity.payroll.Employee;
import com.faraya.legioss.core.entity.payroll.agreement.Agreements;
import com.faraya.legioss.core.entity.payroll.agreement.HoursAgreement;
import com.faraya.legioss.core.entity.payroll.agreement.PayType;
import com.faraya.legioss.core.entity.payroll.agreement.PieceworkAgreement;
import com.faraya.legioss.core.entity.payroll.log.DailyAttendance;
import com.faraya.legioss.core.entity.payroll.log.PieceworkLog;
import com.faraya.legioss.core.entity.security.IUser;
import com.faraya.legioss.core.entity.security.User;
import com.faraya.legioss.core.model.payroll.PayrollContext;
import org.apache.commons.lang3.RandomStringUtils;
import static org.mockito.Mockito.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * Created by fabrizzio on 12/5/15.
 */

public class PayrollMockUtils {

    static PieceworkLog mockPieceworkLog(Piecework piecework, Employee employee, int count){
        PieceworkLog log = mock(PieceworkLog.class);
        when(log.getId()).thenReturn(System.currentTimeMillis());
        when(log.getEmployee()).thenReturn(employee);
        Long employeeId = employee.getId();
        when(log.getEmployeeId()).thenReturn(employeeId);
        when(log.getPiecework()).thenReturn(piecework);
        Long pieceworkId = piecework.getId();
        when(log.getPieceworkId()).thenReturn(pieceworkId);
        when(log.getUnitCount()).thenReturn(count);// ????? Integer???
        when(log.getDate()).thenReturn(LocalDate.now());
        return log;
    }

    static Set<PieceworkAgreement> mockPieceworkAgreements(Set<PieceworkParam> pieceworkParams){
        final Set<PieceworkAgreement> pieceworkAgreements = new HashSet<>();
        for(PieceworkParam pieceworkParam:pieceworkParams){
            double amount = pieceworkParam.getRate();
            PieceworkAgreement agreement = mockPieceworkAgreement(pieceworkParam.getPiecework(), BasicMoney.ofUSD(amount));
            pieceworkAgreements.add(agreement);
        }
        return pieceworkAgreements;
    }

    public static PieceworkAgreement mockPieceworkAgreement(Piecework piecework, BasicMoney rate){
        PieceworkAgreement agreement = mock(PieceworkAgreement.class);
        final Long id = piecework.getId();
        when(agreement.getPieceworkId()).thenReturn(id);
        when(agreement.getPiecework()).thenReturn(piecework);
        when(agreement.isActive()).thenReturn(true);
        when(agreement.getRate()).thenReturn(rate);
        when(agreement.getValidity()).thenReturn(new Period(LocalDate.now().minus(6, ChronoUnit.MONTHS)));
        return agreement;
    }

    static Set<HoursAgreement> mockHoursAgreements(){
        Set<HoursAgreement> hoursAgreements = new HashSet<>();

        HoursAgreement regularShift = mock(HoursAgreement.class);
        when(regularShift.getId()).thenReturn(1L);
        when(regularShift.isActive()).thenReturn(true);
        when(regularShift.getPayType()).thenReturn(PayType.WEEKDAY);
        when(regularShift.getValidity()).thenReturn(new Period(LocalDate.now().minus(6, ChronoUnit.MONTHS)));
        when(regularShift.getRate()).thenReturn(new BasicMoney(new BigDecimal("30"), BasicCurrency.usd()));
        when(regularShift.getSchedule()).thenReturn(new DailyWorkSchedule(LocalTime.of(8, 0), LocalTime.of(17, 0)));
        hoursAgreements.add(regularShift);
        return hoursAgreements;
    };

    static Agreements mockAgreement(Employee employee){
        Set<HoursAgreement> hoursAgreements = mockHoursAgreements();
        Agreements agreements = mock(Agreements.class);
        when(agreements.getId()).thenReturn(1L);
        when(agreements.getBaseSalary()).thenReturn(new BasicMoney(BigDecimal.TEN, BasicCurrency.usd()));
        when(agreements.getEmployeeId()).thenReturn(1L);
        when(agreements.getEmployee()).thenReturn(employee);
        when(agreements.getHoursAgreements()).thenReturn(hoursAgreements);
        return agreements;
    }

    static Set<HoursAgreement> mockHoursAgreements(Set<HoursAgreement> hoursAgreements, HoursAgreementParam param){
        HoursAgreement regularShift = mock(HoursAgreement.class);
        when(regularShift.getId()).thenReturn(System.currentTimeMillis());
        when(regularShift.isActive()).thenReturn(true);
        when(regularShift.getPayType()).thenReturn(PayType.WEEKDAY);
        when(regularShift.getValidity()).thenReturn(new Period(LocalDate.now().minus(6, ChronoUnit.MONTHS)));
        when(regularShift.getRate()).thenReturn(new BasicMoney(param.getRate(), BasicCurrency.of(param.getCurrency())));
        when(regularShift.getSchedule()).thenReturn(new DailyWorkSchedule(param.getTimeIn(),param.getTimeOut()));
        hoursAgreements.add(regularShift);
        return hoursAgreements;
    }

    static Agreements mockAgreement(Employee employee, List<HoursAgreementParam> params){
        Set<HoursAgreement> hoursAgreements = new HashSet<>();
        for(HoursAgreementParam p:params){
            hoursAgreements = mockHoursAgreements (hoursAgreements,p);
        }
        Agreements agreements = mock(Agreements.class);
        when(agreements.getId()).thenReturn(1L);
        when(agreements.getBaseSalary()).thenReturn(new BasicMoney(BigDecimal.TEN, BasicCurrency.usd()));
        when(agreements.getEmployeeId()).thenReturn(1L);
        when(agreements.getEmployee()).thenReturn(employee);
        when(agreements.getHoursAgreements()).thenReturn(hoursAgreements);
        return agreements;
    }


    static User mockUser(){
        User user = mock(User.class);
        when(user.getId()).thenReturn(1L);
        when(user.getFirstName()).thenReturn("Fabrizzio");
        when(user.getLastName()).thenReturn("Araya");
        when(user.getPrimaryEmail()).thenReturn("fabrizzioaraya@gmail.com");
        when(user.getStatus()).thenReturn(IUser.Status.ACTIVE);

        return user;
    };

    static Employee mockEmployee(User user){
        Employee employee = mock(Employee.class);

        when(employee.getId()).thenReturn(1L);
        when(employee.getHireDate()).thenReturn(LocalDate.now().minus(6,ChronoUnit.MONTHS));
        when(employee.getUser()).thenReturn(user);
        Agreements agreements = mockAgreement(employee);
        when(employee.getAgreements()).thenReturn(agreements);

        return employee;
    };

        static Business mockBusiness(){
        Business b = mock(Business.class);
        when(b.getName()).thenReturn("Legioss soft Inc");
        when(b.getId()).thenReturn(1L);
        return b;
    };

    static DailyWorkedHours mockDailyWorkedHoursRegularShift(){
        DailyWorkedHours dh = mock(DailyWorkedHours.class);
        when(dh.getHours()).thenReturn(8);
        when(dh.getTimeIn()).thenReturn(LocalTime.of(8,0));
        when(dh.getTimeOut()).thenReturn(LocalTime.of(17,0));
        return dh;
    }

    static DailyWorkedHours mockDailyWorkedHours(LocalTime in, LocalTime out){
        DailyWorkedHours dh = mock(DailyWorkedHours.class);
        when(dh.getHours()).thenReturn((int)Duration.between(in,out).toHours());
        when(dh.getTimeIn()).thenReturn(in);
        when(dh.getTimeOut()).thenReturn(out);
        return dh;
    }

    static LocalDate weekDay(){
        LocalDate now = LocalDate.now();
        TemporalField fieldISO = WeekFields.of(Locale.FRANCE).dayOfWeek();
        //System.out.println(now.with(fieldISO, 1)); // 2015-02-09 (Monday)
        return now.with(fieldISO, 1);

        //TemporalField fieldUS = WeekFields.of(Locale.US).dayOfWeek();
        //System.out.println(now.with(fieldUS, 1)); // 2015-02-08 (Sunday)
    }

    static DailyAttendance mockAttendanceLog(Employee employee){
        LocalDate weekday = weekDay();
        Long id = employee.getId();
        DailyWorkedHours dailyWorkedHours = mockDailyWorkedHoursRegularShift();
        DailyAttendance al = mock(DailyAttendance.class);
        when(al.getDate()).thenReturn(weekday);
        when(al.getWorkedHours()).thenReturn(dailyWorkedHours);
        when(al.getEmployeeId()).thenReturn(id);
        when(al.getEmployee()).thenReturn(employee);
        when(al.getId()).thenReturn(System.currentTimeMillis());

        return al;
    }

    static DailyAttendance mockAttendanceLog(Employee employee, LocalTime in, LocalTime out){
        LocalDate weekday = weekDay();
        Long id = employee.getId();
        DailyWorkedHours dailyWorkedHours = mockDailyWorkedHours(in,out);
        DailyAttendance al = mock(DailyAttendance.class);
        when(al.getDate()).thenReturn(weekday);
        when(al.getWorkedHours()).thenReturn(dailyWorkedHours);
        when(al.getEmployeeId()).thenReturn(id);
        when(al.getEmployee()).thenReturn(employee);
        when(al.getId()).thenReturn(System.currentTimeMillis());

        return al;
    }

    static CalendarDate mockCalendarDate(Type type){
        CalendarDate holiday = mock(CalendarDate.class);
        when(holiday.getDate()).thenReturn(LocalDate.now().plus(1, ChronoUnit.DAYS));
        when(holiday.getType()).thenReturn(type);
        return holiday;
    }

    static Calendar mockCalendar(){
        CalendarDate holiday = mockCalendarDate(Type.MANDATORY_HOLIDAY);
        Set<CalendarDate> dates = new HashSet<>();
        dates.add(holiday);

        Calendar c = mock(Calendar.class);
        when(c.getCalendarDates()).thenReturn(dates);
        when(c.getBusinessId()).thenReturn(1L);
        when(c.getName()).thenReturn("Costa Rica - holidays");
        return c;
    }

   static Period mockPeriod(LocalDate start, LocalDate end){
        Period period = mock(Period.class);
        when(period.getStart()).thenReturn(start);
        when(period.getEnd()).thenReturn(end);
        when(period.isWithinPeriod(any(LocalDate.class))).thenReturn(true);
        return period;
    }

    static Map<LocalDate,CalendarDate> mockCalendarDates(){
        Calendar calendar = mockCalendar();
        Set<CalendarDate> calendarDates = calendar.getCalendarDates();
        return calendarDates.stream().collect(Collectors.toMap(CalendarDate::getDate, calendarDate -> calendarDate));
    }


    static PayrollContext mockPayrollContext(){
        Business business = mockBusiness();
        Map<LocalDate, CalendarDate>  calendarDateMap = mockCalendarDates();
        PayrollContext context = mock(PayrollContext.class);
        when(context.getBusiness()).thenReturn(business);
        when(context.getCalendarDates()).thenReturn(calendarDateMap);
        when(context.getRegularShiftHours()).thenReturn(8);
        return context;
    }

    public interface PieceworkParam{

        Piecework getPiecework();

        double getRate();

    }

    public  interface HoursAgreementParam{

        LocalTime getTimeIn();

        LocalTime getTimeOut();

        BigDecimal getRate();

        Currency getCurrency();
    }

}
