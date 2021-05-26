package com.cos.myjpa.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cos.myjpa.domain.user.User;
import com.cos.myjpa.handler.ex.MyAuthenticationException;

@Configuration // IoC등록
public class WebMvcConfig implements WebMvcConfigurer { // View컨트롤, Model컨트롤, Controller
	@Override
	public void addInterceptors(InterceptorRegistry registry) { // 인터셉터를 하나 등록
		// TODO Auto-generated method stub
		registry.addInterceptor(new HandlerInterceptor() {

			// 컨트롤러 실행 직전에 동작
			// - 반환값이 true일 경우 정상적진행 - 인터셉터가 실행되고 난 후 컨트롤러의 메서드 실행
			// false이면 컨트롤러 진입을 안함.
			@Override
			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
					throws Exception {

				HttpSession session = request.getSession();
				User principal = (User) session.getAttribute("principal");

				if (principal == null) {
					throw new MyAuthenticationException();
				} else {
					return true;
				}
			}
		}).addPathPatterns("/user").addPathPatterns("/post");

		WebMvcConfigurer.super.addInterceptors(registry);
	}
}
