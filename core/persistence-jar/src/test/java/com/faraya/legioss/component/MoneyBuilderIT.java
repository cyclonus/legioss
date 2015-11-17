package com.faraya.legioss.component;

import com.faraya.legioss.BaseIntegrationTest;
import com.faraya.legioss.core.entity.common.Currency;
import com.faraya.legioss.core.entity.common.Money;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

/**
 *
 * Created by fabrizzio on 11/15/15.
 */

public class MoneyBuilderIT extends BaseIntegrationTest {

    @Autowired
    MoneyBuilder builder;

    @Test
    public void testBigDecimalAmountInstance(){
        double in = 123.00;
        BigDecimal out = builder.of(in);
        assertEquals(out.doubleValue(), in, 0);
    }

    @Test
    public void testUSD(){
        Currency out = builder.usd();
        assertNotNull(out);
        Money money = builder.ofUsd(2);
        assertNotNull(money);
        assertNotNull(money.getCurrency());
        double doubleValue = money.getAmount().doubleValue();
        assertEquals(doubleValue, 2, 0);
        assertNotNull(money.getCurrency());
        assertEquals(money.getCurrency().getShortName(), MoneyBuilder.CurrencyNames.USD.name());
    }

    @Test
    public void testCRC(){
        Currency out = builder.crc();
        assertNotNull(out);
        Money money = builder.ofCrc(2);
        assertNotNull(money);
        double doubleValue = money.getAmount().doubleValue();
        assertEquals(doubleValue, 2, 0);
        assertNotNull(money.getCurrency());
        assertEquals(money.getCurrency().getShortName(), MoneyBuilder.CurrencyNames.CRC.name());
    }

}
