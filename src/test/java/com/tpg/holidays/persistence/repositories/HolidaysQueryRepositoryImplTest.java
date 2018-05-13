package com.tpg.holidays.persistence.repositories;

import com.tpg.holidays.context.PersistenceConfig;
import com.tpg.holidays.persistence.entities.HolidayEntity;
import com.tpg.holidays.persistence.entities.HolidayEntityFixture;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@Import(value = {PersistenceConfig.class})
@DataJpaTest
@ActiveProfiles({"dev"})
@Profile("dev")
public class HolidaysQueryRepositoryImplTest implements HolidayEntityFixture {

    @Before
    public void setUp() {

        List<HolidayEntity> entities = asList(
            holidayEntity("holiday 1", NOW.minusDays(3), NOW.plusDays(10)),
            holidayEntity("holiday 2", NOW.minusDays(5), NOW)
        );

        entities.forEach(entity -> entityManager.persist(entity));
        entityManager.flush();
    }

    @Test
    public void searchByCheckinAndCheckout() {

        ZonedDateTime checkIn = NOW.minusDays(4);

        List<HolidayEntity> found = repository.findByCheckInAndCheckoutDates(checkIn, NOW)
                .collect(toList());

        assertEquals(1, found.size());
    }

    private static final ZonedDateTime NOW = ZonedDateTime.now();

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private HolidaysQueryRepository repository;
}
