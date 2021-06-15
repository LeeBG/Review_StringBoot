package com.cos.blog.config.oauth;

import java.util.Map;

public class KakaoInfo extends OAuth2UserInfo{
	
	public KakaoInfo(Map<String, Object> attributes) {
		super(attributes);
	}

	@Override
	public String getId() {
		return attributes.get("id").toString();
	}

	@Override
	public String getName() {
		Map<String, Object> temp = (Map)attributes.get("properties");
		return temp.get("nickname").toString();
	}

	@Override
	public String getEmail() {
		Map<String, Object> temp = (Map)attributes.get("kakao_account");
		return temp.get("email").toString();
	}

	@Override
	public String getImageUrl() {
		Map<String, Object> temp = (Map)attributes.get("properties");
		return temp.get("profile_image").toString();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return "Kakao_"+attributes.get("id").toString();
	}

	
}
