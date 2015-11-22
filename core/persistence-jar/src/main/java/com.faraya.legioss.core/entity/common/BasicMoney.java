package com.faraya.legioss.core.entity.common;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 *
 * Created by fabrizzio on 10/11/15.
 */

@Embeddable
public class BasicMoney implements Comparable<BasicMoney> {

    @Column(name = "amount", nullable = false, columnDefinition="Decimal(10,2) default '0'")
    private BigDecimal amount;

    @Embedded
    private BasicCurrency currency;

    public BasicMoney() {
    }

    public BasicMoney(BigDecimal amount, BasicCurrency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BasicCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(BasicCurrency currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasicMoney money = (BasicMoney) o;

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
    public int compareTo(BasicMoney o) {
        return 0; //TODO: Fixme!!
               // (amount.compareTo(o.amount) == 0 && currency.toCurrency().compareTo(o.currency) == 0) ? 0 : 1 ;
    }

    private static BigDecimal of(double d){
       String ds = Double.toString(d);
       return new BigDecimal(ds);
    }

    public static BasicMoney ofUSD(double amount) {
        return new BasicMoney(of(amount), BasicCurrency.usd());
    }

    public static BasicMoney ofCRC(double amount) {
        return new BasicMoney(of(amount), BasicCurrency.crc());
    }


}
