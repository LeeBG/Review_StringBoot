package com.cos.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cos.blog.domain.post.Post;
import com.cos.blog.domain.post.PostRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {
	private final PostRepository postRepository;
	
	public List<Post> 전체찾기(){
		return postRepository.findAll();
	}
}
