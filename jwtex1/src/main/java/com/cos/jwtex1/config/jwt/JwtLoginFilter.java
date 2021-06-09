package com.cos.jwtex1.config.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cos.jwtex1.web.dto.LoginReqDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

//토큰 만들어주기

@RequiredArgsConstructor
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
	
	private final AuthenticationManager authenticationManager;	//DI완료
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		System.out.println("로그인 요청 옴.");
		ObjectMapper om = new ObjectMapper();
		LoginReqDto loginReqDto = null;
		try {
			loginReqDto = om.readValue(request.getInputStream(), LoginReqDto.class);	//파싱:스트림으로 받아서(버퍼를 넣는 것이 아니다.)
		} catch (Exception e) {
			e.printStackTrace();
		}
		//1. UsernamePassword토큰 만들기
		
		UsernamePasswordAuthenticationToken authToken =
				new UsernamePasswordAuthenticationToken(loginReqDto.getUsername(),loginReqDto.getPassword());
		
		//2. AuthenticaionManager에게 토큰을 전달하면 -> 자동으로 UserDetailsService가 호출 => 응답 Authentication
		Authentication authentication = authenticationManager.authenticate(authToken);//DetailsService실행
		return authentication;
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		System.out.println("로그인 완료되어서 세션 만들어짐. 이제 JWT토큰 만들어서 response.header에 응답할 차례");
	}
	// 로그인이 완료되면 Authentication객체가 정상적으로 리턴이 되면 자동으로 동작한다.
	
}
