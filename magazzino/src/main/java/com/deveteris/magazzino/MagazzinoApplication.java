package com.deveteris.magazzino;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@PropertySource({"classpath:permissions-db.properties","classpath:magazzino-db.properties","classpath:magazzino-config.properties"})
@EnableAsync
@EnableScheduling

public class MagazzinoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MagazzinoApplication.class, args);
	}

}
