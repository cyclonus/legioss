package com.faraya.legioss.core.dao.costing;

import com.faraya.legioss.core.dao.AbstractJPAGenericDAO;
import com.faraya.legioss.core.entity.costing.Piecework;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * Created by fabrizzio on 10/11/15.
 */

@Repository
public class PieceworkDAO extends AbstractJPAGenericDAO<Piecework,Long> implements IPieceworkDAO {

    @PersistenceContext(unitName = "legioss")
    EntityManager entityManager;

    public PieceworkDAO() {
        super(Piecework.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

}
