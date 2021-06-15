package com.cos.blog.config.oauth;

import java.util.Map;

public class NaverInfo extends OAuth2UserInfo{
	
	public NaverInfo(Map<String, Object> attributes) {
		super(attributes);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getId() {
		
		return attributes.get("id").toString();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return attributes.get("name").toString();
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return attributes.get("email").toString();
	}

	@Override
	public String getImageUrl() {
		// TODO Auto-generated method stub
		return attributes.get("").toString();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return "Naver_"+attributes.get("id").toString();
	}

	
}
