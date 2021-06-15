package com.cos.blog.config.oauth;

import java.util.Map;

public class FacebookInfo extends OAuth2UserInfo{
	
	public FacebookInfo(Map<String, Object> attributes) {
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
		return "";
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return "Facebook_"+attributes.get("id").toString();
	}

	
}
