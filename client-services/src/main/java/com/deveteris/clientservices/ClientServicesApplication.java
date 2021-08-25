package com.deveteris.clientservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication()
@EnableScheduling
@EnableAsync
@PropertySource({"classpath:notifications-db.properties", "classpath:permissions-db.properties"})
public class ClientServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientServicesApplication.class, args);
	}
}
