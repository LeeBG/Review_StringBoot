package com.cos.myjpa.web.post.dto;

import com.cos.myjpa.domain.post.Post;

import lombok.Data;

@Data
public class PostUpdateReqDto {
	private String title;
	private String content;
	//Dto에는 항상 다시 오브젝트(원모델)로 변경시켜주는 함수가 필요함
	//매번 적는게 귀찮기 때문에 toEntity로 만드는 것임
	public Post toEntity() {
		return Post.builder()
				.title(title)
				.content(content)
				.build();
	}
}
