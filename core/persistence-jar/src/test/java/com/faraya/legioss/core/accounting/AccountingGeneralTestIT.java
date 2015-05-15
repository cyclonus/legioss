package com.faraya.legioss.core.accounting;

import com.faraya.legioss.BasePersitenceTest;
import com.faraya.legioss.core.dao.accounting.*;
import com.faraya.legioss.core.entity.accounting.*;
import com.faraya.legioss.core.model.accounting.BalanceType;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.ExpectedException;
import org.springframework.test.annotation.Rollback;

import javax.persistence.PersistenceException;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by fabrizzio on 5/11/15.
 *
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountingGeneralTestIT extends BasePersitenceTest {

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
    IAccountCatalogDAO accountCatalogDAO;

    @Autowired
    ICurrencyDAO currencyDAO;
    
/*
    @Test
    @ExpectedException(javax.persistence.PersistenceException.class)
    public void attemptCreatingTwoCurrenciesNamedTheSame(){

           Currency currency1 = new Currency("All mighty dollar", "USD");
           currencyDAO.save(currency1);
           assertNotNull("id", currency1.getId());

           Currency currency2 = new Currency("All mighty dollar", "USD");
           currencyDAO.save(currency2);
           assertNotNull("id", currency2.getId());

    }
*/
    @Test
    @ExpectedException(PersistenceException.class)
    public void attemptCreatingTwoCatalogsNamedTheSame(){
        AccountCatalog a = new AccountCatalog("DuplicateCatalog");
        accountCatalogDAO.save(a);
        assertNotNull("id", a.getId());

        AccountCatalog b = new AccountCatalog("DuplicateCatalog");
        accountCatalogDAO.save(b);
        assertNotNull("id", b.getId());

    }

    @Test
    @Rollback(false)
    public void firstCreateAccountHappyPath(){
        Currency crc =  new Currency("Costa Rica Colon","CRC");
        currencyDAO.save(crc);
        assertNotNull("id", crc.getId());

        assertNotNull(" accountCatalogDAO is null", accountCatalogDAO);
        AccountCatalog catalog = new AccountCatalog("myCatalog");
        accountCatalogDAO.save(catalog);
        assertNotNull("id", catalog.getId());

        Account account = new Account("Happy-Account",crc, catalog);

        accountDAO.save(account);
        assertNotNull("account", account.getId());

        TransactionJournal transactionJournal = new TransactionJournal(account, new Date());
        transactionJournalDAO.save(transactionJournal);
        assertNotNull("id", transactionJournal.getId());

        JournalEntry creditEntry = new JournalEntry(new Date(), new BigDecimal(1.0), BalanceType.CREDIT, 0L);
        JournalEntry debitEntry = new JournalEntry(new Date(), new BigDecimal(1.0), BalanceType.DEBIT, 0L);

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
