package com.faraya.legioss.core.model.accounting;

import com.faraya.legioss.core.entity.common.Period;

import java.math.BigDecimal;
import java.util.Set;

/**
 *
 * Created by fabrizzio on 4/27/15.
 */

public interface IAccountSnapshot {

    String getAccountID();

    String getDescription();

    AccountType getAccountType();

    Period getPeriod();

    BigDecimal getBalance();

    Set <IAccountingEntry> getDebits();

    Set <IAccountingEntry> getCredits();

}
