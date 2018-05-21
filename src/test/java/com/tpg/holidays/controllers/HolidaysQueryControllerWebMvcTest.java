package com.tpg.holidays.controllers;

import com.tpg.holidays.context.WebApplicationConfig;
import com.tpg.holidays.context.WebSecurityConfig;
import com.tpg.holidays.controllers.wui.SearchRequest;
import com.tpg.holidays.service.HolidaysQueryService;
import com.tpg.holidays.service.HotelsQueryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.tpg.holidays.controllers.WebSearchForHolidays.given;
import static java.util.Collections.singletonList;

@RunWith(SpringRunner.class)
@WebMvcTest(HolidaysQueryController.class)
@ActiveProfiles({"dev"})
@Profile({"dev"})
@ContextConfiguration(classes = { WebApplicationConfig.class, WebSecurityConfig.class })
public class HolidaysQueryControllerWebMvcTest implements SearchRequestFixture {

    @Test
    public void shouldSearchForHolidays() throws Exception {

        SearchRequest searchRequest = searchRequest("Swindon", "01/08/2018", "07/08/2018",
                2, singletonList(12));

        given()
            .holidaysQueryService(holidaysQueryService)
            .hotelsQueryService(hotelsQueryService)
            .searchRequest(searchRequest)
            .mockMvc(mockMvc)
        .when()
            .searchForHolidays()
        .then()
            .holidaysSearched()
            .hotelsSearched();
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HolidaysQueryService holidaysQueryService;

    @MockBean
    private HotelsQueryService hotelsQueryService;
}
