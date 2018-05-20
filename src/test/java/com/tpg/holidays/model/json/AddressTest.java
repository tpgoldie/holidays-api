package com.tpg.holidays.model.json;

import com.tpg.holidays.model.Address;
import org.junit.Test;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.json.ObjectContent;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AddressTest extends JsonTest<Address> {

    @Test
    public void serialize() throws IOException {

        Address address = new Address("123 High Street", "London Road", "Croydon", "Surrey", "CR0 6DG");

        JsonContent<Address> actual = jsonTester.write(address);

        assertStringValue("@.lineOne", "123 High Street", actual);
        assertStringValue("@.lineTwo", "London Road", actual);
        assertStringValue("@.municipal", "Croydon", actual);
        assertStringValue("@.state", "Surrey", actual);
        assertStringValue("@.postCode", "CR0 6DG", actual);
    }

    @Test
    public void deserialize() throws IOException {

        String content = "{\"lineOne\":\"123 High Street\",\"lineTwo\":\"London Road\",\"municipal\":\"Croydon\",\"state\":\"Surrey\",\"postCode\":\"CR0 6DG\"}";

        ObjectContent<Address> actual = jsonTester.parse(content);

        Map<String, String> mapping = new LinkedHashMap<>();

        mapping.put("lineOne", "123 High Street");
        mapping.put("lineTwo", "London Road");
        mapping.put("municipal", "Croydon");
        mapping.put("state", "Surrey");
        mapping.put("postCode", "CR0 6DG");

        assertThat(actual.getObject()).isEqualTo(mapping);
    }
}
