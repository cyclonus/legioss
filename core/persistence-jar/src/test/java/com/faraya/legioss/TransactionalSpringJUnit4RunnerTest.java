package com.faraya.legioss;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


/**
 * User: fabrizzio
 * Date: 6/8/13
 * Time: 5:22 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:context.xml"})
@Transactional
public abstract class TransactionalSpringJUnit4RunnerTest {
}
