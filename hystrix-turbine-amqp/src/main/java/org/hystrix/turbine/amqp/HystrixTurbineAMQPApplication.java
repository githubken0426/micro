package org.hystrix.turbine.amqp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

@EnableTurbineStream
@EnableDiscoveryClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class HystrixTurbineAMQPApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixTurbineAMQPApplication.class, args);
	}
}
