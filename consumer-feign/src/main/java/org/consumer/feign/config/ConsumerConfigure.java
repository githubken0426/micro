package org.consumer.feign.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@Configuration
public class ConsumerConfigure implements WebMvcConfigurer {

	@Bean("restTemplate")
	@LoadBalanced
	public RestTemplate restTemplate() {
		RestTemplate template = new RestTemplate();
		template.getMessageConverters().clear();
		template.getMessageConverters().add(new MyJsonHttpMessageConverter());
		return template;
	}

	class MyJsonHttpMessageConverter extends FastJsonHttpMessageConverter {
		public MyJsonHttpMessageConverter() {
			this.setSupportedMediaTypes(supportedMediaTypes());
		}

		private List<MediaType> supportedMediaTypes() {
			List<MediaType> supportedMediaTypes = new ArrayList<>();
			supportedMediaTypes.add(MediaType.APPLICATION_JSON);
			supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
			supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
			supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
			supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
			supportedMediaTypes.add(MediaType.APPLICATION_PDF);
			supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML);
			supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
			supportedMediaTypes.add(MediaType.APPLICATION_XML);
			supportedMediaTypes.add(MediaType.IMAGE_GIF);
			supportedMediaTypes.add(MediaType.IMAGE_JPEG);
			supportedMediaTypes.add(MediaType.IMAGE_PNG);
			supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
			supportedMediaTypes.add(MediaType.TEXT_HTML);
			supportedMediaTypes.add(MediaType.TEXT_MARKDOWN);
			supportedMediaTypes.add(MediaType.TEXT_PLAIN);
			supportedMediaTypes.add(MediaType.TEXT_XML);
			return supportedMediaTypes;
		}
	}
}