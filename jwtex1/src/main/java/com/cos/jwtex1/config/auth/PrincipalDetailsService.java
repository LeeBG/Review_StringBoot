package com.cos.jwtex1.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.jwtex1.domain.User;
import com.cos.jwtex1.domain.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService{	//기존의 UserDetailsService가 바꿔치기 된다. PrincipalDetailsService

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("loadByUsername실행됨.");
		
		User userEntity = userRepository.findByUsername(username);	//영속화
		if(userEntity == null) {
			return null;
		}else {
			return new PrincipalDetails(userEntity);
		}
		
	}

}
