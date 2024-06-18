package com.example.pwa_backend_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class PwaBackendApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PwaBackendApiApplication.class, args);
	}

}
