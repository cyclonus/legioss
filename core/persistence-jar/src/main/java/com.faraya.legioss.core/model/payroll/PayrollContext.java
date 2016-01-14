package com.faraya.legioss.core.model.payroll;

import com.faraya.legioss.core.entity.calendar.CalendarDate;
import com.faraya.legioss.core.entity.common.Business;
import org.hibernate.annotations.Immutable;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by fabrizzio on 12/25/15.
 */
@Immutable
public class PayrollContext {

    private int regularShiftHours = 8;//horas

    private Map<LocalDate,CalendarDate> calendarDates;

    private Business business;

    PayrollContext(Business business, int regularShiftHours, Map<LocalDate, CalendarDate> calendarDates) {
        this.business = business;
        this.regularShiftHours = regularShiftHours;
        this.calendarDates = calendarDates;
    }

    public int getRegularShiftHours() {
        return regularShiftHours;
    }

    public Business getBusiness() {
        return business;
    }

    public Map<LocalDate, CalendarDate> getCalendarDates() {
        return new HashMap<>(calendarDates);
    }

    //Predicate<HoursAdjustment> hoursAdjustment;

    //private Predicate<Rule> holidayRate;

    //boolean absenceGraceDays; // numero maximo de dias de ausencia sin rebaja

    //private int standardDaysPerPeriod = 30; // todos los meses se va a calcular de 30 dias (asi sea un mes de 29 o de 31)

}
