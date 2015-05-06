package com.faraya.legioss.core.ns;

import com.faraya.legioss.BasePersitenceTest;
import com.faraya.legioss.core.dao.accounting.IAccountCatalogDAO;
import com.faraya.legioss.core.dao.accounting.IAccountNodeDAO;
import com.faraya.legioss.core.entity.accounting.AccountNode;
import com.faraya.legioss.core.entity.accounting.AccountCatalog;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * This is considered a bad practice, but I found my self in a situation where I needed to guarantee the execution order of the category methods
 * Second methods relies on the commits done on the first, third method relies on the commits accomplished by the second and so on.
 * <p/>
 * When adding new methods to this category case, keep in mind stiking to the naming convention
 * if Last method was "thirdTestSomething"
 * The new one should start with "fourthSomethingDescriptive"
 *
 * @See http://howtodoinjava.com/2012/11/24/ordered-testcases-execution-in-junit-4/
 *
 *
 * for integration category use
 * @See http://zeroturnaround.com/rebellabs/the-correct-way-to-use-integration-tests-in-your-build-process/
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TreeHierarchyIT extends BasePersitenceTest {

    Logger logger = LoggerFactory.getLogger(NestedSetDaoIT.class);

    @Autowired
    IAccountCatalogDAO treeDao;


    @Autowired
    IAccountNodeDAO nestedSetDAO;

    @Test
    public void firstInDepthTest() throws Exception{

        assertNotNull("null", nestedSetDAO);
        assertNotNull("null", treeDao);

        AccountCatalog tree = new AccountCatalog("TreeHierarchyTest");
        treeDao.save(tree);
        assertNotNull("null", tree.getId());

        AccountNode root = new AccountNode("root");
        nestedSetDAO.add(root, tree);
        assertEquals("root insertion, root.getLeft ", 1L, (long) root.getLeft());
        assertEquals("root insertion, root.getRight ", 2L, (long) root.getRight());

        AccountNode depth1 = new AccountNode("depth1");
        nestedSetDAO.add(depth1, root, tree);
        nestedSetDAO.refresh(root);

        assertEquals("depth 1 insertion, root.getLeft ",1L,(long)root.getLeft());
        assertEquals("depth 1 insertion, root.getRight",4L,(long)root.getRight());
        assertEquals("depth 1 insertion, depth1.getLeft",2L,(long)depth1.getLeft());
        assertEquals("depth 1 insertion, depth1.getRight", 3L, (long) depth1.getRight());

        AccountNode depth2 = new AccountNode("depth2");
        nestedSetDAO.add(depth2, depth1, tree);

        nestedSetDAO.refresh(depth1);
        nestedSetDAO.refresh(root);

        assertEquals("depth 2 insertion, root.getLeft ",1L,(long)root.getLeft());
        assertEquals("depth 2 insertion, root.getRight ",6L,(long)root.getRight());
        assertEquals("depth 2 insertion, depth1.getLeft ",2L,(long)depth1.getLeft());
        assertEquals("depth 2 insertion, depth1.getRight ",5L,(long)depth1.getRight());
        assertEquals("depth 2 insertion, depth2.getLeft ",3L,(long)depth2.getLeft());
        assertEquals("depth 2 insertion, depth2.getRight ", 4L, (long) depth2.getRight());

        AccountNode depth3 = new AccountNode("depth3");
        nestedSetDAO.add(depth3, depth2, tree);

        nestedSetDAO.refresh(depth1);
        nestedSetDAO.refresh(depth2);
        nestedSetDAO.refresh(root);

        assertEquals("depth 3 insertion, root.getLeft ",1,(long)root.getLeft());
        assertEquals("depth 3 insertion, root.getRight ",8,(long)root.getRight());
        assertEquals("depth 3 insertion, depth1.getLeft ",2,(long)depth1.getLeft());
        assertEquals("depth 3 insertion, depth1.getRight ",7,(long)depth1.getRight());
        assertEquals("depth 3 insertion, depth2.getLeft ",3,(long)depth2.getLeft());
        assertEquals("depth 3 insertion, depth2.getRight ",6,(long)depth2.getRight());
        assertEquals("depth 3 insertion, depth3.getLeft ",4,(long)depth3.getLeft());
        assertEquals("depth 3 insertion, depth3.getRight ", 5, (long) depth3.getRight());

        logger.info("", nestedSetDAO.listAll());
    }


/*
    @Test
    @Rollback(false)
    public void baseTree() throws Exception {

        //http://blog.richardknop.com/2009/05/nested-set-model/
        assertNotNull("null", nestedSetDAO);
        Node rdb = new Node("rdb");
        nestedSetDAO.add(rdb);

        Node sqlLite = new Node("SQL-Lite");
        nestedSetDAO.add(sqlLite,rdb);

        Node mySql = new Node("mySQL");
        nestedSetDAO.add(mySql,rdb);

        Node oracle = new Node("Oracle");
        nestedSetDAO.add(oracle,rdb);

        Node innoDB = new Node("InnoDB");
        nestedSetDAO.add(innoDB,mySql);

        Node myIsam = new Node("myIsam");
        nestedSetDAO.add(myIsam,mySql);

        System.out.println(nestedSetDAO.listAll());

    }
    */
}
