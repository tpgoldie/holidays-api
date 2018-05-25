package com.tpg.holidays.model.json;

import com.tpg.holidays.model.Address;
import com.tpg.holidays.model.Location;
import org.junit.Test;
import org.springframework.boot.test.json.JsonContent;

import java.io.IOException;

public class LocationTest extends JsonTest<Location> {

    @Test
    public void serialize() throws IOException {

        Address address = new Address("123 High Street", "London Road", "Croydon", "Surrey", "CR0 6DG");

        Location location = new Location(address, "/abc/123");

        JsonContent<Location> actual = jsonTester.write(location);

        assertStringValue("@.address.lineOne", "123 High Street", actual);
        assertStringValue("@.mapLink", "/sasabc/123", actual);
    }
}
