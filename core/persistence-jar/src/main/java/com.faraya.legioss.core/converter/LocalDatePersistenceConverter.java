package com.faraya.legioss.core.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDate;

/**
 *
 * Created by fabrizzio on 11/10/15.
 */

@Converter(autoApply = true)
public class LocalDatePersistenceConverter implements AttributeConverter <LocalDate,java.sql.Date> {

    @Override
    public java.sql.Date convertToDatabaseColumn(LocalDate entityValue) {
        return java.sql.Date.valueOf(entityValue);
    }

    @Override
    public LocalDate convertToEntityAttribute(java.sql.Date databaseValue) {
        return databaseValue.toLocalDate();
    }
}
