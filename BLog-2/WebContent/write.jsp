<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.blog.ex.*"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="assets/css/main.css" />
<title>블로그</title>
<script src="ckeditor/ckeditor.js"></script>
</head>
<body>
	<%
		String userno = session.getAttribute("userno").toString();
		String status = session.getAttribute("status").toString();
	%>
	<div style="float: right;">
		<a href="blog_Main.do?userno=<%=userno%>">리스트로 돌아가기</a>
	</div>

	<h2>등록하기</h2>
	<hr>


	<form action="writeOut.do">
		제목 : <input type="text" name="title"
			style="width: 20em; position: relative; left: 36px;"><br>
		<textarea name="story" id="editor1"
			style="height: 20em; width: 20em; position: relative; left: 36px;"></textarea>
		<script>
			CKEDITOR
					.replace(
							'editor1',
							{
								allowedContent : true, // html 태그 자동삭제 방지 설정
								//width: 640,
								//height: 600
								filebrowserUploadUrl : '${pageContext.request.contextPath}/FileUpload.jsp'

							});
		</script>





		<input type="hidden" value="<%=status%>" name="manipulation_read">
		<input type="hidden" name="writepost" value="<%=userno%>"> <input
			type="submit" value="등록">&nbsp; <input type="reset"
			value="초기화">
	</form>

</body>
</html>

