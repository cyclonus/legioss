package com.faraya.legioss.core.model.payroll.piecework;

import java.math.BigDecimal;
import java.util.Currency;

/**
 *
 * Created by fabrizzio on 1/19/16.
 */
public class PieceworkDetail {

    private String code;

    private BigDecimal count;

    private Currency currency;

    private  BigDecimal rate;

    private BigDecimal amount;

    public PieceworkDetail(String code, BigDecimal count, Currency currency, BigDecimal rate, BigDecimal amount) {
        this.code = code;
        this.count = count;
        this.currency = currency;
        this.rate = rate;
        this.amount = amount;
    }

    public String getCode() {
        return code;
    }

    public BigDecimal getCount() {
        return count;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
