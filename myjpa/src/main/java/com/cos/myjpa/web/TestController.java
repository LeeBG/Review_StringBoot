package com.cos.myjpa.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.myjpa.domain.post.Post;
import com.cos.myjpa.domain.post.PostRepository;
import com.cos.myjpa.web.dto.CommonRespDto;
import com.cos.myjpa.web.post.dto.PostSaveReqDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TestController {
	
	private final PostRepository postRepository;	//의존성 주입
	
	@PostMapping("/test/post") //Body 데이터를 들고 올 수 있음http body = 데이터타입이 JSON
	public CommonRespDto<?> Save(@RequestBody PostSaveReqDto postSaveReqDto) { // title, content 
		//@RequestBody Post post 이렇게 절대 하지 말것
//		Post post = new Post();
//		post.setTitle(postSaveReqDto.getTitle());
//		post.setContent(postSaveReqDto.getContent());
		//Post오브젝트만 save되기 때문에 toEntity로 오브젝트 변환을 한 후 save응답
		Post postEntity = postRepository.save(postSaveReqDto.toEntity());
		//postEntity는 DB에서 들고온 데이터 
		return new CommonRespDto<>(1,"성공",postEntity);	//실패 하면 내부적으로 무조건 exception을 탄다.
	}
}
