package org.consumer.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 因为feign底层是使用了ribbon作为负载均衡的客户端，而ribbon的负载均衡也是依赖于eureka
 * 获得各个服务的地址，所以要引入eureka-client
 * 
 * @author kun.f.wang
 *
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = { "org.consumer.feign" }, exclude = DataSourceAutoConfiguration.class)
public class ConsumerFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerFeignApplication.class, args);
	}
}
