package com.faraya.legioss.core.model.payroll;

import com.faraya.legioss.core.model.payroll.attendance.DailyAttendanceSalary;
import org.hibernate.annotations.Immutable;

import java.util.List;

/**
 *
 * Created by fabrizzio on 11/19/15.
 */

@Immutable
public class EmployeePayment {

    private List <DailyAttendanceSalary> dailySalaries;

    public EmployeePayment(List<DailyAttendanceSalary> dailySalaries) {
        this.dailySalaries = dailySalaries;
    }
}
