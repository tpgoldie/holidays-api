package com.tpg.holidays.model;

import java.time.ZonedDateTime;

import static org.junit.Assert.assertEquals;

public class HolidayAssertion {

    public static HolidayAssertion assertThat(Holiday actual) {

        return new HolidayAssertion(actual);
    }

    public HolidayAssertion hasTitle(String value) {

        assertEquals("Title did not match", value, actual.getTitle());

        return this;
    }

    public HolidayAssertion hasDescription(String value) {

        assertEquals("Description did not match", value, actual.getDescription());

        return this;
    }

    public HolidayAssertion hasCheckIn(ZonedDateTime value) {

        assertEquals("Check in did not match", value, actual.getCheckIn());

        return this;
    }

    public HolidayAssertion hasCheckOut(ZonedDateTime value) {

        assertEquals("Check out did not match", value, actual.getCheckOut());

        return this;
    }

    private HolidayAssertion(Holiday actual) {

        this.actual = actual;
    }

    private final Holiday actual;
}
