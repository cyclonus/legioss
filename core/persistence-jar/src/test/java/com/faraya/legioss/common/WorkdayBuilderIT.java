package com.faraya.legioss.common;

import com.faraya.legioss.BaseIntegrationTest;
import com.faraya.legioss.component.WorkdayBuilder;
import com.faraya.legioss.core.entity.common.Workday;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertEquals;
/**
 *
 * Created by fabrizzio on 11/16/15.
 */

public class WorkdayBuilderIT extends BaseIntegrationTest {

    @Autowired
    WorkdayBuilder builder;

    @Test
    public void testRegularWorkday(){
        Workday wd = builder.regularEightHoursWorkday();
        assertEquals("not good!", wd.getHours(), 8);
    }


    @Test
    public void testBuilder(){
        Workday wd = builder.fromToHours(8,17);
        assertEquals("not good!",wd.getHours(),8);
    }

}
