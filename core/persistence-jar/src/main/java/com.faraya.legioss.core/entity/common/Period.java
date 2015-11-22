package com.faraya.legioss.core.entity.common;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

/**
 *
 * Created by fabrizzio on 4/27/15.
 */

@Embeddable
public class Period {

    @Column(name = "period_start", nullable = false)
    LocalDate start;

    @Column(name = "period_end", nullable = true)
    LocalDate end;

    public Period() {
        this.start = LocalDate.now();
    }

    public Period(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    public Period(LocalDate start) {
        this(start,null);
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public boolean isWithinPeriod(LocalDate date){
        if(isOpen() && (start.isAfter(date) || start.equals(date))){
            return true;
        }
        return  (start.isAfter(date) && end.isBefore(date) || start.equals(date) || end.equals(date));
    }

    public boolean isOpen(){
        return (end == null);
    }
}
