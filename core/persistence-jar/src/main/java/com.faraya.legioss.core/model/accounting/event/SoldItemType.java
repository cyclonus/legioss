package com.faraya.legioss.core.model.accounting.event;

/**
 *
 * Created by fabrizzio on 5/18/15.
 */
public enum SoldItemType {
    rawMaterial,
    equipment,  //VEHICLES, FLOOR_PLANT (We, need such level of detail there)
    services,
    inventory,
    finishedGood,
    accountsReceivable
}
