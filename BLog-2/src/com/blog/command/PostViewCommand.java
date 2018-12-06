package com.blog.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.ex.DAO;

import jsp.member.action.Action;
import jsp.member.action.ActionForward;

import com.blog.ex.BlogDTO;
import com.blog.ex.ComDTO;

public class PostViewCommand implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String idpost = request.getParameter("postnm");
        DAO dao = new DAO();
		BlogDTO[] arr = null;

		try {
			arr = dao.postview(idpost);
			request.setAttribute("list", arr);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
