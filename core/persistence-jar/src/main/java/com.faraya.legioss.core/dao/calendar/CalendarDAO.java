package com.faraya.legioss.core.dao.calendar;

import com.faraya.legioss.core.dao.AbstractJPAGenericDAO;
import com.faraya.legioss.core.entity.calendar.Calendar;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * Created by fabrizzio on 11/2/15.
 */

@Repository
public class CalendarDAO extends AbstractJPAGenericDAO<Calendar, Long> implements ICalendarDAO{

    @PersistenceContext(unitName = "legioss")
    EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public CalendarDAO() {
        super(Calendar.class);
    }

}
