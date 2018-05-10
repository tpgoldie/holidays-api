package com.tpg.holidays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"spring.session.store-type=redis"})
public class HolidaysApplicationTests {

	@Test
	public void contextLoads() {
	}

}
