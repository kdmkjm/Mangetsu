<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String contentPage = request.getParameter("contentPage");
	if (contentPage == null) {
		contentPage = "Middle.jsp";
	}
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>메인</title>

<style>
#wrap {
	width: 800px;
	margin: 0 auto 0 auto;
}

#header {
	text-align: center;
	width: 800px;
	height: 150px;
	background-color: #92FFFF;
	padding: 60px 0px;
}

#main {
	float: left;
	width: 800px;
	height: 500px;
	background-color: #FFCA6C;
	text-align: center;
	vertical-align: middle;
}

#footer {
	clear: left;
	width: 800px;
	height: 60px;
	background-color: #7DFE74;
}
</style>
</head>
<body>
 
    <div id="wrap">
    
        <div id="main">
            <jsp:include page="<%=contentPage%>" />
        </div>
        <div id="header">
            <jsp:include page="Header.jsp" />
        </div>
        
        <% if(session.getAttribute("sessionID") != null && session.getAttribute("sessionID") != ""){ %>
        <div id="footer">
            <jsp:include page="footer.jsp" />
        </div>
        <%} %>
    </div>
    
</body>
</html>