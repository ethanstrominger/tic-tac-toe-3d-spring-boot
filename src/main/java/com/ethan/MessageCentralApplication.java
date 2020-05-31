package com.ethan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessageCentralApplication {

	public static void main(String[] args) {
		System.out.println("Starting server");
		SpringApplication.run(MessageCentralApplication.class, args);
		System.out.println("Server is started!!");
	}

}
