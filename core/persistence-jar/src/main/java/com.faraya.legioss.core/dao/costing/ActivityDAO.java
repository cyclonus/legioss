package com.faraya.legioss.core.dao.costing;

import com.faraya.legioss.core.dao.AbstractJPAGenericDAO;
import com.faraya.legioss.core.entity.costing.Activity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * Created by fabrizzio on 5/10/15.
 */

@Repository
public class ActivityDAO extends AbstractJPAGenericDAO<Activity,Long> implements IActivityDAO {

    @PersistenceContext(unitName = "legioss")
    EntityManager entityManager;

    public ActivityDAO() {
        super(Activity.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }


}
