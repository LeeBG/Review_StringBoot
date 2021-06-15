<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<body>
	<div class="container">
		<form action="/login" class="was-validated" method="POST">
			<div class="form-group" style="width: 300px">
				<label for="username">Username:</label> <input type="text" class="form-control" id="username" placeholder="Enter username"
					name="username" required>
				<div class="valid-feedback">Valid.</div>
				<div class="invalid-feedback">Please fill out this field.</div>
			</div>
			<div class="form-group" style="width: 300px">
				<label for="password">Password:</label> <input type="password" class="form-control" id="password" placeholder="Enter password"
					name="password" required>
				<div class="valid-feedback">Valid.</div>
				<div class="invalid-feedback">Please fill out this field.</div>
			</div>
			<button type="submit" class="btn btn-primary">로그인</button>
		</form>
	</div>
	<hr />
	<div class="container">
		아직 회원가입을 하지 않으셨나요? <a href="/joinForm">회원가입</a><br/><br/>
		<a href="/oauth2/authorization/google"><img src='https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=http%3A%2F%2Fcfile27.uf.tistory.com%2Fimage%2F998689465C3D7D1217F053' width=200 border=0></a><br/>
		<a href="/oauth2/authorization/facebook"><img src='https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=http%3A%2F%2Fcfile27.uf.tistory.com%2Fimage%2F991159465C3D7D110F889A' width=200 border=0></a></br/>
		<a href="/oauth2/authorization/naver"><img src='https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=http%3A%2F%2Fcfile24.uf.tistory.com%2Fimage%2F99580C465C3D7D130CF754' width=200 border=0></a></br/>
		<a href="/oauth2/authorization/kakao"><img src='https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=http%3A%2F%2Fcfile28.uf.tistory.com%2Fimage%2F99BEE8465C3D7D12140EAC' width=200 border=0></a></br/>
	</div>
	<%@ include file="../layout/footer.jsp"%>