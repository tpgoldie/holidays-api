package com.tpg.holidays.persistence.entities;

import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.TimeZone;

import static java.time.ZonedDateTime.ofInstant;

@Converter(autoApply = true)
@Component
public class ZonedDateTimeConverter implements AttributeConverter<ZonedDateTime, Calendar> {

    @Override
    public Calendar convertToDatabaseColumn(ZonedDateTime zonedDateTime) {

        if (zonedDateTime == null) { return null; }

        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(zonedDateTime.toInstant().toEpochMilli());
        calendar.setTimeZone(TimeZone.getTimeZone(zonedDateTime.getZone()));

        return calendar;
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(Calendar calendar) {

        if (calendar == null) { return null; }

        return ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId());
    }
}
