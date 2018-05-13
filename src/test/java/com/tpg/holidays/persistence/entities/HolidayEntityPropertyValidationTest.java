package com.tpg.holidays.persistence.entities;

import org.junit.Before;

import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static javax.validation.Validation.buildDefaultValidatorFactory;

public abstract class HolidayEntityPropertyValidationTest {

    @Before
    public void setUp() {

        ValidatorFactory factory = buildDefaultValidatorFactory();
        validator = factory.getValidator();

        entity = new HolidayEntity();

        entity.setTitle("Hello");
        entity.setDescription("Hello");
    }

    Validator validator;
    HolidayEntity entity;
}
