package com.cos.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

	@GetMapping("/user")
	public @ResponseBody String hello() {		//@ResponseBody를 붙이면 @Controller+@ResponseBody = @RestController(데이터 리턴)
		return "User";
	}
	
	@GetMapping({"","/"})
	public String home() {
		return "index";			//viewResolver발동 /WEB-INF/view/index.jsp [prefix+index+suffix]
	}
}

