package com.faraya.legioss.core.dao.profile;

import com.faraya.legioss.core.dao.AbstractJPAGenericDAO;
import com.faraya.legioss.core.entity.profile.Education;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * User: fabrizzio
 * Date: 10/11/13
 * Time: 11:28 PM
 */

@Repository
public class EducationDAO extends AbstractJPAGenericDAO<Education,Long> implements IEducationDAO {

    @PersistenceContext(unitName = "legioss")
    EntityManager entityManager;

    public EducationDAO() {
        super(Education.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
