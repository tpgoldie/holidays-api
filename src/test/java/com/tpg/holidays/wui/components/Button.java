package com.tpg.holidays.wui.components;

import org.fluentlenium.core.domain.FluentWebElement;

public final class Button extends WebComponent {

    public Button(FluentWebElement webElement) {

        super(webElement);
    }

    public void submit() {

        webElement.submit();
    }
}
