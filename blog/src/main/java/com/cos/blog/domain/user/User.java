package com.cos.blog.domain.user;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;//시퀀스, auto_increment
	
	@Column(nullable = false, length = 100,unique = true)
	private String username;
	
	@Column(nullable = true, length = 100)
	private String password;
	
	@Column(nullable = false,length = 50)
	private String email;
	
	@Enumerated(EnumType.STRING)	
	private RoleType role; //ADMIN, USER 
	//RoleType은 데이터베이스가 이해를 못함. - @Enumerated사용해서 String으로 받을수 있음
	
	@CreationTimestamp
	private Timestamp createDate;
}
