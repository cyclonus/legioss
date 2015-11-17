package com.faraya.legioss.common;

import com.faraya.legioss.BaseIntegrationTest;
import com.faraya.legioss.core.dao.common.IGlobalParamDAO;
import com.faraya.legioss.core.entity.common.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 *
 * Created by fabrizzio on 11/16/15.
 */

public class GlobalParamDaoIT extends BaseIntegrationTest {


    Logger logger = LoggerFactory.getLogger(GlobalParamDaoIT.class);

    @Autowired
    IGlobalParamDAO globalParamDAO;

    @Test
    public void testInsert() throws Exception{
        GlobalParam gp = new GlobalParam("LOL","LOL", ParamType.string,"LOL",createBusiness());
        globalParamDAO.save(gp);
        assertNotNull("null id", gp.getId());
        List <GlobalParam> list =  globalParamDAO.findAllByGroup("LOL");
        assertNotNull("null id", list);
        assertFalse(list.isEmpty());
        GlobalParam p = list.iterator().next();
        assertEquals("LOL",p.getValue());
        assertNotNull(globalParamDAO.findByGroupAndName("LOL", "LOL"));
    }

}
