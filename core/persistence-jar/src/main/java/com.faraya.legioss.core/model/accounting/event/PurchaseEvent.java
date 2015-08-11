package com.faraya.legioss.core.model.accounting.event;

/**
 *
 *  Created by fabrizzio on 5/18/15.
 */
public class PurchaseEvent extends AbstractAccountingEvent {

    private PurchaseType purchaseType;

    public PurchaseEvent(long userId, int businessId) {
        super(userId, businessId);
    }
}
