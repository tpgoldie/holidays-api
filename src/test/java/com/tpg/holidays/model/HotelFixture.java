package com.tpg.holidays.model;

import java.math.BigDecimal;
import java.util.List;

public interface HotelFixture {

    default Hotel hotel(Location location, List<Room> rooms, BigDecimal price) {

        return new Hotel(location, rooms, price);
    }
}
