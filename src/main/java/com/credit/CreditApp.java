package com.credit;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CreditApp extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(CreditApp.class, args);

	}

}

