package com.faraya.legioss.core.dao.profile;

import com.faraya.legioss.core.dao.AbstractJPAGenericDAO;
import com.faraya.legioss.core.entity.profile.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * User: fabrizzio
 * Date: 10/11/13
 * Time: 10:55 PM
 */

@Repository
public class ProfileDAO extends AbstractJPAGenericDAO<Profile,Long> implements IProfileDAO {

    @PersistenceContext(unitName = "legioss")
    EntityManager entityManager;

    public ProfileDAO() {
        super(Profile.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
