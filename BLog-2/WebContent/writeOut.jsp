<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	int cnt = (Integer) request.getAttribute("result");

	String userno = (String) session.getAttribute("userno");

	if (cnt > 0) {
%>
<script>
	alert("게시물 등록 성공");
	location.href = "blog_Main.do?userno=<%=userno%>";
</script>
<%
	} else {
%>
<script>
	alert("등록 실패");
	location.href = "blog_Main.do?userno=<%=userno%>";
</script>
<%
	}
%>




