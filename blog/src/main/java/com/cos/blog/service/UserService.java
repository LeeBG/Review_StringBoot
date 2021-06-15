package com.cos.blog.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.domain.user.User;
import com.cos.blog.domain.user.UserRepository;
import com.cos.blog.web.user.dto.UserUpdateReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//더치체킹을 할 것이다.
	@Transactional
	public User 회원수정(Integer id, UserUpdateReqDto userUpdateReqDto) {
		//먼저 영속화를 시켜야 한다.
		User userEntity = userRepository.findById(id).get();	//1차 캐시
		String encPassword = bCryptPasswordEncoder.encode(userUpdateReqDto.getPassword());
		userEntity.setPassword(encPassword);
		userEntity.setEmail(userUpdateReqDto.getEmail());	
		return userEntity;
	}// 더티 체킹이 된다.(변했음을 감지하고 데이터 베이스에 flush)
}
