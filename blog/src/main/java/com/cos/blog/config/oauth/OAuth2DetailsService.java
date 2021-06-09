package com.cos.blog.config.oauth;

import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.cos.blog.config.auth.PrincipalDetails;
import com.cos.blog.domain.user.User;
import com.cos.blog.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OAuth2DetailsService extends DefaultOAuth2UserService {

	private final UserRepository userRepository;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println("OAuth2 로그인 진행중 ......");

		// 1. AccessToken으로 회원정보를 받았다는 의미
		OAuth2User oAuth2User = super.loadUser(userRequest);
		// userRequest가 Access토큰을 가지고 있다. - 이미 한번 로그인해서 code랑 Access토큰을 받았다.
		// oAuth2User가 회원정보를 받는다.

		System.out.println(oAuth2User.getAttributes());
		// 여기서 함수를 하나 만들어서 때린다.

		return processOAuth2User(userRequest, oAuth2User);
	}

	// 구글 로그인 프로세스
	private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
		// 1. 통합 클래스를 생성 - 누군가는 구글로 로그인하고 누군가는 카카오로 로그인할 수 있는데 로그인할 때마다 걔네들이 던지는
		// attribute정보들이 다 다르다.
		System.out.println("뭐로 로그인 되었지?" + userRequest.getClientRegistration().getClientName());// Google
		OAuth2UserInfo oAuth2UserInfo = null;

		if (userRequest.getClientRegistration().getClientName().equals("Google")) {// Provider명에 따른 분기문
			oAuth2UserInfo = new GoogleInfo(oAuth2User.getAttributes());
		} else if (userRequest.getClientRegistration().getClientName().equals("Facebook")) {

		}

		// 2 최초(DB에 있는지 여부 확인) - 회원가입+로그인 / 최초가 아닌 경우 : 로그인
		User userEntity = userRepository.findByUsername(oAuth2UserInfo.getUsername());
		
		UUID uuid = UUID.randomUUID();
		String encPassword = new BCryptPasswordEncoder().encode(uuid.toString());
		//아무도 일반적인 로그인으로 로그인 못한다.
		
		if (userEntity == null) {
			User user = User.builder()
					.username(oAuth2UserInfo.getUsername())
					.email(oAuth2UserInfo.getEmail())
					.password(encPassword)
					.build();
			userEntity = userRepository.save(user);
			return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
		} else {// 이미 회원가입이 완료되었다는 뜻(원래는 구글 정보가 변경될 수 있기 때문에 update 해야되는데 지금은 안하겠음)
			return new PrincipalDetails(userEntity, oAuth2User.getAttributes()); // PrincipalDetails로 return해도 되는 이유는 OAuth2User이기 때문이다.
		}
	}
}
