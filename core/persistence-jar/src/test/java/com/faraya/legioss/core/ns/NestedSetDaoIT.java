package com.faraya.legioss.core.ns;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.faraya.legioss.BasePersitenceTest;
import com.faraya.legioss.core.dao.ns.INestedSetDAO;
import com.faraya.legioss.core.entity.ns.NestedSetNode;
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
    INestedSetDAO dao;

    @Test
    public void firstAddChildrenTest() throws Exception{

        // first add two nodes
        assertNotNull("null", dao);
        NestedSetNode root = new NestedSetNode("root");
        dao.add(root);
        assertEquals(root.countChildren(),0);
        assertRoot(root);

        NestedSetNode child1 = new NestedSetNode("child-1");
        dao.add(child1,root);
        dao.refresh(root); // Require for the Test to work
        assertEquals(root.countChildren(), 1);
        assertEquals(child1.getParent(),root.getId());
        assertRootHasOneChild(root);

        NestedSetNode child2 = new NestedSetNode("child-2");
        dao.add(child2,root);
        dao.refresh(root); // Require for the Test to work
        assertEquals(root.countChildren(), 2);
        assertEquals(child2.getParent(),root.getId());

    }

    @Test
    public void secondRemoveChildrenTest() throws Exception{
        // now first remove child 2

        NestedSetNode child2 = dao.findByName("child-2");
        assertNotNull(child2);
        dao.delete(child2);

        NestedSetNode child1 = dao.findByName("child-1");
        assertEquals(child1.countChildren(), 0);

        //then remove child 1
        child1 = dao.findByName("child-1");
        assertNotNull(child1);
        dao.delete(child1);

        NestedSetNode root = dao.findRoot();
        assertEquals(root.countChildren(), 0);
    }


        public void assertRoot(NestedSetNode root){
        assertNotNull(root.getId());
        assertEquals("Root's left must always be 1 ",1,(int)root.getLeft());
        assertEquals("Root's right must be 2 when first added ",2,(int)root.getRight());
        assertNull("initially, root does not have right most node",dao.getRightMostNodeFor(root));
    }

    public void assertRootHasOneChild(NestedSetNode root){
        NestedSetNode rm1 = dao.getRightMostNodeFor(root);
        assertNotNull("right most node must not be null this time",rm1);
        assertEquals("right most is the only child owned by root ",2L,(long)rm1.getId());

        List<NestedSetNode> onlyChild = dao.getTree(root.getId());
        assertEquals("must have 1 child",1,onlyChild.size());
        NestedSetNode c1 = onlyChild.get(0);
        assertNode(c1,2,3);

        //Expected is
        // [Node{ id=1, name='root', left=1, right=4},
        //    Node{ id=2, name='child-1', left=2, right=3}
        // ]
    }

    public void assertRootFirstGrandChild(NestedSetNode child1,NestedSetNode grandChild){
        NestedSetNode rm3 = dao.getRightMostNodeFor(child1);
        assertNotNull(rm3);
        assertEquals("right most node is child-3", grandChild.getId(), rm3.getId());
        List<NestedSetNode> subTree2 = dao.getTree(child1.getId());
        assertEquals("must have 1 child", 1, subTree2.size());
        NestedSetNode c4 = subTree2.get(0);
        assertEquals("Must match.. ",rm3,c4);
        //Expected is
        // [Node{ id=1, name='root', left=1, right=8},
        //    Node{ id=2, name='child-1', left=2, right=5}, Node{ id=3, name='child-2', left=5, right=6}
        //      Node{ id=4, name='child-3 (grandChild)', left=3, right=4}
        // ]

    }

    private void assertNode(NestedSetNode node,int left, int right){
        assertEquals("",left, (int)node.getLeft());
        assertEquals("",right,(int)node.getRight());
    }
}
