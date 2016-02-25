package com.faraya.legioss.core.dao.costing;

import com.faraya.legioss.core.dao.AbstractJPAGenericDAO;
import com.faraya.legioss.core.entity.costing.Piecework;
import com.faraya.legioss.core.entity.security.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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


    @Override
    public Piecework findByCode(String code) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Piecework> criteriaQuery = criteriaBuilder.createQuery(Piecework.class);
        Root<Piecework> from = criteriaQuery.from(Piecework.class);
        CriteriaQuery cq = criteriaQuery.where(criteriaBuilder.equal(from.get("code"), code));
        Query query = entityManager.createQuery(cq);
        Piecework result = null;
        try{
            result = Piecework.class.cast(query.getSingleResult());
        }catch (javax.persistence.NoResultException nre){
            //TODO configure a logger
        }
        return result;
    }

    @Override
    public Piecework findByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Piecework> criteriaQuery = criteriaBuilder.createQuery(Piecework.class);
        Root<Piecework> from = criteriaQuery.from(Piecework.class);
        CriteriaQuery cq = criteriaQuery.where(criteriaBuilder.equal(from.get("name"), name));
        Query query = entityManager.createQuery(cq);
        Piecework result = null;
        try{
            result = Piecework.class.cast(query.getSingleResult());
        }catch (javax.persistence.NoResultException nre){
            //TODO configure a logger
        }
        return result;
    }
}
