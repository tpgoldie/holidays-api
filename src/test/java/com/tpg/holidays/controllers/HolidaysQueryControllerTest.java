package com.tpg.holidays.controllers;

import com.tpg.holidays.controllers.wui.SearchRequest;
import com.tpg.holidays.model.DateTimeFixture;
import com.tpg.holidays.service.HolidaysQueryService;
import com.tpg.holidays.service.HotelsQueryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.tpg.holidays.controllers.SearchForHolidays.given;
import static java.util.Collections.singletonList;

@RunWith(MockitoJUnitRunner.class)
public class HolidaysQueryControllerTest implements DateTimeFixture, SearchRequestFixture {

    @Test
    public void search() {

        SearchRequest searchRequest = searchRequest("Swindon", "01/08/2018", "07/08/2018",
                2, singletonList(12));

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
