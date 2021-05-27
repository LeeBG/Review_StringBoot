package com.cos.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration  //IoC등록
@EnableWebSecurity // 내가 원래
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	//왜 어댑터를 썼을까? 어댑터가 붙었다는 것은 함수를 걸러준다는 것이다. 우리에게 필요한것 하나만 구현할 것이다.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/user","/post").authenticated()
		.anyRequest().permitAll()
		.and()
		.formLogin()
		.loginPage("/loginForm");  // 데이터를 x-www-form-urlencoded로 전송을 해야한다. json던지면 security가 못받음(내부적으로 id,password받아서 검증함)
	}
}
