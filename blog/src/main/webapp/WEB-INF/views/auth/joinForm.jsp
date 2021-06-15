<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<body>
	<div class="container">
		<form action="/join" class="was-validated" method="POST">
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
			<div class="form-group" style="width: 300px">
				<label for="email">Email:</label> <input type="email" class="form-control" id="email" placeholder="Enter Email" name="email"
					required>
				<div class="valid-feedback">Valid.</div>
				<div class="invalid-feedback">Please fill out this field.</div>
			</div>
			<button type="submit" class="btn btn-primary">회원가입</button>
		</form>
	</div>
	<hr />
	<div class="container">
		이미 회원가입이 되셨나요? <a href="/loginForm">로그인</a>
	</div>
	<%@ include file="../layout/footer.jsp"%>