package com.faraya.legioss.core.dao.payroll.log;

import com.faraya.legioss.core.dao.AbstractJPAGenericDAO;
import com.faraya.legioss.core.entity.common.Period;
import com.faraya.legioss.core.entity.payroll.log.DailyAttendance;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 *
 * Created by fabrizzio on 11/17/15.
 */
@Repository
public class AttendanceLogDAO extends AbstractJPAGenericDAO<DailyAttendance,Long> implements IAttendanceLogDAO {

    public AttendanceLogDAO() {
        super(DailyAttendance.class);
    }

    @PersistenceContext(unitName = "legioss")
    EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public List<DailyAttendance> findAttendance(Long employeeId, Period period){
        Query query = entityManager.createQuery(
              "SELECT a FROM DailyAttendance a WHERE a.employeeId = :employeeId AND day(a.date) >= day(:start) " +
                      " AND day(a.date) <= day(:end) ORDER BY a.day "
        );
        query.setParameter("employeeId",employeeId);
        query.setParameter("start",period.getStart());
        query.setParameter("end",period.getEnd());
        @SuppressWarnings("unchecked")
        List<DailyAttendance> dailyAttendances = query.getResultList();
        return dailyAttendances;
    }

}
