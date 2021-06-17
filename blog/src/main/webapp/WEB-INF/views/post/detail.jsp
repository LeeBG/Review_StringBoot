<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container" style="border: 1px solid gray; padding: 10px">
	<div>
		<button class="btn btn-secondary" onclick="history.go(-1)">뒤로가기</button>
		<c:if test="${post.user.id == principal.user.id}">
			<a href="/post/${post.id}/updateForm/" class="btn btn-warning">수정</a>
			<button id="btn-delete" type="submit" class="btn btn-danger" value="${post.id}">삭제</button>
		</c:if>
		<br/><br/>
		글 번호 : ${post.id} <br /> 작성자 : ${post.user.username}
		<hr />
		<div>
			<h3>${post.title}</h3>
		</div>
		<hr />
		<div>
			<div>${post.content}</div>
		</div>
	</div>
</div>
<script>
	$("#btn-delete").on("click",(e)=>{
		
		let id = e.currentTarget.value;
		
		$.ajax({
			type: "DELETE",
			url: "/post/"+id,
			dateType: "json"
		}).done(res=>{
			if(res.statusCode === 1){
				console.log(res.statusCode);
				alert("삭제에 성공하였습니다.");
				location.href = "/";
			}else{
				alert("삭제에 실패했습니다.")
			}
		});
	});
</script>
<%@ include file="../layout/footer.jsp"%>