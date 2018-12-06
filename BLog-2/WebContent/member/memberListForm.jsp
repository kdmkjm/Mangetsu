<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="jsp.member.model.MemberBean"%>
<%
	// MemberInfoAction에서 넘긴 회원정보를 추출한다.
	ArrayList<MemberBean> memberList = (ArrayList<MemberBean>)request.getAttribute("memberList");
%>

<html>
<head>
<title>관리자 화면</title>

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

</head>
<body>
	<br>
	<br>
	<b><font size="6" color="gray">전체 회원정보</font></b>
	<br>
	<br>
	<table>
		<tr align="center">

			<td id=title>유저번호</td>
			<td id=title>아이디</td>
			<td id=title>비밀번호</td>
			<td id=title>이름</td>
			<td id=title>이메일</td>
			<td id=title>전화</td>
			<td id=title>주소</td>
			<td id=title>가입일</td>
			<td id=title>이메일인증</td>
			<td id=title>권한</td>
		</tr>

		<%
			for (MemberBean member : memberList) {
		%>
		<tr>
			<td><%=member.getUserno()%></td>
			<td><%=member.getId()%></td>
			<td><%=member.getPassword()%></td>
			<td><%=member.getName()%></td>
			<td><%=member.getMail1()%></td>
			<td><%=member.getPhone()%></td>
			<td><%=member.getAddress2()%></td>
			<td><%=member.getReg()%></td>
			<td>
				<%
					if ("1".equals(member.getMailcheck())) {
							out.print("인증됨");
						} else {
							out.print("인증되지 않음");
						}
				%>
			</td>
			<td>
				<%
					if ("1".equals(member.getStatus())) {
							out.print("일반회원");
						} else if ("2".equals(member.getStatus())) {
							out.print("부관리자");
						} else if ("3".equals(member.getStatus())) {
							out.print("관리자");
						}
				%>
			</td>
		</tr>
		<%
			}
		%>
	</table>


</body>
</html>

