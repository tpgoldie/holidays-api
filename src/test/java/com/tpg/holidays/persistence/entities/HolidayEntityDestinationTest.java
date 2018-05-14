package com.tpg.holidays.persistence.entities;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.junit.Assert.*;

public class HolidayEntityDestinationTest extends HolidayEntityPropertyValidationTest {

    @Test
    public void validateDestination() {
        
        Set<ConstraintViolation<HolidayEntity>> violations = validator.validate(entity);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void missingDestination() {

        entity.setDestination(null);

        Set<ConstraintViolation<HolidayEntity>> violations = validator.validate(entity);
        assertFalse(violations.isEmpty());

        ConstraintViolation<HolidayEntity> violation = violations.iterator().next();

        assertPropertyViolation("destination", "may not be null", violation);
        assertNull(violation.getInvalidValue());
    }
}
