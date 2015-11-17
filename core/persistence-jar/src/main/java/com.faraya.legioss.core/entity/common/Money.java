package com.faraya.legioss.core.entity.common;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 *
 * Created by fabrizzio on 10/11/15.
 */

@Embeddable
public class Money implements Comparable<Money> {

    @Column(name = "amount", nullable = false, columnDefinition="Decimal(10,2) default '0'")
    private BigDecimal amount;

    @JoinColumn(name = "currency_id", nullable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private Currency currency;

    public Money() {
    }

    public Money(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Money money = (Money) o;

        if (amount != null ? !amount.equals(money.amount) : money.amount != null) return false;
        return !(currency != null ? !currency.equals(money.currency) : money.currency != null);

    }

    @Override
    public int hashCode() {
        int result = amount != null ? amount.hashCode() : 0;
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Money o) {
        return (amount.compareTo(o.amount) == 0 && currency.compareTo(o.currency) == 0) ? 0 : 1 ;
    }
}
