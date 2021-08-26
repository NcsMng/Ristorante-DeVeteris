package com.deveteris.cucina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@PropertySource({"classpath:notifications-db.properties", "classpath:permissions-db.properties","classpath:cucina-db.properties","classpath:config.properties"})

public class CucinaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CucinaApplication.class, args);
	}

}
