package com.faraya.legioss.util;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;


/**
 *
 * Created by fabrizzio on 12/6/15.
 */
public class TemporalDifference {

    private long years;
    private long months;
    private long days;
    private long hours;
    private long minutes;

    private TemporalDifference(long years, long months, long days, long hours, long minutes) {
        this.years = years;
        this.months = months;
        this.days = days;
        this.hours = hours;
        this.minutes = minutes;
    }

    public static TemporalDifference of(Temporal from, Temporal to) {
        Temporal _from = from(from);
        Temporal _to = from(to);
        return computeDifferences(_from, _to);
    }

    private static Temporal from(Temporal t) {
        if (t instanceof LocalDateTime) {
            return LocalDateTime.from(t);
        } else if (t instanceof ZonedDateTime) {
            return ZonedDateTime.from(t);
        } else if (t instanceof OffsetDateTime) {
            return OffsetDateTime.from(t);
        } else if (t instanceof LocalTime) {
            return LocalTime.from(t);
        } else if (t instanceof LocalDate) {
            return LocalDate.from(t);
        }
        throw new IllegalArgumentException(" Temporal implementation not supported " + t.getClass().getSimpleName());
    }

    private static TemporalDifference computeDifferences(Temporal fromDateTime, Temporal toDateTime) {

        long years = 0;
        long months = 0;
        long days = 0;
        long hours = 0;
        long minutes = 0;

        Temporal tempDateTime = fromDateTime;

        if (tempDateTime.isSupported(ChronoUnit.YEARS)) {
            years = tempDateTime.until(toDateTime, ChronoUnit.YEARS);
            tempDateTime = tempDateTime.plus(years, ChronoUnit.YEARS);
        }
        if (tempDateTime.isSupported(ChronoUnit.MONTHS)) {
            months = tempDateTime.until(toDateTime, ChronoUnit.MONTHS);
            tempDateTime = tempDateTime.plus(months, ChronoUnit.MONTHS);
        }
        if (tempDateTime.isSupported(ChronoUnit.DAYS)) {
            days = tempDateTime.until(toDateTime, ChronoUnit.DAYS);
            tempDateTime = tempDateTime.plus(days, ChronoUnit.DAYS);
        }
        if (tempDateTime.isSupported(ChronoUnit.HOURS)) {
            hours = tempDateTime.until(toDateTime, ChronoUnit.HOURS);
            tempDateTime = tempDateTime.plus(hours, ChronoUnit.HOURS);
        }
        if (tempDateTime.isSupported(ChronoUnit.MINUTES)) {
            minutes = tempDateTime.until(toDateTime, ChronoUnit.MINUTES);
        }

        return new TemporalDifference(years, months, days, hours, minutes);
    }

    public long getYears() {
        return years;
    }

    public long getMonths() {
        return months;
    }

    public long getDays() {
        return days;
    }

    public long getHours() {
        return hours;
    }

    public long getMinutes() {
        return minutes;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb = sb.append(days).append("d ").append(hours).append("h ").append(minutes).append("m");
        return sb.toString();
    }
}
