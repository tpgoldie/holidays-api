package com.tpg.holidays.persistence.entities;

import org.junit.Before;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.time.ZonedDateTime;

import static javax.validation.Validation.buildDefaultValidatorFactory;
import static org.junit.Assert.assertEquals;

public abstract class HolidayEntityPropertyValidationTest implements DestinationEntityFixture {

    @Before
    public void setUp() {

        ValidatorFactory factory = buildDefaultValidatorFactory();
        validator = factory.getValidator();

        entity = new HolidayEntity();

        entity.setTitle("Hello");
        entity.setDescription("Hello");
        entity.setAvailableFrom(ZonedDateTime.now());
        entity.setAvailableTo(ZonedDateTime.now());
        entity.setDestination(destinationEntity("SWD", "Swindon", "Swindon"));
    }

    <T> void assertPropertyViolation(String property, String message, ConstraintViolation<T> violation) {

        assertEquals(message, violation.getMessage());
        assertEquals(property, violation.getPropertyPath().toString());
    }

    Validator validator;
    HolidayEntity entity;
}
