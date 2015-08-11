package com.faraya.legioss.core.model.accounting.event;

import java.io.Serializable;

/**
 *
 * Created by fabrizzio on 5/18/15.
 */
public abstract class AbstractAccountingEvent implements Serializable {

    private boolean processed = false;

    private long userId;

    private int businessId;

    private String notes;

    public AbstractAccountingEvent(long userId, int businessId) {
        this.userId = userId;
        this.businessId = businessId;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
