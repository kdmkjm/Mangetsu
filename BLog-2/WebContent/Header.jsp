<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>main 상단</title>
<link rel="stylesheet" href="css/bootstrap.min.css">

<style type="text/css">
#wrap {
	text-align: center;
	width: 800px;
	height: 150px;
}
</style>

<script type="text/javascript">
	function changeView(value) {

		if (value == "0") // HOME 버튼 클릭시 첫화면으로 이동
		{
			location.href = "mainForm.do";
		} else if (value == "1") // 로그인 버튼 클릭시 로그인 화면으로 이동
		{
			location.href = "loginForm.do";
		} else if (value == "2") // 회원가입 버튼 클릭시 회원가입 화면으로 이동
		{
			location.href = "joinForm.do";
		} else if (value == "3") // 로그아웃 버튼 클릭시 로그아웃 처리
		{
			location.href = "MemberLogoutAction.do";
		} else if (value == "4") 
		{
			location.href = "MemberInfoAction.do";
		} else if (value == "5")
		{
			location.href = "MemberListAction.do";
		}
		 else if (value == "6")
		{
			location.href = "blog_Main.do";
		}
		 else if (value == "7")
			{
				location.href = "index.jsp?idfriend=<%=session.getAttribute("sessionID").toString()%>";
			}
	}
</script>

</head>
<body>
	<div id="wrap">
		<p>
			<button class="btn btn-success" onclick="changeView(0)">HOME</button>
			<%
				if (session.getAttribute("sessionID") == null) {
			%>
			<button id="loginBtn" class="btn btn-primary" onclick="changeView(1)">로그인</button>
			<button id="joinBtn" class="btn btn-primary" onclick="changeView(2)">회원가입</button>

			<%
				} else {
			%>
			<button id="logoutBtn" class="btn btn-primary"
				onclick="changeView(3)">로그아웃</button>
			<button id="updateBtn" class="btn btn-primary"
				onclick="changeView(4)">내정보</button>
			<button id="blogViewBtn" class="btn btn-primary" 
				onclick="changeView(6)">블로그</button>
			<button id="blogViewBtn" class="btn btn-primary" 
				onclick="changeView(7)">채팅</button>
			<%
				}
			%>
			<% 
				if(session.getAttribute("sessionID")!= null && session.getAttribute("sessionID").equals("admin")){ 
			%>
			
			<button id="memberViewBtn" class="btn btn-primary" 
				onclick="changeView(5)">회원보기</button>
			
			
			<% } %>
		</p>
	</div>
</body>
</html>
