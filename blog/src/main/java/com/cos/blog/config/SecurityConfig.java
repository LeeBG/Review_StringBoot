package com.cos.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration  //IoC등록
@EnableWebSecurity // 내가 원래
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	//왜 어댑터를 썼을까? 어댑터가 붙었다는 것은 함수를 걸러준다는 것이다. 우리에게 필요한것 하나만 구현할 것이다.
	
	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
		.antMatchers("/user","/post").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
		.antMatchers("/admin").access("hasRole('ROLE_ADMIN')")
		.anyRequest().permitAll()
		.and()
		.formLogin()
		.loginPage("/loginForm")
		.loginProcessingUrl("/login");	//Spring Security가 /login이라는 주소가 POST방식으로 들어오면 낚아챈다.(get방식은 낚아채지 않는다.)
	}
}
