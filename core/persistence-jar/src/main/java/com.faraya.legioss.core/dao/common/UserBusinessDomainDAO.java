package com.faraya.legioss.core.dao.common;

import com.faraya.legioss.core.dao.AbstractJPAGenericDAO;
import com.faraya.legioss.core.entity.common.UserBusinessDomain;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * Created by fabrizzio on 9/14/15.
 */


@Repository
public class UserBusinessDomainDAO extends AbstractJPAGenericDAO<UserBusinessDomain,Long> implements IUserBusinessDomainDAO{

    @PersistenceContext(unitName = "legioss")
    EntityManager entityManager;

    public UserBusinessDomainDAO() {
        super(UserBusinessDomain.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

}
