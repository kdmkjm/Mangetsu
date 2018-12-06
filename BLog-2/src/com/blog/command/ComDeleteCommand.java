package com.blog.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.ex.DAO;

import jsp.member.action.Action;
import jsp.member.action.ActionForward;

import com.blog.ex.BlogDTO;

public class ComDeleteCommand implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DAO dao = new DAO();
		int cnt = 0;

		try {
			cnt = dao.deleteByComId(request.getParameter("delcomnm"));
			request.setAttribute("result", cnt);
//			forward.setRedirect(true);
//			forward.setNextPath("Comdelete.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

}
