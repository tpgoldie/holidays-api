package com.tpg.holidays.wui.controllers;

import com.tpg.holidays.model.DateTimeSpecification;
import com.tpg.holidays.model.Holiday;
import com.tpg.holidays.model.HolidaySummary;
import com.tpg.holidays.model.Hotel;
import com.tpg.holidays.service.HolidaysQueryService;
import com.tpg.holidays.service.HotelsQueryService;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class HolidaysQueryController implements DateTimeSpecification {

    public ResponseEntity<List<HolidaySummary>> search(SearchRequest searchRequest) {

        List<Holiday> holidays = holidaysQueryService.searchHolidaysByCheckInAndCheckout(
                toZonedDateTime(searchRequest.getCheckIn()),
                toZonedDateTime(searchRequest.getCheckOut()));

        List<Hotel> hotels = hotelsQueryService.searchForHolidaysByDestination(searchRequest);

        return ResponseEntity.ok(toHolidaySummaries(holidays, hotels));
    }

    private List<HolidaySummary> toHolidaySummaries(List<Holiday> holidays, List<Hotel> hotels) {

        List<HolidaySummary> summaries = new ArrayList<>();

        holidays.forEach(holiday -> hotels.forEach(hotel -> summaries.add(new HolidaySummary(holiday, hotel))));

        return summaries;
    }

    public HolidaysQueryController(HolidaysQueryService holidaysQueryService, HotelsQueryService hotelsQueryService) {

        this.holidaysQueryService = holidaysQueryService;
        this.hotelsQueryService = hotelsQueryService;
    }

    private HolidaysQueryService holidaysQueryService;
    private final HotelsQueryService hotelsQueryService;
}
