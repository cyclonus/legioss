package com.faraya.legioss.core.entity.common;

import com.faraya.legioss.core.IIdentifiable;
import com.faraya.legioss.core.entity.AbstractEntity;

import javax.persistence.*;
import java.util.Currency;

/**
 *
 * Created by fabrizzio on 4/24/15.
 */

@Embeddable
public class BasicCurrency {

    @Column(name = "currency_code", nullable = false, length = 3)
    private String currencyCode;

    public BasicCurrency() {
    }

    public BasicCurrency(String currencyCode) {
        this.currencyCode = Currency.getInstance(currencyCode).getCurrencyCode();
    }

    public BasicCurrency(Currency currency) {
        this.currencyCode = currency.getCurrencyCode();
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Currency toCurrency(){
        return Currency.getInstance(currencyCode);
    }

    /**
     *
     * @param currency
     * @return
     */
    public static BasicCurrency of(Currency currency){
        return new BasicCurrency(currency);
    }

    /**
     * Factory method
     * @return
     */
    public static BasicCurrency usd() {
        return new BasicCurrency("USD");
    }

    /**
     * Factory method
     * @return
     */
    public static BasicCurrency crc() {
        return new BasicCurrency("CRC");
    }

}
