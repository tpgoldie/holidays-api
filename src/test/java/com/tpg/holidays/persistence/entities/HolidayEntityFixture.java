package com.tpg.holidays.persistence.entities;

import java.time.ZonedDateTime;

public interface HolidayEntityFixture {

    default HolidayEntity holidayEntity(String title, DestinationEntity destination, ZonedDateTime from, ZonedDateTime to) {

        return holidayEntity(title, title, destination, from, to);
    }

    default HolidayEntity holidayEntity(String title, String description, DestinationEntity destination, ZonedDateTime from, ZonedDateTime to) {

        HolidayEntity entity = new HolidayEntity();

        entity.setTitle(title);
        entity.setDescription(description);
        entity.setDestination(destination);
        entity.setAvailableFrom(from);
        entity.setAvailableTo(to);

        return entity;
    }
}
