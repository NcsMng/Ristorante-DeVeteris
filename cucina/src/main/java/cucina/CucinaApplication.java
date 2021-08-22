package cucina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"notificationsmanager.repositories"})
@EnableScheduling
public class CucinaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CucinaApplication.class, args);
	}

}
