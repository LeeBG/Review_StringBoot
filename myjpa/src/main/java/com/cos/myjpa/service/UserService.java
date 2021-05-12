package com.cos.myjpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cos.myjpa.domain.user.User;
import com.cos.myjpa.domain.user.UserRepository;
import com.cos.myjpa.web.dto.CommonRespDto;
import com.cos.myjpa.web.user.dto.UserRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor			//DI를위해 생성자
@Service
public class UserService {
	//서비스 - 여기서 기능을 구현한다. 데이터베이스 관련 로직을 구현해준다.
	//JPA Repository는 그냥 API함수들이지 기능이 아니다.
	
	private UserRepository userRepository;	//DI
	
	public List<UserRespDto> 전체찾기() {
		List<User>  usersEntity= userRepository.findAll();
		//forEach
		List<UserRespDto> userRespDtos = new ArrayList<>();
		for (User user : usersEntity) {
			userRespDtos.add(new UserRespDto(user));
		}
		return userRespDtos;
	}

	public void 한건찾기() {

	}

	public void 프로파일() {

	}

	public void 로그인() {

	}

	public void 회원가입() {

	}

}
