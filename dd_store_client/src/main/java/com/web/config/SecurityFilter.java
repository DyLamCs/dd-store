package com.web.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityFilter implements Filter {


	@Override
	public void destroy() {

	}

	/**
	 * 
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest(httpServletRequest);

		// Lấy thông tin session
		HttpSession session = httpServletRequest.getSession(true);
		// Lấy context của session có tên "SPRING_SECURITY_CONTEXT"
		SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");

		// nếu có tồn tại thì đặt xác thực cho context này
		if (context != null) {
			SecurityContextHolder.getContext().setAuthentication(context.getAuthentication());
		} 

		chain.doFilter(mutableRequest, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}
}
