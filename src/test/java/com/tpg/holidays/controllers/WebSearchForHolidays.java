package com.tpg.holidays.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tpg.holidays.controllers.wui.SearchRequest;
import com.tpg.holidays.model.*;
import com.tpg.holidays.service.HolidayFixture;
import com.tpg.holidays.service.HolidaysQueryService;
import com.tpg.holidays.service.HotelsQueryService;
import org.mockito.Mockito;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.ZonedDateTime;
import java.util.List;

import static com.tpg.holidays.model.Room.RoomType.DOUBLE;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class WebSearchForHolidays implements DateTimeFixture, HolidayFixture, HotelFixture, BasicAuthorizationFixture {

    public static WebSearchForHolidays given() {

        return new WebSearchForHolidays();
    }

    public WebSearchForHolidays when() { return this; }

    public WebSearchForHolidays hotelsQueryService(HotelsQueryService hotelsQueryService) {

        this.hotelsQueryService = hotelsQueryService;

        return this;
    }

    public WebSearchForHolidays holidaysQueryService(HolidaysQueryService holidaysQueryService) {

        this.holidaysQueryService = holidaysQueryService;

        return this;
    }

    public WebSearchForHolidays searchRequest(SearchRequest searchRequest) {

        this.searchRequest = searchRequest;

        checkIn = toZonedDateTime(searchRequest.getCheckIn());
        checkOut = toZonedDateTime(searchRequest.getCheckOut());

        return this;
    }

    public WebSearchForHolidays searchForHolidays() throws Exception {

        List<Holiday> holidays = buildHolidays();

        Mockito.when(holidaysQueryService.searchHolidaysByCheckInAndCheckout(checkIn, checkOut)).thenReturn(holidays);

        List<Hotel> hotels = buildHotels();

        Mockito.when(hotelsQueryService.searchForHolidaysByDestination(eq(searchRequest))).thenReturn(hotels);

        String content = objectMapper.writeValueAsString(searchRequest);

        mockMvc.perform(get("/holidays/search")
                .contentType(APPLICATION_JSON_UTF8_VALUE)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.summaries", hasSize(1)))
                .andExpect(jsonPath("$.summaries[0].holiday.destination.name", is("Swindon")))
                .andExpect(jsonPath("$.summaries[0].holiday.numberOfAdults", is(2)))
                .andExpect(jsonPath("$.summaries[0].holiday.children[0].age", is(12)))
                .andExpect(jsonPath("$.summaries[0].holiday.numberOfRooms", is(1)))
                .andExpect(jsonPath("$.summaries[0].hotel.rooms[0].roomType", is(DOUBLE.name())))
                .andDo(print());

        return this;
    }

    private List<Holiday> buildHolidays() {

        Destination destination = Destination.builder().name("Swindon").code("SWD").description("Swindon Town").build();

        Child child = new Child();
        child.setAge(12);

        return singletonList(holiday("Jury Inn, Swindon", "Lovely place", destination, checkIn, checkOut,
                1, 2, singletonList(child)));
    }

    private List<Hotel> buildHotels() {

        Address address = new Address("123 High Street", "", "Swindon", "Surrey", "SW10 1XS");

        return singletonList(hotel(new Location(address, "http://abc/123"),
                singletonList(new Room(DOUBLE, 2, 1))));
    }

    public WebSearchForHolidays then() { return this; }

    public WebSearchForHolidays holidaysSearched() {

        verify(holidaysQueryService)
                .searchHolidaysByCheckInAndCheckout(
                        toZonedDateTime(searchRequest.getCheckIn()),
                        toZonedDateTime(searchRequest.getCheckOut()));

        return this;
    }

    public WebSearchForHolidays hotelsSearched() {

        verify(hotelsQueryService).searchForHolidaysByDestination(searchRequest);

        return this;
    }

    private HolidaysQueryService holidaysQueryService;
    private HotelsQueryService hotelsQueryService;
    private SearchRequest searchRequest;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private ZonedDateTime checkIn;
    private ZonedDateTime checkOut;

    public WebSearchForHolidays mockMvc(MockMvc mockMvc) {

        this.mockMvc = mockMvc;

        return this;
    }
}
