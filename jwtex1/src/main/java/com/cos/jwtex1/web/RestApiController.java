package com.cos.jwtex1.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("api/v1")
@RestController
public class RestApiController {
	
	@GetMapping({"","/"})
	public String home() {
		return "<h1>Home<h1>";
	}
	
	@GetMapping("/user")
	public String user() {
		return "<h1>User<h1>";
	}
	
	@PostMapping("/user")
	public String userPost() {
		return "<h1>UserPost<h1>";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "<h1>Admin<h1>";
	}
	
}
