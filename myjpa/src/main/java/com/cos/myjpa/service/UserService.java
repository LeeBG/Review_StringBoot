package com.cos.myjpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.myjpa.domain.user.User;
import com.cos.myjpa.domain.user.UserRepository;
import com.cos.myjpa.web.dto.CommonRespDto;
import com.cos.myjpa.web.user.dto.UserJoinReqDto;
import com.cos.myjpa.web.user.dto.UserLoginReqDto;
import com.cos.myjpa.web.user.dto.UserRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor			//DI를위해 생성자
@Service
public class UserService {
	//서비스 - 여기서 기능을 구현한다. 데이터베이스 관련 로직을 구현해준다.
	//JPA Repository는 그냥 API함수들이지 기능이 아니다.
	
	private final UserRepository userRepository;	//DI
	
	@Transactional(readOnly = true)
	public List<UserRespDto> 전체찾기() {
		List<User>usersEntity= userRepository.findAll();
		//forEach
		List<UserRespDto> userRespDtos = new ArrayList<>();
		for (User user : usersEntity) {
			userRespDtos.add(new UserRespDto(user));
		}
		return userRespDtos;
	}
	
	@Transactional(readOnly = true)
	public UserRespDto 한건찾기(Long id) {
		User userEntity = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		});
		
		UserRespDto userRespDto = new UserRespDto(userEntity);
		return userRespDto;
	}

	@Transactional(readOnly = true)
	public User 프로파일(Long id) {
		User userEntity = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("id를 찾을 수 없습니다");
		});
		
		return userEntity;
	}
	
	@Transactional(readOnly = true)
	public User 로그인(UserLoginReqDto userLoginReqDto) {
		User userEntity = userRepository.findByUsernameAndPassword(userLoginReqDto.getUsername(), userLoginReqDto.getPassword());
		return userEntity;
	}

	@Transactional	//데이터베이스의 변경이 일어날 때 @Transactional - 데이터 변경 시 다른애들 동시에 쓰지 못하게 write에 대한 lock을 건다.
	public User 회원가입(UserJoinReqDto userJoinReqDto) {
		User userEntity = userRepository.save(userJoinReqDto.toEntity());
		return userEntity;
	}

}
