package org.config.client.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/**
 *	配置中心的动态刷新：
 * Post调用/actuator/refresh接口，结合@RefreshScope
 * @author kun.f.wang
 *
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ConfigClientEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientEurekaApplication.class, args);
	}
}
