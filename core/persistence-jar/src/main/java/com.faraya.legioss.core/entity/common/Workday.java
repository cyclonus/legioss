package com.faraya.legioss.core.entity.common;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalTime;

@Embeddable
public class Workday {

    private transient int hours;

    @Column(name = "hour_in", nullable = false)
    private LocalTime hourIn;

    @Column(name = "hour_out", nullable = false)
    private LocalTime hourOut;

    public Workday() {
    }

    public Workday(LocalTime hourIn, LocalTime hourOut) {
        if(hourIn.isAfter(hourOut))
            throw new IllegalArgumentException("Time in must be earlier than time out.");

        this.hourIn = hourIn;
        this.hourOut = hourOut;
        this.hours = hourOut.minusHours(hourIn.getHour()).getHour() -1 ;// Minus lunch
    }

    public LocalTime getHourIn() {
        return hourIn;
    }

    public void setHourIn(LocalTime hourIn) {
        this.hourIn = hourIn;
    }

    public LocalTime getHourOut() {
        return hourOut;
    }

    public void setHourOut(LocalTime hourOut) {
        this.hourOut = hourOut;
    }

    public int getHours() {
        return hours;
    }
}
