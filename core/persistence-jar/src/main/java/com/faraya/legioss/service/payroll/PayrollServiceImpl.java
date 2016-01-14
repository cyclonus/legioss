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
import com.faraya.legioss.core.model.payroll.PayrollContext;
import com.faraya.legioss.core.model.payroll.attendance.DailyAttendanceSalary;
import com.faraya.legioss.core.model.payroll.EmployeePayment;
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
 * Created by fabrizzio on 10/31/15.
 */

@Service
public class PayrollServiceImpl implements IPayrollService{

    @Autowired
    IAttendanceSalaryCalculatorService attendanceSalaryCalculatorService;

    @Autowired
    IPieceworkSalaryService pieceworkSalaryService;

    /**
     *
     * @param employee
     * @param period
     * @param context
     * @return
     */
    public EmployeePayment computePayroll(Employee employee, Period period, PayrollContext context){

       List<DailyAttendanceSalary> attendances = attendanceSalaryCalculatorService.computeDailySalary(employee, period, context);

       return new EmployeePayment(attendances,null);
    }

}
