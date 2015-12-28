package com.faraya.legioss.core.model.payroll.attendance;

import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/**
 *
 * Created by fabrizzio on 12/3/15.
 */

@Immutable
public class DailyAttendanceSalary {

    private LocalDate date;

    private boolean holiday;

    private Set<HoursDetail> hoursDetails;

    private Map<Currency,BigDecimal> attendanceTotals;

    public DailyAttendanceSalary(LocalDate date, boolean holiday,Map<Currency, BigDecimal> attendanceTotals, Set<HoursDetail> hoursDetails) {
        this.date = date;
        this.holiday = holiday;
        this.attendanceTotals = attendanceTotals;
        this.hoursDetails = hoursDetails;
    }

    public Map<Currency, BigDecimal> getAttendanceTotals() {
        return new HashMap<>(attendanceTotals);
    }

    public Set<HoursDetail> getHoursDetails() {
        return new HashSet<>(hoursDetails);
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isHoliday() {
        return holiday;
    }
}
