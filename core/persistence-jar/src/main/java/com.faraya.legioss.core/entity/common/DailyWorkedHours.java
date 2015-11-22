package com.faraya.legioss.core.entity.common;

import javax.persistence.Embeddable;
import java.time.LocalTime;

@Embeddable
public class DailyWorkedHours extends TimeInTimeOut {

    public DailyWorkedHours(LocalTime timeIn, LocalTime timeOut) {
        super(timeIn,timeOut);
    }

}
