package com.faraya.legioss.core.accounting;

import com.faraya.legioss.BasePersitenceTest;
import com.faraya.legioss.core.dao.accounting.IAccountCatalogDAO;
import com.faraya.legioss.core.dao.accounting.IAccountDAO;
import com.faraya.legioss.core.dao.accounting.IAccountNodeDAO;
import com.faraya.legioss.core.dao.accounting.ICurrencyDAO;
import com.faraya.legioss.core.entity.accounting.Account;
import com.faraya.legioss.core.entity.accounting.AccountCatalog;
import com.faraya.legioss.core.entity.accounting.Currency;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.ExpectedException;
import org.springframework.test.annotation.Rollback;

import javax.persistence.PersistenceException;

import static org.junit.Assert.assertNotNull;

/**
 * Created by fabrizzio on 5/11/15.
 *
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountingGeneralTestIT extends BasePersitenceTest {

    Logger logger = LoggerFactory.getLogger(AccountingGeneralTestIT.class);

    @Autowired
    IAccountNodeDAO accountNodeDAO;

    @Autowired
    IAccountDAO accountDAO;

    @Autowired
    IAccountCatalogDAO accountCatalogDAO;

    @Autowired
    ICurrencyDAO currencyDAO;

    @Test
    //@ExpectedException(javax.persistence.PersistenceException.class)
    //@ExpectedException(org.hibernate.exception.ConstraintViolationException.class)
    public void AttemptCreatingTwoCurrenciesNamedTheSame(){

           Currency currency1 = new Currency("All mighty dollar", "USD");
           currencyDAO.save(currency1);
           assertNotNull("id", currency1.getId());

           Currency currency2 = new Currency("All mighty dollar", "USD");
           currencyDAO.save(currency2);
           assertNotNull("id", currency2.getId());

    }

    @Test
    @ExpectedException(PersistenceException.class)
    public void AttemptCreatingTwoCatalogsNamedTheSame(){
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
        AccountCatalog c = new AccountCatalog("myCatalog");
        accountCatalogDAO.save(c);
        assertNotNull("id", c.getId());

        Account account = new Account("Happy-Account",crc);

        accountDAO.save(account);
        assertNotNull("account", account.getId());

    }


}
