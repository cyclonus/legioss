package com.faraya.legioss.core.dao.payroll;

import com.faraya.legioss.core.dao.AbstractJPAGenericDAO;
import com.faraya.legioss.core.entity.payroll.agreement.Agreement;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * Created by fabrizzio on 10/11/15.
 */
@Repository
public class AgreementDAO extends AbstractJPAGenericDAO<Agreement,Long> implements IAgreementDAO {

    @PersistenceContext(unitName = "legioss")
    EntityManager entityManager;

    public AgreementDAO() {
        super(Agreement.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
