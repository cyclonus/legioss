package com.faraya.legioss.service;

import static org.junit.Assert.assertEquals;

import com.faraya.legioss.core.entity.ns.NestedSetNode;
import com.faraya.legioss.core.entity.ns.Tree;
import com.faraya.legioss.service.ns.transform.DefaultNodeInstanceFactory;
import com.faraya.legioss.service.ns.transform.INode;
import com.faraya.legioss.service.ns.transform.ITree;
import com.faraya.legioss.service.ns.transform.NestedSetNodesTransformer;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabrizzio on 3/29/15.
 */
public class NestedSetNodesTransformerTest {

    Logger logger = LoggerFactory.getLogger(NestedSetNodesTransformerTest.class);

    private List<NestedSetNode> mockDataSet(){

        Tree tree = new Tree("TransformerTestTree");

        NestedSetNode root = new NestedSetNode("rdb",tree);
        root.setId(1L);
        root.setLeft(1L);
        root.setRight(12L);
        root.setParent(null);
        NestedSetNode sqlLite = new NestedSetNode("SQL-Lite",tree);
        sqlLite.setId(2L);
        sqlLite.setLeft(2L);
        sqlLite.setRight(3L);
        sqlLite.setParent(1L);
        NestedSetNode mySQL = new NestedSetNode("mySQL",tree);
        mySQL.setId(3L);
        mySQL.setLeft(4L);
        mySQL.setRight(9L);
        mySQL.setParent(1L);
        NestedSetNode oracle = new NestedSetNode("Oracle",tree);
        oracle.setId(4L);
        oracle.setLeft(10L);
        oracle.setRight(11L);
        oracle.setParent(1L);
        NestedSetNode InnoDB = new NestedSetNode("InnoDB",tree);
        InnoDB.setId(5L);
        InnoDB.setLeft(5L);
        InnoDB.setRight(6L);
        InnoDB.setParent(3L);
        NestedSetNode MyISAM = new NestedSetNode("MyISAM",tree);
        MyISAM.setId(5L);
        MyISAM.setLeft(7L);
        MyISAM.setRight(8L);
        MyISAM.setParent(3L);
        List<NestedSetNode> list = new ArrayList<NestedSetNode>(6);
        list.add(root);
        list.add(sqlLite);
        list.add(mySQL);
        list.add(oracle);
        list.add(InnoDB);
        list.add(MyISAM);
        return list;
    }

    @Test
    public void DataTransformationTest() {
        DefaultNodeInstanceFactory instanceFactory = new DefaultNodeInstanceFactory();
        List<NestedSetNode> dataSet = mockDataSet();
        NestedSetNodesTransformer <Long,NestedSetNode> transformer = new NestedSetNodesTransformer<Long,NestedSetNode>(dataSet,instanceFactory);
        ITree<Long> tree = transformer.transformAndGet();

        List <INode<Long>> children = tree.getRoot().getChildren();
        for(INode n : children ){
            System.out.println(n);
        }
        System.out.println(tree);
    }


}
