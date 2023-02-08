package com.example.security.jwt;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.login.dto.User;
import com.example.security.service.PrincipalDetails;
import com.fasterxml.jackson.databind.ObjectMapper;

//POST    http://localhost:8090/login

public class JwtAuthenticationFilter  extends UsernamePasswordAuthenticationFilter{
	// 인증을 관리해주는 객체
	private AuthenticationManager authManager;
	
	public JwtAuthenticationFilter(AuthenticationManager authManager) {
		this.authManager = authManager;
	}
	
	//http://localhost:8090/login 요청을 하면 실행되는 함수
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		System.out.println("JwtAuthenticationFilter => login 요청 처리를 시작");
		
		try {
//			BufferedReader br = request.getReader();
//			String input = null;
//			while((input=br.readLine())!=null) {
//				System.out.println(input);
//			}
			
			ObjectMapper om = new ObjectMapper();
			//username, password 정보를 가져온다.
			User user= om.readValue(request.getInputStream(), User.class);
			System.out.printf("username:%s password:%s\n", user.getUsername(), user.getPassword());
			
			// 유저정보를 토큰값에 저장
			UsernamePasswordAuthenticationToken authenticationToken = 
					new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
			
			// 인증처리
			Authentication authentication = authManager.authenticate(authenticationToken);
			
			// 인증을 확인하기 위한 처리
			PrincipalDetails principalDetails =  (PrincipalDetails)authentication.getPrincipal();
			System.out.printf("로그인 완료 됨(인증)  %s %s " , principalDetails.getUsername(), principalDetails.getPassword());
			
			// 인증처리된 결과를 리턴
			return authentication;
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		return null;
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
			
		
		System.out.println("successfulAuthentication 실행됨: 인증이 완료되었다는 의미이기도 함");
		PrincipalDetails principalDetails = (PrincipalDetails)authResult.getPrincipal();
		
		//RSA방식은 아니고 Hash방식
       String jwtToken = JWT.create()
    		                .withSubject("mycos")
    		                //만료시간
    		                .withExpiresAt(new Date(System.currentTimeMillis() + (60*1000*3*1L))) //3분
    		                .withClaim("username",principalDetails.getUser().getUsername())
    		                .withClaim("authRole", principalDetails.getUser().getAuthRole())
    		                //알고리즘을 통해 토큰생성
    		                .sign(Algorithm.HMAC512("mySecurityCos"));
       // header, payload, signature 가 '.' 을 기준으로 3개의 부분으로 나누어진다.
       System.out.println("jwtToken:" + jwtToken);
       // 토큰 값은 header로 보내준다.
       response.addHeader("Authorization", "Bearer " + jwtToken); 
       final Map<String, Object> body = new HashMap<String, Object>();
       // 유저 정보는 body로 보내준다.
       body.put("username", principalDetails.getUser().getUsername());
       
       ObjectMapper mapper= new ObjectMapper();
       mapper.writeValue(response.getOutputStream(), body );       		
	}	 
 
}
