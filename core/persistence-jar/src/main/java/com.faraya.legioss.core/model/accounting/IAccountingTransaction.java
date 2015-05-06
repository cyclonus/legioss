package com.faraya.legioss.core.model.accounting;

import java.util.Date;
import java.util.Set;

/**
 *
 * Created by fabrizzio on 4/27/15.
 */

public interface IAccountingTransaction {

    Long getId();

    Date getDate();

    //IAccountingEvent getAccountingEvent(), event must have a User and company information
    //Transaction Type

    Set<IAccountSnapshot> getAffectedAccounts();


}
