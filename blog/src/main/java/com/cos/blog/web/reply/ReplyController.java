package com.cos.blog.web.reply;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.blog.config.auth.PrincipalDetails;
import com.cos.blog.domain.reply.Reply;
import com.cos.blog.service.PostService;
import com.cos.blog.service.ReplyService;
import com.cos.blog.web.dto.CMRespDto;
import com.cos.blog.web.reply.dto.ReplySaveReqDto;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class ReplyController {
	
	private final ReplyService replyService;
	private final PostService postService;
	@DeleteMapping("/reply/{id}")
	public @ResponseBody CMRespDto<?> deleteById(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails){
		//모든 컨트롤러에 삭제하기, 수정하기는 무조건 동일인물이 로그인 했는지 확인한다!!
		int result = replyService.삭제하기(id,principalDetails.getUser().getId());
		return new CMRespDto<>(result,null);
	}
	
	@PostMapping("/reply/{id}")
	public @ResponseBody CMRespDto<?> save(@PathVariable int id, @RequestBody ReplySaveReqDto replySaveReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails){
		Reply reply = replySaveReqDto.toEntity();
		reply.setUser(principalDetails.getUser());
		reply.setPost(postService.상세보기(id));
		Reply replyEntity = replyService.댓글쓰기(reply);
		
		if(replyEntity==null) {
			return new CMRespDto<>(-1,null);
		}else {
			return new CMRespDto<>(1,replyEntity);
		}	
	}
}
