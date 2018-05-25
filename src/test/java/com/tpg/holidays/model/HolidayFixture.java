package com.tpg.holidays.model;

import java.time.ZonedDateTime;
import java.util.List;

import static com.tpg.holidays.model.Holiday.builder;
import static java.util.stream.Collectors.toList;

public interface HolidayFixture {

    default Holiday holiday(String title, String description, Destination destination, ZonedDateTime checkIn, ZonedDateTime checkOut,
                            int numberOfAdults, int numberOfRooms, List<Integer> ages) {

        List<Child> children = ages.stream().map(i -> {
                    Child child = new Child();
                    child.setAge(i);
                    return child;
                }).collect(toList());

        return builder()
                    .destination(destination)
                    .title(title)
                    .description(description)
                    .checkIn(checkIn)
                    .checkOut(checkOut)
                    .numberOfRooms(numberOfRooms)
                    .numberOfAdults(numberOfAdults)
                    .children(children)
                    .build();
    }
}
