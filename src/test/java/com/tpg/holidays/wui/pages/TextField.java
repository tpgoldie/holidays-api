package com.tpg.holidays.wui.pages;

import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;

public abstract class TextField {

    public void enterText(String value) {

        webElement.fill().with(value);
    }

    protected TextField(String id) {
        this.webElement = By.id(id);
    }

    protected final FluentWebElement webElement;
}
