package org.consumer.feign.service;

import org.consumer.feign.config.ProduceConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = ProduceConstant.PRODUCE_NAME)
public interface ProduceService {

	@GetMapping("/produce/services/569")
	String consumer();
}
