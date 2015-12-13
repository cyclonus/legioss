package com.faraya.legioss.service.payroll;

import com.faraya.legioss.core.entity.calendar.CalendarDate;
import com.faraya.legioss.core.entity.common.Business;
import com.faraya.legioss.core.entity.common.Period;
import com.faraya.legioss.core.entity.payroll.Employee;
import com.faraya.legioss.core.entity.payroll.log.DailyAttendance;
import com.faraya.legioss.core.model.payroll.DailySalary;
import com.faraya.legioss.core.model.payroll.EmployeePayment;

/**
 *
 * Created by fabrizzio on 10/31/15.
 */

public interface IPayrollService {


    DailySalary computeDailySalary(DailyAttendance dailyAttendance, CalendarDate calendarDate);

    EmployeePayment computePayroll(Employee employee, Period period, Business business);

}
