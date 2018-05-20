package com.tpg.holidays.model;

import com.tpg.holidays.model.Child;
import com.tpg.holidays.model.Destination;
import com.tpg.holidays.model.Holiday;

import java.time.ZonedDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

public interface HolidayFixture {

    default Holiday holiday(String title, String description, Destination destination, ZonedDateTime checkIn, ZonedDateTime checkOut,
                            int numberOfAdults, int numberOfRooms, List<Integer> ages) {

        List<Child> children = ages.stream().map(Child::new).collect(toList());

        Holiday holiday = Holiday.builder()
                            .destination(destination)
                            .title(title)
                            .description(description)
                            .checkIn(checkIn)
                            .checkOut(checkOut)
                            .numberOfAdults(numberOfAdults)
                            .numberOfRooms(numberOfRooms)
                            .children(children)
                            .build();

        return holiday;
    }
}
