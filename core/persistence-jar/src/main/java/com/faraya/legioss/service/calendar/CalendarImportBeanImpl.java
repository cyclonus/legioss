package com.faraya.legioss.service.calendar;

import com.faraya.legioss.core.dao.calendar.ICalendarDAO;
import com.faraya.legioss.core.entity.calendar.Calendar;
import com.faraya.legioss.core.entity.calendar.CalendarDate;
import com.faraya.legioss.core.entity.calendar.Type;
import com.faraya.legioss.core.entity.common.Business;
import com.faraya.legioss.core.model.calendar.CalendarEntryModel;
import com.faraya.legioss.core.model.calendar.CalendarModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

/**
 *
 * Created by fabrizzio on 11/2/15.
 */

@Component
public class CalendarImportBeanImpl implements ICalendarImportBean {



    @Autowired
    ICalendarDAO calendarDAO;

    public void createHolidaysCalendar(CalendarModel model, Business business){
        Calendar calendar = new Calendar(model.getName(),business);
        List<CalendarEntryModel> calendarEntries = model.getCalendar();
        for(CalendarEntryModel cem : calendarEntries){
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.set(java.util.Calendar.MONTH,cem.getMonthDay().getMonthValue());
            cal.set(java.util.Calendar.DAY_OF_MONTH,cem.getMonthDay().getDayOfMonth());
            calendar.addCalendarDate(new CalendarDate(cem.getName(),cal.getTime(),
                     cem.isMandatory() ? Type.MANDATORY_HOLIDAY : Type.NON_MANDATORY_HOLIDAY)
            );
        }
        calendarDAO.save(calendar);
    }

    @Override
    public CalendarModel importFromJson(InputStream inputStream) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        CalendarModel model = objectMapper.readValue(inputStream,CalendarModel.class);
        return model;
    }


}
