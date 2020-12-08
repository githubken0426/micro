package org.consumer.ribbon.service;

public interface ConsumerRibbonService {
	public String hystrix(long id);
	
	public String services(long id);
}
