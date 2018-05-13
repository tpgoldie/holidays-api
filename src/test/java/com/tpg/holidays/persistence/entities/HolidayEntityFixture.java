package com.tpg.holidays.persistence.entities;

import java.time.ZonedDateTime;

public interface HolidayEntityFixture {

    default HolidayEntity holidayEntity(String title, ZonedDateTime from, ZonedDateTime to) {

        HolidayEntity entity = new HolidayEntity();

        entity.setTitle(title);
        entity.setDescription(title);
        entity.setAvailableFrom(from);
        entity.setAvailableTo(to);

        return entity;
    }
}
