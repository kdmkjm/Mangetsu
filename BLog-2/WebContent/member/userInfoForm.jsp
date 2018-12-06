<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jsp.member.model.MemberDAO"%>
<%@ page import="jsp.member.model.MemberBean"%>

<html>
<head>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script> <script src="/js/jquery.form.js"></script>


<title>현재 유저정보 출력화면</title>

<style type="text/css">
table {
	margin-left: auto;
	margin-right: auto;
	border: 3px solid skyblue;
}

td {
	border: 1px solid skyblue
}

#title {
	background-color: skyblue
}
</style>

<script type="text/javascript">
	function changeForm(val) {
		if (val == "-1") {
			location.href = "mainForm.do";
		} else if (val == "0") {
			location.href = "MemberModifyFormAction.do";
		} else if (val == "1") {
			location.href = "deleteForm.do";
		}
	}
</script>

</head>
<body>
	<%
		String id = session.getAttribute("sessionID").toString();

		// 세션에 저장된 아이디를 가져와서
		// 그 아이디 해당하는 회원정보를 가져온다.
		MemberDAO dao = MemberDAO.getInstance();
		MemberBean memberBean = dao.getUserInfo(id);
	%>

	<br>
	<br>
	<b><font size="6" color="gray">내 정보</font></b>
	<br>
	<br>
	<br>
	<!-- 가져온 회원정보를 출력한다. -->
	<table>
		<tr>
			<td id="title">아이디</td>
			<td><%=memberBean.getId()%></td>
		</tr>

		<tr>
			<td id="title">비밀번호</td>
			<td><%=memberBean.getPassword()%></td>
		</tr>

		<tr>
			<td id="title">이름</td>
			<td><%=memberBean.getName()%></td>
		</tr>
		<tr>
			<td id="title">이메일</td>
			<td><%=memberBean.getMail1()%>@ <%=memberBean.getMail2()%></td>
		</tr>

		<tr>
			<td id="title">휴대전화</td>
			<td><%=memberBean.getPhone()%></td>
		</tr>
		<tr>
			<td id="title">주소</td>
			<td><%=memberBean.getAddress2()%></td>
		</tr>

		<tr>

			<td>


<script type="text/javascript">
$(function(){ //폼전송 
	$('#ajaxform').ajaxForm({ 
	//보내기전 validation check가 필요할경우
	beforeSubmit: function (data, frm, opt) { alert("전송전!!"); return true; }, 
	//submit이후의 처리
	success: function(responseText, statusText){ alert("전송성공!!"); }, 
	//ajax error 
	error: function(){ alert("에러발생!!"); } }); });

</script>

<form id="ajaxform" action="saveFileTest.jsp" method="post" enctype="multipart/form-data"> 

<input type="file" name="test4" />
<input type="submit" value="Submit" />
</form>






			</td>


		</tr>

	</table>

	<br>
	<input type="button" value="뒤로" onclick="changeForm(-1)">
	<input type="button" value="회원정보 변경" onclick="changeForm(0)">
	<input type="button" value="회원탈퇴" onclick="changeForm(1)">
</body>
</html>