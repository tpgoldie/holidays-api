package com.tpg.holidays.model.json;

import com.tpg.holidays.model.Room;
import org.junit.Test;
import org.springframework.boot.test.json.JsonContent;

import java.io.IOException;

import static com.tpg.holidays.model.Room.RoomType.SINGLE;

public class RoomTest extends JsonTest<Room> {

    @Test
    public void serialize() throws IOException {

        Room room = new Room(SINGLE, 2, 1);

        JsonContent<Room> actual = jsonTester.write(room);

        assertStringValue("@.roomType", SINGLE.name(), actual);
        assertNumberValue("@.numberOfAdults", 2, actual);
        assertNumberValue("@.numberOfChildren", 1, actual);
    }
}
