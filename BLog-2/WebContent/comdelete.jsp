<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.blog.ex.*"%>

<!--  jsp:useBean id="dao" class="com.blog.ex.MemberDAO" />
-->

<%int cnt = (Integer) request.getAttribute("result");

//String userno = (String) session.getAttribute("userno");
String com_post = request.getParameter("com_post");
			if (cnt > 0) {%>
	<script>
	alert("삭제가 완료되었습니다.");
	location.href = "postView.do?postnm=<%=com_post%>";
</script>
<%
	} else {
%>
<script>
	alert("삭제에 싪");
	location.href = "postView.do?postnm=<%=com_post%>";
</script>
<%
	} // end if
%>



