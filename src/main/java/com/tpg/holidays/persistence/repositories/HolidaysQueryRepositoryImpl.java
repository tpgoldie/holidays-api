package com.tpg.holidays.persistence.repositories;

import com.mysema.query.SearchResults;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.tpg.holidays.persistence.entities.HolidayEntity;
import com.tpg.holidays.persistence.entities.QHolidayEntity;
import com.tpg.holidays.persistence.entities.ZonedDateTimeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.stream.Stream;

@Repository
public class HolidaysQueryRepositoryImpl implements HolidaysQueryRepository {

    @Override
    public Stream<HolidayEntity> findByCheckInAndCheckoutDates(Calendar checkInCal, Calendar checkOutCal) {

        QHolidayEntity qHoliday = QHolidayEntity.holidayEntity;

        JPQLQuery query = new JPAQuery(entityManager);

        ZonedDateTime checkIn = zonedDateTimeConverter.convertToEntityAttribute(checkInCal);
        ZonedDateTime checkOut = zonedDateTimeConverter.convertToEntityAttribute(checkOutCal);

        SearchResults<HolidayEntity> found = ((JPAQuery) query).from(qHoliday)
                .where(qHoliday.availableFrom.before(checkIn)
                        .and(qHoliday.availableTo.after(checkOut)))
                .listResults(qHoliday);

        return found.getResults().stream();
    }

    @Autowired
    public HolidaysQueryRepositoryImpl(EntityManager entityManager) {

        this(entityManager, new ZonedDateTimeConverter());
    }

    HolidaysQueryRepositoryImpl(EntityManager entityManager, ZonedDateTimeConverter zonedDateTimeConverter) {

        this.entityManager = entityManager;
        this.zonedDateTimeConverter = zonedDateTimeConverter;
    }

    private final EntityManager entityManager;
    private final ZonedDateTimeConverter zonedDateTimeConverter;
}
