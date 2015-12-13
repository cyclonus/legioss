package com.faraya.legioss.core.model.payroll;

import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by fabrizzio on 12/3/15.
 */

@Immutable
public class DailySalary {

    private Map<Currency,BigDecimal> attendanceTotals;

    public DailySalary(Map<Currency, BigDecimal> attendanceTotals) {
        this.attendanceTotals = attendanceTotals;
    }

    public Map<Currency, BigDecimal> getAttendanceTotals() {
        return new HashMap<>(attendanceTotals);
    }
}
