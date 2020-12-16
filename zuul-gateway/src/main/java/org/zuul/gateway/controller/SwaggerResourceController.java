package org.zuul.gateway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zuul.gateway.config.BusSwaggerResourcesProvider;

import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

@RestController
@RequestMapping("/swagger-resources")
public class SwaggerResourceController {

	@Autowired
	public BusSwaggerResourcesProvider busSwaggerResourcesProvider;
	
//	@Autowired
//	public SwaggerResourceController(BusSwaggerResourcesProvider busSwaggerResourcesProvider) {
//		this.busSwaggerResourcesProvider = busSwaggerResourcesProvider;
//	}

	@RequestMapping(value = "/configuration/security")
	public ResponseEntity<SecurityConfiguration> securityConfiguration() {
		return new ResponseEntity<>(SecurityConfigurationBuilder.builder().build(), HttpStatus.OK);
	}

	@RequestMapping(value = "/configuration/ui")
	public ResponseEntity<UiConfiguration> uiConfiguration() {
		return new ResponseEntity<>(UiConfigurationBuilder.builder().build(), HttpStatus.OK);
	}

	@RequestMapping
	public ResponseEntity<List<SwaggerResource>> swaggerResources() {
		return new ResponseEntity<>(busSwaggerResourcesProvider.get(), HttpStatus.OK);
	}
}
