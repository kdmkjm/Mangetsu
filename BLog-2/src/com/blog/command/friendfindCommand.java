package com.blog.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.ex.DAO;
import com.blog.ex.friendDTO;

import jsp.member.action.Action;
import jsp.member.action.ActionForward;

import com.blog.ex.BlogDTO;

public class friendfindCommand implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		DAO dao = new DAO();
		friendDTO[] cnt;

		try {
			cnt = dao.findfriend(request.getParameter("idme"), request.getParameter("findwantid"));
			System.out.println(cnt.length);
			request.setAttribute("result", cnt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

}
