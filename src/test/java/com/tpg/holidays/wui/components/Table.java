package com.tpg.holidays.wui.components;

import org.fluentlenium.core.domain.FluentWebElement;

import java.util.Optional;

import static java.util.Optional.empty;

public class Table extends WebComponent {

    public Table(FluentWebElement webElement) {

        super(webElement);
    }

    public Optional<FluentWebElement> getRow(int rowIndex) {

        return empty();
    }
}
