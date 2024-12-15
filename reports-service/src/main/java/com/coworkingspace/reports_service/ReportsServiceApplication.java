package com.coworkingspace.reports_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ReportsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportsServiceApplication.class, args);
	}

}
