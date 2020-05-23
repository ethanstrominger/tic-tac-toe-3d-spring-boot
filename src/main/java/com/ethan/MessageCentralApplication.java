package com.ethan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessageCentralApplication {

	public static void main(String[] args) {
		System.out.println("About to start server");
		SpringApplication.run(MessageCentralApplication.class, args);
		System.out.println("Started server");
	}

}
