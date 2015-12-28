package com.faraya.legioss.core.model.payroll.attendance;

import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;
import java.util.Currency;

/**
 *
 * Created by fabrizzio on 12/23/15.
 */
@Immutable
public class HoursDetail {

    private BigDecimal hours;

    private BigDecimal rate;

    private Currency currency;

    private String projectRef;

    public HoursDetail(BigDecimal hours, BigDecimal rate, Currency currency, String projectRef) {
        this.hours = hours;
        this.rate = rate;
        this.currency = currency;
        this.projectRef = projectRef;
    }

    public BigDecimal getHours() {
        return hours;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getProjectRef() {
        return projectRef;
    }
}
