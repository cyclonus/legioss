package com.faraya.legioss.core.model.payroll;

import org.hibernate.annotations.Immutable;

import java.util.Currency;
import java.util.List;
import java.util.Map;

/**
 *
 * Created by fabrizzio on 11/19/15.
 */

@Immutable
public class EmployeePayment {

    private List <DailySalary> dailySalaries;

    public EmployeePayment(List<DailySalary> dailySalaries) {
        this.dailySalaries = dailySalaries;
    }
}
