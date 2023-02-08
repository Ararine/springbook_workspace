package com.example.security.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration //환경설정을 잡는 class
@EnableWebSecurity //security를 활성화 시켜주는 어노테이션
public class CorsConfig {
	
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		//내 서버가 응답할 때 json을 자바스크립트에서 처리할 수 있게 할 지를 설정한다.
		config.setAllowCredentials(true);
		//ip허용 설정
		config.addAllowedOrigin("http://localhost:3000");
		//포트번호 응답 다름 허용
		config.addAllowedOriginPattern("*");
		//모든 요청 header에 응답허용
		config.addAllowedHeader("*");
		//모든 post, get, put, delete 등 모든 메소드에 응답을 허용한다.
		config.addAllowedMethod("*");
		// front-end에 노출하도록 허용
		config.addExposedHeader("Authorization");
		// config.addExposedHader("refreshToken");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}
