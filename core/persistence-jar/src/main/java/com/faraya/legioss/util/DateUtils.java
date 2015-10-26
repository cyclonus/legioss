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

    public static Date yesterday() {
        return addDays(new Date(),-1);
    }

    public static Date tomorrow() {
        return addDays(new Date(),1);
    }

    public static Date addDays(Date date, Integer days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public static Date addHours(Date date, Integer hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, hours);
        return cal.getTime();
    }

    private DateUtils() {
        // utility
    }

}
