package com.cos.blog.web.user;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.blog.config.auth.PrincipalDetails;
import com.cos.blog.domain.user.User;
import com.cos.blog.service.UserService;
import com.cos.blog.web.dto.CMRespDto;
import com.cos.blog.web.user.dto.UserUpdateReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {
	
	private final UserService userService;
	
	@GetMapping("/user")
	public @ResponseBody String findAll(@AuthenticationPrincipal PrincipalDetails principalDetails) {		//@ResponseBody를 붙이면 @Controller+@ResponseBody = @RestController(데이터 리턴)
		return "User";
	}
	
	@GetMapping("/user/{id}")
	public String updateForm(@PathVariable Integer id, Model model) {
		model.addAttribute("id",id);
		return "user/updateForm";	//user폴더에 있는 updateForm.jsp파일
	}
	
	@PutMapping("/user/{id}")
	public @ResponseBody CMRespDto update(@PathVariable Integer id, @RequestBody UserUpdateReqDto userUpdateReqDto,@AuthenticationPrincipal PrincipalDetails principalDetails) {
		User userEntity = userService.회원수정(id, userUpdateReqDto);		//영속화 되어있는 userEntity
		
		// UsernamePasswordToken -> Authentication 객체로 만들어서 securityContextHolder에 집어 넣으면 된다.
		// 객체를 그냥 강제로 바꾸면 된다. Token을 만들어서 Authentication객체로 만들어서 -> 시큐리티 컨텍스트 홀더에 집어 넣으면 된다.
		principalDetails.setUser(userEntity);
		Authentication authentication = new UsernamePasswordAuthenticationToken(userEntity.getUsername(),userEntity.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return new CMRespDto<>(1,null);	
	}

}

