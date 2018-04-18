package activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = {"classpath:application-reactor.xml"})
public class ReactorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactorApplication.class, args);
	}
}
