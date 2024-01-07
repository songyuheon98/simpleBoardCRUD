<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container">
		<h2>Simple Board</h2>
		<div class="panel panel-info">
			<div class="panel-heading">BOARD</div>
			<div class="panel-body">
				<form action="/simpleBoard/simpleBoardUpdate" method="post">
					<input type="hidden" name="id" value="${vo.id}" />
					<table class="table table-bordered">
						<tr>
							<td>제목</td>
							<td><input type="text" name="title" class="form-control"
								value="${vo.title}" /></td>
						</tr>
						<tr>
							<td>내용</td>
							<td><textarea rows="5" name="content" class="form-control">${vo.content}</textarea></td>
						</tr>
						<tr>
							<td>작성자</td>
							<td><input type="text" class="form-control"
								value="${vo.writer}" readonly="readonly" /></td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<button type="submit" class="btn btn-primary btn-sm">수정</button>
								<button type="reset" class="btn btn-warning btn-sm">취소</button>
								<button type="button" class="btn btn-info btn-sm"
									onclick="location.href='/simpleBoard/home'">목록</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div class="panel-footer">SYH</div>
		</div>
	</div>

</body>
</html>