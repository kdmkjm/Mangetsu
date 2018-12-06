<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.blog.ex.*"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="assets/css/main.css" />
<title>Insert title here</title>
</head>


<body id="top">



	<%
		BlogDTO[] arr = (BlogDTO[]) request.getAttribute("list");
		int postnm = Integer.parseInt(request.getParameter("postnm"));
		String userno = (String) session.getAttribute("userno");
	%>





<div id="main" style="padding: 2em 2em 2em 2em; margin: auto;">
	<a href="blog_Main.do?userno=<%=userno%>">리스트로 돌아가기</a>
	<table style="width: 100%" border="3">
		<%
			int i = 0;
			for (i = 0; i < arr.length; i++) {
				if (arr[i].getIdpost() == postnm) {
					out.println("<tr><th id=" + "" + "title" + " " + "height=" + "" + "15%" + "" + ">"
							+ arr[i].getTitle() + "</tr>");
					out.println("<tr><th height=" + "" + "5%" + "" + ">" + arr[i].getPostdate() + "</tr>");

					out.println("</tr>");
					out.println("<tr><th id=" + "" + "story" + " " + "height=" + "" + "10em" + "" + ">"
							+ arr[i].getStory() + "</tr>");
				}
			}
		%>

	</table>


	<form action="delete.do" name="deleteplay" method="post">
		<script type="text/javascript">
			function button_del() {
				if (confirm("정말 삭제하시겠습니까??") == true) { //확인
					document.deleteplay.submit();
				} else {
					return;
				}
			}
		</script>
		<div style="float: left;">
			<input type="hidden" name="delnm" value="<%=postnm%>"> <input
				type="button" value="삭제하기" onclick="button_del();">
		</div>

	</form>


	<form id="post_com_send" method="post" action="ComWrite.jsp"
		target="ComWrite">
		<input type="hidden" name="post_com" value="<%=postnm%>" />
	</form>

	<script type="text/javascript">
		function postwrite() {
			window
					.open('', 'ComWrite',
							"width=370, height=360, resizable=no, scrollbars=no, status=no;");
			document.getElementById("post_com_send").submit();
		}
	</script>
	<div style="float: right;">
		<input type="button" value="댓글 작성" onclick="postwrite();">
	</div>

	<form action="reWrite.do" method="post">
		<div style="float: left;">

			<input type="hidden" name="repost" value="<%=postnm%>"> <input
				type="submit" value="수정하기">
		</div>
	</form>
	<br>
	<hr>
	<jsp:include page="./ComViewReady.jsp">
		<jsp:param name="post_com" value="<%=postnm%>" />
	</jsp:include>

</div>

</body>
</html>