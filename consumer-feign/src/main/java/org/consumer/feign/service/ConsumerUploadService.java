package org.consumer.feign.service;

import org.consumer.feign.config.HystrixConfigure;
import org.consumer.feign.config.ProduceConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = ProduceConstant.PRODUCE_NAME, configuration = HystrixConfigure.class)
public interface ConsumerUploadService {

	@PostMapping(value = "/produce/upload", 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	String handleFileUpload(@RequestPart(value = "file",required = false) MultipartFile file);

}
