package com.tpg.holidays.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public final class Destination {

    private final String name;
    private final String description;
    private final String code;
}
