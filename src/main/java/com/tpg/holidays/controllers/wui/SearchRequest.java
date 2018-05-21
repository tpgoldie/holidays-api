package com.tpg.holidays.controllers.wui;

import com.tpg.holidays.model.Child;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public final class SearchRequest {

    public SearchRequest() {}

    private String destination;
    private String checkIn;
    private String checkOut;
    private int numberOfAdults;
    private List<Child> children;
}
