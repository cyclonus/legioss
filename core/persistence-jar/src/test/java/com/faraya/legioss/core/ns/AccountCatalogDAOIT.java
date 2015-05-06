package com.faraya.legioss.core.ns;

import com.faraya.legioss.BasePersitenceTest;
import com.faraya.legioss.core.dao.accounting.IAccountCatalogDAO;
import com.faraya.legioss.core.entity.accounting.AccountCatalog;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import static org.junit.Assert.assertNotNull;

/**
 * Created by fabrizzio on 4/21/15.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountCatalogDAOIT extends BasePersitenceTest {

    Logger logger = LoggerFactory.getLogger(AccountCatalogDAOIT.class);

    @Autowired
    IAccountCatalogDAO dao;

    @Test
    @Rollback(false)
    public void firstCreateTreeTest() throws Exception {
        assertNotNull(" nestedSetDAO is null", dao);
        AccountCatalog t = new AccountCatalog("myTree");
        dao.save(t);
        assertNotNull("id",t.getId());
    }

    @Test
    @Rollback(false)
    public void secondDropTreeTest() throws Exception {
        assertNotNull(" nestedSetDAO is null", dao);
        AccountCatalog t = dao.findByName("myTree");
        dao.remove(t);
    }

}
