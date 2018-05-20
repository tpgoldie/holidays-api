package com.tpg.holidays.model.json;

import com.tpg.holidays.model.FacilityRating;
import org.junit.Test;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.json.ObjectContent;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class FacilityRatingTest extends JsonTest<FacilityRating> {

    @Test
    public void serialize() throws IOException {

        FacilityRating rating = new FacilityRating("Staff", new BigDecimal(6.5));

        JsonContent<FacilityRating> actual = jsonTester.write(rating);

        assertStringValue("@.description", "Staff", actual);
        assertNumberValue("@.rating", 6.5, actual);
    }

    @Test
    public void deserialize() throws IOException {

        String content = "{\"description\":\"Staff\",\"rating\":6.5}";

        ObjectContent<FacilityRating> actual = jsonTester.parse(content);

        Map<String, Object> mapping = new LinkedHashMap<>();
        mapping.put("description", "Staff");
        mapping.put("rating", 6.5);

        assertThat(actual.getObject()).isEqualTo(mapping);

    }
}
