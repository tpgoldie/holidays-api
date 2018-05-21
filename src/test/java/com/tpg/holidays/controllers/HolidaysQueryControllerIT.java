package com.tpg.holidays.controllers;

import com.tpg.holidays.HolidaysApplication;
import com.tpg.holidays.context.PersistenceConfig;
import com.tpg.holidays.context.WebApplicationConfig;
import com.tpg.holidays.context.WebSecurityConfig;
import com.tpg.holidays.controllers.wui.SearchRequest;
import com.tpg.holidays.model.HolidaySummaries;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpMethod.GET;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = { HolidaysApplication.class, WebApplicationConfig.class, WebSecurityConfig.class, PersistenceConfig.class},
        properties = {"spring.session.store-type=none"})
public class HolidaysQueryControllerIT implements SearchRequestFixture {

    @Test
    public void search() {

        SearchRequest searchRequest = searchRequest("Swindon", "01/08/2018", "07/08/2018",
                2, singletonList(12));

        HttpEntity<SearchRequest> entity = new HttpEntity<>(searchRequest, headers);

        ResponseEntity<HolidaySummaries> actual = restTemplate.exchange(
                createURLWithPort("/holidays/search", port),
                GET,
                entity,
                HolidaySummaries.class);

        assertThat(actual.getBody().getSummaries()).hasSize(1);
    }

    private static String createURLWithPort(String uri, int port) {
        return "http://localhost:" + port + uri;
    }

    @LocalServerPort
    private int port;

    private HttpHeaders headers = new HttpHeaders();

    private TestRestTemplate restTemplate = new TestRestTemplate();
}
