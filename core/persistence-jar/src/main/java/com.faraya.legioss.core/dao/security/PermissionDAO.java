package com.faraya.legioss.core.dao.security;

import com.faraya.legioss.core.dao.AbstractJPAGenericDAO;
import com.faraya.legioss.core.entity.security.Permission;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * Created by fabrizzio on 8/5/15.
 */
@Repository
public class PermissionDAO extends AbstractJPAGenericDAO<Permission,Long> implements IPermissionDAO  {

    public PermissionDAO() {
        super(Permission.class);
    }

    @PersistenceContext(unitName = "legioss")
    EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
