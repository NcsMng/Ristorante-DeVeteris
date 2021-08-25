package com.deveteris.clientservices;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.deveteris.permessi.repository.UtenteRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class ClientServicesApplicationTests {
	@Autowired
	private UtenteRepository utenteRepository;


	@Test
	void contextLoads() {

	}

}
