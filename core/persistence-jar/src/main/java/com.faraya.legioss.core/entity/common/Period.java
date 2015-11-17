package com.faraya.legioss.core.entity.common;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 *
 * Created by fabrizzio on 4/27/15.
 */

@Embeddable
public class Period {

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "period_start", nullable = false)
    java.util.Date start;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "period_end", nullable = true)
    java.util.Date end;

    public Period() {
        this.start = new Date();
    }

    public Period(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    public Period(Date start) {
        this(start,null);
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public boolean isWithinPeriod(Date date){
        if(isOpen() && (start.after(date) || start.equals(date))){
            return true;
        }
        return  (start.after(date) && end.before(date) || start.equals(date) || end.equals(date));
    }

    public boolean isOpen(){
        return (end == null);
    }
}
