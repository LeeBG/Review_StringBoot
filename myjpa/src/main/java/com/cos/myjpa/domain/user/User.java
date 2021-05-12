package com.cos.myjpa.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;

import com.cos.myjpa.domain.post.Post;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	
	//역방향 매핑
	@JsonIgnoreProperties({"user"})
	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
	private List<Post> posts;
	
}
