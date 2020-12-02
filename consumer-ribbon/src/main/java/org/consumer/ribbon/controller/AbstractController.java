package org.consumer.ribbon.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.EurekaClient;

@Controller
public abstract class AbstractController {
	public static final Logger logger = LogManager.getLogger();

	@Qualifier("eurekaClient")
	@Autowired
	public EurekaClient eurekaClient;
	@Autowired
	public DiscoveryClient discoveryClient;
	@Autowired
	public RestTemplate restTemplate;
}
