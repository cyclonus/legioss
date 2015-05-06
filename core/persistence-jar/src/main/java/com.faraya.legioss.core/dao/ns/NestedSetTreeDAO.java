package com.faraya.legioss.core.dao.ns;

import com.faraya.legioss.core.dao.AbstractJPAGenericDAO;
import com.faraya.legioss.core.entity.ns.NestedSetTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * Created by fabrizzio on 4/6/15.
 */

public abstract class NestedSetTreeDAO <T extends NestedSetTree> extends AbstractJPAGenericDAO<T,Long> implements ITreeDAO <T> {

    Logger logger = LoggerFactory.getLogger(NestedSetTreeDAO.class);

    @PersistenceContext(unitName = "legioss")
    EntityManager entityManager;

    public NestedSetTreeDAO(final Class<T> persistentClass) {
        super(persistentClass);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public T findByName(String name) {
        T tree = null;
        String clazzName = getPersistentClass().getSimpleName();
        String queryStr = String.format(" SELECT t FROM %s t WHERE t.name = :n ",clazzName);
        Query query = getEntityManager().createQuery(queryStr, getPersistentClass());
        try {
            query.setParameter("n", name);
            tree = getPersistentClass().cast(query.getSingleResult());
        } catch (NoResultException nre) {
            logger.warn(" NestedSetTree not found under name :"+name);
        }
        return tree;
    }
}
