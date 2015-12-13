package com.faraya.legioss.core.dao.calendar;

import com.faraya.legioss.core.dao.AbstractJPAGenericDAO;
import com.faraya.legioss.core.entity.calendar.Calendar;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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

    public Calendar findByBusinessId(Long businessId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Calendar> criteriaQuery = criteriaBuilder.createQuery(Calendar.class);
        Root<Calendar> entity = criteriaQuery.from(Calendar.class);
        criteriaQuery.select(entity);

        CriteriaQuery cq = criteriaQuery.where(
                criteriaBuilder.equal(entity.get("businessId"), businessId)
        );
        Query query = entityManager.createQuery(cq);
        @SuppressWarnings("unchecked")
        Calendar result = Calendar.class.cast(query.getSingleResult());
        return result;
    }

}
