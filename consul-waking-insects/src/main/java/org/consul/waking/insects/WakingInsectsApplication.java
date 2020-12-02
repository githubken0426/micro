package org.consul.waking.insects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/**
   *   需要到官网下载服务端
 * @author kun.f.wang
 *
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class WakingInsectsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WakingInsectsApplication.class, args);
	}
}
