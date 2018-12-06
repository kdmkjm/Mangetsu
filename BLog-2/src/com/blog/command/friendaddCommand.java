package com.blog.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.ex.DAO;
import com.blog.ex.friendDTO;

import jsp.member.action.Action;
import jsp.member.action.ActionForward;
import jsp.member.model.MemberBean;
import jsp.member.model.MemberDAO;

import com.blog.ex.BlogDTO;

public class friendaddCommand implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DAO dao = new DAO();
		MemberDAO mdao = MemberDAO.getInstance();
		String addidfriendno = request.getParameter("addidfriendno");

		
		friendDTO friend = new friendDTO(request.getParameter("idme"), addidfriendno);
		int cnt = 0;
		
		try {
			cnt = dao.plusfriend(friend);
			request.setAttribute("result", cnt);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

}
