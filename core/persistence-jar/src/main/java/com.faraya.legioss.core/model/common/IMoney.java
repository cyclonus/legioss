package com.faraya.legioss.core.model.common;

import java.math.BigDecimal;
import java.util.Currency;

/**
 *
 * Created by fabrizzio on 11/20/15.
 */
public interface IMoney {

    BigDecimal getAmount();

    Currency getCurrency();

    IMoney plus(IMoney money);

    IMoney minus(IMoney money);

    IMoney multiplyBy(IMoney money);

}
