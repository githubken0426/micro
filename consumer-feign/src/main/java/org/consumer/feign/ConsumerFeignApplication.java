package org.consumer.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *	 因为feign底层是使用了ribbon作为负载均衡的客户端，而ribbon的负载均衡也是依赖于eureka获得各个服务的地址，所以要引入eureka-client
 *
 *	Feign默认使用的连接工具实现类，发现只要你有body体对象，就会强制的把GET请求转换成POST请求
 * 	1、开启feign.httpclient.enabled: true
 * 	2、 pom中增加feign-httpclient支持
 * @author kun.f.wang
 */
@EnableSwagger2
@EnableFeignClients
@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = { "org.consumer.feign" }, exclude = DataSourceAutoConfiguration.class)
public class ConsumerFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerFeignApplication.class, args);
	}
}
