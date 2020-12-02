package org.produce.begin.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * There are multiple implementations of "Discovery Service" (eureka, consul, zookeeper). 
 * @EnableDiscoveryClient lives in spring-cloud-commons and picks the implementation on the classpath.  
 * @EnableEurekaClient lives in spring-cloud-netflix and only works for eureka. If eureka is on your classpath, 
 * they are effectively the same.
 * @author kun.f.wang
 *
 */
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {"org.produce.begin.spring"}, exclude = DataSourceAutoConfiguration.class)
public class ProduceBeginSpring {
	
    public static void main(String[] args) {
        SpringApplication.run(ProduceBeginSpring.class, args);
    }
}

