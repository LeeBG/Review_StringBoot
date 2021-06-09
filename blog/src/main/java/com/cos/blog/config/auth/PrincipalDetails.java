package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.cos.blog.domain.user.User;

import lombok.Data;

@Data // 외부에서 getUser()하기 위해서
public class PrincipalDetails implements UserDetails, OAuth2User { // 시큐리티는 이 함수들을 때리지 내가 만든 객체는 궁금하지 않다.

	private User user; // Composition
	private Map<String, Object> attributes; // OAuth 제공자로 부터 받은 회원 정보 - 왜 Map으로 받냐면 그래야 모든 데이터를 다 받을 수 있다.

	public PrincipalDetails(User user) { // DB에서 User객체를 받으면 PrincipalDetails 객체를 만들어서 거기에다가 쏙 넣어주면 PrincipalDetails를
											// 리턴해주면된다.
		this.user = user;
	}

	public PrincipalDetails(User user,Map<String, Object> attributes) { //오버로딩
		this.user = user;
		this.attributes = attributes;
	}

	@Override
	public Map<String, Object> getAttributes() { // OAuth2User는 getAttributes()
		return attributes;
	}

	@Override
	public String getName() { // getName()을 가진다.
		return "모른다.";
	}

	@Override
	public String getPassword() { // password는 알려줘야한다. (스프링 시큐리티가 알아서 검증해준다.)
		// TODO Auto-generated method stub
		return user.getPassword(); // 이런식으로 해야 시큐리티가 관리해줄 수 있다.
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() { // 해당 계정이 만료되었는지 물어본다. (예) 1년동안 안쓰면 휴면 -
		return true; // true로 두면 영원히 만료 안된다. - 실제 서비스할 떄에는 이런거 구현해야한다.
	}

	@Override
	public boolean isAccountNonLocked() { // (예)ID/PASSWORD 세번 틀리면 잠길 떄
		return true; // 잠김 없음
	}

	@Override
	public boolean isCredentialsNonExpired() { // 비밀번호 만료
		return true; // 위의 함수중 하나라도 false되어있으면 절대 로그인 되지 않을 것이다.
	}

	@Override
	public boolean isEnabled() { // 계정 활성화
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { // 권한
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		// 모든 ArrayList들은 전부 다 Collection의 자식이다.
		collectors.add(() -> "ROLE_" + user.getRole().toString());
		return collectors; // collector에 무엇을 넣어야 하냐면 권한을 넣어 줘야한다.
	}

}
