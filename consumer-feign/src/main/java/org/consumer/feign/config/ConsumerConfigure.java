package org.consumer.feign.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

//@Configuration
@Deprecated
public class ConsumerConfigure implements WebMvcConfigurer {

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