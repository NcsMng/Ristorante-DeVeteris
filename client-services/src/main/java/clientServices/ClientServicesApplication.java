package clientServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages={"notificationsmanager.repository","clientServices.mapper"})
@EnableScheduling
@EnableAsync
public class ClientServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientServicesApplication.class, args);
	}

}
