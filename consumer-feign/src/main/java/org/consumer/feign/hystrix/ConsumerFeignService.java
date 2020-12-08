package org.consumer.feign.hystrix;

import org.consumer.feign.config.HystrixConfigure;
import org.consumer.feign.config.ProduceConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * fallback = ProduceServiceImpl.class, 
 * fallbackFactory = HystrixFallbackFactory.class二者取一。
 * @author kun.f.wang
 */
@FeignClient(name = ProduceConstant.PRODUCE_NAME, configuration = HystrixConfigure.class, fallbackFactory = HystrixFallbackFactory.class)
public interface ConsumerFeignService {

	@GetMapping("/produce/services/569")
	String services(long id);

	@GetMapping("/produce/hystrix/569")
	public String hystrix(int id);
}
