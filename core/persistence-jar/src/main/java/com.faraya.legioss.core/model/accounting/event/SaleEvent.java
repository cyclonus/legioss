package com.faraya.legioss.core.model.accounting.event;

/**
 *
 * Created by fabrizzio on 5/18/15.
 */
public class SaleEvent extends AbstractAccountingEvent {

    private SoldItemType soldItemType;

    public SaleEvent(long userId, int businessId) {
        super(userId, businessId);
    }

    public SoldItemType getSoldItemType() {
        return soldItemType;
    }

    public void setSoldItemType(SoldItemType soldItemType) {
        this.soldItemType = soldItemType;
    }

}
