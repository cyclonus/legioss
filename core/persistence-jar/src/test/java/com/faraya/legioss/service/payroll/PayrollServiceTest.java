package com.faraya.legioss.service.payroll;

import com.faraya.legioss.core.dao.calendar.ICalendarDAO;
import com.faraya.legioss.core.dao.payroll.log.IAttendanceLogDAO;
import com.faraya.legioss.core.entity.calendar.Calendar;
import com.faraya.legioss.core.entity.calendar.CalendarDate;
import com.faraya.legioss.core.entity.calendar.Type;
import com.faraya.legioss.core.entity.common.*;
import com.faraya.legioss.core.entity.payroll.Employee;
import com.faraya.legioss.core.entity.payroll.log.DailyAttendance;
import com.faraya.legioss.core.entity.security.User;
import com.faraya.legioss.core.model.payroll.DailySalary;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static com.faraya.legioss.service.payroll.PayrollMockUtils.*;
import static org.junit.Assert.*;

/**
 *
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

    User user;
    Employee employee;
    DailyAttendance dailyAttendance;
    Calendar calendar;
    CalendarDate calendarDate;

    @Before
    public void setUp(){

        user = mockUser();
        employee = mockEmployee(user);
        dailyAttendance = mockAttendanceLog(employee);
        List<DailyAttendance> dailyAttendances = new ArrayList<>();
        dailyAttendances.add(dailyAttendance);
        calendar = mockCalendar();
        when(calendarDAO.findByBusinessId(anyLong())).thenReturn(calendar);
        when(attendanceLogDAO.findAttendance(anyLong(), any(Period.class))).thenReturn(dailyAttendances);
        calendarDate = mockCalendarDate(Type.MANDATORY_HOLIDAY);
    }

    @Test
    public void testDailyPayroll() {
       DailySalary dailySalary = payrollService.computeDailySalary(dailyAttendance, calendarDate);
       assertNotNull(dailySalary);
    }



}
