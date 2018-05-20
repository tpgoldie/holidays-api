package com.tpg.holidays.model.json;

import com.tpg.holidays.model.Destination;
import com.tpg.holidays.model.DestinationFixture;
import com.tpg.holidays.model.FacilityRating;
import org.junit.Test;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.json.ObjectContent;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DestinationTest extends JsonTest<Destination> implements DestinationFixture {

    @Test
    public void serialize() throws IOException {

        Destination value = destination("London", "LDN", "All of London");

        JsonContent<Destination> actual = jsonTester.write(value);

        assertStringValue("@.code", "LDN", actual);
        assertStringValue("@.name", "London", actual);
        assertStringValue("@.description", "All of London", actual);
    }

    @Test
    public void deserialize() throws IOException {

        String content = "{\"description\":\"All of London\",\"name\":\"London\", \"code\":\"LDN\"}";

        ObjectContent<Destination> actual = jsonTester.parse(content);

        Map<String, String> mapping = new LinkedHashMap<>();

        mapping.put("description", "All of London");
        mapping.put("name", "London");
        mapping.put("code", "LDN");

        assertThat(actual.getObject()).isEqualTo(mapping);
    }
}
