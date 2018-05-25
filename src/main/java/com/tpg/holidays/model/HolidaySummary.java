package com.tpg.holidays.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public final class HolidaySummary {

    @JsonCreator
    public HolidaySummary(@JsonProperty("holiday") Holiday holiday, @JsonProperty("hotel") Hotel hotel) {

        this.holiday = holiday;
        this.hotel = hotel;
    }

    private final Holiday holiday;
    private final Hotel hotel;
}
