package com.tpg.holidays.controllers;

public interface UrlFixture {

    default String createURLWithPort(String uri, int port) {
        return String.format("http://localhost:%d/%s", port, uri);
    }
}
