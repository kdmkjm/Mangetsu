<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.blog.ex.DAO"%>
<%@page import="com.blog.ex.ComDTO"%>


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="assets/css/main.css" />
<title>Insert title here</title>

</head>


<body>
	<%
		DAO dao = new DAO();
		String post_com = request.getParameter("post_com");
		ComDTO[] comarr = null;
		comarr = dao.selectcom(post_com);
	%>

	<table style="width: 100%; border: 3px;">
		<tr>
			<th style="width: 20%;">작성자</th>
			<th style="width: 60%;">내용</th>
			<th style="width: 20%;">삭제</th>

		</tr>

		<%
			for (int i = 0; i < comarr.length; i++) {
				if (post_com.equals(comarr[i].getPost_com())) {
					out.println("<tr><th>" + comarr[i].getCom_name() + "</th>");
					out.println("<th>" + comarr[i].getCom_story() + "</th>");
		%>
		<th>
			<form action="ComDelete.do" name="comdeleteplay<%=i%>" method="post">
				<script type="text/javascript">
					function combutton_del<%=i%>() {
						if (confirm("정말 삭제하시겠습니까??") == true) { //확인
							document.comdeleteplay<%=i%>.submit();
						} else {
							return;
						}
					}
				</script>
				<div style="float: left;">
					<input type="hidden" name="delcomnm" value="<%=comarr[i].getIdpost_com()%>">
					<input type="hidden" name="com_post" value="<%=post_com%>">
					<input type="button" value="삭제하기" onclick="combutton_del<%=i%>();">
				</div>

			</form>

		</th>
		<%
			out.println("</tr>");
				}
			}
		%>


	</table>
</body>
</html>