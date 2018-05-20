package com.tpg.holidays.model.json;

import com.tpg.holidays.model.DateTimeFixture;
import com.tpg.holidays.model.Destination;
import com.tpg.holidays.model.Holiday;
import com.tpg.holidays.model.HolidayFixture;
import org.junit.Test;
import org.springframework.boot.test.json.JsonContent;

import java.io.IOException;

import static java.util.Collections.singletonList;

public class HolidayTest extends JsonTest<Holiday> implements DateTimeFixture, HolidayFixture {

    @Test
    public void serialize() throws IOException {

        Destination destination = Destination.builder()
                    .name("Swindon")
                    .description("Swindon Town")
                    .code("SW1 1AA")
                    .build();

        Holiday holiday = holiday("Guided tour", "Great Break", destination,
                toZonedDateTime("12/03/2018"),
                toZonedDateTime("15/03/2018"), 1, 2,
                singletonList(12));

        JsonContent<Holiday> actual = jsonTester.write(holiday);

        assertStringValue("@.description", "Staff", actual);

    }
}
