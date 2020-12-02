package org.consumer.feign.config;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Request;
import feign.Request.HttpMethod;
import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * feign不支持GET请求直接传递POJO对象的，目前解决方法如下： 把POJO拆散城一个一个单独的属性放在方法参数中 把方法参数编程Map传递
 * 使用GET传递@RequestBody，但此方式违反restful风格
 * 
 * @author kun.f.wang
 */
@Component
public class CustomRequestInteceptor implements RequestInterceptor {
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void apply(RequestTemplate template) {
		if (HttpMethod.GET.toString() != template.method())
			return;
		Request.Body data = template.requestBody();
		if (data != null)
			return;
		// feign 不支持GET方法传输POJO 转换成json，再换成query
		try {
			@SuppressWarnings("unchecked")
			Map<String, Collection<String>> map = objectMapper.readValue(template.bodyTemplate(), Map.class);
			template.queries(map);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
