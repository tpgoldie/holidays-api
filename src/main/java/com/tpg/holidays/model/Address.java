package com.tpg.holidays.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class Address {

    private final String lineOne;
    private final String lineTwo;
    private final String municipal;
    private final String state;
    private final String postCode;
}
