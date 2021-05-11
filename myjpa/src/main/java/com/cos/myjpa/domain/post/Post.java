package com.cos.myjpa.domain.post;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.myjpa.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity		//식별자가 없어서 오류 = primarykey를 넣어줘야함
public class Post {
	@Id		//PK
	@GeneratedValue(strategy = GenerationType.IDENTITY)		//아이디 자동증가
	// Table,ayto_increment,Sequence => 자동으로 찾음 h2의 전략을 모르기 때문에 Identity=>자신의 데이터베이스의 전략을 자동으로 따라감 auto는 위험함
	private Long id;
	
	@Column(length = 60, nullable = false) // 20자로 제한 // 크기를 정하지 않으면 255바이트 //not null
	private String title;
	
	//@Valid는 DTO에서만 사용
	@Lob	//대용량 데이터
	private String content;
	
	//누가 적었는지 N(게시글) To 1(User)
	@ManyToOne(fetch = FetchType.LAZY)	//연관관계 맺는 법, FK의 주인인 곳에서 적어야함
	@JoinColumn(name = "userId")	//DB테이블에는 userId
	@JsonIgnoreProperties({"posts"})
	private User user;
	//naming 전략이 user_id - application.yml에서 전략을 수정해 줘야함

	@CreationTimestamp
	private LocalDateTime createDate;		//실시간 DB저장
}
