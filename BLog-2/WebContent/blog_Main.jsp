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
<link rel="stylesheet" href="assets/css/main.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Insert title here</title>
</head>

	<%
		MemberDAO dao = MemberDAO.getInstance();
		MemberBean userdata = dao.getUserInfo(session.getAttribute("sessionID").toString());
	%>

<%    


	int cnt = 0;
	int in;
    
	int writePages = 5;    // 한 ‘페이징’에 몇개의 페이지를 표현할 것인가?
	int pageRows = 5;   // 한 페이지에 몇개의 글을 리스트업할것인가? 
	int totalPage = 0; //총 몇 페이지 분량인가?
	int curPage = 1;  // 현재 페이지 
	
    try{
		Class.forName(driver);
		conn = DriverManager.getConnection(url, uid, upw);
		stmt = conn.createStatement();
		
		// 전체 항목 개수
		rs = stmt.executeQuery("SELECT DISTINCT COUNT(DISTINCT idpost) cnt FROM post where writepost =" + "'" + userdata.getUserno() + "'");  // 전체항목수 추출 쿼리
		if(rs.next()) cnt = rs.getInt(1);
		rs.close();	 // 다시 쿼리 할 것이라 일단, 기존 result set 은 close
		in = cnt;
		
		// 전화번호부 항목 가져오기.
		//rs = stmt.executeQuery(D.SQL_SELECT_ALL);  // 전체 항목을 최신등록순 추출 쿼리
%>

<%
	totalPage = (int)Math.ceil(cnt / (double)pageRows); //총 몇 페이지 분량인가?
	
	// 현재 몇 페이지?
	String this_Page = request.getParameter("page");
	if(this_Page == null || this_Page.trim().equals("")){
		curPage = 1;  // page 파라미터가 없으면 디폴트로 첫페이지로
	}else{
		curPage = Integer.parseInt(this_Page);
	}
	
	int fromRow = (curPage - 1) * pageRows + 1;	// 몇번째 row 부터?
	pstmt = conn.prepareStatement(
			"SELECT * FROM (SELECT ROWNUM AS RNUM, T.* FROM (SELECT * FROM  post ORDER BY idpost DESC) T) WHERE RNUM >= ? AND RNUM < ? AND writepost =" + "" + userdata.getUserno() + ""
			);
	pstmt.setInt(1, fromRow);    // 몇번째 row 부터
	pstmt.setInt(2, fromRow + pageRows);  // 몇번째 row 전까지?
	rs = pstmt.executeQuery();
%>


<body id="top">



	<header id="header"
		style="background-position: left top, center center;">



		<%
			if (userdata.getPic_blog() != null) {
		%>
		<img src="<%=userdata.getPic_blog()%>" class="image avatar"> <br>
		<%
			}
		%>
		<h1>
			<strong><%=userdata.getName()%>님의 블로그입니다.</strong>
			<hr>
			<%=userdata.getIntro_blog()%>
		</h1>

	</header>

	<%
		BlogDTO[] arr = (BlogDTO[]) request.getAttribute("list");
		//		String userno = (String) session.getAttribute("userno");
		String userno = userdata.getUserno();
		//		String status = (String) session.getAttribute("status");
		String status = userdata.getStatus();

	%>





	<div id="main">
					<form action="mainForm.do" method="post">
		<p style="float: right;">
<input type="submit" value="HOME">
		</p>
		</form>
		<table>
			<tr>
				<th>게시물번호</th>
				<th>제목</th>
				<th>등록일</th>
				<th>조회수</th>
				<th style="width: 10%">삭제</th>
				<th style="width: 10%">수정</th>
			</tr>
			<%

						while(rs.next()){
				String Idpost	=	rs.getString("Idpost");
				String Title	=	rs.getString("Title");
				String Postdate	=	rs.getString("Postdate");
				String Cnt_post	=	rs.getString("Cnt_post");
	
						out.print("<tr>");
						out.println("<td>" + Idpost + "</td>");
						out.print("<td>");
			%>

			<a href="postView.do?postnm=<%=Idpost%>"> <%
 	out.print(Title);
 %></a>
			<%
				out.println("</td>");
						out.println("<td>" + Postdate + "</td>");
						out.println("<td>" + Cnt_post + "</td>");
						out.println("<td>");
			%>

			<form action="delete.do" name="del<%=Idpost%>" method="post">
				<script type="text/javascript">
				function button_del<%=Idpost%>() {
					if (confirm("정말 삭제하시겠습니까??") == true) { //확인
						document.del<%=Idpost%>.submit();
					} else {
						return;
					}
				}
			</script>
				<input type="hidden" name="delnm" value="<%=Idpost%>">
				<input type="button" value="삭제하기" onclick="button_del<%=Idpost%>();">
			</form>
			<%
				out.println("</td>");
						out.println("<td>");
			%>
			<form action="reWrite.do" method="post">
				<input type="hidden" name="repost" value="<%=Idpost%>">
				<input type="submit" value="수정하기">
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
<jsp:include page="pagination.jsp">
	<jsp:param value="<%= writePages %>" name="writePages"/>
	<jsp:param value="<%= totalPage %>" name="totalPage"/>
	<jsp:param value="<%= curPage %>" name="curPage"/>
</jsp:include>




		<form action="find.do" method="post">

			<div style="float: right;">
				<input type="text" name="name"><input type="submit"
					value="찾기">
			</div>
		</form>

		<form action="write.jsp" method="post">

			<div style="float: left;">
				<input type="submit" value="글쓰기">
			</div>
		</form>
	</div>
</body>
</html>