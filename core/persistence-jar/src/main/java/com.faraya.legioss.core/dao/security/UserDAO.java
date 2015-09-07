package com.faraya.legioss.core.dao.security;

import com.faraya.legioss.core.dao.AbstractJPAGenericDAO;
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
 * Created by fabrizzio on 8/5/15.
 */
@Repository
public class UserDAO extends AbstractJPAGenericDAO<User,Long> implements IUserDAO {

    @PersistenceContext(unitName = "legioss")
    EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public UserDAO() {
        super(User.class);
    }

    public User findByEmail(String email){
        email = email.toLowerCase();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery <User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> from = criteriaQuery.from(User.class);
        CriteriaQuery cq = criteriaQuery.where(criteriaBuilder.equal(from.get("primaryEmail"), email));
        Query query = entityManager.createQuery(cq);
        User result = null;
        try{
          result = User.class.cast(query.getSingleResult());
        }catch (javax.persistence.NoResultException nre){
            //TODO configure a logger
        }
        return result;
    }

}
