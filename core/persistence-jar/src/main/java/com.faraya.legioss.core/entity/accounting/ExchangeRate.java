package com.faraya.legioss.core.entity.accounting;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by fabrizzio on 4/24/15.
 */
public class ExchangeRate {

    private Long id;

    private Date date;

    private Currency source;

    private Currency target;

    private BigDecimal amount;


}
