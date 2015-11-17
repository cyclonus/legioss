package com.faraya.legioss.common;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
/**
 *
 * Created by fabrizzio on 11/11/15.
 */

public class WorkdayTest {

    @Test
    public void testFormatter() {
        String timeInput = "11:00 AM";
        String monthDayInput = "April 09";
        String monthDayTimeInput = "April 09 11:00 PM";

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");
        DateTimeFormatter monthDayFormatter = DateTimeFormatter.ofPattern("MMMM dd");
        DateTimeFormatter monthDayTimeFormatter = DateTimeFormatter.ofPattern("MMMM dd h:mm a");

        LocalTime time1 = LocalTime.parse(timeInput, timeFormatter);
        LocalDateTime fromTime = LocalDateTime.now().with(time1);
        System.out.println("fromTime: " + fromTime);

        MonthDay monthDay1 = MonthDay.parse(monthDayInput, monthDayFormatter);
        LocalDateTime fromMonthDay = LocalDateTime.now().with(monthDay1).with(LocalTime.MIDNIGHT);
        System.out.println("fromMonthDay: " + fromMonthDay);

        MonthDay monthDay2 = MonthDay.parse(monthDayTimeInput, monthDayTimeFormatter);
        LocalTime time2 = LocalTime.parse(monthDayTimeInput, monthDayTimeFormatter);
        LocalDateTime fromMonthDayTime = LocalDateTime.now().with(monthDay2).with(time2);
        System.out.println("fromMonthDayTime: " + fromMonthDayTime);
    }


}
