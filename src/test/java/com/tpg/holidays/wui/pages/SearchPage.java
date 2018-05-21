package com.tpg.holidays.wui.pages;

import lombok.Getter;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.PageUrl;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

@PageUrl("http://localhost")
@Getter
public class SearchPage extends FluentPage {

    @FindBy(id = "destination_input_field")
    private FluentWebElement destinationField;

    @FindBy(id = "checkin_input_field")
    private FluentWebElement checkInDateField;

    @FindBy(id = "checkout_input_field")
    private FluentWebElement checkOutDateField;

    @FindBy(id = "number_of_adults_input_field")
    private FluentWebElement numberOfAdultsField;

    @FindBy(id = "number_of_children_input_field")
    private FluentWebElement numberOfChildrenField;

    @FindBy(id = "number_of_rooms_input_field")
    private FluentWebElement numberOfRoomsField;
}
