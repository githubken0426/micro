package org.consumer.ribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service(value = "consumerService")
public class ConsumerServiceImpl implements ConsumerService {
	@Autowired
	public RestTemplate restTemplate;

	@Override
	@HystrixCommand(fallbackMethod = "fallback")
	public String consumer(long id) {
		return restTemplate.getForObject("http://produce/produce/hystrix/{1}", String.class, id);
	}

	public String fallback(long id) {
		return "fallback:" + id;
	}

}
