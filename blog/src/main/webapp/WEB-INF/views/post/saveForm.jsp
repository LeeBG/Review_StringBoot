<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<form action="/post" method="post">
	<div class="container">
		<div class="form-group">
			<input type="text" class="form-control" placeholder="Enter Title" name="title">
		</div>

		<div class="form-group">
			<textarea rows="" cols="5" class="form-control" name="content" id="content"></textarea>
		</div>

		<button type="submit" class="btn btn-primary">글쓰기 완료</button>
	</div>
</form>


<script>
	$('#content').summernote({
		tabsize : 2,
		height : 300
	});
</script>

<%@ include file="../layout/footer.jsp"%>