<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<body>
	<div class="container">
		<form>
		    <div class="form-group" style="width: 300px">
		    	<input type="hidden" id="id" value="${id}">
		    </div>
			<div class="form-group" style="width: 300px">
				<label for="username">Username:</label> <input type="text" value="${principal.user.username}" class="form-control" id="username" placeholder="Edit username"
					name="username" readonly="readonly" required>
				
			</div>
			<div class="form-group" style="width: 300px">
				<label for="password">Password:</label> <input type="password" value="" class="form-control" id="password" placeholder="Edit password"
					name="password" required>
				
			</div>
			<div class="form-group" style="width: 300px">
				<label for="email">Email:</label> <input type="email" value="${principal.user.email}" class="form-control" id="email" placeholder="Edit Email" name="email"
					required>
				
			</div>
			<button id="btn-update" class="btn btn-primary" type="button">회원수정</button>
		</form>
	</div>
	<script>
	
		$("#btn-update").on("click",(e)=> {		//e는 클릭 이벤트
			e.preventDefault();	   				//form태그 액션을 안타게 막아버리는 것이다.
			let data = {
				username: $("#username").val(),
				password: $("#password").val(),
				email: $("#email").val(),
			};
			
			let id = $("#id").val();
			
			console.log(data);
			
			$.ajax({
				type:"PUT",
				url:"/user/"+id,
				data:JSON.stringify(data),
				contentType:"application/json; charset=utf-8",
				dataType:"json"
			}).done((res)=>{
				console.log(res);
				if(res.statusCode === 1){	//정상
					alert("수정에 성공하였습니다.");
					location.href = "/";
				}else{
					alert("수정에 실패하였습니다.");
				}
			});
			
		}); //리스너 - 지켜보고 있다가 클릭이 되면 함수를 넣어준다.
	</script>
	<%@ include file="../layout/footer.jsp"%>