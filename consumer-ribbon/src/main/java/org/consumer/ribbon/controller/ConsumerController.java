package org.consumer.ribbon.controller;

import java.util.List;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController extends AbstractController {

	@GetMapping("/consumer/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String getServices(@PathVariable("id") long id) {
		String serviceUrl = "http://produce/produce/services/{1}";
		String obj = restTemplate.getForObject(serviceUrl, String.class, id);
		return obj;
	}

	@GetMapping("/user-instance/{serviceId}")
	public List<ServiceInstance> showInfo(@PathVariable("serviceId") String serviceId) {
		System.out.println(serviceId);
		return discoveryClient.getInstances(serviceId);
	}
}