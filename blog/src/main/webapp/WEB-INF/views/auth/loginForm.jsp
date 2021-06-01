<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Blog 로그인</title>
</head>
<body>
	<h1>로그인 페이지</h1>
	<hr />
	<form action="/login" method="POST">
		<input type="text" placeholder="Username" name="username" /><br/>
		 <input type="password" placeholder="password" name="password" /><br/>
		<button>로그인</button><br/>
	</form>
	아직 회원가입을 하지 않으셨나요? <a href="/joinForm">회원가입</a>
</body>
</html>