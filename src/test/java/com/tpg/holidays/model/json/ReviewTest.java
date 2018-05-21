package com.tpg.holidays.model.json;

import com.tpg.holidays.model.FacilityRating;
import com.tpg.holidays.model.Review;
import org.junit.Test;
import org.springframework.boot.test.json.JsonContent;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class ReviewTest extends JsonTest<Review> {

    @Test
    public void serialize() throws IOException {

        List<FacilityRating> ratings = asList(
                new FacilityRating("Staff", new BigDecimal(6.5)),
                new FacilityRating("Cleanliness", new BigDecimal(7.5)));

        Review review = new Review("jdoe", "Very good", ratings);

        JsonContent<Review> actual = jsonTester.write(review);

        assertStringValue("@.userId", "jdoe", actual);
        assertStringValue("@.comments", "Very good", actual);
        assertNumberValue("@.overallRating", 7.0, actual);
    }
}
