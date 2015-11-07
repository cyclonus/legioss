package com.faraya.legioss.calendar;

import com.faraya.legioss.BaseIntegrationTest;
import com.faraya.legioss.core.model.calendar.CalendarModel;
import com.faraya.legioss.service.calendar.ICalendarImportBean;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;

/**
 *
 * Created by fabrizzio on 11/3/15.
 */

public class CalendarImportBeanTest extends BaseIntegrationTest{

    @Autowired
    ICalendarImportBean calendarImportBean;

    @Test
    public void testImportBean() throws Exception{
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("calendars.json");
        CalendarModel model = calendarImportBean.importFromJson(inputStream);
        assertNotNull(model);
        assertNotNull(model.getCalendar());
        assertEquals(12, model.getCalendar().size());
        assertEquals("año nuevo",model.getCalendar().get(0).getName());
        assertEquals("Fiesta de Fin de Año",model.getCalendar().get(11).getName());
    }

}
