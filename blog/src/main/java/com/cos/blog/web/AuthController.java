package com.cos.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.blog.service.AuthService;
import com.cos.blog.web.auth.dto.AuthJoinReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AuthController {
	
	private final AuthService authService;
	
	@PostMapping("/join")
	public String join(AuthJoinReqDto authJoinReqDto) {	//x-www-form-urlencoded로 받을거라 @RequstBody가 필요가 없다.
		authService.회원가입(authJoinReqDto.toEntity());
		return "redirect:/loginForm"; //만들어 놓은 /loginForm을 get요청하여 재활용한다. - loginForm에 여러개의 로직이 올 수 있기 때문이다.
	}
	
	//이런식으로 page로 이동하는 것은 RestApi주소방식으로 적는 것이 아니다. 그래서 auth페이지를 따로 만들어주는 것이다.- authController
	@GetMapping("/loginForm")		// 로그인을 하는 것이아니라 로그인 페이지로 가는 것
	public String loginForm() {
		return "auth/loginForm";		// @Controller이기 때문에 viewResolver가 발동한다.
	}
	
	@GetMapping("/joinForm")		// 회원가입을 하는 것이아니라 회원가입 페이지로 가는 것
	public String joinForm() {
		return "auth/joinForm";		// @Controller이기 때문에 viewResolver가 발동한다.
	}
	
}
