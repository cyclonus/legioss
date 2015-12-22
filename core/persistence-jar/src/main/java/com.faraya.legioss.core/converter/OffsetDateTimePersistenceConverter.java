package com.faraya.legioss.core.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 *
 * Created by fabrizzio on 11/10/15.
 */
@Converter(autoApply = true)
public class OffsetDateTimePersistenceConverter implements AttributeConverter <OffsetDateTime,String>{

    /**
     * @return a value as a String such as 2014-12-03T10:15:30+01:00
     * @see OffsetDateTime#toString()
     */
    @Override
    public String convertToDatabaseColumn(OffsetDateTime entityValue) {
        return Objects.toString(entityValue, null);
    }

    @Override
    public OffsetDateTime convertToEntityAttribute(String databaseValue) {
        return OffsetDateTime.parse(databaseValue);
    }
}