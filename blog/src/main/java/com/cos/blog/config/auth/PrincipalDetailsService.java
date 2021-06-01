package com.cos.blog.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.blog.domain.user.User;
import com.cos.blog.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService{

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {	//username만(사용자가 있는지) - > UserDetails로 리턴
		// AuthenticationManager가 username을 꼽아준다. 얘는 무조건 UserDetails를 리턴을 AuthenticationManager가 다시 받아서 이 userDetails를 authentication객체를 만들어서
		// 이 authentication 객체를 세션에다가 저장한다
		// 패스워드를 분석안하는 이유는 내부적으로 알아서 검사해준다. - 패스워드를 건드리는 로직이 있으면 해커도 똑같이 건드릴 수 있다.
		// 외부에서 건드릴 수 없다. 완벽한 암호화가 되어있다. (완전 추상화 되어 있어서 개발자도 몰라도 된다.)
		
		User principal = userRepository.findByUsername(username);
		if(principal == null) {
			return null; //인증 실패
		}else {
			return new PrincipalDetails(principal);		//내부적으로 세션이 생긴다.
		}
			
	}

}
