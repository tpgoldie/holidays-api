package com.tpg.holidays.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public final class Review {

    public Review(String userId, String comments, List<FacilityRating> facilityRatings) {

        this.userId = userId;
        this.comments = comments;
        this.facilityRatings = facilityRatings;
        this.overallRating = calculateOverallRating();
    }

    public BigDecimal getOverallRating() {

        return overallRating;
    }

    private BigDecimal calculateOverallRating() {

        int size = facilityRatings.isEmpty() ? 1 : facilityRatings.size();

        BigDecimal sum = facilityRatings.stream().map(FacilityRating::getRating).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

        return sum.divide(new BigDecimal(size));
    }

    private final String userId;
    private final String comments;
    private final BigDecimal overallRating;
    private final List<FacilityRating> facilityRatings;
}
