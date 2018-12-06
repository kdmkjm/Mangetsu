<%@page import="com.blog.ex.DAO"%>
<%@page import="com.blog.ex.ComDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


	<%
		out.println("asdasd");

		String post_com = request.getParameter("post_com");
		out.println(post_com);

		DAO dao = new DAO();
		ComDTO[] comarr = null;

		//	comarr = dao.selectcom(request.getParameter("postnm"));
		//	request.setAttribute("comlist", comarr);

		//	out.println(request.getAttribute("comlist"));

		//	String userno = (String) session.getAttribute("userno");
		//	out.println(userno);

//		out.println("<table style=" + "" + "width: 100%" + "" + "border=" + "" + "3" + "" + ">");
	%>
	<%
		/*		int i = 0;
			for (i = 0; i < comlist.length; i++) {
				if (comlist[i].getWritecommand() == postnm) {
					out.println(
							"<tr><th style=" + "" + "width : 20%" + "" + ">" + comlist[i].getWritecommand() + "</th>");
					out.println("<th><th>" + comlist[i].getCommand_story() + "</th>");
					out.println("</tr>");
					out.println("	</table>");
				}
			}
		*/
	%>
