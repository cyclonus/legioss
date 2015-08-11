package com.faraya.legioss.core.model.accounting.event;

/**
 *
 * Created by fabrizzio on 5/18/15.
 */
public class PaymentReceivedEvent extends AbstractAccountingEvent {
    public PaymentReceivedEvent(long userId, int businessId) {
        super(userId, businessId);
    }
}
