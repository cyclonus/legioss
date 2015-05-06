package com.faraya.legioss.service;

import static org.junit.Assert.assertEquals;

import com.faraya.legioss.core.entity.accounting.AccountCatalog;
import com.faraya.legioss.core.entity.accounting.AccountNode;
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
 *
 * Created by fabrizzio on 3/29/15.
 */
public class NestedSetNodesTransformerTest {

    Logger logger = LoggerFactory.getLogger(NestedSetNodesTransformerTest.class);

    private List<AccountNode> mockDataSet(){

        AccountCatalog tree = new AccountCatalog("TransformerTestTree");

        AccountNode root = new AccountNode("rdb");
        root.setTree(tree);
        root.setId(1L);
        root.setLeft(1L);
        root.setRight(12L);
        root.setParent(null);

        AccountNode sqlLite = new AccountNode("SQL-Lite");
        sqlLite.setTree(tree);
        sqlLite.setId(2L);
        sqlLite.setLeft(2L);
        sqlLite.setRight(3L);
        sqlLite.setParent(1L);

        AccountNode mySQL = new AccountNode("mySQL");
        mySQL.setTree(tree);
        mySQL.setId(3L);
        mySQL.setLeft(4L);
        mySQL.setRight(9L);
        mySQL.setParent(1L);

        AccountNode oracle = new AccountNode("Oracle");
        oracle.setTree(tree);
        oracle.setId(4L);
        oracle.setLeft(10L);
        oracle.setRight(11L);
        oracle.setParent(1L);

        AccountNode InnoDB = new AccountNode("InnoDB");
        InnoDB.setTree(tree);
        InnoDB.setId(5L);
        InnoDB.setLeft(5L);
        InnoDB.setRight(6L);
        InnoDB.setParent(3L);

        AccountNode MyISAM = new AccountNode("MyISAM");
        MyISAM.setTree(tree);
        MyISAM.setId(5L);
        MyISAM.setLeft(7L);
        MyISAM.setRight(8L);
        MyISAM.setParent(3L);

        List<AccountNode> list = new ArrayList<>(6);
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
        List<AccountNode> dataSet = mockDataSet();
        NestedSetNodesTransformer <Long,AccountNode> transformer = new NestedSetNodesTransformer<>(dataSet,instanceFactory);
        ITree<Long> tree = transformer.transformAndGet();

        List <INode<Long>> children = tree.getRoot().getChildren();
        for(INode n : children ){
            logger.info("node: ",n);
        }
        logger.info("tree: ",tree);
    }


}
