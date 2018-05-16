package com.tpg.holidays.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Room {

    public enum RoomType { SINGLE, DOUBLE, TRIPLE }

    private final RoomType roomType;
    private final int numberOfAdults;
    private final int numberOfChildren;
}
