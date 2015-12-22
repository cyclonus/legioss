package com.faraya.legioss.core.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Time;
import java.time.LocalTime;

/**
 *
 * Created by fabrizzio on 11/12/15.
 */
@Converter(autoApply = true)
public class LocalTimePersistenceConverter implements AttributeConverter<LocalTime, Time> {

    @Override
    public Time convertToDatabaseColumn(LocalTime entityValue) {
        return (entityValue == null) ? null : Time.valueOf(entityValue);
    }


    @Override
    public LocalTime convertToEntityAttribute(Time databaseValue) {
        return databaseValue.toLocalTime();
    }

}
