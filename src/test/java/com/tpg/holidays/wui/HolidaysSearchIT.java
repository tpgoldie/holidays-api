package com.tpg.holidays.wui;

import com.tpg.holidays.HolidaysApplication;
import com.tpg.holidays.wui.pages.SearchPage;
import org.fluentlenium.adapter.junit.FluentTest;
import org.fluentlenium.core.annotation.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;

import static com.tpg.holidays.wui.SearchForHolidays.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT,
    classes = {HolidaysApplication.class})
public class HolidaysSearchIT extends FluentTest {

    @Page
    SearchPage searchPage;

    @Test
    public void search() {

        SearchPage page = goTo(searchPage);
        given()
            .searchPage(page)
            .destination("Swindon")
            .checkInDate(NOW.plusDays(10))
            .checkOutDate(NOW.plusDays(17))
            .numberOfAdults(2)
            .numberOfChildren(1)
            .numberOfRooms(1)
        .when()
            .searchForHolidays()
        .then()
            receiveSearchResults();
    }

    private static ZonedDateTime NOW = ZonedDateTime.now();
}
