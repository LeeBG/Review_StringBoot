package com.cos.blog.web.reply.dto;

import com.cos.blog.domain.post.Post;
import com.cos.blog.domain.reply.Reply;

import lombok.Data;

@Data
public class ReplySaveReqDto {
	private String content;
	
	public Reply toEntity() {
		return Reply.builder()
				.content(content)
				.build();
	}
}
