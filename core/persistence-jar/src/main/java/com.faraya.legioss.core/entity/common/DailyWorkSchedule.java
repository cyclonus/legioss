package com.faraya.legioss.core.entity.common;

import javax.persistence.Embeddable;
import java.time.LocalTime;

/**
 *
 * Created by fabrizzio on 11/18/15.
 */

@Embeddable
public class DailyWorkSchedule extends TimeInTimeOut {

    public DailyWorkSchedule(LocalTime timeIn, LocalTime timeOut) {
        super(timeIn, timeOut);
    }

    public static DailyWorkSchedule regularEightHoursWorkday(){
        return new DailyWorkSchedule(LocalTime.of(8,0),LocalTime.of(17,0));
    }

    public static DailyWorkSchedule fromToHours(int from, int to){
        return new DailyWorkSchedule(LocalTime.of(from,0),LocalTime.of(to,0));
    }

    public boolean isWithinBoundaries(LocalTime time){
        int cmp1 = getTimeIn().compareTo(time);
        int cmp2 = getTimeOut().compareTo(time);
        return (cmp1 >= 0 && cmp2 <= 0);
    }

}
