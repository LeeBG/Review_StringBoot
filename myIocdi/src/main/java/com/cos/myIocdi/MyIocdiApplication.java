package com.cos.myIocdi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyIocdiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyIocdiApplication.class, args);
	}
	
	//@Bean		//IoC (리턴값을 IoC컨테이너에 저장함)
	public Robot getRobot() {
		return new Robot(); 
	}

}
