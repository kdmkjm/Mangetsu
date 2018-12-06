<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.blog.ex.*"%>



<%
	// 입력값을 얻어오기
	String name = request.getParameter("name");
	String userno = (String) session.getAttribute("userno");
	// 유효성 체크
	if (name == null || name.trim().equals("")) {
%>

<script>
	alert('잘못된 검색입니다.');
	history.back();
</script>
<%
	return; // 더이상 JSP프로세싱 하지 않도록 여기서 종료
	}
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="assets/css/main.css" />
<title>검색</title>



</head>
<body>



	<div style="float: right;">
		<a href="blog_Main.do?userno=<%=userno%>">리스트로 돌아가기</a>
	</div>

	<%
		BlogDTO[] arr = (BlogDTO[]) request.getAttribute("result");

		if (arr == null || arr.length == 0) {
	%>
	<script>
		alert("검색된 게시물이 없습니다.");
		//	history.back()
		location.href = "blog_Main.do?userno=<%=userno%>";
	</script>
	<%
		} else {
	%>
	<br><%=arr.length%>명 검색
	<br>

	<%
		}
	%>


	<table style="width: 100%">
		<tr>
			<th>게시물번호</th>
			<th>제목</th>
			<th>등록일</th>
			<th>삭제</th>
			<th>수정</th>

		</tr>



		<%
			for (int i = 0; i < arr.length; i++) {
				out.print("<tr>");
				out.println("<td width=" + "" + "15%" + "" + ">" + arr[i].getIdpost() + "</td>");
				out.print("<td  width=" + "" + "40%" + "" + ">");
		%>
		<a href="postView.do?postnm=<%=arr[i].getIdpost()%>"> <%
 	out.print(arr[i].getTitle());
 %></a>
		<%
			out.println("</td>");
				out.println("<td width=" + "" + "20%" + "" + ">" + arr[i].getPostdate() + "</td>");
				out.println("<td width=" + "" + "10%" + "" + ">");
		%>
		<a href="delete.do?delnm=<%=arr[i].getIdpost()%>"> 삭제</a>
		<%
			out.println("</td>");
				out.println("<td width=" + "" + "10%" + "" + ">");
		%>
		<a href="reWrite.do?repost=<%=arr[i].getIdpost()%>"> 수정</a>
		<%
			out.println("</td>");

				out.print("</tr>");
			}
		%>



	</table>


	<form action="find.do" method="post">
		<div style="float: right;">
			<input type="text" name="name"> <input type="submit"
				value="찾기">
		</div>
	</form>


</body>
</html>