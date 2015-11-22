package com.faraya.legioss.core.accounting;

import com.faraya.legioss.TransactionalSpringJUnit4RunnerTest;
import com.faraya.legioss.core.dao.accounting.*;
import com.faraya.legioss.core.entity.accounting.*;
import com.faraya.legioss.core.entity.common.BasicCurrency;
import com.faraya.legioss.core.model.accounting.AccountType;
import com.faraya.legioss.core.model.accounting.BalanceType;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by fabrizzio on 5/11/15.
 *
 */


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountingGeneralTestIT extends TransactionalSpringJUnit4RunnerTest {

    Logger logger = LoggerFactory.getLogger(AccountingGeneralTestIT.class);

    @Autowired
    ITransactionJournalDAO transactionJournalDAO;

    @Autowired
    IJournalEntryDAO journalEntryDAO;

    @Autowired
    IAccountNodeDAO accountNodeDAO;

    @Autowired
    IAccountDAO accountDAO;

    @Autowired
    ICatalogDAO accountCatalogDAO;


/*
http://stackoverflow.com/questions/1108066/junit4-expected-exception-not-working-with-spring

    @Test
    @ExpectedException(javax.persistence.PersistenceException.class)
    public void attemptCreatingTwoCurrenciesNamedTheSame(){

           BasicCurrency currency1 = new BasicCurrency("All mighty dollar", "USD");
           currencyDAO.save(currency1);
           assertNotNull("id", currency1.getId());

           BasicCurrency currency2 = new BasicCurrency("All mighty dollar", "USD");
           currencyDAO.save(currency2);
           assertNotNull("id", currency2.getId());

    }


    //@Rule
    //public TestName name = new TestName();

    @Test
    @ExpectedException(javax.persistence.PersistenceException.class)
    public void attemptCreatingTwoCatalogsNamedTheSame(){
        Catalog a = new Catalog("DuplicateCatalog");
        accountCatalogDAO.save(a);
        assertNotNull("id", a.getId());

        Catalog b = new Catalog("DuplicateCatalog");
        accountCatalogDAO.save(b);
        assertNotNull("id", b.getId());

    }
*/

    @Test
    @Rollback(true)
    public void firstCreateAccountHappyPath(){
        BasicCurrency crc =  new BasicCurrency("CRC");
        assertNotNull(" accountCatalogDAO is null", accountCatalogDAO);
        Catalog catalog = new Catalog("myCatalog");
        accountCatalogDAO.save(catalog);
        assertNotNull("id", catalog.getId());

        Account account = new Account("Happy-Account", AccountType.ASSET, crc, catalog);

        accountDAO.save(account);
        assertNotNull("account", account.getId());

        TransactionJournal transactionJournal = new TransactionJournal(account, new Date());
        transactionJournalDAO.save(transactionJournal);
        assertNotNull("id", transactionJournal.getId());

        JournalEntry creditEntry = new JournalEntry(new Date(), new BigDecimal(1.0), BalanceType.credit, 0L);
        JournalEntry debitEntry = new JournalEntry(new Date(), new BigDecimal(1.0), BalanceType.debit, 0L);

        journalEntryDAO.save(creditEntry);
        assertNotNull("creditEntry", creditEntry.getId());
        journalEntryDAO.save(debitEntry);
        assertNotNull("debitEntry", debitEntry.getId());

        transactionJournal.addEntry(creditEntry);
        transactionJournal.addEntry(debitEntry);

        transactionJournalDAO.save(transactionJournal);
        TransactionJournal tj = transactionJournalDAO.findByPK(transactionJournal.getId());
        assertNotNull("id", tj.getId());
        assertEquals("accounting entries in journal dont mantch",tj.getEntries().size(), 2);
    }


}
