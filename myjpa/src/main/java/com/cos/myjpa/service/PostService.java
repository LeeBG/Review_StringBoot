package com.cos.myjpa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.myjpa.domain.post.Post;
import com.cos.myjpa.domain.post.PostRepository;
import com.cos.myjpa.domain.user.User;
import com.cos.myjpa.web.post.dto.PostRespDto;
import com.cos.myjpa.web.post.dto.PostSaveReqDto;
import com.cos.myjpa.web.post.dto.PostUpdateReqDto;

import lombok.RequiredArgsConstructor;

// JPA 영속성 컨텍스트는 변경감지를 하는데 언제하는가? 서비스 종료시에 한다.
// 1차 캐시와 2차캐시의 비교연산 후 flush

@RequiredArgsConstructor
@Service
public class PostService {

	private final PostRepository postRepository;

	@Transactional
	public Post 글쓰기(PostSaveReqDto postSaveReqDto, User principal) {

		Post post = postSaveReqDto.toEntity();
		post.setUser(principal); // FK를 insert할 수 있다.
		// postEntity는 DB에서 들고온 데이터
		Post postEntity = postRepository.save(post);// 실패시 exception을 탄다.

		return postEntity;
	}

	@Transactional(readOnly = true)
	public PostRespDto 한건찾기(Long id) {
		// 옵셔널 get(), orElseGet(), orElseThrow()
		Post postEntity = postRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		});

		PostRespDto postRespDto = new PostRespDto(postEntity);

		return postRespDto;
	}

	@Transactional(readOnly = true)
	public List<Post> 전체찾기() {
		List<Post> postsEntity = postRepository.findAll();
		return postsEntity;
	}

	@Transactional
	public Post 수정하기(Long id, PostUpdateReqDto postUpdateReqDto) {
		// 영속화
		Post postEntity = postRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("id를 찾을 수 없습니다");
		});

		postEntity.setTitle(postUpdateReqDto.getTitle());
		postEntity.setContent(postUpdateReqDto.getContent());

		return postEntity;
	}// 트랜잭션 종료시 영속성 컨텍스트에 영속화 되어있는 모든 객체의 변경을 감지하여 변경된 아이들을 flush한다(Commit) = 더티체킹

	@Transactional
	public void 삭제하기(Long id) {	//삭제는 리턴할 필요가 없다 삭제하다가 오류가나면 GlobalException처리
		postRepository.deleteById(id);
	}
}
