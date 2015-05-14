package com.faraya.legioss.core.dao.accounting;

import com.faraya.legioss.core.dao.AbstractJPAGenericDAO;
import com.faraya.legioss.core.entity.accounting.Account;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * Created by fabrizzio on 5/10/15.
 */

@Repository
public class AccountDAO extends AbstractJPAGenericDAO<Account,Long> implements IAccountDAO {

    @PersistenceContext(unitName = "legioss")
    EntityManager entityManager;

    public AccountDAO() {
        super(Account.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }


}
