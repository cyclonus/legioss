package com.faraya.legioss.core.dao.accounting;

import com.faraya.legioss.core.dao.AbstractJPAGenericDAO;
import com.faraya.legioss.core.entity.accounting.TransactionJournal;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * Created by fabrizzio on 5/14/15.
 */

@Repository
public class TransactionJournalDAO extends AbstractJPAGenericDAO<TransactionJournal,Long> implements ITransactionJournalDAO{

    public TransactionJournalDAO() {
        super(TransactionJournal.class);
    }

    @PersistenceContext(unitName = "legioss")
    EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

}
