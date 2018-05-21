package com.tpg.holidays.controllers;

import com.tpg.holidays.controllers.wui.SearchRequest;
import com.tpg.holidays.model.Child;

import java.util.List;

import static java.util.stream.Collectors.toList;

public interface SearchRequestFixture {

    default SearchRequest searchRequest(String destination, String checkIn, String checkOut, int numberOfAdults,
                                        List<Integer> ages) {

        SearchRequest searchRequest = new SearchRequest();

        searchRequest.setDestination(destination);
        searchRequest.setCheckIn(checkIn);
        searchRequest.setCheckOut(checkOut);
        searchRequest.setNumberOfAdults(numberOfAdults);

        List<Child> children = ages.stream().map(i -> {
            Child child = new Child();
            child.setAge(i);

            return child;
        }).collect(toList());

        searchRequest.setChildren(children);

        return searchRequest;
    }
}
