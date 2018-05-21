package com.tpg.holidays.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class HolidaySummaries {

    @JsonCreator
    public HolidaySummaries(@JsonProperty("summaries") List<HolidaySummary> summaries) {

        this.summaries = summaries;
    }

    private final List<HolidaySummary> summaries;
}
