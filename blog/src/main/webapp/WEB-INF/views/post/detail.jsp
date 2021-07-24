<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container" style="border: 1px solid gray; padding: 10px">
	<div>
		<button class="btn btn-secondary" onClick="history.go(-1)">뒤로가기</button>
		<c:if test="${post.user.id == principal.user.id}">
			<a href="/post/${post.id}/updateForm/" class="btn btn-warning">수정</a>
			<button id="btn-delete" type="submit" class="btn btn-danger" value="${post.id}">삭제</button>
		</c:if>
		<br /> <br /> 글 번호 : ${post.id} <br /> 작성자 : ${post.user.username}
		<hr />
		<div>
			<h3>${post.title}</h3>
		</div>
		<hr />
		<div>
			<div>${post.content}</div>
		</div>
	</div>
	<!-- 댓글 시작 -->
	<div class="card">
		<form>
			<div class="card-body">
				<textarea id="reply-content" class="form-control" rows="1"></textarea>
			</div>
			<div class="card-footer">
				<button id="btn-reply-save" type="submit" class="btn btn-primary" value="${post.id}">등록</button>
			</div>
		</form>
	</div>
	<br />

	<div class="card">
		<div class="card-header">댓글 리스트</div>
		<ul id="reply-box" class="list-group">
			<c:forEach var="reply" items="${post.replys}">
				<li id="reply-${reply.id}" class="list-group-item d-flex justify-content-between">
					<div>${reply.content}</div> <!-- 레이지로딩 시작 - 이유는 getter가 호출되니까(세션이 열려있음 open-in-view가 true이기 때문이다.) -->
					<div class="d-flex">
						<div class="font-italic">작성자 : ${reply.user.username} &nbsp;</div>
						<!-- 댓글을 쓴 사용자와 현재 로그인한 사용자가 같아야 삭제가 가능하다. -->
						<c:if test="${reply.user.id == principal.user.id}">
							<button onClick="deleteReply(${reply.id})" class="badge">삭제</button>
						</c:if>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
	<!-- 댓글 끝 -->
</div>
<script>
	function deleteReply(id){
		$.ajax({
			type: "DELETE",
			url: "/reply/"+id,
			dataType: "json"
		}).done((res)=>{
			console.log(res);
			if(res.statusCode === 1){
				$("#reply-"+id).remove();
			}else{
				alert("삭제에 실패하였습니다.");
			}
		});
	}
</script>
<script>
	$("#btn-delete").on("click",(e)=>{
		let id = e.currentTarget.value;
		$.ajax({
			type:"DELETE",
			url:"/post/"+id,
			dataType:"json"
		}).done(res=>{
			if(res.statusCode === 1){
				alert("삭제에 성공하였습니다.");
				history.back();
			} else {
				alert("삭제에 실패했습니다.");
			}
		});
	});
</script>
<script>
	$("#btn-reply-save").on("click",(e)=>{
		e.preventDefault();
		let id = e.currentTarget.value;
		let data = {
			content: $("#reply-content").val()
		};
	
		$.ajax({
			type:"POST",
			url:"/reply/"+id,
			data:JSON.stringify(data),
			contentType:"application/json; charset=utf-8",
			dataType:"json"
		}).done((res)=>{
			console.log(res);
			if(res.statusCode === 1){
				alert("댓글에 성공하였습니다.");
				history.go(0);
			}else{
				alert("댓글에 실패하였습니다.");
				history.go(0);
			}
		});
		
	});
</script>
<%@ include file="../layout/footer.jsp"%>