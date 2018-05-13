package com.tpg.holidays.persistence.repositories;

import com.tpg.holidays.persistence.entities.HolidayEntity;
import com.tpg.holidays.persistence.entities.ZonedDateTimeConverter;

import javax.persistence.EntityManager;
import java.time.ZonedDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

public class FindByCheckInAndCheckOut {

    public static FindByCheckInAndCheckOut given() {

        return new FindByCheckInAndCheckOut();
    }

    public FindByCheckInAndCheckOut entityManager(EntityManager entityManager) {

        this.entityManager = entityManager;

        return this;
    }

    public FindByCheckInAndCheckOut repository(HolidaysQueryRepository repository) {

        this.repository = repository;

        return this;
    }

    public FindByCheckInAndCheckOut when() { return this; }

    public FindByCheckInAndCheckOut then() { return this; }

    public FindByCheckInAndCheckOut findByCheckInAndCheckout(ZonedDateTime checkIn, ZonedDateTime checkOut) {

        actual = repository.findByCheckInAndCheckoutDates(zonedDateTimeConverter.convertToDatabaseColumn(checkIn),
                zonedDateTimeConverter.convertToDatabaseColumn(checkOut)).collect(toList());

        return this;
    }

    public FindByCheckInAndCheckOut holidaysAreFound() {

        assertEquals("Size does not match", 1, actual.size());

        return this;
    }

    private FindByCheckInAndCheckOut() {

        zonedDateTimeConverter = new ZonedDateTimeConverter();
    }

    private EntityManager entityManager;

    private HolidaysQueryRepository repository;

    private ZonedDateTimeConverter zonedDateTimeConverter;

    private List<HolidayEntity> actual;
}
