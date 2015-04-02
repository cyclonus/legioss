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
    public void firstAddInDepth() throws Exception{

        assertNotNull("null", dao);
        NestedSetNode root = new NestedSetNode("root");
        dao.add(root);
        assertEquals(root.countChildren(),0);

        for(int i=1; i<=1; i++) {
           addChildrenInDepth(4,1,i,root);
        }
        assertEquals(root.countChildren(),1);
    }

    private void addChildrenInDepth(int depth, int depthCount, int id ,NestedSetNode parent){
       if(depthCount <= depth) {
           NestedSetNode child = new NestedSetNode(String.format("child-depth:%d-id:%d",depthCount,id));
           child = dao.add(child, parent);
           assertNotNull(child.getId());
           addChildrenInDepth(depth, ++depthCount, id, child);
       }
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
