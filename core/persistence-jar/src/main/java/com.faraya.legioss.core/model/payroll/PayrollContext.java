package com.faraya.legioss.core.model.payroll;

import com.faraya.legioss.core.entity.calendar.CalendarDate;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;

/**
 *
 * Created by fabrizzio on 12/25/15.
 */
@Immutable
public class PayrollContext {

    private int hoursPerShift;

    private CalendarDate calendarDate;

    //Predicate<HoursAdjustment> hoursAdjustment;

    //private BigDecimal holidayRate;

    //boolean ignoreAbsenceDays;

}
