package com.tpg.holidays.model;

import java.util.List;

public interface HotelFixture {

    default Hotel hotel(Location location, List<Room> rooms) {

        return new Hotel(location, rooms);
    }
}
