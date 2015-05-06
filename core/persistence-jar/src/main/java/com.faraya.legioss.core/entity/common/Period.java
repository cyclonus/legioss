package com.faraya.legioss.core.entity.common;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by fabrizzio on 4/27/15.
 */

@Embeddable
public class Period {

    @Temporal(value = TemporalType.DATE) java.util.Date startDate;
    @Temporal(value = TemporalType.DATE) java.util.Date endDate;

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
      return   startDate.after(date) && endDate.before(date) ||
              (startDate.equals(date) || endDate.equals(date));
    }
}
