package com.tpg.holidays.service;

import com.tpg.holidays.model.Holiday;
import com.tpg.holidays.persistence.entities.HolidayEntity;
import com.tpg.holidays.persistence.repositories.HolidaysQueryRepository;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
public class HolidaysQueryServiceImpl implements HolidaysQueryService {

    public HolidaysQueryServiceImpl(HolidaysQueryRepository repository) {

        this.repository = repository;
    }

    @Override
    public List<Holiday> searchHolidaysByCheckInAndCheckout(ZonedDateTime checkIn, ZonedDateTime checkOut) {

        Stream<HolidayEntity> found = repository.findByCheckInAndCheckoutDates(checkIn, checkOut);

        HolidayConverter holidayConverter = new HolidayConverter(checkIn, checkOut);

        return found.map(holidayConverter::convert).collect(toList());
    }

    private final HolidaysQueryRepository repository;
}
