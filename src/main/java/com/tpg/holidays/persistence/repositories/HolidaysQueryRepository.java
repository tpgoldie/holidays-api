package com.tpg.holidays.persistence.repositories;

import com.tpg.holidays.persistence.entities.HolidayEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.stream.Stream;

public interface HolidaysQueryRepository extends Repository<HolidayEntity, Long> {

//    @Query(value = "SELECT h.* FROM holidays h WHERE :checkIn BETWEEN h.available_from AND h.available_to " +
//            "AND :checkOut BETWEEN h.available_from AND h.available_to", nativeQuery = true)
//    @Query(value = "SELECT h.* FROM holidays h WHERE :checkIn > h.available_from AND :checkIn < h.available_to " +
//            "AND :checkOut > h.available_from AND :checkOut < h.available_to", nativeQuery = true)
//    @Query("SELECT h FROM HolidayEntity h WHERE h.availableFrom < :checkIn AND :checkIn < h.availableTo AND " +
//            ":checkOut > h.availableTo AND :checkOut <= h.availableTo ")
    @Query("SELECT h FROM HolidayEntity h WHERE h.availableFrom <= :checkIn AND :checkIn < h.availableTo " +
            "AND :checkOut <= h.availableTo AND :checkOut > h.availableFrom")
    Stream<HolidayEntity> findByCheckInAndCheckoutDates(@Param("checkIn") ZonedDateTime checkIn,
                                                        @Param("checkOut") ZonedDateTime checkOut);
}
