package com.tpg.holidays.wui;

import com.tpg.holidays.model.DateTimeFixture;
import com.tpg.holidays.wui.components.Table;
import com.tpg.holidays.wui.pages.SearchPage;
import org.fluentlenium.core.domain.FluentWebElement;
import org.fluentlenium.core.wait.FluentWait;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchForHolidays implements DateTimeFixture {

    public static SearchForHolidays given() {

        return new SearchForHolidays();
    }

    public SearchForHolidays when() { return this; }

    public SearchForHolidays searchPage(SearchPage page) {

        searchPage = page;

        return this;
    }

    public SearchForHolidays destination(String value) {

        searchPage.destination(value);

        return this;
    }

    public SearchForHolidays checkInDate(ZonedDateTime value) {

        searchPage.checkInDate(value);

        return this;
    }

    public SearchForHolidays checkOutDate(ZonedDateTime value) {

        searchPage.checkOutDate(value);

        return this;
    }

    public SearchForHolidays numberOfAdults(int value) {

        searchPage.numberOfAdults(value);

        return this;
    }

    public SearchForHolidays numberOfChildren(int value) {

        searchPage.numberOfChildren(value);

        return this;
    }

    public SearchForHolidays numberOfRooms(int value) {

        searchPage.numberOfRooms(value);

        return this;
    }

    public SearchForHolidays searchForHolidays(FluentWait await, int length, TimeUnit timeUnit,
                                               FluentWebElement searchResultsComponent) {

        searchPage.search();

        await.atMost(length, timeUnit).until(searchResultsComponent).displayed();

        searchResultsTable = new Table(searchResultsComponent);

        return this;
    }

    public SearchForHolidays then() { return this; }

    public SearchForHolidays receiveSearchResults() {


        return this;
    }

    private SearchPage searchPage;
    private Table searchResultsTable;
}
