package com.faraya.legioss.core.entity.common;

import javax.persistence.Embeddable;
import java.time.LocalTime;

@Embeddable
public class DailyWorkedHours extends TimeInTimeOut {

    private boolean adjustLunchHours;

    public DailyWorkedHours(LocalTime timeIn, LocalTime timeOut, boolean adjustLunchHours) {
        super(timeIn,timeOut);
        this.adjustLunchHours = adjustLunchHours;
    }

    public DailyWorkedHours(LocalTime timeIn, LocalTime timeOut) {
        this(timeIn,timeOut,false);
    }

    @Override
    public int getHours() {
        int hours = super.getHours();
        //TODO: Fix me, subtract 1 hour for every number of hours corresponding to a shift
        return (adjustLunchHours ? hours -1 : hours);
    }

}
