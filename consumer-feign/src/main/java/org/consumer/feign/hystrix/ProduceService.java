package org.consumer.feign.hystrix;

import org.consumer.feign.config.ProduceConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * fallback = ProduceServiceImpl.class, 
 * fallbackFactory = HystrixFallbackFactory.class二者取一。
 * Feign默认使用的连接工具实现类，所以里面发现只要你有body体对象，就会强制的把GET请求转换成POST请求
 * @author kun.f.wang
 */
@FeignClient(name = ProduceConstant.PRODUCE_NAME, configuration = HystrixConfigure.class, fallbackFactory = HystrixFallbackFactory.class)
public interface ProduceService {

	@GetMapping("/produce/services/569")
	String consumer();

	@GetMapping("/produce/hystrix/569")
	public String hystrix(int id);
}
