package com.faraya.legioss.util;

import sun.util.resources.cldr.rm.CalendarData_rm_CH;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * Created by fabrizzio on 10/11/15.
 */
public class DateUtils {

    public static Date now() {
        return new Date();
    }

    public static long toUnixTime(Date date) {
        return toUnixTime(date.getTime());
    }

    public static long nowUnixTime() {
        return toUnixTime(System.currentTimeMillis());
    }

    public static long toUnixTime(long millisSinceEpoch) {
        return TimeUnit.MILLISECONDS.toSeconds(millisSinceEpoch);
    }

    public static Date computeYearsFrom(Date date, int n){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(n, Calendar.YEAR);
        return calendar.getTime();
    }

    private DateUtils() {
        // utility
    }

}
