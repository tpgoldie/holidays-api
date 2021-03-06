package com.tpg.holidays.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public interface DateTimeFixture {

    DateTimeFormatter DD_MM_YYYY_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    ZonedDateTime NOW = ZonedDateTime.now();

    default ZonedDateTime toZonedDateTime(String value) {

        return LocalDate.parse(value, DD_MM_YYYY_FORMATTER).atStartOfDay(ZoneId.systemDefault());
    }
}
