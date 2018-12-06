package jsp.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.member.model.MemberBean;
import jsp.member.model.MemberDAO;

public class MemberModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8"); // 인코딩
        
        ActionForward forward = new ActionForward();
        
        MemberDAO dao = MemberDAO.getInstance();
        
        // 세션이 가지고있는 로그인한 ID 정보를 가져온다
        HttpSession session = request.getSession();
        String id = session.getAttribute("sessionID").toString();
        
        // 수정할 정보를 자바빈에 세팅한다.
        MemberBean member = new MemberBean();
        member.setId(id);
        member.setPassword(request.getParameter("password"));
        member.setMail1(request.getParameter("mail1"));
        member.setMail2(request.getParameterValues("mail2")[0]); 
        member.setPhone(request.getParameter("phone"));
        member.setAddress2(request.getParameter("address2"));
        
        dao.updateMember(member);
 
        forward.setRedirect(true);
           forward.setNextPath("result.do");
        
           // 회원정보 수정 성공 메시지를 세션에 담는다.
           session.setAttribute("msg", "0");
           
        return forward;
    }
 
}
