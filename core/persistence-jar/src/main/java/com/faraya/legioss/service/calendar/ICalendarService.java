package com.faraya.legioss.service.calendar;

import com.faraya.legioss.core.entity.calendar.Calendar;
import com.faraya.legioss.core.entity.common.Business;
import com.faraya.legioss.core.model.calendar.CalendarModel;

/**
 *
 * Created by fabrizzio on 11/2/15.
 */

public interface ICalendarService {

    Calendar find(String name, Business business);

    void importCalendar(CalendarModel model, Business business);

}
