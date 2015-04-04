package com.faraya.legioss.core.ns;

import com.faraya.legioss.BasePersitenceTest;
import com.faraya.legioss.core.dao.ns.INestedSetDAO;
import com.faraya.legioss.core.entity.ns.NestedSetNode;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * This is considered a bad practice, but I found my self in a situation where I needed to guarantee the execution order of the test methods
 * Second methods relies on the commits done on the first, third method relies on the commits accomplished by the second and so on.
 * <p/>
 * When adding new methods to this test case, keep in mind stiking to the naming convention
 * if Last method was "thirdTestSomething"
 * The new one should start with "fourthSomethingDescriptive"
 *
 * @See http://howtodoinjava.com/2012/11/24/ordered-testcases-execution-in-junit-4/
 *
 *
 * for integration test use
 * @See http://zeroturnaround.com/rebellabs/the-correct-way-to-use-integration-tests-in-your-build-process/
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TreeHierarchyIT extends BasePersitenceTest {

    Logger logger = LoggerFactory.getLogger(NestedSetDaoIT.class);

    @Autowired
    INestedSetDAO dao;


    @Test
    @Rollback(false)
    public void firstInDepthTest() throws Exception{


        assertNotNull("null", dao);
        NestedSetNode root = new NestedSetNode("root");
        dao.add(root);
        assertEquals("root insertion, root.getLeft ", 1, (int) root.getLeft());
        assertEquals("root insertion, root.getRight ",2,(int)root.getRight());

        NestedSetNode depth1 = new NestedSetNode("depth1");
        dao.add(depth1,root);
        dao.refresh(root);

        assertEquals("depth 1 insertion, root.getLeft ",1,(int)root.getLeft());
        assertEquals("depth 1 insertion, root.getRight",4,(int)root.getRight());
        assertEquals("",2,(int)depth1.getLeft());
        assertEquals("",3,(int)depth1.getRight());

        NestedSetNode depth2 = new NestedSetNode("depth2");
        dao.add(depth2,depth1);

        dao.refresh(depth1);
        dao.refresh(root);

        assertEquals("depth 2 insertion, root.getLeft ",1,(int)root.getLeft());
        assertEquals("depth 2 insertion, root.getRight ",6,(int)root.getRight());
        assertEquals("depth 2 insertion, depth1.getLeft ",2,(int)depth1.getLeft());
        assertEquals("depth 2 insertion, depth1.getRight ",5,(int)depth1.getRight());
        assertEquals("depth 2 insertion, depth2.getLeft ",3,(int)depth2.getLeft());
        assertEquals("depth 2 insertion, depth2.getRight ",4,(int)depth2.getRight());

        NestedSetNode depth3 = new NestedSetNode("depth3");
        dao.add(depth3,depth2);

        dao.refresh(depth1);
        dao.refresh(depth2);
        dao.refresh(root);

        assertEquals("",1,(int)root.getLeft());
        assertEquals("",8,(int)root.getRight());
        assertEquals("",2,(int)depth1.getLeft());
        assertEquals("",7,(int)depth1.getRight());
        assertEquals("",3,(int)depth2.getLeft());
        assertEquals("",6,(int)depth2.getRight());
        assertEquals("",4,(int)depth3.getLeft());
        assertEquals("",5,(int)depth3.getRight());

        System.out.println(dao.listAll());
    }



/*
    @Test
    @Rollback(false)
    public void baseTree() throws Exception {

        //http://blog.richardknop.com/2009/05/nested-set-model/
        assertNotNull("null", dao);
        Node rdb = new Node("rdb");
        dao.add(rdb);

        Node sqlLite = new Node("SQL-Lite");
        dao.add(sqlLite,rdb);

        Node mySql = new Node("mySQL");
        dao.add(mySql,rdb);

        Node oracle = new Node("Oracle");
        dao.add(oracle,rdb);

        Node innoDB = new Node("InnoDB");
        dao.add(innoDB,mySql);

        Node myIsam = new Node("myIsam");
        dao.add(myIsam,mySql);

        System.out.println(dao.listAll());

    }
    */
}
