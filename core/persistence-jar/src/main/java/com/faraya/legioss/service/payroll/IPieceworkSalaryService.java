package com.faraya.legioss.service.payroll;

import com.faraya.legioss.core.entity.common.Period;
import com.faraya.legioss.core.entity.costing.Piecework;
import com.faraya.legioss.core.entity.payroll.Employee;
import com.faraya.legioss.core.model.payroll.PayrollContext;
import com.faraya.legioss.core.model.payroll.piecework.DailyPieceworkSalary;

import java.util.List;

/**
 *
 * Created by fabrizzio on 1/6/16.
 */
public interface IPieceworkSalaryService {

   Piecework addPiecework(String code, String name, String desc);

   Piecework findPieceworkByCode(String code);

   Piecework findPieceworkByName(String name);

   DailyPieceworkSalary computeDailySalary(Long employeeId, Period period, PayrollContext context);

}
