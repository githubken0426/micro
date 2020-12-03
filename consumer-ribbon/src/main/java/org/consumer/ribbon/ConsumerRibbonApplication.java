package org.consumer.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @EnableCircuitBreaker 或
 * @EnableHystrix 注解开启Hystrix的使用。
 * 
 * @SpringCloudApplication 包含了 @EnableCircuitBreaker @EnableDiscoveryClient @SpringBootApplication
 * @author kun.f.wang
 */
@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ConsumerRibbonApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(ConsumerRibbonApplication.class, args);
    }
}

