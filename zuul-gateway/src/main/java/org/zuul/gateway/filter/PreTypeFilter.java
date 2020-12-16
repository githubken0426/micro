package org.zuul.gateway.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * Zuul 内部提供了一个动态读取、编译和运行这些 Filter 的机制。 Filter 之间不直接通信，在请求线程中会通过 RequestContext
 * 	来共享状态，它的内部是用 ThreadLocal 实现的， 当然你也可以在 Filter之间使用 ThreadLocal 来收集自己需要的状态或数据。
 * 
 * @author kun.f.wang
 */
@Component
public class PreTypeFilter extends ZuulFilter {
	private static Logger log = LoggerFactory.getLogger(PreTypeFilter.class);

	@Override
	public Object run() throws ZuulException {
		log.info("[Pretype]: shouldFilter {} ,filterType {},filterOrder {}", true, FilterConstants.PRE_TYPE,
				FilterConstants.PRE_DECORATION_FILTER_ORDER);
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		log.info("[Pretype]: send {} request to {}", request.getMethod(), request.getRequestURL().toString());

		Object accessToken = request.getHeader("preAccessToken");
		if (accessToken == null) {
			log.warn("[Pretype]: access {} token is empty", FilterConstants.PRE_TYPE);
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			ctx.setResponseBody("[Pretype]: access " + FilterConstants.PRE_TYPE + " token is empty");
			return false;
		}
		log.info("[Pretype]: access {} token ok!", FilterConstants.PRE_TYPE);
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
		return FilterConstants.PRE_TYPE;
	}

	/**
	 * 过滤器的执行顺序。当请求在一个阶段中存在多个过滤器时，需要根据该方法返回的值来依次执行
	 */
	@Override
	public int filterOrder() {
		return FilterConstants.PRE_DECORATION_FILTER_ORDER;
	}

}
