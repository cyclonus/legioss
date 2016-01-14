package com.faraya.legioss.core.model.payroll;

import com.faraya.legioss.core.model.payroll.attendance.DailyAttendanceSalary;
import com.faraya.legioss.core.model.payroll.piecework.DailyPieceworkSalary;
import org.hibernate.annotations.Immutable;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by fabrizzio on 11/19/15.
 */

@Immutable
public class EmployeePayment {

    private List <DailyAttendanceSalary> attendanceSalaries;

    private List<DailyPieceworkSalary> pieceworkSalaries;

    public EmployeePayment(List<DailyAttendanceSalary> attendanceSalaries, List<DailyPieceworkSalary> pieceworkSalaries) {
        this.attendanceSalaries = attendanceSalaries;
        this.pieceworkSalaries = pieceworkSalaries;
    }

    public List<DailyAttendanceSalary> getAttendanceSalaries() {
        return new ArrayList<>(attendanceSalaries);
    }

    public List<DailyPieceworkSalary> getPieceworkSalaries() {
        return new ArrayList<>(pieceworkSalaries);
    }
}
