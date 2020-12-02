package org.consumer.feign.service;

import org.consumer.feign.config.ProduceConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import feign.Logger;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;

@FeignClient(value = ProduceConstant.PRODUCE_NAME, configuration = ProduceUploadService.MultipartSupportConfig.class)
public interface ProduceUploadService {

	@PostMapping(value = "/produce/upload", 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	String handleFileUpload(@RequestPart(value = "file",required = false) MultipartFile file);

	@Configuration
	class MultipartSupportConfig {
		@Bean
		public Encoder multipartFormEncoder() {
			return new SpringFormEncoder();
		}

		@Bean
		public Logger.Level logger() {
			return Logger.Level.FULL;
		}
	}
}
