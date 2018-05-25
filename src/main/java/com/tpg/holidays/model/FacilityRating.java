package com.tpg.holidays.model;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public final class FacilityRating {

    public FacilityRating(String description, BigDecimal rating) {

        this.rating = rating;
        this.description = description;
    }

    private final String description;
    private final BigDecimal rating;
}
