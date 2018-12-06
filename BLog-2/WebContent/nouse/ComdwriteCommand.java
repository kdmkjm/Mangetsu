package com.blog.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.ex.DAO;
import com.blog.ex.BlogDTO;

public class ComdwriteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		DAO dao = new DAO();
		BlogDTO member = new BlogDTO(request.getParameter("title"), request.getParameter("story"),
		 request.getParameter("manipulation_read"),
				request.getParameter("writepost")

		);
		int cnt = 0;

		try {
			cnt = dao.insertPost(member);
			request.setAttribute("result", cnt);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
