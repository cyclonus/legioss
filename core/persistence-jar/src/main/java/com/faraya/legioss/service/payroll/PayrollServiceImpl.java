package com.faraya.legioss.service.payroll;

import com.faraya.legioss.core.dao.calendar.ICalendarDAO;
import com.faraya.legioss.core.entity.calendar.Calendar;
import com.faraya.legioss.core.entity.common.Business;
import com.faraya.legioss.core.entity.common.BasicMoney;
import com.faraya.legioss.core.entity.common.Period;
import com.faraya.legioss.core.entity.payroll.Employee;
import com.faraya.legioss.core.model.payroll.EmployeePayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by fabrizzio on 10/31/15.
 */

@Service
public class PayrollServiceImpl implements IPayrollService{

    @Autowired
    ICalendarDAO calendarDAO;

    private Calendar getCalendar(Period period){
        return null;
    }

    /**
     * Returns totals per currency
     * @param employee
     * @param period
     * @param business
     * @return
     */
    EmployeePayment computePayroll(Employee employee, Period period, Business business){

       //Calendar calendar = getCalendar(period,business);
       //List<HoursAgreement> hoursAgreements = getHoursAgreement(employee,business);
       //Map<LocalDate,AttendanceLog> attendance = getAttendanceLogMap(employee,period,business);
       // for(LocalDate date:attendance.keys()){
       //    AttendanceLog entry = attendance.get(date);
       //    DailyWorkedHours wh = entry.getWorkedHours();
       //    Map<BasicCurrency,BasicMoney> dailySalary = computeDailySalary(wh,hoursAgreements);
       //    pay = addUpToPayroll(payroll,dailySalary);
       // }
       return null;
    }

}
