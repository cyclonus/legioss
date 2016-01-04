package com.faraya.legioss.service.payroll;

import com.faraya.legioss.core.entity.common.Period;
import com.faraya.legioss.core.entity.payroll.Employee;
import com.faraya.legioss.core.model.payroll.PayrollContext;
import com.faraya.legioss.core.model.payroll.attendance.DailyAttendanceSalary;

import java.util.List;

/**
 *
 * Created by fabrizzio on 12/27/15.
 */
public interface IAttendanceSalaryCalculatorService {

    List<DailyAttendanceSalary> computeDailySalary(Employee employee, Period period, PayrollContext context);

}
