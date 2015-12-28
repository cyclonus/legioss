package com.faraya.legioss.core.model.payroll.attendance;

import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;
import java.util.Currency;

/**
 *
 * Created by fabrizzio on 12/23/15.
 */
@Immutable
public class DailyWorkInfo {

    private Currency currency;
    private BigDecimal payment;
    private String projectRef;
    private BigDecimal hours;

    public DailyWorkInfo(Currency currency, BigDecimal payment, String projectRef, BigDecimal hours) {
        this.currency = currency;
        this.payment = payment;
        this.projectRef = projectRef;
        this.hours = hours;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public String getProjectRef() {
        return projectRef;
    }

    public BigDecimal getHours() {
        return hours;
    }

}
