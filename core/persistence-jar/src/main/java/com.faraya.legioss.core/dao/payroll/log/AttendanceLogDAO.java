package com.faraya.legioss.core.dao.payroll.log;

import com.faraya.legioss.core.dao.AbstractJPAGenericDAO;
import com.faraya.legioss.core.entity.payroll.log.AttendanceLog;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * Created by fabrizzio on 11/17/15.
 */
@Repository
public class AttendanceLogDAO extends AbstractJPAGenericDAO<AttendanceLog,Long> implements IAttendanceLogDAO {

    public AttendanceLogDAO() {
        super(AttendanceLog.class);
    }

    @PersistenceContext(unitName = "legioss")
    EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
