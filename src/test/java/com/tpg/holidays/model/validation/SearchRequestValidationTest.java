package com.tpg.holidays.model.validation;

import com.tpg.holidays.controllers.SearchRequestFixture;
import org.junit.Before;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static javax.validation.Validation.buildDefaultValidatorFactory;
import static org.assertj.core.api.Assertions.assertThat;

public abstract class SearchRequestValidationTest implements SearchRequestFixture {

    @Before
    public void setUp() {

        validatorFactory = buildDefaultValidatorFactory();

        validator = validatorFactory.getValidator();
    }

    protected <T> void assertViolation(ConstraintViolation<T> violation, String message, String property, Object value) {

        assertThat(violation.getMessage()).isEqualTo(message);
        assertThat(violation.getPropertyPath().toString()).isEqualTo(property);
        assertThat(violation.getInvalidValue()).isEqualTo(value);
    }

    private ValidatorFactory validatorFactory;
    protected Validator validator;
}
