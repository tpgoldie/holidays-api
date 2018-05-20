package com.tpg.holidays.model.json;

import com.tpg.holidays.model.*;
import org.junit.Test;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.json.ObjectContent;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.tpg.holidays.model.Room.RoomType.SINGLE;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class HotelTest extends JsonTest<Hotel> implements AddressFixture, HotelFixture {

    @Test
    public void serialize() throws IOException {

        Address address = address("Flat 1", "555 High Street", "Croydon", "Surrey", "CR0 01X");
        Location location = new Location(address, "/abc/123");
        Room room = new Room(SINGLE, 2, 1);

        Hotel value = hotel(location, singletonList(room), new BigDecimal("125"));

        JsonContent<Hotel> actual = jsonTester.write(value);

        assertStringValue("@.location.address.lineTwo", "555 High Street", actual);
        assertNumberValue("@.price", 125, actual);
        assertNumberValue("@.rooms[0].numberOfAdults", 2, actual);
        assertNumberValue("@.rooms[0].numberOfChildren", 1, actual);
    }

    @Test
    public void deserialize() throws IOException {

        String content = "{\"description\":\"All of London\",\"name\":\"London\", \"code\":\"LDN\"}";

        ObjectContent<Hotel> actual = jsonTester.parse(content);

        Map<String, String> mapping = new LinkedHashMap<>();

        mapping.put("description", "All of London");
        mapping.put("name", "London");
        mapping.put("code", "LDN");

        assertThat(actual.getObject()).isEqualTo(mapping);
    }

}
