package com.cos.blog.web.post;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.blog.config.auth.PrincipalDetails;
import com.cos.blog.domain.post.Post;
import com.cos.blog.service.PostService;
import com.cos.blog.web.post.dto.PostSaveReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PostController {
	private final PostService postService;

	@PostMapping("/post")
	public String save(PostSaveReqDto postSaveReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		Post post = postSaveReqDto.toEntity();
		post.setUser(principalDetails.getUser());

		Post postEntity = postService.글쓰기(post);
		System.out.println(postEntity.toString());

		if (postEntity == null) {
			return "post/saveForm";
		} else {
			return "redirect:/";
		}
	}

	@GetMapping("/") // 메인 페이지
	public String findAll(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 5) Pageable pageable,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		Page<Post> posts = postService.전체찾기(pageable);
		model.addAttribute("posts", posts); // 모델에 담았다는 것 = requestDispacher에 담고 forwarding한 것과 같다
		return "post/list";
	}

	@GetMapping("/post/saveForm")
	public String saveForm() {
		return "post/saveForm";
	}
	
	@GetMapping("/post/{id}")
	public String detail(@PathVariable Integer id, Model model) {
		Post postEntity = postService.상세보기(id);
		model.addAttribute("post",postEntity);
		return "post/detail";		//View Resolver가 발동을 한다. - jsp파일을 찾아줄 것이다.
		
	}
}
