package com.cos.myjpa.web;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

@RestController
@RequiredArgsConstructor
public class TestController {
	
	private final PostRepository postRepository;	//의존성 주입
	
	@PostMapping("/test/post") //Body 데이터를 들고 올 수 있음http body = 데이터타입이 JSON
	public CommonRespDto<?> Save(@RequestBody PostSaveReqDto postSaveReqDto) { // title, content 
		//원래는 세션값을 넣어야 함.
		User user = new User(1L,"ssar","1234","ssar@nate.com",LocalDateTime.now());
		
		Post postEntity = postRepository.save(postSaveReqDto.toEntity());
		//postEntity는 DB에서 들고온 데이터
		postEntity.setUser(user);
		return new CommonRespDto<>(1,"성공",postEntity);	//실패 하면 내부적으로 무조건 exception을 탄다.
	}
	
	//전체찾기
	@GetMapping("/test/post")
	public CommonRespDto<?> findAll(){
		List<Post> postsEntity = postRepository.findAll();
		return new CommonRespDto<>(1,"성공",postsEntity);
	}
	
	//id로 한 건 찾기
	@GetMapping("/test/post/{id}")
	public CommonRespDto<?> findById(@PathVariable Long id){
		
		Post postEntity = postRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		});
		
		return new CommonRespDto<>(1,"성공,",postEntity);
	}
}
