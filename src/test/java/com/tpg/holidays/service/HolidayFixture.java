package com.tpg.holidays.service;

import com.tpg.holidays.model.Destination;
import com.tpg.holidays.model.Holiday;

import java.time.ZonedDateTime;

public interface HolidayFixture {

    default Holiday holiday(String title, String description, Destination destination, ZonedDateTime checkIn, ZonedDateTime checkOut) {

        Holiday holiday = Holiday.builder()
                            .destination(destination)
                            .title(title)
                            .description(description)
                            .checkIn(checkIn)
                            .checkOut(checkOut)
                            .build();

        return holiday;
    }
}
