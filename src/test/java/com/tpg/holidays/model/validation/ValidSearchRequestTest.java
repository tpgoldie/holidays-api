package com.tpg.holidays.model.validation;

import com.tpg.holidays.controllers.wui.SearchRequest;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class ValidSearchRequestTest extends SearchRequestValidationTest {

    @Test
    public void noViolations() {

        SearchRequest searchRequest = searchRequest("London", "23/12/2018", "26/12/2018", 2,
                singletonList(8), 1);

        Set<ConstraintViolation<SearchRequest>> violations = validator.validate(searchRequest);

        assertTrue(violations.isEmpty());
    }
}
