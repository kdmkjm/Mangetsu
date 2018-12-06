<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="assets/css/main.css" />
<title>ComWrite</title>
</head>
<body>
	<%
		String post_com = request.getParameter("post_com");
		String name = session.getAttribute("Name").toString();
		String Com_Name = name;
		String userno = session.getAttribute("userno").toString();
	%>

	<form action="ComWrite.do" method="post">
		<input type="hidden" value="<%=post_com%>" name="post_com"> <input
			type="hidden" value="<%=userno%>" name="writecom"> <input
			type="hidden" value="<%=Com_Name%>" name="Com_Name">

		<table
			style="width: 60%; height: 100px; margin: auto; text-align: center;">
			<tr>
				<th><strong style="align-self: center;"> 작성자 <%=Com_Name%>님
				</strong></th>
			</tr>
			<tr>
				<td>댓글내용 : <textarea name="Com_story"></textarea>
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="제출" onclick="selfclose();"><input
					type="button" onclick="selfclose();" value="닫기"></td>
			</tr>





		</table>
	</form>

	<script type="text/javascript">
		function selfclose() {
			opener.parent.location.reload();
			window.close();
		}
	</script>

</body>
</html>