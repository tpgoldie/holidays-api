package com.tpg.holidays.model;

import lombok.Getter;
import org.codehaus.jackson.annotate.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

@Getter
public final class Hotel {

    public Hotel(@JsonProperty("location") Location location,
                 @JsonProperty("rooms") List<Room> rooms,
                 @JsonProperty("price") BigDecimal price) {

        this.location = location;
        this.rooms = rooms;
        this.price = price;
    }

    private final Location location;
    private final List<Room> rooms;
    private final BigDecimal price;
}
