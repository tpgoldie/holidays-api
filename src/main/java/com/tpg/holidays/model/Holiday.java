package com.tpg.holidays.model;

import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Builder
public final class Holiday {

    private final String title;
    private final Destination destination;
    private final String description;
    private final String reasonsToVisit;
    private final ZonedDateTime checkIn;
    private final ZonedDateTime checkOut;
    private final int numberOfRooms;
    private final int numberOfAdults;
    private final List<Child> children;
}
