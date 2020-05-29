package com.ethan.server;

import com.ethan.MessageCentralApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MessageCentralApplicationTests {

	@Test
	void contextLoads() {
		MessageCentralApplication.main(new String[]{""});
	}

}
