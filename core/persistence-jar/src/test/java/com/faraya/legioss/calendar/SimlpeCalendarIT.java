package com.faraya.legioss.calendar;

import com.faraya.legioss.BaseIntegrationTest;
import com.faraya.legioss.core.dao.calendar.ICalendarDAO;
import com.faraya.legioss.core.entity.calendar.Calendar;
import com.faraya.legioss.core.entity.calendar.CalendarDate;
import com.faraya.legioss.core.entity.calendar.Type;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertNotNull;

/**
 *
 * Created by fabrizzio on 11/6/15.
 */

public class SimlpeCalendarIT extends BaseIntegrationTest {

    @Autowired
    ICalendarDAO calendarDAO;

    @Test
    public void testInsertCalendar(){
        Calendar c = new Calendar("Holidays",createBusiness());
        c.addCalendarDate(new CalendarDate("today",new Date(), Type.BUSINESS_DAY));
        c.addCalendarDate(new CalendarDate("whatever",new Date(), Type.MANDATORY_HOLIDAY));

        calendarDAO.save(c);
        assertNotNull("null id", c.getId());
    }

}
