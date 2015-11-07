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
        Root<Credential> entity = criteriaQuery.from(Credential.class);
        criteriaQuery.select(entity);
        CriteriaQuery cq = criteriaQuery.where(
                criteriaBuilder.equal(entity.get("owner").<Long>get("id"), id)
        );
        Query query = entityManager.createQuery(cq);
        Credential result = Credential.class.cast(query.getSingleResult());
        return result;
    }


}

