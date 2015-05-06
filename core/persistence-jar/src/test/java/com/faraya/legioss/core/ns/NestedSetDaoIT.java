package com.faraya.legioss.core.ns;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import com.faraya.legioss.core.dao.accounting.IAccountCatalogDAO;
import com.faraya.legioss.core.dao.accounting.IAccountNodeDAO;
import com.faraya.legioss.core.entity.accounting.AccountNode;
import com.faraya.legioss.core.entity.accounting.AccountCatalog;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.faraya.legioss.BasePersitenceTest;
import java.util.List;

/**
 * User: fabrizzio
 * Date: 6/8/13
 * Time: 10:48 PM
 */

/**
 *  READ Before modifying these Tests
 *  @See http://zeroturnaround.com/rebellabs/the-correct-way-to-use-integration-tests-in-your-build-process/
 *  @See http://maven.apache.org/surefire/maven-failsafe-plugin/examples/junit.html
 *  @See http://howtodoinjava.com/2012/11/24/ordered-testcases-execution-in-junit-4/
 */

@Ignore
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NestedSetDaoIT extends BasePersitenceTest {

    Logger logger = LoggerFactory.getLogger(NestedSetDaoIT.class);

    @Autowired
    IAccountCatalogDAO catalogDAO;

    @Autowired
    IAccountNodeDAO accountRefNodeDAO;


    @Test
    public void firstAddChildrenTest() throws Exception{

        assertNotNull("null", accountRefNodeDAO);
        AccountCatalog tree = new AccountCatalog("NestedSetTreeTest");
        catalogDAO.save(tree);
        assertNotNull("null", tree.getId());

        // first add two nodes
        AccountNode root = new AccountNode("root");
        accountRefNodeDAO.add(root,tree);
        assertEquals(root.countChildren(), 0);
        assertRoot(root);

        AccountNode child1 = new AccountNode("child-1");
        accountRefNodeDAO.add(child1, root, tree);
        accountRefNodeDAO.refresh(root); // Require for the Test to work
        assertEquals(root.countChildren(), 1);
        assertEquals(child1.getParent(),root.getId());
        assertRootHasOneChild(root);

        AccountNode child2 = new AccountNode("child-2");
        accountRefNodeDAO.add(child2, root, tree);
        accountRefNodeDAO.refresh(root); // Require for the Test to work
        assertEquals(root.countChildren(), 2);
        assertEquals(child2.getParent(),root.getId());

    }

    @Test
    public void secondRemoveChildrenTest() throws Exception{

        AccountCatalog tree = catalogDAO.findByName("NestedSetTreeTest");
        assertNotNull("null", tree);
        assertNotNull("null", tree.getId());
        // now first remove child 2
        AccountNode child2 = accountRefNodeDAO.findByName("child-2", tree);
        assertNotNull(child2);
        accountRefNodeDAO.delete(child2, tree);

        AccountNode child1 = accountRefNodeDAO.findByName("child-1", tree);
        assertEquals(child1.countChildren(), 0);

        //then remove child 1
        child1 = accountRefNodeDAO.findByName("child-1", tree);
        assertNotNull(child1);
        accountRefNodeDAO.delete(child1, tree);

        AccountNode root = accountRefNodeDAO.findRoot(tree);
        assertEquals(root.countChildren(), 0);
    }


    /**
     * Must allow nodes with same name on different trees, otherwise the index is broken
     * @throws Exception
     */
    @Test
    public void uniqueNamesConstraintOnDifferentTreesTest() throws Exception {

        AccountCatalog tree1 = catalogDAO.findByName("tree1");

        assertNotNull("null", tree1);
        assertNotNull("null", tree1.getId());

        AccountNode child1 = new AccountNode("randomNode");

        AccountCatalog tree2 = catalogDAO.findByName("tree1");

        assertNotNull("null", tree2);
        assertNotNull("null", tree2.getId());

        AccountNode child2 = new AccountNode("randomNode");

        accountRefNodeDAO.add(child1,tree1);
        assertNotNull(child1.getId());
        accountRefNodeDAO.add(child2, tree2);
        assertNotNull(child2.getId());
    }


    public void assertRoot(AccountNode root){
        assertNotNull(root.getId());
        assertEquals("Root's left must always be 1 ",1,(long)root.getLeft());
        assertEquals("Root's right must be 2 when first added ",2,(long)root.getRight());
        assertNull("initially, root does not have right most node", accountRefNodeDAO.getRightMostNodeFor(root));
    }

    public void assertRootHasOneChild(AccountNode root){
        AccountNode rm1 = accountRefNodeDAO.getRightMostNodeFor(root);
        assertNotNull("right most node must not be null this time",rm1);
        assertEquals("right most is the only child owned by root ", 2L, (long) rm1.getId());

        List<AccountNode> onlyChild = accountRefNodeDAO.getTree(root.getId());
        assertEquals("must have 1 child",1,onlyChild.size());
        AccountNode c1 = onlyChild.get(0);
        assertNode(c1,2,3);

        //Expected is
        // [Node{ id=1, name='root', left=1, right=4},
        //    Node{ id=2, name='child-1', left=2, right=3}
        // ]
    }

    public void assertRootFirstGrandChild(AccountNode child1, AccountNode grandChild){
        AccountNode rm3 = accountRefNodeDAO.getRightMostNodeFor(child1);
        assertNotNull(rm3);
        assertEquals("right most node is child-3", grandChild.getId(), rm3.getId());
        List<AccountNode> subTree2 = accountRefNodeDAO.getTree(child1.getId());
        assertEquals("must have 1 child", 1, subTree2.size());
        AccountNode c4 = subTree2.get(0);
        assertEquals("Must match.. ",rm3,c4);
        //Expected is
        // [Node{ id=1, name='root', left=1, right=8},
        //    Node{ id=2, name='child-1', left=2, right=5}, Node{ id=3, name='child-2', left=5, right=6}
        //      Node{ id=4, name='child-3 (grandChild)', left=3, right=4}
        // ]

    }

    private void assertNode(AccountNode node,int left, int right){
        assertEquals("",left, (long)node.getLeft());
        assertEquals("",right,(long)node.getRight());
    }
}
