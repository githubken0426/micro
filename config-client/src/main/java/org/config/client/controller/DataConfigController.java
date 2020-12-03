package org.config.client.controller;

import org.config.client.config.DataAutoRefreshConfig;
import org.config.client.config.DataConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class DataConfigController {
	@Autowired
	DataAutoRefreshConfig dataAutoRefreshConfig;
	@Autowired
	DataConfig dataConfig;

	@GetMapping(value = "showByValue")
	public Object showByValue() {
		return dataConfig;
	}

	@GetMapping(value = "showByProperties")
	public Object showByProperties() {
		return dataAutoRefreshConfig;
	}
}
