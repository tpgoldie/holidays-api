package com.tpg.holidays.model.validation;

import com.tpg.holidays.controllers.wui.SearchRequest;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class InvalidDestinationTest extends SearchRequestValidationTest {

    @Test
    public void shouldDetectNullValue() {

        SearchRequest searchRequest = searchRequest(null, "23/12/2018", "26/12/2018", 2,
                singletonList(8), 1);

        Set<ConstraintViolation<SearchRequest>> violations = validator.validate(searchRequest);

        assertThat(violations).hasSize(1);

        ConstraintViolation<SearchRequest> violation = violations.iterator().next();

        assertViolation(violation,"may not be empty", "destination", null);
    }

    @Test
    public void shouldDetectEmptyValue() {

        SearchRequest searchRequest = searchRequest("", "23/12/2018", "26/12/2018", 2,
                singletonList(8), 1);

        Set<ConstraintViolation<SearchRequest>> violations = validator.validate(searchRequest);

        assertThat(violations).hasSize(1);

        ConstraintViolation<SearchRequest> violation = violations.iterator().next();

        assertViolation(violation, "may not be empty", "destination", "");
    }
}
