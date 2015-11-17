package com.faraya.legioss.core.dao.common;

import com.faraya.legioss.core.dao.AbstractJPAGenericDAO;
import com.faraya.legioss.core.entity.common.Currency;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * Created by fabrizzio on 5/10/15.
 */
@Repository
public class CurrencyDAO extends AbstractJPAGenericDAO<Currency,Long> implements ICurrencyDAO{

    @PersistenceContext(unitName = "legioss")
    EntityManager entityManager;

    public CurrencyDAO() {
        super(Currency.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Currency findByShortName(String name){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Currency> criteriaQuery = criteriaBuilder.createQuery(Currency.class);
        Root<Currency> entity = criteriaQuery.from(Currency.class);
        criteriaQuery.select(entity);
        CriteriaQuery cq = criteriaQuery.where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(entity.get("shortName"), name)
                )
        );
        Query query = entityManager.createQuery(cq);
        @SuppressWarnings("unchecked")
        Currency result = Currency.class.cast(query.getSingleResult());
        return result;
    }

}
