package ristorante.de.veteris;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class PermessiApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void criptazione(){
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String test5 = bCryptPasswordEncoder.encode("test5");
		System.out.println(test5);
	}

}
