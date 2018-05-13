package com.tpg.holidays.persistence.repositories;

import com.tpg.holidays.persistence.entities.HolidayEntity;
import org.springframework.data.repository.Repository;

import java.util.Calendar;
import java.util.stream.Stream;

public interface HolidaysQueryRepository extends Repository<HolidayEntity, Long> {

//    @Query(value = "SELECT h.* FROM holidays h WHERE :checkIn BETWEEN h.available_from AND h.available_to " +
//            "AND :checkOut BETWEEN h.available_from AND h.available_to", nativeQuery = true)
//    @Query("SELECT h FROM HolidayEntity h WHERE :#{#checkIn} BETWEEN h.availableFrom AND h.availableTo AND :#{#checkOut} BETWEEN h.availableFrom AND h.availableTo")
//    Stream<HolidayEntity> findByCheckInAndCheckoutDates(@Param("checkIn") Calendar checkIn,
//                                                        @Param("checkOut") Calendar checkOut);

    Stream<HolidayEntity> findByCheckInAndCheckoutDates(Calendar checkIn, Calendar checkOut);
}
