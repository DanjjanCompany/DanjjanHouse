package com.ssafy.ky.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.ky.interceptor.SessionConfirmInterceptor;

@Configuration
@EnableAspectJAutoProxy
// mapper 인터페이스 스캔 설정
@MapperScan(basePackages = {"com.ssafy.ky.model.mapper"})
public class WebMvcConfig implements WebMvcConfigurer {

	// interceptor 등록
	@Autowired
	private SessionConfirmInterceptor sessionConfirmInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		/*
		 * registry.addInterceptor(sessionConfirmInterceptor)
		 * .addPathPatterns("/book/*") // 허용 X .excludePathPatterns("/book/list");
		 * WebMvcConfigurer.super.addInterceptors(registry);
		 */
	}

//	// 정적 자원 경로 매핑
//	// assets로 시작하는 요청 시
//	// src/main/webapp/resources/assets로 매핑
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/assets/**")
//		.addResourceLocations("/resources/assets/");
//		WebMvcConfigurer.super.addResourceHandlers(registry);
//	}
}
