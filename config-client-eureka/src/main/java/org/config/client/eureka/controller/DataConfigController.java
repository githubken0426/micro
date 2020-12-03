package org.config.client.eureka.controller;

import org.config.client.eureka.config.DataAutoRefreshConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class DataConfigController {
	@Autowired
	DataAutoRefreshConfig dataAutoRefreshConfig;

	@GetMapping(value = "showByProperties")
	public Object showByProperties() {
		return dataAutoRefreshConfig;
	}
}
