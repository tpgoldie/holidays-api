package com.tpg.holidays.wui.pages;

import com.tpg.holidays.model.DateTimeSpecification;
import com.tpg.holidays.wui.components.Button;
import com.tpg.holidays.wui.components.TextField;
import lombok.Getter;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.PageUrl;

import java.time.ZonedDateTime;

@PageUrl("http://localhost")
@Getter
public class SearchPage extends FluentPage implements DateTimeSpecification {

    public SearchPage() {

        destinationField = new TextField($("destination_input_field").first());
        checkInDateField = new TextField($("checkin_input_field").first());
        checkOutDateField = new TextField($("checkout_input_field").first());
        numberOfAdultsField = new TextField($("number_of_adults_input_field").first());
        numberOfChildrenField = new TextField($("number_of_children_input_field").first());
        numberOfRoomsField = new TextField($("number_of_rooms_input_field").first());

        searchButton = new Button($("search_holidays_button").first());
    }

    public SearchPage destination(String value) {

        getDestinationField().enterText(value);

        return this;
    }

    public SearchPage checkInDate(ZonedDateTime value) {

        getCheckInDateField().enterText(value.format(DD_MM_YYYY_FORMATTER));

        return this;
    }

    public SearchPage checkOutDate(ZonedDateTime value) {

        getCheckOutDateField().enterText(value.format(DD_MM_YYYY_FORMATTER));

        return this;
    }

    public SearchPage numberOfAdults(int value) {

        enterValue(getNumberOfAdultsField(), value);

        return this;
    }

    private SearchPage enterValue(TextField textField, int value) {

        textField.enterText(Integer.toString(value));

        return this;
    }

    public SearchPage numberOfChildren(int value) {

        enterValue(getNumberOfChildrenField(), value);

        return this;
    }

    public SearchPage numberOfRooms(int value) {

        enterValue(getNumberOfRoomsField(), value);

        return this;
    }

    public SearchPage search() {

        getSearchButton().submit();

        return this;
    }

    private final TextField destinationField;

    private final TextField checkInDateField;

    private final TextField checkOutDateField;

    private final TextField numberOfAdultsField;

    private final TextField numberOfChildrenField;

    private final TextField numberOfRoomsField;

    private final Button searchButton;
}
