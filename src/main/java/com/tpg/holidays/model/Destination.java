package com.tpg.holidays.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public final class Destination {

    public Destination(@JsonProperty("name") String name, @JsonProperty("description") String description, @JsonProperty("code") String code) {

        this.name = name;
        this.description = description;
        this.code = code;
    }

    private final String name;
    private final String description;
    private final String code;
}
