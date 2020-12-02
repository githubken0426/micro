package org.config.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ClientConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientConfigApplication.class, args);
	}
}
