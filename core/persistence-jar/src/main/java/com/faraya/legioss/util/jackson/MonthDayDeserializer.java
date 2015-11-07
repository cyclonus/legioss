package com.faraya.legioss.util.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.MonthDay;

/**
 * Created by fabrizzio on 11/5/15.
 */

public class MonthDayDeserializer extends StdDeserializer<MonthDay> {


    public MonthDayDeserializer() {
        super(MonthDay.class);
    }

    @Override
    public MonthDay deserialize(JsonParser parser, DeserializationContext ctx) throws IOException, JsonProcessingException {
        JsonToken t = parser.getCurrentToken();
        if (t == JsonToken.VALUE_STRING) {
            String str = parser.getText().trim();
            if (str.isEmpty()) {
                return null;
            }
            return MonthDay.parse(str);
        }
        return null;
    }

}
