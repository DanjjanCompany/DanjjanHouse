package com.ssafy.ky.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
	final static String SECRET_KEY = "ssafy";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object Handler)
			throws Exception {

		log.debug("요청 메소드 종류 : {}", request.getMethod());
		System.out.println("RequestURI : " + request.getRequestURI());
		// 회원가입 요청은 token 체크 X
		if(HttpMethod.POST.matches(request.getMethod()) && request.getRequestURI().equals("/api/users/") || request.getRequestURI().equals("/api/users") || request.getRequestURI().equals("/api/users/mail") ) return true;
//		if (CorsUtils.isPreFlightRequest(request)) {
//		    return true;
//		}
		if(HttpMethod.OPTIONS.matches(request.getMethod())) return true;

		//뷰에서 access-token라는 이름의 토큰을 받아옴
		final String token = request.getHeader("access-token");

		// 토큰 존재 여부 체크
		if(token == null) {
			response.getWriter().append("not Login"); 
			log.debug("header에 access-token 정보가 없음");
			return false;
		}

		// 토큰의 유효성 체크
		try {
			Jwts.parser()
					.setSigningKey(SECRET_KEY.getBytes("UTF-8"))
					.parseClaimsJws(token);
			log.debug("토큰이 존재하고 유효함 ");
			return true;

		} catch (Exception e) {
			response.getWriter().append("not Login"); 
			log.debug("토큰은 존재하지만 유효하지 않음 : {}", e.getMessage());
			return false;
		}
	}
}
