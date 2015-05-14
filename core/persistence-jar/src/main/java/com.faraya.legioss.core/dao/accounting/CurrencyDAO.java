package com.faraya.legioss.core.dao.accounting;

import com.faraya.legioss.core.dao.AbstractJPAGenericDAO;
import com.faraya.legioss.core.entity.accounting.Currency;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
