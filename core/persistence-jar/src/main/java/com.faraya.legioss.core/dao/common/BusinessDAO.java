package com.faraya.legioss.core.dao.common;

import com.faraya.legioss.core.dao.AbstractJPAGenericDAO;
import com.faraya.legioss.core.entity.common.Business;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * Created by fabrizzio on 9/9/15.
 */

@Repository
public class BusinessDAO extends AbstractJPAGenericDAO<Business,Long> implements IBusinessDAO{

    @PersistenceContext(unitName = "legioss")
    EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public BusinessDAO() {
        super(Business.class);
    }


}
