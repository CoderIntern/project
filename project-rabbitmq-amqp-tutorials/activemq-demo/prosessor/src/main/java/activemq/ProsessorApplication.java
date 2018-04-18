package activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = {"classpath:application-prosessor.xml"})
public class ProsessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProsessorApplication.class, args);
	}
}
