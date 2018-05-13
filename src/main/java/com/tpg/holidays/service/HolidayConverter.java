package com.tpg.holidays.service;

import com.tpg.holidays.model.Holiday;
import com.tpg.holidays.persistence.entities.HolidayEntity;
import org.springframework.core.convert.converter.Converter;

import java.time.ZonedDateTime;

public class HolidayConverter implements Converter<HolidayEntity, Holiday> {

    @Override
    public Holiday convert(HolidayEntity holidayEntity) {

        Holiday model = Holiday.builder()
                            .title(holidayEntity.getTitle())
                            .description(holidayEntity.getDescription())
                            .checkIn(checkIn)
                            .checkOut(checkOut)
                            .build();

        return model;
    }

    public HolidayConverter(ZonedDateTime checkIn, ZonedDateTime checkOut) {

        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    private final ZonedDateTime checkIn;
    private final ZonedDateTime checkOut;
}
