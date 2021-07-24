package com.cos.blog.web.reply;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.domain.post.Post;
import com.cos.blog.domain.post.PostRepository;
import com.cos.blog.domain.reply.ReplyRepository;
import com.cos.blog.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ReplyControllerTest {
	
	private final PostRepository postRepository;
	private final ReplyRepository replyRepository;
	
	//게시글 상세보기할 때 (user,post,reply들)
	@Transactional(readOnly = true)
	@GetMapping("/test/post/{id}")
	public CMRespDto<?> testPost(@PathVariable int id){
		Post postEntity = postRepository.findById(id).get();
		return new CMRespDto<>(1,postEntity);
	}

}

