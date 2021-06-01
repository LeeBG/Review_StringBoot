package com.cos.blog.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.domain.post.Post;
import com.cos.blog.service.PostService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PostController {
	private final PostService postService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/post")		//메인 페이지
	public String findAll(Model model) {
		List<Post> posts = postService.전체찾기();
		//@Controller는 데이터를 리턴하는 것이 아니다.
		//이것을 requestDispacher에 담아서 이동하면 된다.
		//내부적으로 코드에 짜져있다. Model
		model.addAttribute("posts",posts);	//모델에 담았다는 것 = requestDispacher에 담고 forwarding한 것과 같다
		// 왜냐하면 이 return의 주소를 viewResolver가 낚아채서 무조건 forwarding이다. 
		//기본적으로 return은 전부 forward이다. - request들고간다. model에다가 값만 집어넣으면 Page까지 끌고 가준다.
		return "post/list";
	}
	
	@GetMapping("/post/saveForm")
	public String saveForm() {
		return "post/saveForm";
	}
}
