<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="assets/css/main.css" />
<title>Insert title here</title>
</head>
<body id="top">
	<form action="blog_Main.do">
	<input type="hidden" value="1" name="userno">
		<table
			style="width: 60%; height: 200px; margin: auto;  text-align: center; ">
			<tr>
				<td><strong>아이디</strong><input type="text" value="test" name="ID"><strong>비밀번호</strong><input type="password" value="test" name="PW"></td>
			</tr>
			<tr>
				<td><input type="submit" value="로그인"></td>
			</tr>
		</table>
	</form>
</body>
</html>