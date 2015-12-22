package com.faraya.legioss.core.entity.common;

import javax.persistence.Embeddable;
import java.time.Duration;
import java.time.LocalTime;

/**
 *
 * Created by fabrizzio on 11/18/15.
 */

@Embeddable
public class DailyWorkSchedule extends AbstractLocalTimeSpan {



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
        int cmp1 = time.compareTo(getTimeIn());
        int cmp2 = time.compareTo(getTimeOut());
        return (cmp1 >= 0 && cmp2 <= 0);
    }

    public boolean isAfterBoundaries(LocalTime time){
        int cmp1 = time.compareTo(getTimeIn());
        int cmp2 = time.compareTo(getTimeOut());
        return (cmp1 > 0 && cmp2 > 0);
    }

    public boolean isBeforeBoundaries(LocalTime time){
        int cmp1 = time.compareTo(getTimeIn());
        int cmp2 = time.compareTo(getTimeOut());
        return (cmp1 < 0 && cmp2 < 0);
    }

    /**
     * given a work scheduled defined by (timeIn and timeOut) we want to know how many effective working hours were spent within such period
     * The algorithm tries to determine the earlier point in time to count from and the latest point in time (both within this schedule)
     * @param in
     * @param out
     * @return
     */
    public Duration getTimeBetween(LocalTime in, LocalTime out){
        LocalTime low;
        LocalTime high;

        // if 'in' happened before we start counting from the beginning of this schedule returned by 'getTimeIn'
        if(in.isBefore(getTimeIn())){
            low = getTimeIn();
        } else {
            low = in;
        }

        //If out happens before time out, we stop counting there, out becomes the time when the worker leaves
        if(out.isBefore(getTimeOut())){
            high = out;
        } else {
            // on the contrary if out is beyond, we stop counting at the end of the period
            // for the rest of his/her hours should be handled by another schedule
            high = getTimeOut();
        }

        return Duration.between(low,high);
    }

    @Override
    public String toString() {
        return String.format("DailyWorkSchedule{in=(%s),out=(%s),hours=%d}",getTimeIn(),getTimeOut(),getHours());
    }
}
