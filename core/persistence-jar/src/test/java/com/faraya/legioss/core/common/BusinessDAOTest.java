package com.faraya.legioss.core.common;

import com.faraya.legioss.TransactionalSpringJUnit4RunnerTest;
import com.faraya.legioss.core.dao.common.IBusinessDAO;
import com.faraya.legioss.core.entity.common.Business;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * Created by fabrizzio on 9/12/15.
 */
public class BusinessDAOTest extends TransactionalSpringJUnit4RunnerTest {


    @Autowired
    IBusinessDAO businessDAO;

    @Test
    public void testCreateBusiness(){

        Business business = new Business();


    }

}