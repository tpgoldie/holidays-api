package com.tpg.holidays.model;

public interface DestinationFixture {

    default Destination destination(String name, String code, String description) {

        return Destination.builder()
                .code(code)
                .name(name)
                .description(description)
                .build();
    }
}
