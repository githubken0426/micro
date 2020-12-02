package org.produce.begin.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/produce")
public class ProduceController {
	@Autowired
	DiscoveryClient discoveryClient;

	@GetMapping("/services/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String getServices(@PathVariable("id") long id) {
		String services = "{'produce':'produce-begin-spring','id':'" + id + "','zoneName':'LightSnow','Services':'"
				+ discoveryClient.getServices() + "'}";
		System.out.println(services);
		return services;
	}

	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public String handleFileUpload(@RequestPart(value = "file") MultipartFile file) {
		System.out.println("handleFileUpload:" + file.getOriginalFilename());
		return file.getOriginalFilename();
	}
}