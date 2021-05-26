package com.cos.myjpa.web.post;

import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.myjpa.domain.post.Post;
import com.cos.myjpa.domain.post.PostRepository;
import com.cos.myjpa.domain.user.User;
import com.cos.myjpa.service.PostService;
import com.cos.myjpa.web.dto.CommonRespDto;
import com.cos.myjpa.web.post.dto.PostRespDto;
import com.cos.myjpa.web.post.dto.PostSaveReqDto;
import com.cos.myjpa.web.post.dto.PostUpdateReqDto;

import lombok.RequiredArgsConstructor;

/**
 * 
 * ORM = Object Relation Mapping (자바오브젝트를 먼저 만들고 DB에 테이블을 자동 생성, 자바오브젝트로 연관관계
 * 맺음) JPA = 자바 오브젝트를 영구적으로 저장하기 위한 인터페이스(함수)
 * 
 */
//컨트롤러의 역할은 request받아서 response해주는 것만 있으면 된다.

@RequiredArgsConstructor
@RestController
public class PostController {
	private final PostRepository postRepository; // 의존성 주입
	private final HttpSession session;
	private final EntityManager em;
	private final PostService postService;
	
	// 인증만 필요
	@PostMapping("/post") // Body 데이터를 들고 올 수 있음http body = 데이터타입이 JSON
	public CommonRespDto<?> save(@RequestBody PostSaveReqDto postSaveReqDto) { // title, content

		User principal = (User) session.getAttribute("principal");
		
		if (principal == null) { // 로그인 안돼있을 시 나중에 AOP처리
			return new CommonRespDto<>(-1, "실패", null);
		}

		return new CommonRespDto<>(1, "성공", postService.글쓰기(postSaveReqDto, principal)); // 실패 하면 내부적으로 무조건 exception을 탄다.

	}

	// 인증만 필요
	@GetMapping("/post/{id}")
	public CommonRespDto<?> findById(@PathVariable Long id) {
		return new CommonRespDto<>(1, "성공,", postService.한건찾기(id)); // MessageConverter가 모든 getter를 다 호출해서 JSON으로 만들어준다.
	}

	//인증만 필요
	@GetMapping("/post")
	public CommonRespDto<?> findAll() {	
		return new CommonRespDto<>(1, "성공", postService.전체찾기());
	}

	//인증(Authentication) + 권한(Authorization)이 필요하다.
	//http://localhost:8080/post/1
	@PutMapping("/post/{id}")				//수정하기
	public CommonRespDto<?> update(@PathVariable Long id, @RequestBody PostUpdateReqDto postUpdateReqDto) {
		return new CommonRespDto<>(1, "성공", postService.수정하기(id, postUpdateReqDto));
	}
	
	//인증 + 권한이 필요하다.
	//http://localhost:8080/post/1
	@DeleteMapping("/post/{id}")
	public CommonRespDto<?> delete(@PathVariable Long id) {
		postService.삭제하기(id);
		return new CommonRespDto<>(1,"성공",null);
	}
}
