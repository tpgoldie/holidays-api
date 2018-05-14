package com.tpg.holidays.persistence.entities;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.junit.Assert.*;

public class HolidayEntityDescriptionTest extends HolidayEntityPropertyValidationTest {

    @Test
    public void validateDescription() {
        
        Set<ConstraintViolation<HolidayEntity>> violations = validator.validate(entity);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void missingDescription() {

        entity.setDescription("");

        Set<ConstraintViolation<HolidayEntity>> violations = validator.validate(entity);
        assertFalse(violations.isEmpty());

        ConstraintViolation<HolidayEntity> violation = violations.iterator().next();

        assertPropertyViolation("description", "may not be empty", violation);
        assertEquals("", violation.getInvalidValue());
    }
}
