package com.faraya.legioss.core.model.accounting;

/**
 *
 *  Created by fabrizzio on 4/26/15.
 */
public enum BalanceType {
    /*
     * ASSETS = LIABILITIES + CAPITAL - DRAWINGS + REVENUE - EXPENSES
     *
     * ASSETS:      DEB+ CRED-
     * LIABILITIES: DEB- CRED+
     * CAPITAL:     DEB- CRED+
     * DRAWINGS:    DEB+ CRED-
     * REVENUE:     DEB+ CRED-
     * EXPENSES     DEB+ CRED-
     * */

    debit,
    credit
}
