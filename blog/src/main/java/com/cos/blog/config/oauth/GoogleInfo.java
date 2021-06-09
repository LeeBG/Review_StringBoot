package com.cos.blog.config.oauth;

import java.util.Map;

public class GoogleInfo extends OAuth2UserInfo{
	
	public GoogleInfo(Map<String, Object> attributes) {
		super(attributes);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getId() {
		
		return attributes.get("sub").toString();
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
		return attributes.get("picture").toString();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return "Google_"+attributes.get("sub").toString();
	}

	
}
