package com.example.kitchensink;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.TimeZone;

//@SpringBootApplication
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class KitchensinkApplication {

	@PostConstruct
	void started(){
		TimeZone.setDefault(TimeZone.getTimeZone("ETC"));
	}
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(KitchensinkApplication.class);
		//app.setWebApplicationType(WebApplicationType.SERVLET);
		app.run(args);
	}

}
