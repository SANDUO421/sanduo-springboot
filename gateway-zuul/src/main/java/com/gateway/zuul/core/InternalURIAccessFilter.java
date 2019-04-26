//package com.gateway.zuul.core;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.util.PatternMatchUtils;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
//
// */
//@Component
//public class InternalURIAccessFilter extends ZuulFilter {
//
//	@Override
//	public Object run() {
//		RequestContext requestContext = RequestContext.getCurrentContext();
//		requestContext.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
//		requestContext.setResponseBody(HttpStatus.FORBIDDEN.getReasonPhrase());
//		requestContext.setSendZuulResponse(false);
//
//		return null;
//	}
//
//	@Override
//	public boolean shouldFilter() {
//		RequestContext requestContext = RequestContext.getCurrentContext();
//		HttpServletRequest request = requestContext.getRequest();
//
//		return PatternMatchUtils.simpleMatch("/user/getOne/**", request.getRequestURI());
//	}
//
//	@Override
//	public int filterOrder() {
//		return 0;
//	}
//
//	@Override
//	public String filterType() {
//		return FilterConstants.PRE_TYPE;
//	}
//
//}
