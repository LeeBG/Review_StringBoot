package com.cos.blog.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.blog.config.auth.PrincipalDetails;

@Controller
public class UserController {
	
	//로그인, 로그아웃, 회원가입 (AuthController), 회원정보 변경
	
	/**
	 *  /user1 -> 유저 정보 가져오기
	 */

	@GetMapping("/user")
	public @ResponseBody String findAll(@AuthenticationPrincipal PrincipalDetails principalDetails) {		//@ResponseBody를 붙이면 @Controller+@ResponseBody = @RestController(데이터 리턴)
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();//SecurityContextHolder에 접근하는 방법
//		PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();// principal정보를 받아 principalDetails에 저장하기(casting)
//		System.out.println(principalDetails.getUser());
		return "User";
	}

}

