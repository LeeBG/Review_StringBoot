package com.cos.jwtex1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cos.jwtex1.config.jwt.JwtLoginFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	public BCryptPasswordEncoder PasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	//Bearer Auth 인증방식
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.addFilter(new JwtLoginFilter(authenticationManager()))
		//.addFilter(null)
		.csrf().disable()
		.formLogin().disable()
		.httpBasic().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeRequests()
		.antMatchers("/api/v1/user/**").access("hasRole('ROLE_USER')")
		.antMatchers("/api/v1/admin/**").access("hasRole('ROLE_ADMIN')")
		.anyRequest().permitAll();
	}
}
