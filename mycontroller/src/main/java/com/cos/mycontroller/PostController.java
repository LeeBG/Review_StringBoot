package com.cos.mycontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.mycontroller.domain.User;

//localhost:8080
//디스패쳐가 PostController를 찾음
//모든 메서드를 다 검색해서 /를 찾는다.

@RestController
public class PostController {
	@GetMapping("/")
	public String home() {
		return "<h1>데이터</h1>";
	}
	//연습 : (1) GET : /test/post -> 응답 : "포스트"
	
	@GetMapping("/test/post")
	public String post() {
		return "포스트";
	}
	//쿼리스트링 = 주소?username=ssar?password=1234
	@GetMapping("/test/post/data")
	public String testPostData(String username,String password) {
		return "username :"+username+" password: "+password; 
	}
	
	//Get방식은 데이터를 전송하는데에 목적이 있는 것이 아니라 데이터 요구(select)에 목적이 있음.
	//데이터 전송(insert)가 목적이면 Post를 사용
	@PostMapping("/test/post")
	public String testPost2(String username, String password) {
		return "username:"+username+" password: "+password;
	}
	
	@PostMapping("/test/post/object")
	public User testPostObject(User user) {
		return user;
	}
}
