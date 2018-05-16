package com.tpg.holidays.wui.controllers;

import com.tpg.holidays.model.Child;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public final class SearchRequest {

    private final String destination;
    private final String checkIn;
    private final String checkOut;
    private final int numberOfAdults;
    private final List<Child> children;
    private final int numberOfRooms;
}
