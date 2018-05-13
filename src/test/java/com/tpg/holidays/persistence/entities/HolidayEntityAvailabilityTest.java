package com.tpg.holidays.persistence.entities;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.junit.Assert.*;

public class HolidayEntityAvailabilityTest extends HolidayEntityPropertyValidationTest {

    @Test
    public void validateAvailableFrom() {
        
        Set<ConstraintViolation<HolidayEntity>> violations = validator.validate(entity);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void missingAvailableFrom() {

        entity.setAvailableFrom(null);

        Set<ConstraintViolation<HolidayEntity>> violations = validator.validate(entity);
        assertFalse(violations.isEmpty());

        ConstraintViolation<HolidayEntity> violation = violations.iterator().next();

        assertEquals("may not be null", violation.getMessage());
        assertEquals("availableFrom", violation.getPropertyPath().toString());
        assertNull(violation.getInvalidValue());
    }

    @Test
    public void validateAvailableTo() {

        Set<ConstraintViolation<HolidayEntity>> violations = validator.validate(entity);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void missingAvailableTo() {

        entity.setAvailableTo(null);

        Set<ConstraintViolation<HolidayEntity>> violations = validator.validate(entity);
        assertFalse(violations.isEmpty());

        ConstraintViolation<HolidayEntity> violation = violations.iterator().next();

        assertEquals("may not be null", violation.getMessage());
        assertEquals("availableTo", violation.getPropertyPath().toString());
        assertNull(violation.getInvalidValue());
    }
}
