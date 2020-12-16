package org.zuul.gateway.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PostTypeFilter extends ZuulFilter {
	private static Logger log = LoggerFactory.getLogger(PostTypeFilter.class);

	@Override
	public Object run() throws ZuulException {
		log.info("[Posttype]: shouldFilter {} ,filterType {},filterOrder {}", true, FilterConstants.POST_TYPE,
				FilterConstants.RIBBON_ROUTING_FILTER_ORDER);
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String requestUrl = request.getRequestURL().toString();
		log.info("[Posttype]: send {} request to {}", request.getMethod(), requestUrl);
		if (requestUrl != null && requestUrl.contains("/v2/api-docs")) {
			return true;
		}
		Object accessToken = request.getHeader("postAccessToken");
		if (accessToken == null) {
			log.warn("[Posttype]: access {} token is empty", FilterConstants.POST_TYPE);
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			ctx.setResponseBody("[Posttype]: access " + FilterConstants.POST_TYPE + " token is empty");
			return false;
		}
		log.info("[Posttype]: access {} token ok!", FilterConstants.POST_TYPE);
		return true;
	}

	/**
	 * 判断该过滤器是否需要被执行。这里我们直接返回了true，因此该过滤器对所有请求都会生效。实际运用中我们可以利用该函数来指定过滤器的有效范围
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/**
	 * 过滤器的类型，它决定过滤器在请求的哪个生命周期中执行。这里定义为pre，代表会在请求被路由之前执行
	 */
	@Override
	public String filterType() {
		return FilterConstants.POST_TYPE;
	}

	/**
	 * 过滤器的执行顺序。当请求在一个阶段中存在多个过滤器时，需要根据该方法返回的值来依次执行
	 */
	@Override
	public int filterOrder() {
		return FilterConstants.RIBBON_ROUTING_FILTER_ORDER;
	}

}
