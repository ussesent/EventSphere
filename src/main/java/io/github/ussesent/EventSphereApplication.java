package io.github.ussesent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EventSphereApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventSphereApplication.class, args);
	}

}
