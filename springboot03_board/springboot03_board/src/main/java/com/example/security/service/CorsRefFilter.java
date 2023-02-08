package com.example.security.service;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
/*
 * - 실제값이 작을수록 먼저 실행됨
 * 실행순서	상수명							실제값
 * 	  1		Ordered.HIGHEST_PRECEDENCE		-2147483648 먼저 실행됨
 * 	  2		Ordered.LOWEST_PRECEDENCE	 	 2147483648 	
 * 
 * ============================================================
 * 
 * 	  1		Ordered.HIGHEST_PRECEDENCE	 	-2147483648
 * 	  2		Ordered.HIGHEST_PRECEDENCE + 1	-2147483647
 * 	  3		Ordered.HIGHEST_PRECEDENCE + 2	-2147483646
 */


@Component
@Order(Ordered.HIGHEST_PRECEDENCE) // 실행순서를 주는 어노테이션. 숫자가 낮은 값이 먼저 실행됨
public class CorsRefFilter implements Filter{
	
	@Override
	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		System.out.printf("method : %s\n", req.getRemoteHost());
		 	HttpServletResponse response = (HttpServletResponse) res;
	        HttpServletRequest request = (HttpServletRequest) req;
	        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
	        response.setHeader("Access-Control-Allow-Credentials", "true");
	        response.setHeader("Access-Control-Allow-Methods",
	                "ACL, CANCELUPLOAD, CHECKIN, CHECKOUT, COPY, DELETE, GET, HEAD, LOCK, MKCALENDAR, MKCOL, MOVE, OPTIONS, POST, PROPFIND, PROPPATCH, PUT, REPORT, SEARCH, UNCHECKOUT, UNLOCK, UPDATE, VERSION-CONTROL");
	        response.setHeader("Access-Control-Max-Age", "3600");
	        response.setHeader("Access-Control-Allow-Headers",
	                "Origin, X-Requested-With, Content-Type, Accept, Key, Authorization");

	        //equalsIgnoreCase 대소문자 구분없이 문자열 비교
	        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
	            response.setStatus(HttpServletResponse.SC_OK);
	        } else {
	            chain.doFilter(req, res);
	        }
		
	}
	
	@Override
	//처음 필터가 실행이 되기 전 실행
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
	}
	
	@Override
	//종료 되기 직전 실행
	public void destroy() {
		Filter.super.destroy();
	}
}
