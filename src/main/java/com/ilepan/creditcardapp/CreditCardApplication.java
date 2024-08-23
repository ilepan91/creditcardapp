package com.ilepan.creditcardapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * The main class for the Spring Boot RESTful CRUD application.
 * This class is annotated with {@link SpringBootApplication}, indicating that it is the entry point
 * for the Spring Boot application and enabling auto-configuration and component scanning.
 */
@SpringBootApplication
public class CreditCardApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CreditCardApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CreditCardApplication.class);
	}


}
