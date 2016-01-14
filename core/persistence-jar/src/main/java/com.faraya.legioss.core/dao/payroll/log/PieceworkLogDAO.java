package com.faraya.legioss.core.dao.payroll.log;

import com.faraya.legioss.core.dao.AbstractJPAGenericDAO;
import com.faraya.legioss.core.entity.payroll.log.PieceworkLog;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * Created by fabrizzio on 11/17/15.
 */

@Repository
public class PieceworkLogDAO extends AbstractJPAGenericDAO<PieceworkLog, Long> implements IPieceworkLogDAO {

    public PieceworkLogDAO() {
        super(PieceworkLog.class);
    }

    @PersistenceContext(unitName = "legioss")
    EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public List<PieceworkLog> findPieceworkLogBetween(Long employeeId, LocalDate startDate, LocalDate endDate) {
        Query query = entityManager.createQuery(" SELECT pl FROM PieceworkLog pl WHERE pl.employeeId = :employeeId AND pl.date BETWEEN :startDate AND :endDate ");
        query.setParameter("employeeId", employeeId);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        @SuppressWarnings("unchecked")
        List<PieceworkLog> results = query.getResultList();
        return results;
    }
}
