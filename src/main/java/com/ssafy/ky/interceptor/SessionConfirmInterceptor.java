package com.ssafy.ky.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component // 인터셉터 빈 등록
public class SessionConfirmInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
//        //1. 세션에서 유저정보가 있는지 체크
//        HttpSession session = request.getSession();
//        User userInfo = (User)session.getAttribute("userInfo");
//        
//        //2-1. 유저정보가 없는 경우 - 홈 페이지로 보냄
//        if(userInfo == null) {
//            response.sendRedirect(request.getContextPath());
//            //컨트롤러로 요청가지 않도록 false 리턴
//            return false;
//        }
        //2-2. 유저정보가 있는 경우
        //요청을 컨트롤러로 정상적으로 보냄
        return true;
    }
}