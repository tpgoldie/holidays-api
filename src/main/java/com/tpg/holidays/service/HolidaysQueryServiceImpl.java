package com.tpg.holidays.service;

import com.tpg.holidays.model.Holiday;
import com.tpg.holidays.persistence.entities.HolidayEntity;
import com.tpg.holidays.persistence.repositories.HolidaysQueryRepository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class HolidaysQueryServiceImpl {

    public HolidaysQueryServiceImpl(HolidaysQueryRepository repository) {

        this.repository = repository;
    }

    public List<Holiday> searchHolidaysByCheckInAndCheckout(ZonedDateTime checkIn, ZonedDateTime checkOut) {

        Stream<HolidayEntity> found = repository.findByCheckInAndCheckoutDates(checkIn, checkOut);

        HolidayConverter holidayConverter = new HolidayConverter(checkIn, checkOut);

        return found.map(holidayConverter::convert).collect(toList());
    }

    private final HolidaysQueryRepository repository;
}
