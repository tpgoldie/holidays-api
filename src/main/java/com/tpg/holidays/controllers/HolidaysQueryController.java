package com.tpg.holidays.controllers;

import com.tpg.holidays.controllers.wui.SearchRequest;
import com.tpg.holidays.model.*;
import com.tpg.holidays.service.HolidaysQueryService;
import com.tpg.holidays.service.HotelsQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/holidays")
public class HolidaysQueryController implements DateTimeSpecification {

    @GetMapping(value = "/search", consumes = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<HolidaySummaries> search(@RequestBody SearchRequest searchRequest) {

        List<Holiday> holidays = holidaysQueryService.searchHolidaysByCheckInAndCheckout(
                toZonedDateTime(searchRequest.getCheckIn()),
                toZonedDateTime(searchRequest.getCheckOut()));

        List<Hotel> hotels = hotelsQueryService.searchForHolidaysByDestination(searchRequest);

        return ResponseEntity.ok(toHolidaySummaries(holidays, hotels));
    }

    private HolidaySummaries toHolidaySummaries(List<Holiday> holidays, List<Hotel> hotels) {

        List<HolidaySummary> summaries = new ArrayList<>();

        holidays.forEach(holiday -> hotels.forEach(hotel -> summaries.add(new HolidaySummary(holiday, hotel))));

        return new HolidaySummaries(summaries);
    }

    public HolidaysQueryController(HolidaysQueryService holidaysQueryService, HotelsQueryService hotelsQueryService) {

        this.holidaysQueryService = holidaysQueryService;
        this.hotelsQueryService = hotelsQueryService;
    }

    private HolidaysQueryService holidaysQueryService;
    private final HotelsQueryService hotelsQueryService;
}
