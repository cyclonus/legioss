package com.faraya.legioss.core.model.accounting.rule;

/**
 * http://www.accountingtools.com/chart-of-accounts-overview
 * Created by fabrizzio on 5/21/15.
 */

public abstract class AbstractPostingRule implements IPostingRule{

    private Long businessID;

    public Long getBusinessID() {
        return businessID;
    }

    public void setBusinessID(Long businessID) {
        this.businessID = businessID;
    }


}
