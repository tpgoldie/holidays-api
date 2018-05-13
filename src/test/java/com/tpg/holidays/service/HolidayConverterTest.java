package com.tpg.holidays.service;

import com.tpg.holidays.model.DateTimeFixture;
import com.tpg.holidays.model.Holiday;
import com.tpg.holidays.persistence.entities.HolidayEntityFixture;
import org.junit.Before;
import org.junit.Test;

import java.time.ZonedDateTime;

import static com.tpg.holidays.model.HolidayAssertion.assertThat;

public class HolidayConverterTest implements DateTimeFixture, HolidayEntityFixture {

    @Before
    public void setUp() {

        checkIn = NOW.minusDays(5);

        converter = new HolidayConverter(checkIn, NOW);
    }

    @Test
    public void convert() {

        Holiday actual = converter.convert(holidayEntity("Jury Inn, Swindon", "Swindon", checkIn, NOW));

        assertThat(actual)
                .hasTitle("Jury Inn, Swindon")
                .hasDescription("Swindon")
                .hasCheckIn(checkIn)
                .hasCheckOut(NOW);
    }

    private ZonedDateTime checkIn;

    private HolidayConverter converter;
}
