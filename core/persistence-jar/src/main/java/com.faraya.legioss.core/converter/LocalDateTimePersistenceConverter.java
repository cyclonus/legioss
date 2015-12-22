package com.faraya.legioss.core.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * Created by fabrizzio on 11/10/15.
 */
@Converter(autoApply = true)
public class LocalDateTimePersistenceConverter implements AttributeConverter <LocalDateTime,java.sql.Timestamp>{

    @Override
    public java.sql.Timestamp convertToDatabaseColumn(LocalDateTime entityValue) {
        return Timestamp.valueOf(entityValue);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(java.sql.Timestamp databaseValue) {
        return databaseValue.toLocalDateTime();
    }

}
