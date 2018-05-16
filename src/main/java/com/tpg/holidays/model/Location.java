package com.tpg.holidays.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class Location {
    private final Address address;
    private final String mapLink;
}
