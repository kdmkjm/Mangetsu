package com.blog.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.ex.DAO;

import jsp.member.action.Action;
import jsp.member.action.ActionForward;

import com.blog.ex.BlogDTO;

public class frienddeleteCommand implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		DAO dao = new DAO();

		int cnt = 0;
		
		try {
			cnt = dao.frienddelete(request.getParameter("idme"),request.getParameter("idfriend"));
			request.setAttribute("result", cnt);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

}
