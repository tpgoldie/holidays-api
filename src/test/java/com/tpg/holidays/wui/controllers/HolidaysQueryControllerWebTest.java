package com.tpg.holidays.wui.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tpg.holidays.model.Child;
import com.tpg.holidays.service.HolidaysQueryService;
import com.tpg.holidays.service.HotelsQueryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HolidaysQueryController.class)
public class HolidaysQueryControllerWebTest {

    @Test
    public void shouldSearchForHolidays() throws Exception {

        SearchRequest searchRequest = new SearchRequest("Swindon", "01/08/2018", "07/08/2018",
                2, singletonList(new Child(12)), 1);

        String content = objectMapper.writeValueAsString(searchRequest);

        mockMvc.perform(get("/holidays/search")
                    .accept(APPLICATION_JSON_UTF8)
                    .content(content))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Swindon")))
                .andExpect(content().string(containsString("2")))
                .andExpect(content().string(containsString("1")))
                .andDo(print());
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private HolidaysQueryService holidaysQueryService;

    @MockBean
    private HotelsQueryService hotelsQueryService;
}
