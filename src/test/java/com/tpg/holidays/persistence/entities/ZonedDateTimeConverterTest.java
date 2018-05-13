package com.tpg.holidays.persistence.entities;

import org.junit.Before;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ZonedDateTimeConverterTest {

    @Before
    public void setUp() {
        converter = new ZonedDateTimeConverter();
    }

    @Test
    public void convertToDatabaseColumn() {

        ZonedDateTime now = ZonedDateTime.now();

        Calendar actual = converter.convertToDatabaseColumn(now);
        assertEquals(now.toEpochSecond(), actual.getTimeInMillis() / 1000);
    }

    @Test
    public void convertToDatabaseColumnHandlesNull() {

        Calendar actual = converter.convertToDatabaseColumn(null);
        assertThat( actual, is(nullValue()));
    }

    @Test
    public void convertToEntityAttribute() {

        Calendar calendar = Calendar.getInstance();

        ZonedDateTime actual = converter.convertToEntityAttribute(calendar);
        assertEquals(calendar.getTimeInMillis() / 1000, actual.toEpochSecond());
    }

    @Test
    public void convertToEntityAttributeHandlesNull() {

        ZonedDateTime actual = converter.convertToEntityAttribute(null);
        assertThat( actual, is(nullValue()));
    }

    private ZonedDateTimeConverter converter;
}
