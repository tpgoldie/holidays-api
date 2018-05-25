package com.tpg.holidays.controllers;

import org.springframework.http.HttpHeaders;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public interface HeadersFixture {

    default HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", APPLICATION_JSON_VALUE);
        headers.add("Content-Type", APPLICATION_JSON_VALUE);
        headers.add("X-Auth-Token", "1001");

        return headers;
    }
}
