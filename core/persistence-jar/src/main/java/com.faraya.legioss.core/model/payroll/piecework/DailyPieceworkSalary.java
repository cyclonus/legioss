package com.faraya.legioss.core.model.payroll.piecework;

import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;
import java.util.*;

/**
 *
 * Created by fabrizzio on 1/7/16.
 */

@Immutable
public class DailyPieceworkSalary {

    private Map<Currency,BigDecimal> totals;

    private Set<PieceworkDetail> pieceworkDetails;


    public DailyPieceworkSalary(Map<Currency, BigDecimal> totals, Set<PieceworkDetail> pieceworkDetails) {
        this.totals = totals;
        this.pieceworkDetails = pieceworkDetails;
    }

    public Map<Currency, BigDecimal> getTotals() {
        return new HashMap<>(totals);
    }

    public Set<PieceworkDetail> getPieceworkDetails() {
        return new HashSet<>(pieceworkDetails);
    }
}
