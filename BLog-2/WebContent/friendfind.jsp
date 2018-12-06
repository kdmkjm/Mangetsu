<%@page import="com.blog.ex.DAO"%>
<%@page import="com.blog.ex.friendDTO"%>
<%@page import="jsp.member.model.MemberDAO"%>
<%@page import="jsp.member.model.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.*"%>



<%
	// 입력값을 얻어오기
	String name = request.getParameter("name");
	String userno = (String) session.getAttribute("userno");
	String iduser = (String) session.getAttribute("sessionID");
	MemberDAO dao = MemberDAO.getInstance();
	DAO command = new DAO();
	
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
	friendDTO[] arr = (friendDTO[]) request.getAttribute("result");

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
	<br>검색완료.
	<br>

	<%
		}
		MemberBean youdata = dao.getUserInfo(arr[0].getidfriendr());
		
		
	%>


	<table>
			<%


	
						out.print("<tr>");
						out.println("<td>" +  youdata.getPic_blog() + "</td><td>");%>
						<a href="index?idfriend=<%=youdata.getId()%>">
						 <%	 	out.print(youdata.getId());	%></a>
								<%
				out.println("</td>");
						out.println("<td>");
			%>
				<script type="text/javascript">
				function button_del<%=youdata.getId()%>() {
					if (confirm("정말 삭제하시겠습니까??") == true) { //확인
						<%command.frienddelete(iduser, youdata.getId()); %>
						opener.parent.location.reload();
						window.close();
					} else {
						window.close();
					}
				}
			</script>

				<input type="button" value="친구삭제" onclick="button_del<%=youdata.getId()%>();">
			<%
				out.println("</td>");
						out.print("</tr>");
					 
			%>
		</table>


	<form action="friendfind.do" method="post">
		<div style="float: right;">
		<input type="hidden" name="idme" value="<%=iduser%>">
			<input type="text" name="findwantid"> 
			<input type="submit" value="찾기">
		</div>
	</form>


</body>
</html>