package com.cos.myjpa.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cos.myjpa.filter.MyAuthenticationFilter;


//web.xml로 보면 된다
//@Configuration이라는 어노테이션을 붙여놓으면
//설정파일이네?? web.xml에 추가해주자 라고 약속으로 정해져 있다. 
//
@Configuration
public class FilterConfig {
	@Bean	//return 되는 어떤 값을 IoC에 등록
	public FilterRegistrationBean<MyAuthenticationFilter> authenticationFilterRegister() {
		FilterRegistrationBean<MyAuthenticationFilter> bean =
				new FilterRegistrationBean<>(/*내가 만든 필터*/
						new MyAuthenticationFilter());
		bean.addUrlPatterns("/test");	//모든 주소에 대해 필터를 적용한다.
		bean.setOrder(0); //낮은 번호가 먼저 실행
		return bean;//필터 등록 끝
	}
}
