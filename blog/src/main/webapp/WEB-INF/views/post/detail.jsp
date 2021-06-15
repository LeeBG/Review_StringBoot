<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container" style="border: 1px solid gray; padding: 10px">
	<div>
		<c:if test="${post.user.id == principal.user.id}">
			<a href="/post/updateForm" class="btn btn-warning">수정</a>
			<button id="btn-delete" class="btn btn-danger">삭제</button>
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
		console.log(e.currentTarget);
	})
</script>
<%@ include file="../layout/footer.jsp"%>