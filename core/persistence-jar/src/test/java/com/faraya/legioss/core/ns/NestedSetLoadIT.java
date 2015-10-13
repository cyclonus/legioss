package com.faraya.legioss.core.ns;

import com.faraya.legioss.core.dao.accounting.IAccountNodeDAO;
import com.faraya.legioss.core.dao.accounting.ICatalogDAO;
import com.faraya.legioss.core.entity.accounting.AccountNode;
import com.faraya.legioss.core.entity.accounting.Catalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 *
 * Created by fabrizzio on 9/14/15.
 *
 * http://examples.javacodegeeks.com/enterprise-java/testng/testng-spring-integration-example/
 */

@ContextConfiguration(locations={"classpath:context.xml"})
public class NestedSetLoadIT extends AbstractTestNGSpringContextTests {

    @Autowired
    ICatalogDAO treeDao;


    @Autowired
    IAccountNodeDAO nestedSetDAO;

    @BeforeTest
    public void beforeTest() {
      //  Catalog tree = new Catalog("TreeHierarchyTest");
      //  treeDao.save(tree);
      //  AccountNode root = new AccountNode("root");
    }


    @Test(threadPoolSize = 3, invocationCount = 9,  timeOut = 10000)
    public void doSomething() {

    }

}
