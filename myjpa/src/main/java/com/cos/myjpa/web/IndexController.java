package com.cos.myjpa.web;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cos.myjpa.web.user.dto.UserReqDto;
import com.google.gson.Gson;

@RestController
public class IndexController {
	@GetMapping("/test")
	public String index() {
		RestTemplate rt = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		UserReqDto dto = new UserReqDto("ssar","1234");
		Gson gson = new Gson();
		String userJson = gson.toJson(dto);		//Json으로 변환
		HttpEntity<String> entity = new HttpEntity<>(userJson,headers);
		ResponseEntity response = rt.exchange("http://localhost:8080/login", HttpMethod.POST,entity, String.class);
		System.out.println(response.getBody());
		return "ok";
		
	}
}
