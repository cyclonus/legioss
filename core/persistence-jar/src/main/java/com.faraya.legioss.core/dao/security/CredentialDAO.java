package com.faraya.legioss.core.dao.security;

import com.faraya.legioss.core.dao.AbstractJPAGenericDAO;
import com.faraya.legioss.core.entity.security.Credential;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * Created by fabrizzio on 9/4/15.
 */

@Repository
public class CredentialDAO  extends AbstractJPAGenericDAO<Credential,Long> implements ICredentialDAO{

    @PersistenceContext(unitName = "legioss")
    EntityManager entityManager;

    public CredentialDAO() {
        super(Credential.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public Credential findByOwnerId(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Credential> criteriaQuery = criteriaBuilder.createQuery(Credential.class);
        Root<Credential> from = criteriaQuery.from(Credential.class);
        CriteriaQuery cq = criteriaQuery.where(criteriaBuilder.equal(from.get("owner").<Long>get("id"), id));
        //predicate1 = criteriaBuilder.and(predicate1, criteriaBuilder.equal(rootObj.get("Y").<String> get("Z"), param1));}
        Query query = entityManager.createQuery(cq);
        Credential result = Credential.class.cast(query.getSingleResult());
        return result;
    }


}

