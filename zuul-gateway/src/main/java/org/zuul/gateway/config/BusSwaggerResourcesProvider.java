package org.zuul.gateway.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

@Component
@Primary
public class BusSwaggerResourcesProvider implements SwaggerResourcesProvider {
	private static final String SWAGGER2_URL = "/v2/api-docs";

	@Value("${spring.application.name}")
	private String self;
	/**
	 * 网关路由
	 */
	@Autowired
	public RouteLocator routeLocator;

	/**
	 * 构造器注入，防止RouteLocator未加载完成，直接调用，初始化异常。 推断：新版本spring的 @Autowired
	 * 注解应该是已经修复此问题(待调查)
	 */
//	public BusSwaggerResourcesProvider(RouteLocator routeLocator) {
//		this.routeLocator = routeLocator;
//	}

	@Override
	public List<SwaggerResource> get() {
		List<SwaggerResource> resources = new ArrayList<>();
		Set<String> routeHosts = new HashSet<>();
		// 	获取所有可用的 host：serviceId
		routeLocator.getRoutes().stream()
				.filter(route -> route.getId() != null)
				.filter(route -> !self.equals(route.getId()))
				.forEach(route -> {
					routeHosts.add(route.getPrefix());
				});
		System.out.println("routeHosts:" + routeHosts);
		if(routeHosts.size()==0)
			return resources;
		// 	记录已经添加过的server，存在同一个应用注册了多个服务在eureka上
		routeHosts.forEach(instance -> {
			String url = instance.toLowerCase() + SWAGGER2_URL;
			if (!routeHosts.contains(url)) {
				SwaggerResource swaggerResource = new SwaggerResource();
				swaggerResource.setUrl(url);
				swaggerResource.setName(instance);
				resources.add(swaggerResource);
			}
		});
		return resources;
	}

}
