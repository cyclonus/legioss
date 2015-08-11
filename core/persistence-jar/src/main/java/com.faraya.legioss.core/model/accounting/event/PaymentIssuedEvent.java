package com.faraya.legioss.core.model.accounting.event;

/**
 * Created by fabrizzio on 5/18/15.
 */
public class PaymentIssuedEvent extends AbstractAccountingEvent {

    public PaymentIssuedEvent(long userId, int businessId) {
        super(userId, businessId);
    }
}
