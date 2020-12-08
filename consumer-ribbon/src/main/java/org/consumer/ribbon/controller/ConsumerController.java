package org.consumer.ribbon.controller;

import java.util.List;

import org.consumer.ribbon.service.ConsumerRibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController extends AbstractController {
	@Autowired
	public ConsumerRibbonService consumerService;

	@GetMapping("/services/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String services(@PathVariable("id") long id) {
		return consumerService.services(id);
	}

	@GetMapping("/hystrix/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String hystrix(@PathVariable("id") long id) {
		return consumerService.hystrix(id);
	}

	@GetMapping("/user-instance/{serviceId}")
	public List<ServiceInstance> showInfo(@PathVariable("serviceId") String serviceId) {
		System.out.println(serviceId);
		return discoveryClient.getInstances(serviceId);
	}
}