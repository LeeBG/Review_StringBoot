package com.cos.blog.config.oauth;

import java.util.Map;

public abstract class OAuth2UserInfo {		//추상클래스로 만든다.
	protected Map<String, Object> attributes;

	public OAuth2UserInfo(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	
	public Map<String, Object> getAttributes(){
		return attributes;
	}
	// 구글이든 카카오든 페이스북이든 어디든 이 네가지정보가 있으면 된다고 내가 정의를 내리는 것이다. 
	public abstract String getId();
	public abstract String getName();
	public abstract String getEmail();
	public abstract String getImageUrl();
	public abstract String getUsername();
	
}//추상으로 만드는 이유는 - 오버라이딩해서 extends해서 각자 따로 만들 것인데 강제성을 부여하기 위해서이다.
