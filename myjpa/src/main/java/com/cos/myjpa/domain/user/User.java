package com.cos.myjpa.domain.user;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;

import com.cos.myjpa.domain.post.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity		//식별자가 없어서 오류 = primarykey를 넣어줘야함
@Builder
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)		//아이디 자동증가
	private Long id;
	
	private String username;
	private String password;
	private String email;
	
	@CreationTimestamp
	private LocalDateTime createDate;
	
	
}
