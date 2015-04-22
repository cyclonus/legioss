package com.faraya.legioss.core.dao.ns;

import com.faraya.legioss.core.dao.AbstractJPAGenericDAO;
import com.faraya.legioss.core.entity.ns.Tree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by fabrizzio on 4/6/15.
 */

@Repository
public class TreeDAO extends AbstractJPAGenericDAO<Tree,Long> implements ITreeDAO {

    Logger logger = LoggerFactory.getLogger(TreeDAO.class);

    @PersistenceContext(unitName = "legioss")
    EntityManager entityManager;

    public TreeDAO() {
        super(Tree.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Tree findByName(String name) {
        Tree tree = null;
        Query query = getEntityManager().createQuery(" SELECT t FROM Tree t WHERE t.name = :n ", Tree.class);
        try {
            query.setParameter("n", name);
            tree = (Tree) query.getSingleResult();
        } catch (NoResultException nre) {
            logger.warn(" Tree not found under name :"+name);
        }
        return tree;
    }
}
