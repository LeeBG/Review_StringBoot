package com.cos.jwtex1.config.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//토큰 만들어주기
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {	//기존의 form로그인 방식을 가라엎는다.
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		System.out.println("로그인 요청 옴.");//메모리에 뜨지도 않았고 동작을 안함 - securityConfig에서 addFilter에 new해줘야 동작한다.
		return null;
	}//로그인을 시도하는 Authentication - 목적은 Authentication객체를 만드는 것이 목적이다.
	//UserDetailService가 데이터베이스의 Username이 있는지 확인하고 있으면 userDetails를 리턴할때 그때 Authentication객체가 만들어진다.
	// 그런데 우리는 그런 로직 무시하고 강제로 Authentication객체를 만들것이다. 그리고 리턴 해줄 것이다.
	
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
	}
	// 로그인이 완료되면 Authentication객체가 정상적으로 리턴이 되면 자동으로 동작한다.
	
	
}
