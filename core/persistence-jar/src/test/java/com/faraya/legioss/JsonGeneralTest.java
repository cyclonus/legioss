package com.faraya.legioss;

import com.faraya.legioss.core.model.accounting.rule.SalePostingRule;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.Test;


/**
 * http://wiki.fasterxml.com/JacksonInFiveMinutes
 * Created by fabrizzio on 5/22/15.
 */

public class JsonGeneralTest {

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void toJsonTest() throws Exception{
        SalePostingRule  spr = new SalePostingRule();
        //JsonFactory factory = mapper.getFactory();
        String out = mapper.writeValueAsString(spr);

    }

}
