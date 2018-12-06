package com.blog.command;

import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blog.ex.DAO;

import jsp.member.action.Action;
import jsp.member.action.ActionForward;
import jsp.member.model.MemberBean;
import jsp.member.model.MemberDAO;

import com.blog.ex.BlogDTO;

public class blogmainCommand implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		DAO dao = new DAO();
		MemberDAO mdao = MemberDAO.getInstance();
		MemberBean userdata = null;
		BlogDTO[] arr = null;

		try {
			userdata = mdao.getUserInfo(session.getAttribute("sessionID").toString());
			request.setAttribute("userdata", userdata);
			arr = dao.select();
			request.setAttribute("list", arr);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

}
