package org.produce.spring.equinox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = { "org.produce.spring.equinox" }, exclude = DataSourceAutoConfiguration.class)
public class ProduceSpringEquinox {

	public static void main(String[] args) {
		SpringApplication.run(ProduceSpringEquinox.class, args);
	}
}
