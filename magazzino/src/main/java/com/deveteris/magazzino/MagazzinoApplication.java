package com.deveteris.magazzino;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({"classpath:permissions-db.properties","classpath:magazzino-db.properties","classpath:magazzino-config.properties"})

public class MagazzinoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MagazzinoApplication.class, args);
	}

}
