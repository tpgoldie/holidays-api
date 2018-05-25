package com.tpg.holidays.wui.components;

import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;

public final class TextField extends WebComponent {

    public void enterText(String value) {

        webElement.fill().with(value);
    }

    public TextField(FluentWebElement webElement) {

        super(webElement);
    }
}
