package com.tpg.holidays.wui.controllers;

import com.tpg.holidays.model.*;
import com.tpg.holidays.service.HolidaysQueryService;
import com.tpg.holidays.service.HotelsQueryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.tpg.holidays.model.HolidayAssertion.assertThat;
import static com.tpg.holidays.wui.controllers.SearchForHolidays.given;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpStatus.OK;

@RunWith(MockitoJUnitRunner.class)
public class HolidaysQueryControllerTest implements DateTimeFixture {

    @Before
    public void setUp() {
    }

    @Test
    public void search() {

        SearchRequest searchRequest = new SearchRequest("Swindon", "01/08/2018", "07/08/2018",
                2, singletonList(new Child(12)), 1);

        given()
            .hotelsQueryService(hotelsQueryService)
            .holidaysQueryService(holidaysQueryService)
                .holidaysQueryController(controller)
            .searchRequest(searchRequest)
        .when()
            .searchForHolidays()
        .then()
            .holidaysSearched()
            .hotelsSearched()
            .holidaysFound();
    }

    @Mock
    private HolidaysQueryService holidaysQueryService;

    @Mock
    private HotelsQueryService hotelsQueryService;

    @InjectMocks
    private HolidaysQueryController controller;
}
