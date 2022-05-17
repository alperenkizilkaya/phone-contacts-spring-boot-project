package com.contacts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ContactsApp {

	public static void main(String[] args) {
		SpringApplication.run(ContactsApp.class, args);
	}

}
