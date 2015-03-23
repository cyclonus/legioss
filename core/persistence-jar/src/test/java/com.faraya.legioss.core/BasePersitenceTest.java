package com.faraya.legioss.core;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * User: fabrizzio
 * Date: 6/8/13
 * Time: 5:22 PM
 */
@ContextConfiguration(locations={
        "classpath:context.xml"
})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
public abstract class BasePersitenceTest extends AbstractTransactionalJUnit4SpringContextTests {



}
