package com.blog.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.ex.DAO;
import com.blog.ex.BlogDTO;

public class ComdDeleteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		DAO dao = new DAO();

		int cnt = 0;

		try {
			cnt = dao.deleteByID(request.getParameter("delnm"));
			request.setAttribute("result", cnt);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
