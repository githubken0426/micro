package org.consumer.feign.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

@Component
public class HystrixFallbackFactory implements FallbackFactory<ConsumerFeignService> {

	private static final Logger logger = LoggerFactory.getLogger(HystrixFallbackFactory.class);

	@Override
	public ConsumerFeignService create(Throwable cause) {
		HystrixFallbackFactory.logger.info("fallback; exception was: {}", cause.toString());
		HystrixFallbackFactory.logger.info("fallback; reason was: {}", cause.getMessage());
		return new ConsumerFeignService() {

			@Override
			public String services(long id) {
				return "consumer-feign: [HystrixClientFactory.services(" + id + ")] is not arrivable!";
			}

			@Override
			public String hystrix(int id) {
				return "consumer-feign: [HystrixClientFactory.hystrix(" + id + ")] is not arrivable!";
			}

		};
	}
}
