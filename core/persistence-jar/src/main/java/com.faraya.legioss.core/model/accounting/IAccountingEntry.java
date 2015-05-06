package com.faraya.legioss.core.model.accounting;

import java.math.BigDecimal;
import java.util.Set;

/**
 *
 * Created by fabrizzio on 4/27/15.
 */

public interface IAccountingEntry {

    BigDecimal getAmount();

    //IAccountingEvent getAccountingEvent();

    Set<IAccountSnapshot> getAccounts();
}
