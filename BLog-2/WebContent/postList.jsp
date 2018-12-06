<%@page import="javax.naming.Context"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.blog.ex.*"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>게시판메인</title>
<!-- css 파일 분리 -->
<link href='css/blog_style.css' rel='stylesheet' style='text/css' />
</head>
<body>
	<%
		BlogDTO[] arr = (BlogDTO[]) request.getAttribute("list");
		String userno = (String) session.getAttribute("userno");
		String status = (String) session.getAttribute("status");
		if (arr == null || arr.length == 0) {
	%>
	<form action="write.jsp" id="movewrite">
		<script>
		alert("게시글이 없습니다.");
		this.document.getElementById("movewrite").submit();
	</script>
	</form>
	<%
		}
	%>
	<div style="height: 100%; overflow: auto;">
		<table style="width: 100%;">
			<tr>
				<th>게시물번호</th>
				<th>제목</th>
				<th>등록일</th>
				<th>삭제</th>
				<th>수정</th>
			</tr>

			<%
				for (int i = 0; i < arr.length; i++) {
					if (userno.equals(arr[i].getWritepost())) {
						out.print("<tr>");
						out.println("<td width=" + "" + "15%" + "" + ">" + arr[i].getIdpost() + "</td>");
						out.print("<td  width=" + "" + "40%" + "" + ">");
			%>
			<a href="postView.do?postnm=<%=arr[i].getIdpost()%>"> <%
 	out.print(arr[i].getTitle());
 %></a>
			<%
				out.println("</td>");
						out.println("<td wid th=" + "" + "20%" + "" + ">" + arr[i].getPostdate() + "</td>");
						out.println("<td width=" + "" + "10%" + "" + ">");
			%>

			<form action="delete.do" name="del<%=i%>" method="post">
				<script type="text/javascript">
				function button_del<%=i%>() {
					if (confirm("정말 삭제하시겠습니까??") == true) { //확인
						document.del<%=i%>.submit();
					} else {
						return;
					}
				}
			</script>
				<input type="hidden" name="delnm" value="<%=arr[i].getIdpost()%>">
				<input type="button" value="삭제하기" onclick="button_del<%=i%>();">
			</form>
			<%
				out.println("</td>");
						out.println("<td width=" + "" + "10%" + "" + ">");
			%>
			<form action="reWrite.do" method="post">
				<input type="hidden" name="repost" value="<%=arr[i].getIdpost()%>">
				<input type="submit" value="수정하기">
			</form>
			<%
				out.println("</td>");
						out.print("</tr>");
					} else {
			%>
			<form action="write.jsp" id="movewrite">
				<script>
		alert("게시글이 없습니다.");
		this.document.getElementById("movewrite").submit();
	</script>
			</form>
			<%
				}
				}
			%>


		</table>
	</div>




	<form action="find.do" method="post">

		<div style="float: right;">
			<input type="text" name="name"> <input type="submit"
				value="찾기">
		</div>
	</form>

	<form action="write.jsp" method="post">

		<div style="float: left;">
			<input type="submit" value="글쓰기">
		</div>
	</form>
</body>
</html>
