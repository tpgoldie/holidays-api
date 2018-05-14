package com.tpg.holidays.service;

import com.tpg.holidays.model.Holiday;
import com.tpg.holidays.persistence.entities.DestinationEntity;
import com.tpg.holidays.persistence.entities.DestinationEntityFixture;
import com.tpg.holidays.persistence.entities.HolidayEntity;
import com.tpg.holidays.persistence.entities.HolidayEntityFixture;
import com.tpg.holidays.persistence.repositories.HolidaysQueryRepository;
import org.mockito.Mockito;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

public class SearchForHolidays implements DestinationEntityFixture, HolidayEntityFixture {

    public static SearchForHolidays given() {

        return new SearchForHolidays();
    }

    public SearchForHolidays when() { return this; }

    public SearchForHolidays service(HolidaysQueryServiceImpl serviceImpl) {

        this.serviceImpl = serviceImpl;

        return this;
    }

    public SearchForHolidays repository(HolidaysQueryRepository repository) {

        this.repository = repository;

        return this;
    }

    public SearchForHolidays searchingForHolidays(ZonedDateTime checkIn, ZonedDateTime checkOut) {

        this.checkIn = checkIn;
        this.checkOut = checkOut;

        DestinationEntity destination = destinationEntity("SDN", "Swindon", "Swindon");

        HolidayEntity holiday = holidayEntity("Jury Inn Swindon", destination, checkIn, checkOut);

        Mockito.when(repository.findByCheckInAndCheckoutDates(checkIn, checkOut))
                    .thenReturn(Stream.of(holiday));

        actual = serviceImpl.searchHolidaysByCheckInAndCheckout(checkIn, checkOut);

        return this;
    }

    public SearchForHolidays then() { return this; }

    public SearchForHolidays holidaysFound() {

        verify(repository).findByCheckInAndCheckoutDates(checkIn, checkOut);

        assertEquals(1, actual.size());

        return this;
    }

    private HolidaysQueryRepository repository;
    private HolidaysQueryServiceImpl serviceImpl;
    private ZonedDateTime checkIn;
    private ZonedDateTime checkOut;
    private List<Holiday> actual;
}
