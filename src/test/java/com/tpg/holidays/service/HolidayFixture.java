package com.tpg.holidays.service;

import com.tpg.holidays.model.Child;
import com.tpg.holidays.model.Destination;
import com.tpg.holidays.model.Holiday;

import java.time.ZonedDateTime;
import java.util.List;

public interface HolidayFixture {

    default Holiday holiday(String title, String description, Destination destination, ZonedDateTime checkIn,
                            ZonedDateTime checkOut, int numberOfRooms, int numberOfAdults, List<Child> children) {

        Holiday holiday = Holiday.builder()
                            .destination(destination)
                            .title(title)
                            .description(description)
                            .checkIn(checkIn)
                            .checkOut(checkOut)
                            .numberOfRooms(numberOfRooms)
                            .numberOfAdults(numberOfAdults)
                            .children(children)
                            .build();

        return holiday;
    }
}
