package com.faraya.legioss.core.dao.payroll.log;

import com.faraya.legioss.core.dao.AbstractJPAGenericDAO;
import com.faraya.legioss.core.entity.payroll.log.PieceworkLog;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * Created by fabrizzio on 11/17/15.
 */

@Repository
public class PieceworkLogDAO extends AbstractJPAGenericDAO<PieceworkLog,Long> implements IPieceworkLogDAO {

    public PieceworkLogDAO() {
        super(PieceworkLog.class);
    }

    @PersistenceContext(unitName = "legioss")
    EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
