package com.tpg.holidays.controllers;

import static org.springframework.util.Base64Utils.encodeToString;

public interface BasicAuthorizationFixture {

    default String basicAuthorization() {

        return String.format("Basic %s", encodeToString("user:secret".getBytes()));
    }
}
