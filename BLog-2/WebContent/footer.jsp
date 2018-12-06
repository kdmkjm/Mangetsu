<%@page import="jsp.member.model.MemberDAO"%>
<%@page import="jsp.member.model.MemberBean"%>
<%@page import="javax.naming.Context"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.blog.ex.*"%>
<%@ include file="dbcommon.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
table, td{
border: 1px solid; 
}

table{
 
height: 40px;
margin: auto;
text-align: center;
}
</style>
<title>Insert title here</title>
</head>

	<%
		DAO command = new DAO(); 
		MemberDAO dao = MemberDAO.getInstance();
		MemberBean userdata = dao.getUserInfo(session.getAttribute("sessionID").toString());
	%>

<%    

try {
	Class.forName(driver);
	conn = DriverManager.getConnection(url, uid, upw);
	stmt = conn.createStatement();
	
	String sql = "SELECT DISTINCT idfriend FROM friend WHERE idme =?";

	pstmt = conn.prepareStatement(sql);

	pstmt.setString(1, session.getAttribute("sessionID").toString());

	rs = pstmt.executeQuery();
	
	




%>


<body>

<div id="warp">

<div id="header">
		<form action="friendfind.do" method="post">

			<div style="float: right;">
		<input type="hidden" name="idme" value="<%=userdata.getId()%>">
			<input type="text" name="findwantid"> 
				<input type="submit"
					value="찾기">
			</div>
		</form>

	
						


						<script type="text/javascript">
							function firendadd() {
								window
										.open('friendadd.jsp',
												"width=200, height=200, resizable=no, scrollbars=no, status=no;");
							}
						</script>
						<div style="float: left;">
							<input type="button" value="친구추가" onclick="firendadd();">
						</div>
						

</div>




	<div id="footer">
		
		
		<table >
			<%
						while(rs.next()){
							MemberBean frienduser =  dao.getUserInfo(rs.getString("idfriend"));
		
						out.print("<tr>");
						out.println("<td>" + frienduser.getPic_blog() + "</td><td>");%>
						
						 <%	 	out.print(frienduser.getId() + "</td><td>");	%>			
				
							<form action="frienddelete.do" name="delf<%=frienduser.getId()%>" method="post">
				<script type="text/javascript">
				function button_friend<%=frienduser.getId()%>() {
					if (confirm("정말 삭제하시겠습니까??") == true) { //확인
						document.delf<%=frienduser.getId()%>.submit();
						opener.parent.location.reload();
					} else {
						return;
					}
				}
			</script>
				<input type="hidden" name="idme" value="<%=userdata.getId()%>">
				<input type="hidden" name="idfriend" value="<%=frienduser.getId()%>">
				<input type="button" value="친구삭제" onclick="button_friend<%=frienduser.getId()%>();">
			</form>
				
				

				
				
			<%
				out.println("</td>");
						out.print("</tr>");
					} 
			%>
		</table>

<%	
    }catch(Exception e){
    	e.printStackTrace();
    } finally {
    	try{
    		if(rs != null) rs.close();
    		if(pstmt != null) pstmt.close();
    		if(stmt != null) stmt.close();
    		if(conn != null) conn.close();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }   
%>

<%--페이징 --%>






	</div>
	</div>
</body>
</html>