package org.consumer.feign.config;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;

/**
 * exception was: feign.codec.EncodeException: class java.lang.Long is not a type supported by this encoder.
 *	1、沒有定义Encoder： fallback; 
 * 	2、另外检查别的配置类中是否通过 @Configuration 指定了配置，进而覆盖了当前类的配置。
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