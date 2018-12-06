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
	<div style="float: right;">
		<a href="javascript:history.back()">수정 취소</a>
	</div>

	<h2>수정하기</h2>
	<hr>
	<%
		BlogDTO[] arr = (BlogDTO[]) request.getAttribute("list");
		int idpost = Integer.parseInt(request.getParameter("repost"));
		String status = (String) session.getAttribute("status");

		for (int i = 0; i < arr.length; i++) {
			if (arr[i].getIdpost() == idpost) {
	%>

	<form action="reWriteOut.do" method="post">
		<input type="hidden" name="repost" value="<%=idpost%>"> 제목 : <input
			type="text" name="title" value="<%=arr[i].getTitle()%>"
			style="width: 20em; position: relative; left: 36px;"><br>
		<textarea name="story" id="editor1"
			style="height: 20em; width: 20em; position: relative; left: 36px;"><%=arr[i].getStory()%></textarea>
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
		<input type="hidden" value="<%=status%>"
			name="manipulation_read"> <input type="hidden" name="repost"
			value="<%=idpost%>"> <input type="submit" value="수정">&nbsp;
		<input type="reset" value="초기화">
		<%
			}
			}
		%>
	</form>
</body>
</html>

