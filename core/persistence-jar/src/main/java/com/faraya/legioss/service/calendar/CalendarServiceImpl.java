package com.faraya.legioss.service.calendar;

import com.faraya.legioss.core.dao.calendar.ICalendarDAO;
import com.faraya.legioss.core.entity.calendar.Calendar;
import com.faraya.legioss.core.entity.common.Business;
import com.faraya.legioss.core.model.calendar.CalendarModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * Created by fabrizzio on 11/2/15.
 */

@Service
public class CalendarServiceImpl implements ICalendarService {

    @Autowired
    ICalendarDAO calendarDAO;

    @Autowired
    ICalendarImportBean calendarImportBean;

    @Override
    public Calendar find(String name, Business business) {

        return null;
    }

    @Override
    public void importCalendar(CalendarModel model, Business business) {

    }
}
