package com.faraya.legioss.service.payroll;

import com.faraya.legioss.core.dao.calendar.ICalendarDAO;
import com.faraya.legioss.core.dao.payroll.log.IAttendanceLogDAO;
import com.faraya.legioss.core.entity.calendar.Calendar;
import com.faraya.legioss.core.entity.calendar.CalendarDate;
import com.faraya.legioss.core.entity.calendar.Type;
import com.faraya.legioss.core.entity.common.*;
import com.faraya.legioss.core.entity.payroll.Employee;
import com.faraya.legioss.core.entity.payroll.agreement.Agreements;
import com.faraya.legioss.core.entity.payroll.log.DailyAttendance;
import com.faraya.legioss.core.entity.security.User;
import com.faraya.legioss.core.model.payroll.DailySalary;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;

import static org.mockito.Mockito.*;
import static com.faraya.legioss.service.payroll.PayrollMockUtils.*;
import static org.junit.Assert.*;

/**
 * Created by fabrizzio on 12/4/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class PayrollServiceTest {

    @Mock
    ICalendarDAO calendarDAO;

    @Mock
    IAttendanceLogDAO attendanceLogDAO;

    @InjectMocks
    PayrollServiceImpl payrollService;

    @Test
    public void testDailyPayrollOneShiftAgreement() {

        User user = mockUser();
        Employee employee = mockEmployee(user);
        DailyAttendance dailyAttendance = mockAttendanceLog(employee);
        List<DailyAttendance> dailyAttendances = new ArrayList<>();
        dailyAttendances.add(dailyAttendance);
        Calendar calendar = mockCalendar();
        when(calendarDAO.findByBusinessId(anyLong())).thenReturn(calendar);
        when(attendanceLogDAO.findAttendance(anyLong(), any(Period.class))).thenReturn(dailyAttendances);
        CalendarDate calendarDate = mockCalendarDate(Type.MANDATORY_HOLIDAY);

        DailySalary dailySalary = payrollService.computeDailySalary(dailyAttendance, calendarDate);
        assertNotNull(dailySalary);
        assertEquals(dailySalary.getAttendanceTotals().size(), 1);
        BigDecimal bd = dailySalary.getAttendanceTotals().get(Currency.getInstance("USD"));
        assertEquals(bd.compareTo(BigDecimal.valueOf(270)), 0);
    }

    @Test
    public void testDailyPayrollTwoShiftsAgreement() {
        User user = mockUser();
        Employee employee = mockEmployee(user);
        List<HoursAgreementParam> params = Arrays.asList(
                // One shift of 10 hours payed at 20 bucks (200 usd)
                new HoursAgreementParam() {
                    @Override
                    public LocalTime getTimeIn() {
                        return LocalTime.of(7, 0);
                    }

                    @Override
                    public LocalTime getTimeOut() {
                        return LocalTime.of(17, 0);
                    }

                    @Override
                    public BigDecimal getRate() {
                        return BigDecimal.valueOf(20);
                    }

                    @Override
                    public Currency getCurrency() {
                        return Currency.getInstance("USD");
                    }
                },
                // One shift of 6 hours payed at 25 bucks (125 usd)
                new HoursAgreementParam() {

                    @Override
                    public LocalTime getTimeIn() {
                        return LocalTime.of(17, 0);
                    }

                    @Override
                    public LocalTime getTimeOut() {
                        return LocalTime.of(23, 0);
                    }

                    @Override
                    public BigDecimal getRate() {
                        return BigDecimal.valueOf(25);
                    }

                    @Override
                    public Currency getCurrency() {
                        return Currency.getInstance("USD");
                    }
                }
        );

        Agreements agreements = mockAgreement(employee, params);
        when(employee.getAgreements()).thenReturn(agreements);
        Calendar calendar = mockCalendar();
        when(calendarDAO.findByBusinessId(anyLong())).thenReturn(calendar);

        //This is equivalent to 10hours of shift A (10x20) and 3 hours of shift B (3x25)
        DailyAttendance dailyAttendance = mockAttendanceLog(employee,LocalTime.of(7,0),LocalTime.of(20,0));
        List<DailyAttendance> dailyAttendances = new ArrayList<>();
        dailyAttendances.add(dailyAttendance);
        when(attendanceLogDAO.findAttendance(anyLong(), any(Period.class))).thenReturn(dailyAttendances);
        CalendarDate calendarDate = mockCalendarDate(Type.MANDATORY_HOLIDAY);

        DailySalary dailySalary = payrollService.computeDailySalary(dailyAttendance, calendarDate);
        assertNotNull(dailySalary);
        assertEquals(dailySalary.getAttendanceTotals().size(), 1);
        BigDecimal bd = dailySalary.getAttendanceTotals().get(Currency.getInstance("USD"));
        assertEquals(bd.compareTo(BigDecimal.valueOf(275)), 0);
    }

}
