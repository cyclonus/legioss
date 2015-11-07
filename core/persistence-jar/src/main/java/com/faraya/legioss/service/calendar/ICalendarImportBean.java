package com.faraya.legioss.service.calendar;

import com.faraya.legioss.core.entity.common.Business;
import com.faraya.legioss.core.model.calendar.CalendarModel;

import java.io.File;
import java.io.InputStream;

/**
 *
 * Created by fabrizzio on 11/3/15.
 */

public interface ICalendarImportBean {

    void createHolidaysCalendar(CalendarModel model, Business business);

    CalendarModel importFromJson(InputStream inputStream) throws Exception;

}
