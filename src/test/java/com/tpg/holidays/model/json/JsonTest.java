package com.tpg.holidays.model.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tpg.holidays.model.FacilityRating;
import org.junit.Before;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.json.JacksonTester.initFields;

public abstract class JsonTest<T> {

    @Before
    public void setUp() {

        initFields(this, new ObjectMapper());
    }

    protected void assertStringValue(String key, String value, JsonContent<T> actual) {

        assertThat(actual)
                .extractingJsonPathStringValue(key).isEqualTo(value);

    }

    protected void assertNumberValue(String key, Object value, JsonContent<T> actual) {

        assertThat(actual)
                .extractingJsonPathNumberValue(key).isEqualTo(value);
    }

    protected JacksonTester<T> jsonTester;
}
