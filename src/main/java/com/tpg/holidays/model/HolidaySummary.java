package com.tpg.holidays.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class HolidaySummary {

    private final Holiday holiday;
    private final Hotel hotel;
}
