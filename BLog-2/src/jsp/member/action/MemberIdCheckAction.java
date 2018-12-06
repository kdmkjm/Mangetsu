package jsp.member.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.member.model.MemberBean;
import jsp.member.model.MemberDAO;

public class MemberIdCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
        
        // 회원정보를 가져온다.
		String id = request.getParameter("id");
        MemberDAO dao = MemberDAO.getInstance();
       
        boolean result = dao.duplicateIdCheck(id);
        System.out.println(result);
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        
        if(result) {
        	out.println("0");
        } else {
        	out.println("1");
        }
        
        out.close();
        
        return null;

	}

}
