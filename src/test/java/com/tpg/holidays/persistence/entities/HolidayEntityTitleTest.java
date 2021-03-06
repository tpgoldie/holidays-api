package com.tpg.holidays.persistence.entities;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.junit.Assert.*;

public class HolidayEntityTitleTest extends HolidayEntityPropertyValidationTest {

    @Test
    public void validateTitle() {

        Set<ConstraintViolation<HolidayEntity>> violations = validator.validate(entity);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void missingTitle() {

        entity.setTitle("");

        Set<ConstraintViolation<HolidayEntity>> violations = validator.validate(entity);
        assertFalse(violations.isEmpty());

        ConstraintViolation<HolidayEntity> violation = violations.iterator().next();

        assertPropertyViolation("title", "may not be empty", violation);
        assertEquals("", violation.getInvalidValue());
    }
}
