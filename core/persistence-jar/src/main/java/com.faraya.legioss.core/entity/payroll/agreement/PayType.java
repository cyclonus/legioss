package com.faraya.legioss.core.entity.payroll.agreement;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 *
 * Created by fabrizzio on 11/23/15.
 */
public enum PayType {

    WEEKDAY,WEEKEND;

    public static PayType from(LocalDate date){
       DayOfWeek dayOfWeek = date.getDayOfWeek();
       return  (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) ? WEEKEND : WEEKDAY;
    }
}
