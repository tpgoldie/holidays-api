package com.tpg.holidays.controllers;

import ai.grakn.redismock.RedisServer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.tpg.holidays.controllers.wui.SearchRequest;
import com.tpg.holidays.model.*;
import com.tpg.holidays.rest.RestApiIT;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static ai.grakn.redismock.RedisServer.newRedisServer;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.tpg.holidays.model.Room.RoomType.SINGLE;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class HolidaysQueryControllerIT extends RestApiIT implements SearchRequestFixture, DateTimeSpecification,
        AddressFixture, HotelFixture, HolidayFixture, DestinationFixture {

    @EnableRedisHttpSession
    @TestConfiguration
    static class Config {

        @Bean
        public RedisConnectionFactory connectionFactory() {

            redisConnectionFactory = mock(RedisConnectionFactory.class);

            when(redisConnectionFactory.getConnection()).thenReturn(redisConnection);

            return redisConnectionFactory;
        }

        @Bean
        public HttpSessionIdResolver httpSessionIdResolver() {
            return HeaderHttpSessionIdResolver.xAuthToken();
        }

        @MockBean
        private RedisSerializer redisSerializer;

        private RedisConnectionFactory redisConnectionFactory;

        @MockBean
        private RedisConnection redisConnection;
    }

    @Before
    public void setUp() throws IOException {

        super.setUp();

        redisServer = newRedisServer(6379);

        redisServer.start();

        jedis = new Jedis(redisServer.getHost(), redisServer.getBindPort());

        jedis.flushAll();
    }

    @After
    public void tearDown() {

        jedis.close();

        redisServer.stop();
    }

    @Test
    public void search() throws JsonProcessingException {

        SearchRequest searchRequest = searchRequest("Swindon", "01/08/2018", "07/08/2018",
                2, singletonList(12), 1);

        stubHotelsApiCall(searchRequest);

        HttpEntity<SearchRequest> entity = new HttpEntity<>(searchRequest, headers);

        ResponseEntity<HolidaySummaries> actual = restTemplate.exchange(
                createURLWithPort("/holidays/search", port),
                POST,
                entity,
                HolidaySummaries.class);

        assertEquals(200, actual.getStatusCodeValue());
        assertThat(actual.getBody().getSummaries()).hasSize(1);
    }

    private void stubHotelsApiCall(SearchRequest searchRequest) throws JsonProcessingException {

        String requestBody = objectMapper.writeValueAsString(searchRequest);
        String body = objectMapper.writeValueAsString(buildHolidaySummaries());

        stubFor(post(urlEqualTo("/hotels/search"))
                .withHeader("Accept", equalTo(APPLICATION_JSON_VALUE))
                .withRequestBody(equalToJson(requestBody))
                    .willReturn(aResponse()
                                    .withStatus(200)
                                    .withHeader("Content-Type", APPLICATION_JSON_VALUE)
                                    .withBody(body)));
    }

    private HolidaySummaries buildHolidaySummaries() {

        Holiday holiday = buildHoliday();
        Hotel hotel = buildHotel();

        return new HolidaySummaries(singletonList(new HolidaySummary(holiday, hotel)));
    }

    private Holiday buildHoliday() {

        Destination destination = destination("London", "LDN", "City of London");

        ZonedDateTime checkIn = toZonedDateTime("23/12/2018");
        ZonedDateTime checkOut = toZonedDateTime("26/12/2018");

        return holiday("Britannia Hotel City Centre", "Stay in Manchester", destination, checkIn,
                checkOut, 2, 1, singletonList(13));
    }

    private Hotel buildHotel() {

        Address address = address("The Glades", "123 High Street", "Croydon", "Surrey", "CR0 9DX");

        Location location = new Location(address, "/abc/123");

        Room room = new Room(SINGLE, 2, 1);

        return hotel(location, singletonList(room), new BigDecimal(125.00));
    }

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    private RedisServer redisServer;

    private Jedis jedis;
}
