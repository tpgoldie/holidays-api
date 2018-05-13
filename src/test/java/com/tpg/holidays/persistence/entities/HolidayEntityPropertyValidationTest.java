package com.tpg.holidays.persistence.entities;

import org.junit.Before;

import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.time.ZonedDateTime;

import static javax.validation.Validation.buildDefaultValidatorFactory;

public abstract class HolidayEntityPropertyValidationTest {

    @Before
    public void setUp() {

        ValidatorFactory factory = buildDefaultValidatorFactory();
        validator = factory.getValidator();

        entity = new HolidayEntity();

        entity.setTitle("Hello");
        entity.setDescription("Hello");
        entity.setAvailableFrom(ZonedDateTime.now());
        entity.setAvailableTo(ZonedDateTime.now());
    }

    Validator validator;
    HolidayEntity entity;
}
