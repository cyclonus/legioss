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

    @Temporal(value = TemporalType.DATE)
    @Column(name = "start_date", nullable = false)
    java.util.Date startDate;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "end_date", nullable = true)
    java.util.Date endDate;

    public Period() {
        this.startDate = new Date();
    }

    public Period(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Period(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isWithinPeriod(Date date){
        if(isOpen() && (startDate.after(date) || startDate.equals(date))){
            return true;
        }
        return  (startDate.after(date) && endDate.before(date) || startDate.equals(date) || endDate.equals(date));
    }

    public boolean isOpen(){
        return (endDate == null);
    }
}
