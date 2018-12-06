package com.blog.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.ex.DAO;

import jsp.member.action.Action;
import jsp.member.action.ActionForward;

import com.blog.ex.BlogDTO;
import com.blog.ex.ComDTO;

public class ComViewCommand implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String post_com = request.getParameter("post_com");
		DAO dao = new DAO();
		ComDTO[] comarr = null;

		try {
			comarr = dao.selectcom(post_com);
			request.setAttribute("comlist", comarr);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
