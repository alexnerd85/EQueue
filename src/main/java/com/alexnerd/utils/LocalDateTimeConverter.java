/*
 *   Created on : 02.12.2017, 12:15:40
 *   Author     : Popov Aleksey
 *   Site       : alexnerd.com
 *   Email      : alexnerd85@gmail.com
 *   GitHub     : https://github.com/alexnerd85/EQueue
 */

package com.alexnerd.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp>{
    
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime dateTime) {
        return (dateTime != null) ? Timestamp.valueOf(dateTime) : null;
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
        return (timestamp != null) ? timestamp.toLocalDateTime() : null;
    }
    
}
