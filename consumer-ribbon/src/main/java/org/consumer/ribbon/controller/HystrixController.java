package org.consumer.ribbon.controller;

import org.consumer.ribbon.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hystrix")
public class HystrixController extends AbstractController {

	@Autowired
	public ConsumerService consumerService;

	@GetMapping("/consumer/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String consumer(@PathVariable("id") long id) {
		return consumerService.consumer(id);
	}
	
}