package com.insideinc.jwtapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "file:secrets.yml", ignoreResourceNotFound = true)
public class JwtAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtAppApplication.class, args);
	}

}
