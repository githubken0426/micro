package org.zuul.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 	使用 @EnableZuulProxy 注解后包含全部的原生 Filter；
 * 	如果使用 @EnableZuulServer 将缺少 PreDecorationFilter、RibbonRoutingfilter、SimpleHostRoutingFilter；
 * 	参考接口： actuator/filters
 * @author kun.f.wang
 *
 */
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ZuulGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulGatewayApplication.class, args);
	}
}
