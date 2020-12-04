package org.consumer.feign.hystrix;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;

/**
 * 沒有定义Encoder： fallback; exception was: feign.codec.EncodeException: class
 * java.lang.Long is not a type supported by this encoder.
 * 
 * @author kun.f.wang
 */
@Configuration
public class HystrixConfigure {
	@Autowired
	private ObjectFactory<HttpMessageConverters> messageConverters;

	@Bean
	Encoder feignFormEncoder() {
		return new SpringFormEncoder(new SpringEncoder(messageConverters));
	}
}