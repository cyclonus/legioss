package com.faraya.legioss.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.faraya.legioss.core.entity.accounting.Account;
import com.faraya.legioss.core.entity.accounting.Catalog;
import com.faraya.legioss.core.entity.accounting.AccountNode;
import com.faraya.legioss.core.entity.common.Currency;
import com.faraya.legioss.core.model.accounting.AccountType;
import com.faraya.legioss.service.ns.transform.*;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    ObjectMapper mapper = new ObjectMapper();

    //private static ObjectMapper om = JacksonFactory.getInstance().createDefaultObjectMapper();

    private List<AccountNode> mockDataSet(){

        Currency crc =  new Currency("Costa Rica Colon","CRC");
        Catalog catalog = new Catalog("TransformerTestTree");

        Account rdb = new Account("rdb", AccountType.ASSET, crc, catalog);
        AccountNode root = new AccountNode("rdb");
        root.setTree(catalog);
        root.setAccount(rdb);
        root.setId(1L);
        root.setLeft(1L);
        root.setRight(12L);
        root.setParent(null);

        Account lite = new Account("SQL-Lite", AccountType.ASSET, crc, catalog);
        AccountNode sqlLite = new AccountNode("SQL-Lite");
        sqlLite.setAccount(lite);
        sqlLite.setTree(catalog);
        sqlLite.setId(2L);
        sqlLite.setLeft(2L);
        sqlLite.setRight(3L);
        sqlLite.setParent(1L);

        Account my = new Account("mySQL", AccountType.ASSET, crc, catalog);
        AccountNode mySQL = new AccountNode("mySQL");
        mySQL.setAccount(my);
        mySQL.setTree(catalog);
        mySQL.setId(3L);
        mySQL.setLeft(4L);
        mySQL.setRight(9L);
        mySQL.setParent(1L);

        Account ora = new Account("Oracle", AccountType.ASSET, crc, catalog);
        AccountNode oracle = new AccountNode("Oracle");
        oracle.setAccount(ora);
        oracle.setTree(catalog);
        oracle.setId(4L);
        oracle.setLeft(10L);
        oracle.setRight(11L);
        oracle.setParent(1L);

        Account inno = new Account("InnoDB", AccountType.ASSET, crc, catalog);
        AccountNode InnoDB = new AccountNode("InnoDB");
        InnoDB.setAccount(inno);
        InnoDB.setTree(catalog);
        InnoDB.setId(5L);
        InnoDB.setLeft(5L);
        InnoDB.setRight(6L);
        InnoDB.setParent(3L);

        Account isam = new Account("MyISAM", AccountType.ASSET, crc, catalog);
        AccountNode MyISAM = new AccountNode("MyISAM");
        MyISAM.setAccount(isam);
        MyISAM.setTree(catalog);
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
    public void DataTransformationTest() throws Exception{
        DefaultNodeInstanceFactory instanceFactory = new DefaultNodeInstanceFactory();
        List<AccountNode> dataSet = mockDataSet();
        NestedSetNodesTransformer <Long,AccountNode> transformer = new NestedSetNodesTransformer<>(dataSet,instanceFactory);
        ITree<Long> tree = transformer.transformAndGet();

        /*
        List <INode<Long>> children = tree.getRoot().getChildren();
        for(INode n : children ){
            logger.info("node: ",n);
        }
        logger.info("tree: ",tree);
        */
        String out = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tree);
        logger.info(out);

        ITree t = mapper.readValue(out,TreeImpl.class );
        assertNotNull(t);
    }


}
