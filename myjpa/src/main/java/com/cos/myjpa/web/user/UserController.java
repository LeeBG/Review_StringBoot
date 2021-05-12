package com.cos.myjpa.web.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.myjpa.domain.user.User;
import com.cos.myjpa.domain.user.UserRepository;
import com.cos.myjpa.service.UserService;
import com.cos.myjpa.web.dto.CommonRespDto;
import com.cos.myjpa.web.user.dto.UserJoinReqDto;
import com.cos.myjpa.web.user.dto.UserLoginReqDto;
import com.cos.myjpa.web.user.dto.UserRespDto;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RequiredArgsConstructor		//자동으로 생성자를 만들어줌
@RestController
public class UserController {
	private final UserRepository userRepository;
	private final HttpSession session;//tomcat이 들고있는 객체 오브젝트는 IoC에 다 들어가 있다.
	private final UserService userService;
	
	@GetMapping("/user")
	public CommonRespDto<?> findAll(){
		return new CommonRespDto<>(1,"성공",userService.전체찾기());
	}
	
	@PostMapping("/join")	//회원가입  //Json으로 받을것이다.
	public CommonRespDto<?> join(@RequestBody UserJoinReqDto userJoinReqDto){
		User userEntity = userRepository.save(userJoinReqDto.toEntity());
		return new CommonRespDto<>(1,"회원가입 성공",userEntity);
	}
	
	@PostMapping("/login")
	public CommonRespDto<?> login(@RequestBody UserLoginReqDto userLoginReqDto/*,HttpSession session*/){//파라미터로 주입도 가능
		User userEntity = userRepository.findByUsernameAndPassword(userLoginReqDto.getUsername(), userLoginReqDto.getPassword());
		if(userEntity==null) {
			return new CommonRespDto<>(-1,"로그인 실패",null);
		}else {
			//세션을 만들기
			//세션을 만드는 방법은 두가지 http Servlet Request/Response정보(톰캣이 만들어주는 정보) => IoC컨테이너에 이미 다 있음
			//이미 들어가 있기 때문에 DI만 하면 된다. 22번줄 코드확인
			
			//세션이 만들어져야 함 password를 주는것은 아주 위험함
			userEntity.setPassword(null);		//위험하니까  null로 준다.
			session.setAttribute("principal", userEntity);
			return new CommonRespDto<>(1,"로그인 성공",userEntity);
		}
	}
	
	@GetMapping("/user/{id}")//유저정보보기 - 세션이 정확히 들어갔는지 아닌지
	public CommonRespDto<?> userInfo(@PathVariable Long id){
		User principal = (User)session.getAttribute("principal");
		if(principal == null) {
			return new CommonRespDto<>(-1,"유저정보보기 실패",null);
		}else {
			User userEntity = userRepository.findById(id).get();
			return new CommonRespDto<>(1,"유저정보보기 성공",userEntity);
		}
	}
	
}
