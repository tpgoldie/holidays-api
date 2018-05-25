package com.tpg.holidays.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tpg.holidays.HolidaysApplication;
import com.tpg.holidays.context.PersistenceConfig;
import com.tpg.holidays.context.WebApplicationConfig;
import com.tpg.holidays.context.WebSecurityConfig;
import com.tpg.holidays.controllers.HeadersFixture;
import com.tpg.holidays.controllers.UrlFixture;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = { HolidaysApplication.class, WebApplicationConfig.class,
        WebSecurityConfig.class, PersistenceConfig.class},
        properties = {"spring.session.store-type=redis", "spring.redis.host=localhost"})
public abstract class RestApiIT implements HeadersFixture, UrlFixture {

    @Before
    public void setUp() throws IOException {

        headers = headers();

        restTemplate = new TestRestTemplate();

        objectMapper = new ObjectMapper();
    }

    @LocalServerPort
    protected int port;

    protected HttpHeaders headers;

    protected TestRestTemplate restTemplate;

    protected ObjectMapper objectMapper;
}
