package com.tpg.holidays.wui;

import com.tpg.holidays.model.DateTimeFixture;
import com.tpg.holidays.wui.pages.SearchPage;

import java.time.ZonedDateTime;

public class SearchForHolidays implements DateTimeFixture {

    private SearchPage searchPage;

    public static SearchForHolidays given() {

        return new SearchForHolidays();
    }

    public SearchForHolidays when() { return this; }

    public SearchForHolidays searchPage(SearchPage page) {

        searchPage = page;

        return this;
    }

    public SearchForHolidays destination(String value) {

        searchPage.getDestinationField().fill().with(value);

        return this;
    }

    public SearchForHolidays checkInDate(ZonedDateTime value) {

        searchPage.getCheckInDateField().fill().with(value.format(DD_MM_YYYY_FORMATTER));

        return this;
    }

    public SearchForHolidays checkOutDate(ZonedDateTime value) {

        searchPage.getCheckOutDateField().fill().with(value.format(DD_MM_YYYY_FORMATTER));

        return this;
    }

    public SearchForHolidays numberOfAdults(int value) {

        searchPage.getNumberOfAdultsField().fill().with(Integer.toString(value));

        return this;
    }

    public SearchForHolidays numberOfChildren(int value) {

        searchPage.getNumberOfChildrenField().fill().with(Integer.toString(value));

        return this;
    }

    public SearchForHolidays numberOfRooms(int value) {

        searchPage.getNumberOfRoomsField().fill().with(Integer.toString(value));

        return this;
    }

    public SearchForHolidays then() { return this; }
}
