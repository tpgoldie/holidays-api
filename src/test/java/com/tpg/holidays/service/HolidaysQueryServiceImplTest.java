package com.tpg.holidays.service;

import com.tpg.holidays.persistence.repositories.HolidaysQueryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.ZonedDateTime;

import static com.tpg.holidays.service.SearchForHolidays.given;

@RunWith(MockitoJUnitRunner.class)
public class HolidaysQueryServiceImplTest {

    @Before
    public void setUp() {

        serviceImpl = new HolidaysQueryServiceImpl(repository);
    }

    @Test
    public void search() {

        given()
            .repository(repository)
            .service(serviceImpl)
        .when()
            .searchingForHolidays(NOW.minusDays(5), NOW)
        .then()
            .holidaysFound();
    }

    private static final ZonedDateTime NOW = ZonedDateTime.now();

    @Mock
    private HolidaysQueryRepository repository;

    private HolidaysQueryServiceImpl serviceImpl;
}
