package com.cos.mycontroller.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	//1. username=ssar&password=1234 = > 필드있네???
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	//2.setter호출
	public void setUsername(String username) {
		this.username=username; 
	}
	public String getPassword() {
		return password;
	}
	//2.setter호출
	public void setPassword(String password) {
		this.password=password;
	}
}
