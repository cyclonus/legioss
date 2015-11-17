package com.faraya.legioss.component;

import com.faraya.legioss.core.dao.common.ICurrencyDAO;
import com.faraya.legioss.core.entity.common.Currency;
import com.faraya.legioss.core.entity.common.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import java.math.BigDecimal;

/**
 *
 * Created by fabrizzio on 11/15/15.
 */

@Component
public class MoneyBuilder {

    public enum CurrencyNames {

        CRC("CRC"),
        USD("USD");

        private final String val;

        CurrencyNames(final String val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return val;
        }
    }

    @Autowired
    private ICurrencyDAO currencyDAO;

    public Currency usd() {
        Currency crc = null;
        try {
            crc = currencyDAO.findByShortName(CurrencyNames.USD.name());
        } catch (NoResultException nre) {
            crc = currencyDAO.save(new Currency("Amercian USD", CurrencyNames.USD.name(), "$"));
        }
        return crc;
    }

    public Currency crc() {
        Currency crc = null;
        try {
            crc = currencyDAO.findByShortName(CurrencyNames.CRC.name());
        } catch (NoResultException nre) {
            crc = currencyDAO.save(new Currency("Costarican Colon", CurrencyNames.CRC.name(), "₡"));
        }
        return crc;
    }

    public BigDecimal of(String val) {
        return new BigDecimal(val);
    }

    /**
     * Always use the string constructor of BigDecimal
     *
     * @param val
     * @return
     */
    public BigDecimal of(double val) {
        String string = Double.toString(val);
        return new BigDecimal(string);
    }

    public Money ofUsd(double amount) {
        return new Money(of(amount), usd());
    }

    public Money zeroUsd() {
        return ofUsd(0);
    }

    public Money ofCrc(double amount) {
        return new Money(of(amount), crc());
    }

    public Money zeroCrc() {
        return ofCrc(0);
    }
}
