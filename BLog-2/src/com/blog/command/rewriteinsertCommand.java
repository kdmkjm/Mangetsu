package com.blog.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.ex.DAO;

import jsp.member.action.Action;
import jsp.member.action.ActionForward;

import com.blog.ex.BlogDTO;

public class rewriteinsertCommand implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		DAO dao = new DAO();
//		dao.updatePost(request.getParameter("title"), request.getParameter("story"), 
//				request.getParameter("picfile_post"), request.getParameter("manipulation_read"), 
//				request.getParameter("idpost")
////		MemberDTO member = new MemberDTO(
////				request.getParameter("title"),
////				request.getParameter("story"),
////				request.getParameter("picfile_post"),
////				request.getParameter("manipulation_read"),
////				request.getParameter("idpost")
//				
//		);
		
//		String repost = (String) request.getAttribute("repost");
		
	BlogDTO [] arr = null;
		

		try {
			arr = dao.select();
			request.setAttribute("list", arr);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

}
