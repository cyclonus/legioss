package com.faraya.legioss.service.payroll;

import com.faraya.legioss.core.entity.calendar.CalendarDate;
import com.faraya.legioss.core.entity.common.Business;
import com.faraya.legioss.core.entity.common.Period;
import com.faraya.legioss.core.entity.payroll.Employee;
import com.faraya.legioss.core.entity.payroll.log.DailyAttendance;
import com.faraya.legioss.core.model.payroll.PayrollContext;
import com.faraya.legioss.core.model.payroll.attendance.DailyAttendanceSalary;
import com.faraya.legioss.core.model.payroll.EmployeePayment;

/**
 *
 * Created by fabrizzio on 10/31/15.
 */

public interface IPayrollService {

    EmployeePayment computePayroll(Long employeeId, Period period, PayrollContext context);

}
