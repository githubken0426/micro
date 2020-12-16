package org.consumer.feign.controller;

import org.consumer.feign.hystrix.ConsumerFeignService;
import org.consumer.feign.service.ConsumerUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "Consumer Feign APi" })
@RestController
public class ConsumerController {
	@Autowired
	private ConsumerFeignService produceService;
	@Autowired
	private ConsumerUploadService produceUploadService;

	@ApiOperation(value = "services", notes = "services服务接口")
	@ApiImplicitParam(name = "id", value = "服务id", dataType = "long")
	@GetMapping("/services/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String services(@PathVariable("id") long id) {
		return produceService.services(id);
	}

	@ApiOperation(value = "hystrix", notes = "hystrix熔断接口")
	@GetMapping("/hystrix/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String hystrix(@PathVariable("id") int id) {
		return produceService.hystrix(id);
	}

	@ApiOperation(value = "upload", notes = "upload上传接口")
	@PostMapping("/upload")
	@ResponseStatus(HttpStatus.OK)
	public String file(@RequestPart(value = "file") MultipartFile file) {
		System.out.println("file name:" + file.getOriginalFilename());
		return produceUploadService.handleFileUpload(file);
	}

}