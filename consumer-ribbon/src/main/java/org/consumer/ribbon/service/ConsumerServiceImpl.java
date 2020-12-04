package org.consumer.ribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service(value = "consumerService")
public class ConsumerServiceImpl implements ConsumerService {
	@Autowired
	public RestTemplate restTemplate;

	/**
	   *  模拟hystrix的断路器模式
	 * produce-begin-spring:接口抛出异常 produce-spring-equinox:接口正常返回
	 */
	@Override
	@HystrixCommand(fallbackMethod = "consumerFallback")
	public String consumer(long id) {
		System.out.println("consumer-ribbon: consumer(" + id + ")");
		return restTemplate.getForObject("http://produce/produce/hystrix/{1}", String.class, id);
	}

	/**
	 * 回调函数 参数类型,返回值类型和原函数保持一致。
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unused")
	private String consumerFallback(long id) {
		System.out.println("consumer-ribbon: fallback(" + id + ")");
		System.out.println("**************************************");
		return "fallback:" + id;
	}

}
