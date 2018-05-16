package com.tpg.holidays.service;

import com.tpg.holidays.model.Holiday;

import java.time.ZonedDateTime;
import java.util.List;

public interface HolidaysQueryService {
    List<Holiday> searchHolidaysByCheckInAndCheckout(ZonedDateTime checkIn, ZonedDateTime checkOut);
}
