package com.tpg.holidays.model.validation;

import com.tpg.holidays.controllers.wui.SearchRequest;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class MissingNumberOfAdultsTest extends SearchRequestValidationTest {

    @Test
    public void shouldDetectZeroValue() {

        SearchRequest searchRequest = searchRequest("London", "24/12/2018", "26/12/2018", 0,
                singletonList(8), 1);

        Set<ConstraintViolation<SearchRequest>> violations = validator.validate(searchRequest);

        assertThat(violations).hasSize(1);

        ConstraintViolation<SearchRequest> violation = violations.iterator().next();

        assertViolation(violation, "must be greater than or equal to 1", "numberOfAdults", 0);
    }
}
