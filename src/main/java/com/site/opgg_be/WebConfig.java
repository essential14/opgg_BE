package com.site.opgg_be;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

 @Configuration // 이 클래스가 설정 클래스임을 알려줌
 public class WebConfig implements WebMvcConfigurer {

 	@Override
 	public void addCorsMappings(CorsRegistry registry) { // 교차출처 정책에 문제가 되면 서버쪽에서 이렇게 처리해주면 됨
 		registry.addMapping("/**")
 				.allowedOrigins("*");
 	}

 }