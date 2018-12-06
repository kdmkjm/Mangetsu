package jsp.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.member.model.*;

public class MemberJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		        
		        request.setCharacterEncoding("utf-8"); // 인코딩
		        
		        ActionForward forward = new ActionForward();
		        
		        MemberDAO dao = MemberDAO.getInstance();
		        
		        // 입력된 정보를 자바빈에 세팅한다.
		        MemberBean member = new MemberBean();
		        member.setId(request.getParameter("id"));
		        member.setPassword(request.getParameter("password"));
		        member.setName(request.getParameter("name"));
		        member.setMail1(request.getParameter("mail1"));
		        member.setMail2(request.getParameterValues("mail2")[0]);
		        member.setPhone(request.getParameter("phone"));
		        member.setAddress2(request.getParameter("address2"));
		        member.setAddress3(request.getParameterValues("address3")[0]);
//		        member.setChk("0");
		        // 회원가입 실행
		        dao.insertMember(member);
		        
		        // 가입성공
		        forward.setRedirect(true);
		           forward.setNextPath("result.do");
		        
		           // 가입성공 메시지를 세션에 담는다.
		           request.getSession().setAttribute("msg", "1");
		           
		        return forward;
		    }
		}