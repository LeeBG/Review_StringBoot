package com.cos.myjpa.web.post;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.myjpa.domain.post.Post;
import com.cos.myjpa.domain.post.PostRepository;
import com.cos.myjpa.domain.user.User;
import com.cos.myjpa.web.dto.CommonRespDto;
import com.cos.myjpa.web.post.dto.PostSaveReqDto;

import lombok.RequiredArgsConstructor;

/**
 * 
 * ORM = Object Relation Mapping (자바오브젝트를 먼저 만들고 DB에 테이블을 자동 생성, 자바오브젝트로 연관관계
 * 맺음) JPA = 자바 오브젝트를 영구적으로 저장하기 위한 인터페이스(함수)
 * 
 */
@RequiredArgsConstructor
@RestController
public class PostController {
	private final PostRepository postRepository; // 의존성 주입
	private final HttpSession session;
 
	@PostMapping("/post") // Body 데이터를 들고 올 수 있음http body = 데이터타입이 JSON
	public CommonRespDto<?> save(@RequestBody PostSaveReqDto postSaveReqDto) { // title, content
		
		User principal = (User) session.getAttribute("principal");
		
		if (principal == null) { // 로그인 안돼있을 시 나중에 AOP처리
			return new CommonRespDto<>(-1, "실패", null);
		}
		
		Post post = postSaveReqDto.toEntity();
		post.setUser(principal);
		// postEntity는 DB에서 들고온 데이터
		Post postEntity = postRepository.save(post);//실패시 exception을 탄다.
		
		return new CommonRespDto<>(1, "성공", postEntity); // 실패 하면 내부적으로 무조건 exception을 탄다.

	}
	
	@GetMapping("/post/{id}")
	public CommonRespDto<?> findById(@PathVariable Long id){
		
		Post postEntity = postRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		});
		return new CommonRespDto<>(1,"성공,",postEntity);
	}
}
