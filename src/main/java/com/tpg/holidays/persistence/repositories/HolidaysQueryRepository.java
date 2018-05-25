package com.tpg.holidays.persistence.repositories;

import com.tpg.holidays.persistence.entities.HolidayEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.stream.Stream;

@org.springframework.stereotype.Repository
@Transactional(readOnly = true)
public interface HolidaysQueryRepository extends Repository<HolidayEntity, Long> {

    @Query("SELECT h FROM HolidayEntity h WHERE h.availableFrom <= :checkIn AND :checkIn < h.availableTo " +
            "AND :checkOut <= h.availableTo AND :checkOut > h.availableFrom")
    Stream<HolidayEntity> findByCheckInAndCheckoutDates(@Param("checkIn") ZonedDateTime checkIn,
                                                        @Param("checkOut") ZonedDateTime checkOut);
}
