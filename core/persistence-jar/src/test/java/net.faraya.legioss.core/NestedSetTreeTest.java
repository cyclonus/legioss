package net.faraya.legioss.core;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import net.faraya.legioss.core.dao.accounting.ICatalogDAO;
import net.faraya.legioss.core.entity.tree.Node;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * User: fabrizzio
 * Date: 6/8/13
 * Time: 10:48 PM
 */

public class NestedSetTreeTest extends BasePersitenceTest{

    @Autowired
    ICatalogDAO dao;

    @Test
    public void basicTest(){
        assertNotNull("null",dao);
        Node root = new Node();
        root.setName("root");
        dao.addNode(root);
        assertNotNull(root.getId());
        assertEquals("Root's left must always be 1 ",1,(int)root.getLeft());
        assertEquals("Root's right must be 2 when first added ",2,(int)root.getRight());
        assertNull("initially, root does not have right most node",dao.getRightMostNode(root));

        Node child1 = new Node();
        child1.setName("child-1");
        dao.addNode(root,child1);


        Node rm1 = dao.getRightMostNode(root);
        assertNotNull("right most node must not be null this time",rm1);
        assertEquals("right most is the only child owned by root ",2L,(long)rm1.getId());

        List<Node> onlyChild = dao.getTree(root.getId());
        assertEquals("must have 1 child",1,onlyChild.size());
        Node c1 = onlyChild.get(0);
        assertEquals("",2L,(long)c1.getLeft());
        assertEquals("",3L,(long)c1.getRight());
        //Expected is
        // [Node{ id=1, name='root', left=1, right=4},
        //    Node{ id=2, name='child-1', left=2, right=3}
        // ]

        Node child2 = new Node();
        child2.setName("child-2");
        dao.addNode(root,child2);
        Node rm2 = dao.getRightMostNode(root);
        assertNotNull("right most node must not be null this time",rm2);
        assertEquals("right most is the only child owned by root ",3L,(long)rm2.getId());
        List<Node> subTree1 = dao.getTree(root.getId());
        assertNotNull(subTree1);
        assertEquals("must have 2 children", 2, subTree1.size());

        //Expected is
        // [Node{ id=1, name='root', left=1, right=6},
        //    Node{ id=2, name='child-1', left=2, right=3}, Node{ id=3, name='child-2', left=3, right=4}
        // ]
        Node c2 = subTree1.get(0);
        assertEquals("",2L,(long)c2.getLeft());
        assertEquals("", 3L, (long) c2.getRight());

        Node c3 = subTree1.get(1);
        assertEquals("",4L,(long)c3.getLeft());
        assertEquals("",5L,(long)c3.getRight());

        Node child3 = new Node();
        child3.setName("child-3");
        dao.addNode(child1,child3);

        Node rm3 = dao.getRightMostNode(child1);
        assertNotNull(rm3);
        assertEquals("right most node is child-3", child3.getId(), rm3.getId());
        List<Node> subTree2 = dao.getTree(child1.getId());
        assertEquals("must have 1 child", 1, subTree2.size());
        Node c4 = subTree2.get(0);
        assertEquals("Must match.. ",rm3,c4);
        //Expected is
        // [Node{ id=1, name='root', left=1, right=8},
        //    Node{ id=2, name='child-1', left=2, right=5}, Node{ id=3, name='child-2', left=5, right=6}
        //      Node{ id=4, name='child-3', left=3, right=4}
        // ]
        List<Node> subTree3 = dao.getTree(root.getId());

    }

}
