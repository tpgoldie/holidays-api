package com.tpg.holidays.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class HotelSummaries {

    @JsonCreator
    public HotelSummaries(@JsonProperty("summaries") List<Hotel> summaries) {

        this.summaries = summaries;
    }

    private final List<Hotel> summaries;
}
