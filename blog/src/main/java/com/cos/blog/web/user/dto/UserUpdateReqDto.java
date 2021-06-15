package com.cos.blog.web.user.dto;

import lombok.Data;

@Data
public class UserUpdateReqDto {
	
	private String username;
	private String password;
	private String email;
	
	// toEntity()를 만들지 않는 이유는 User오브젝트로 만들어서 save하는 것이 아니라 더티체킹을 할 것이기 때문이다.

}
