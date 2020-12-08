package org.consumer.feign.hystrix;

import org.springframework.stereotype.Component;

@Component
public class ConsumerFeignServiceImpl implements ConsumerFeignService {

	@Override
	public String services(long id) {
		return "[ConsumerFeignServiceImpl.services(" + id + ")] is not arrivable!";
	}

	@Override
	public String hystrix(int id) {
		return "[ConsumerFeignServiceImpl.hystrix(" + id + ")] is not arrivable!";
	}

}
