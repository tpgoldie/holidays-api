package com.tpg.holidays.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tpg.holidays.controllers.SearchRequestFixture;
import com.tpg.holidays.controllers.wui.SearchRequest;
import com.tpg.holidays.model.*;
import com.tpg.holidays.rest.RestApiIT;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.tpg.holidays.model.Room.RoomType.DOUBLE;
import static java.util.Collections.singletonList;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class HotelsQueryServiceImplIT extends RestApiIT implements SearchRequestFixture, AddressFixture, HotelFixture {

    @Before
    public void setUp() throws IOException {

        super.setUp();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("hotels-api", "aabbcc235");

        restTemplate = new TestRestTemplate((String) token.getPrincipal(), (String) token.getCredentials());

        restTemplateBuilder = new RestTemplateBuilder();
        restTemplateBuilder.basicAuthorization((String) token.getPrincipal(), (String) token.getCredentials());

        hotelsQueryServiceApiImpl = new HotelsQueryServiceApiImpl(restTemplateBuilder);
    }

    @Test
    public void search() throws JsonProcessingException {

        SearchRequest searchRequest = searchRequest("Swindon", "01/08/2018", "07/08/2018",
                2, singletonList(12), 1);

        stubHotelsApiCall(searchRequest);

        HttpEntity<SearchRequest> entity = new HttpEntity<>(searchRequest, headers);

        ResponseEntity<HotelSummaries> actual = restTemplate.exchange(
                createURLWithPort("/hotels/search", port),
                POST,
                entity,
                HotelSummaries.class);
    }

    private void stubHotelsApiCall(SearchRequest searchRequest) throws JsonProcessingException {

        String requestBody = objectMapper.writeValueAsString(searchRequest);
        String body = objectMapper.writeValueAsString(buildHotels());

        stubFor(post(urlEqualTo("/hotels/search"))
                .withHeader("Accept", equalTo(APPLICATION_JSON_VALUE))
                .withRequestBody(equalToJson(requestBody))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", APPLICATION_JSON_VALUE)
                        .withBody(body)));
    }

    private List<Hotel> buildHotels() {

        Address address = address("The Grand", "123 Great Bear Street", "Manchester", "Greater Manchester",
                "MN10 2DD");

        Location location = new Location(address, "/abc/123");

        Room room = new Room(DOUBLE, 2, 1);

        return singletonList(hotel(location, singletonList(room), new BigDecimal(97.50)));
    }

    private HotelsQueryServiceApiImpl hotelsQueryServiceApiImpl;

    private RestTemplateBuilder restTemplateBuilder;
}
