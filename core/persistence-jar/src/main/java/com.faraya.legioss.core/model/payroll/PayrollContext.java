package com.faraya.legioss.core.model.payroll;

import com.faraya.legioss.core.entity.calendar.CalendarDate;
import com.faraya.legioss.core.entity.common.Business;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by fabrizzio on 12/25/15.
 */
@Immutable
public class PayrollContext {

    private int hoursStandardShift;

    private Map<LocalDate,CalendarDate> calendarDates;

    private Business business;

    PayrollContext(Business business, int hoursStandardShift, Map<LocalDate, CalendarDate> calendarDates) {
        this.business = business;
        this.hoursStandardShift = hoursStandardShift;
        this.calendarDates = calendarDates;
    }

    public int getHoursStandardShift() {
        return hoursStandardShift;
    }

    public Business getBusiness() {
        return business;
    }

    public Map<LocalDate, CalendarDate> getCalendarDates() {
        return new HashMap<>(calendarDates);
    }

    //Predicate<HoursAdjustment> hoursAdjustment;

    //private BigDecimal holidayRate;

    //boolean ignoreAbsenceDays;

}
