package org.consumer.ribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service(value = "consumerService")
public class ConsumerRibbonServiceImpl implements ConsumerRibbonService {
	@Autowired
	public RestTemplate restTemplate;

	/**
	 * 	模拟hystrix的断路器模式 produce-begin-spring:接口抛出异常 
	 * 	produce-spring-equinox:接口正常返回
	 */
	@Override
	@HystrixCommand(fallbackMethod = "hystrixFallback")
	public String hystrix(long id) {
		System.out.println("consumer-ribbon: ConsumerRibbonServiceImpl.hystrix(" + id + ")");
		return restTemplate.getForObject("http://produce/produce/hystrix/{1}", String.class, id);
	}

	@Override
	@HystrixCommand(fallbackMethod = "servicesFallback")
	public String services(long id) {
		System.out.println("consumer-ribbon: ConsumerRibbonServiceImpl.services(" + id + ")");
		return restTemplate.getForObject("http://produce/produce/services/{1}", String.class, id);
	}

	/**
	 * 	回调函数 参数类型,返回值类型和原函数保持一致。
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unused")
	private String hystrixFallback(long id) {
		System.out.println("consumer-ribbon: hystrixFallback(" + id + ")");
		return "consumer-ribbon: [ConsumerRibbonServiceImpl.hystrix(" + id + ")] is not arrivable,call hystrixFallback()!";
	}
	
	@SuppressWarnings("unused")
	private String servicesFallback(long id) {
		System.out.println("consumer-ribbon: servicesFallback(" + id + ")");
		return "consumer-ribbon: [ConsumerRibbonServiceImpl.services(" + id + ")] is not arrivable,call servicesFallback()!";
	}
}
