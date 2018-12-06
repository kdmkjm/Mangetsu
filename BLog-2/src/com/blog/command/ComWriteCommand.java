package com.blog.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.ex.DAO;

import jsp.member.action.Action;
import jsp.member.action.ActionForward;

import com.blog.ex.BlogDTO;
import com.blog.ex.ComDTO;

public class ComWriteCommand implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		DAO dao = new DAO();
		
		String writecom = request.getParameter("writecom");
		System.out.println(writecom);
		String post_com = request.getParameter("post_com");
		System.out.println(post_com);
		String Com_story = request.getParameter("Com_story");
		System.out.println(Com_story);
		String Com_Name = request.getParameter("Com_Name");
		System.out.println(Com_Name);
		
		ComDTO commant = new ComDTO(writecom,post_com,Com_story,Com_Name);

		try {
			int cnt = 0;
			cnt = dao.comwrite(commant);
			request.setAttribute("result", cnt);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
