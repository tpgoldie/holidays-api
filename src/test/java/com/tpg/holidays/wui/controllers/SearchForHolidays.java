package com.tpg.holidays.wui.controllers;

import com.tpg.holidays.model.*;
import com.tpg.holidays.service.HolidayFixture;
import com.tpg.holidays.service.HolidaysQueryService;
import com.tpg.holidays.service.HotelsQueryService;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;
import java.util.List;

import static com.tpg.holidays.model.Room.RoomType.DOUBLE;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

public class SearchForHolidays implements DateTimeFixture, HolidayFixture, HotelFixture {

    public static SearchForHolidays given() {

        return new SearchForHolidays();
    }

    public SearchForHolidays when() { return this; }

    public SearchForHolidays hotelsQueryService(HotelsQueryService hotelsQueryService) {

        this.hotelsQueryService = hotelsQueryService;

        return this;
    }

    public SearchForHolidays holidaysQueryService(HolidaysQueryService holidaysQueryService) {

        this.holidaysQueryService = holidaysQueryService;

        return this;
    }

    public SearchForHolidays holidaysQueryController(HolidaysQueryController holidaysQueryController) {

        this.holidaysQueryController = holidaysQueryController;

        return this;
    }

    public SearchForHolidays searchRequest(SearchRequest searchRequest) {

        this.searchRequest = searchRequest;

        checkIn = toZonedDateTime(searchRequest.getCheckIn());
        checkOut = toZonedDateTime(searchRequest.getCheckOut());

        return this;
    }

    public SearchForHolidays searchForHolidays() {

        List<Holiday> holidays = buildHolidays();

        Mockito.when(holidaysQueryService.searchHolidaysByCheckInAndCheckout(checkIn, checkOut)).thenReturn(holidays);

        List<Hotel> hotels = buildHotels();

        Mockito.when(hotelsQueryService.searchForHolidaysByDestination(searchRequest)).thenReturn(hotels);

        actual = holidaysQueryController.search(searchRequest);

        return this;
    }

    private List<Holiday> buildHolidays() {
        Destination destination = Destination.builder().name("Swindoni").code("SWD").description("Swindon Town").build();

        return singletonList(holiday("Jury Inn, Swindon", "Lovely place", destination, checkIn, checkOut));
    }

    private List<Hotel> buildHotels() {
        Address address = new Address("123 High Street", "", "Swindon", "Surrey", "SW10 1XS");

        return singletonList(hotel(new Location(address, "http://abc/123"),
                singletonList(new Room(DOUBLE, 2, 1))));
    }

    public SearchForHolidays then() { return this; }

    public SearchForHolidays holidaysSearched() {

        verify(holidaysQueryService)
                .searchHolidaysByCheckInAndCheckout(
                        toZonedDateTime(searchRequest.getCheckIn()),
                        toZonedDateTime(searchRequest.getCheckOut()));

        return this;
    }

    public SearchForHolidays hotelsSearched() {

        verify(hotelsQueryService).searchForHolidaysByDestination(searchRequest);

        return this;
    }

    public SearchForHolidays holidaysFound() {

        assertEquals(1, actual.getBody().size());

        return this;
    }

    private HolidaysQueryController holidaysQueryController;
    private HolidaysQueryService holidaysQueryService;
    private HotelsQueryService hotelsQueryService;
    private SearchRequest searchRequest;

    private ZonedDateTime checkIn;
    private ZonedDateTime checkOut;

    private ResponseEntity<List<HolidaySummary>> actual;
}
