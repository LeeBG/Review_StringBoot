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

	@PostMapping("/post") // Body 데이터를 들고 올 수 있음http body = 데이터타입이 JSON
	public CommonRespDto<?> save(@RequestBody PostSaveReqDto postSaveReqDto) { // title, content

		User principal = (User) session.getAttribute("principal");
		if (principal == null) { // 로그인 안돼있을 시 나중에 AOP처리
			return new CommonRespDto<>(-1, "실패", null);
		}

		Post post = postSaveReqDto.toEntity();
		post.setUser(principal);
		// postEntity는 DB에서 들고온 데이터
		Post postEntity = postRepository.save(post);// 실패시 exception을 탄다.

		return new CommonRespDto<>(1, "성공", postEntity); // 실패 하면 내부적으로 무조건 exception을 탄다.

	}

	@GetMapping("/post/{id}")
	public CommonRespDto<?> findById(@PathVariable Long id) {
		// 옵셔널 get(), orElseGet(), orElseThrow()
		Post postEntity = postRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		});
		
		PostRespDto postRespDto = new PostRespDto(postEntity);
		
		return new CommonRespDto<>(1, "성공,", postRespDto); // MessageConverter가 모든 getter를 다 호출해서 JSON으로 만들어준다.
	}

	// 전체찾기
	// AOP
	@GetMapping("/post")
	public CommonRespDto<?> findAll() {
		List<Post> postsEntity = postRepository.findAll();
		return new CommonRespDto<>(1, "성공", postsEntity);
	}

	//http://localhost:8080/post/1
	@PutMapping("/post/{id}")
	public CommonRespDto<?> update(@PathVariable Long id, @RequestBody PostUpdateReqDto postUpdateReqDto) {
//		영속화 - 데이터베이스에 있는것을 들고와서 영속성 컨텍스트에 넣은 것을 말한다. - 캐싱(1차캐시)
//		Post p = new Post();
//		em.persist(p);
//		em.createNativeQuery("select * from post");
		
		Post postEntity = postRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("id를 찾을 수 없습니다");
		});

		postEntity.setTitle(postUpdateReqDto.getTitle());
		postEntity.setContent(postUpdateReqDto.getContent());

		Post postUpdateEntity = postRepository.save(postEntity);// 더티 체킹을 사용해야 하는데 그러려면 @Service 만들어야 가능함!

		return new CommonRespDto<>(1, "성공", postUpdateEntity);

	}
	
	//http://localhost:8080/post/1
	@DeleteMapping("/post/{id}")
	public CommonRespDto<?> delete(@PathVariable Long id) {
		postRepository.deleteById(id);
		return new CommonRespDto<>(1,"성공",null);
	}
}
