package com.tpg.holidays.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public final class Hotel {

    private final Location location;
    private final List<Room> rooms;
}
