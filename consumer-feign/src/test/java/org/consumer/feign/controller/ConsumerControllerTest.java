package org.consumer.feign.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.IOUtils;
import org.consumer.feign.service.ConsumerUploadService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ConsumerControllerTest {
	@Autowired
	private ConsumerUploadService produceUploadService;

	@Test
	public void testHandleFileUpload() {
		File file = new File("C:\\ec_workspace\\micro\\consumer-feign\\src\\main\\resources\\application.yml");
		DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem("file", MediaType.TEXT_PLAIN_VALUE,
				true, file.getName());
		try (InputStream input = new FileInputStream(file); OutputStream os = fileItem.getOutputStream()) {
			IOUtils.copy(input, os);
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid file: " + e, e);
		}
		MultipartFile multi = new CommonsMultipartFile(fileItem);
		produceUploadService.handleFileUpload(multi);
	}

}