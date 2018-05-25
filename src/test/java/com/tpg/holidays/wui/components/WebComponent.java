package com.tpg.holidays.wui.components;

import org.fluentlenium.core.domain.FluentWebElement;

public abstract class WebComponent {

    protected WebComponent(FluentWebElement webElement) {

        this.webElement = webElement;
    }

    protected final FluentWebElement webElement;
}
