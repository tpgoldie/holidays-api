package com.tpg.holidays.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public final class Address {

    @Override
    public String toString() {

        return String.format("%s, %s, %s, %s, %s", lineOne, lineTwo, municipal, state, postCode);
    }

    private final String lineOne;
    private final String lineTwo;
    private final String municipal;
    private final String state;
    private final String postCode;
}
