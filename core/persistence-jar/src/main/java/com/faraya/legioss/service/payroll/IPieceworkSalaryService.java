package com.faraya.legioss.service.payroll;

import com.faraya.legioss.core.entity.common.Period;
import com.faraya.legioss.core.entity.payroll.Employee;
import com.faraya.legioss.core.model.payroll.PayrollContext;
import com.faraya.legioss.core.model.payroll.piecework.DailyPieceworkSalary;

import java.util.List;

/**
 *
 * Created by fabrizzio on 1/6/16.
 */
public interface IPieceworkSalaryService {

   DailyPieceworkSalary computeDailySalary(Long employeeId, Period period, PayrollContext context);

}
