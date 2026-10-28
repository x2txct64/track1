package com.ryan.f1tracker;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class F1TrackerApplicationTests {

	@Test
	void contextLoads() {
		F1TrackerApplication sampleApi = new F1TrackerApplication();
		assertNotNull(sampleApi);
	}

}
