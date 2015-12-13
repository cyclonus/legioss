package com.faraya.legioss.core.dao.calendar;

import com.faraya.legioss.core.dao.IGenericDAO;
import com.faraya.legioss.core.entity.calendar.Calendar;

/**
 *
 * Created by fabrizzio on 11/2/15.
 */

public interface ICalendarDAO extends IGenericDAO<Calendar,Long> {

    Calendar findByBusinessId(Long businessId);

}
