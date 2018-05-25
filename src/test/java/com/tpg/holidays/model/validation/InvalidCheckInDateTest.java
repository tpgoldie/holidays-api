package com.tpg.holidays.model.validation;

import com.tpg.holidays.controllers.wui.SearchRequest;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class InvalidCheckInDateTest extends SearchRequestValidationTest {

    @Test
    public void shouldDetectNullValue() {

        SearchRequest searchRequest = searchRequest("London", null, "26/12/2018", 2,
                singletonList(8), 1);

        Set<ConstraintViolation<SearchRequest>> violations = validator.validate(searchRequest);

        assertThat(violations).hasSize(1);

        ConstraintViolation<SearchRequest> violation = violations.iterator().next();

        assertViolation(violation, "may not be empty", "checkIn", null);
    }

    @Test
    public void shouldDetectEmptyValue() {

        SearchRequest searchRequest = searchRequest("London", "", "26/12/2018", 2,
                singletonList(8), 1);

        Set<ConstraintViolation<SearchRequest>> violations = validator.validate(searchRequest);

        assertThat(violations).hasSize(1);

        ConstraintViolation<SearchRequest> violation = violations.iterator().next();

        assertViolation(violation, "may not be empty", "checkIn", "");
    }
}
