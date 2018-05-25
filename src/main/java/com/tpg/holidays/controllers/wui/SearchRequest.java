package com.tpg.holidays.controllers.wui;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tpg.holidays.model.Child;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import java.util.List;

@Getter
@EqualsAndHashCode
public final class SearchRequest {

    @JsonCreator
    public SearchRequest(@JsonProperty("destination") String destination, @JsonProperty("checkIn") String checkIn,
                         @JsonProperty("checkOut") String checkOut, @JsonProperty("numberOfAdults") int numberOfAdults,
                         @JsonProperty("children") List<Child> children, @JsonProperty("numberOfRooms") int numberOfRooms) {
        this.destination = destination;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.numberOfAdults = numberOfAdults;
        this.children = children;
        this.numberOfRooms = numberOfRooms;
    }

    @NotEmpty
    private final String destination;

    @NotEmpty
    private final String checkIn;

    @NotEmpty
    private final String checkOut;

    @Min(value = 1)
    private final int numberOfAdults;

    private final List<Child> children;
    private final int numberOfRooms;
}
