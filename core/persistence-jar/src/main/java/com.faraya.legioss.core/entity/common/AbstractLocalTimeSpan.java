package com.faraya.legioss.core.entity.common;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalTime;

/**
 *
 * Created by fabrizzio on 11/18/15.
 */

public abstract class AbstractLocalTimeSpan {

    protected AbstractLocalTimeSpan(LocalTime timeIn, LocalTime timeOut) {
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }

    @Column(name = "time_in", nullable = false)
    private LocalTime timeIn;

    @Column(name = "time_out", nullable = false)
    private LocalTime timeOut;

    public LocalTime getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(LocalTime timeIn) {
        this.timeIn = timeIn;
    }

    public LocalTime getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(LocalTime timeOut) {
        this.timeOut = timeOut;
    }

    public int getHours() {
        return  (timeOut.minusHours(timeIn.getHour()).getHour());
    }

}
