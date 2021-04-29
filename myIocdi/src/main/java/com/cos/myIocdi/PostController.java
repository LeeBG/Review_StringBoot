package com.cos.myIocdi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Ioc = 제어의 역전
 * DI = 의존성 주입
 */

//Component(용도 없음), Configuration(설정파일), Service(서비스)
//Repository(레포지토리) , Bean

//RestController,Controller -> Ioc등록 new PostController(주입(Ioc컨테이너의);

//@AutoWired - 가져와서 쓰는 방법 Robot

//RestController,Controller -> IoC(싱글톤패턴)등록 new PostController(주입);
@RestController
public class PostController {
	@Autowired
	private final Robot robot; 	//DI
	
	public PostController(Robot robot) {
		this.robot = robot;
	}
	//http://localhost:8080/
	@GetMapping("/")
	public String home() {
		return "home"+robot.getName();
	}
}
