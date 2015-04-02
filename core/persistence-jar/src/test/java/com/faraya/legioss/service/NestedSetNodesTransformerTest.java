package com.faraya.legioss.service;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import com.faraya.legioss.core.entity.ns.NestedSetNode;
import com.faraya.legioss.service.ns.transform.DefaultNodeInstanceFactory;
import com.faraya.legioss.service.ns.transform.INode;
import com.faraya.legioss.service.ns.transform.ITree;
import com.faraya.legioss.service.ns.transform.NestedSetNodesTransformer;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabrizzio on 3/29/15.
 */
public class NestedSetNodesTransformerTest {

    Logger logger = LoggerFactory.getLogger(NestedSetNodesTransformerTest.class);

    private List<NestedSetNode> mockDataSet(){

        NestedSetNode root = new NestedSetNode("rdb");
        root.setId(1L);
        root.setLeft(1);
        root.setRight(12);
        root.setParent(null);
        NestedSetNode sqlLite = new NestedSetNode("SQL-Lite");
        sqlLite.setId(2L);
        sqlLite.setLeft(2);
        sqlLite.setRight(3);
        sqlLite.setParent(1L);
        NestedSetNode mySQL = new NestedSetNode("mySQL");
        mySQL.setId(3L);
        mySQL.setLeft(4);
        mySQL.setRight(9);
        mySQL.setParent(1L);
        NestedSetNode oracle = new NestedSetNode("Oracle");
        oracle.setId(4L);
        oracle.setLeft(10);
        oracle.setRight(11);
        oracle.setParent(1L);
        NestedSetNode InnoDB = new NestedSetNode("InnoDB");
        InnoDB.setId(5L);
        InnoDB.setLeft(5);
        InnoDB.setRight(6);
        InnoDB.setParent(3L);
        NestedSetNode MyISAM = new NestedSetNode("MyISAM");
        MyISAM.setId(5L);
        MyISAM.setLeft(7);
        MyISAM.setRight(8);
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
        NestedSetNodesTransformer transformer = new NestedSetNodesTransformer<Long,NestedSetNode>(dataSet,instanceFactory);
        ITree tree = transformer.transformAndGet();
        List <INode> children = tree.getRoot().getChildren();
        for(INode n : children ){
            System.out.println(n);
        }
        System.out.println(tree);
    }


}
