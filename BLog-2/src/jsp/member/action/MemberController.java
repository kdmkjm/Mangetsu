package jsp.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.command.ComDeleteCommand;
import com.blog.command.ComViewCommand;
import com.blog.command.ComWriteCommand;
import com.blog.command.DeleteCommand;
import com.blog.command.FindCommand;
import com.blog.command.ListCommand;
import com.blog.command.PostViewCommand;
import com.blog.command.blogmainCommand;
import com.blog.command.friendaddCommand;
import com.blog.command.frienddeleteCommand;
import com.blog.command.friendfindCommand;
import com.blog.command.rewriteCommand;
import com.blog.command.rewriteinsertCommand;
import com.blog.command.writeCommand;

//회원관련 Controller

public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * GET 방식일 경우 doGet()
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * POST 방식일 경우 doPost()
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * 명령어에 따른 해당 Action을 지정해 준다.
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");

		// 넘어온 커맨드를 추출하는 과정
		String requestURI = request.getRequestURI();
		int cmdIdx = requestURI.lastIndexOf("/") + 1;

		String command = requestURI.substring(cmdIdx);

		// URI, command 확인
		System.out.println("requestURI : " + requestURI);
		System.out.println("command : " + command);

		ActionForward forward = null;
		Action action = null;
		String viewPage = "";
		RequestDispatcher dispatcher = null;

		// 보여줄 화면 URL
		String form = "mainForm.jsp?contentPage=member/";

		// 커맨드에 해당하는 액션을 실행한다.
		try {
			// 화면전환
			if (command.equals("mainForm.do")) // 메인화면 이동
			{
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath("mainForm.jsp");
			} else if (command.equals("loginForm.do")) // 로그인화면 이동
			{
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath(form + "loginForm.jsp");
			} else if (command.equals("joinForm.do")) // 회원가입화면 이동
			{
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath(form + "joinForm.jsp");
			} else if (command.equals("userInfoForm.do")) // 내정보 클릭 - 회원정보화면 이동
			{
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath(form + "userInfoForm.jsp");
			} else if (command.equals("modifyForm.do")) // 회원수정화면 이동
			{
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath(form + "modifyForm.jsp");
			} else if (command.equals("deleteForm.do")) // 회원삭제화면 이동
			{
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath(form + "deleteForm.jsp");
			} else if (command.equals("result.do")) // 각종 처리결과 화면 이동
			{
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath(form + "resultForm.jsp");
			} else if (command.equals("memberListForm.do")) // 각종 처리결과 화면 이동
			{
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath(form + "memberListForm.jsp");
			} else if (command.equals("idCheckForm.do")) // 각종 처리결과 화면 이동
			{
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath(form + "idCheckForm.jsp");
			}
			// 각종 처리 액션
			else if (command.equals("MemberLoginAction.do")) // 로그인 처리
			{
				action = new MemberLoginAction();
				forward = action.execute(request, response);
			} else if (command.equals("MemberLogoutAction.do")) // 로그아웃 처리
			{
				action = new MemberLogoutAction();
				forward = action.execute(request, response);
			} else if (command.equals("MemberJoinAction.do")) // 회원가입 처리
			{
				action = new MemberJoinAction();
				forward = action.execute(request, response);
			} else if (command.equals("MemberInfoAction.do")) // 회원정보화면에 보여줄 정보 처리
			{
				action = new MemberInfoAction();
				forward = action.execute(request, response);
			} else if (command.equals("MemberModifyFormAction.do")) // 회원수정화면에 보여줄 정보 처리
			{
				action = new MemberModifyFormAction();
				forward = action.execute(request, response);
			} else if (command.equals("MemberModifyAction.do")) // 회원수정 처리
			{
				action = new MemberModifyAction();
				forward = action.execute(request, response);
			} else if (command.equals("MemberDeleteAction.do")) // 회원삭제 처리
			{
				action = new MemberDeleteAction();
				forward = action.execute(request, response);
			} else if (command.equals("MemberListAction.do")) // 회원삭제 처리
			{
				action = new MemberListAction();
				forward = action.execute(request, response);
			} else if (command.equals("MemberIdCheckAction.do")) // 회원삭제 처리
			{
				action = new MemberIdCheckAction();
				forward = action.execute(request, response);
			} else if (command.equals("postList.do")) { // 게시물 리스트
				action = new ListCommand();
				action.execute(request, response);
				viewPage = "postList.jsp";
				dispatcher = request.getRequestDispatcher(viewPage);
				dispatcher.forward(request, response);

			} else if (command.equals("writeOut.do")) { // 게시물 작성
				action = new writeCommand();
				action.execute(request, response);
				viewPage = "writeOut.jsp";
				dispatcher = request.getRequestDispatcher(viewPage);
				dispatcher.forward(request, response);

			} else if (command.equals("find.do")) { // 게시물 제목으로 찾기
				action = new FindCommand();
				action.execute(request, response);
				viewPage = "find.jsp";
				dispatcher = request.getRequestDispatcher(viewPage);
				dispatcher.forward(request, response);

			} else if (command.equals("delete.do")) { // 게시물 삭제
				action = new DeleteCommand();
				action.execute(request, response);
				viewPage = "delete.jsp";
				dispatcher = request.getRequestDispatcher(viewPage);
				dispatcher.forward(request, response);

			} else if (command.equals("postView.do")) { // 게시물 내용
				action = new PostViewCommand();
				action.execute(request, response);
				viewPage = "postView.jsp";
				dispatcher = request.getRequestDispatcher(viewPage);
				dispatcher.forward(request, response);

			} else if (command.equals("reWriteOut.do")) { // 게시글 수정
				action = new rewriteCommand();
				action.execute(request, response);
				viewPage = "reWriteOut.jsp";
				dispatcher = request.getRequestDispatcher(viewPage);
				dispatcher.forward(request, response);

			} else if (command.equals("reWrite.do")) { // 게시글 내용 불러오기
				action = new rewriteinsertCommand();
				action.execute(request, response);
				viewPage = "reWrite.jsp";
				dispatcher = request.getRequestDispatcher(viewPage);
				dispatcher.forward(request, response);

			} else if (command.equals("blog_Main.do")) { // 블로그 메인 화면 -- 회원 테이블과 연결
				action = new blogmainCommand();
				action.execute(request, response);
				viewPage = "blog_Main.jsp";
				dispatcher = request.getRequestDispatcher(viewPage);
				dispatcher.forward(request, response);

			} else if (command.equals("ComView.do")) { // 댓글 뷰
				action = new ComViewCommand();
				action.execute(request, response);
				viewPage = "ComView.jsp";
				dispatcher = request.getRequestDispatcher(viewPage);
				dispatcher.forward(request, response);

			} else if (command.equals("ComWrite.do")) { // 댓글 작성
				action = new ComWriteCommand();
				action.execute(request, response);
				viewPage = "ComWrite.jsp";
				dispatcher = request.getRequestDispatcher(viewPage);
				dispatcher.forward(request, response);

			}  else if (command.equals("friendadd.do")) { // 댓글 작성
				action = new friendaddCommand();
				action.execute(request, response);
				viewPage = "mainForm.jsp";
				dispatcher = request.getRequestDispatcher(viewPage);
				dispatcher.forward(request, response);

			} else if (command.equals("friendfind.do")) { // 댓글 작성
				action = new friendfindCommand();
				action.execute(request, response);
				viewPage = "friendfind.jsp";
				dispatcher = request.getRequestDispatcher(viewPage);
				dispatcher.forward(request, response);

			} else if (command.equals("frienddelete.do")) { // 댓글 작성
				action = new frienddeleteCommand();
				action.execute(request, response);
				viewPage = "mainForm.jsp";
				dispatcher = request.getRequestDispatcher(viewPage);
				dispatcher.forward(request, response);

			} else if (command.equals("ComDelete.do")) { // 댓글 작성
				action = new ComDeleteCommand();
				action.execute(request, response);
				System.out.println("댓글 삭제후 페이지 이동");
				viewPage = "comdelete.jsp";
				dispatcher = request.getRequestDispatcher(viewPage);
				dispatcher.forward(request, response);
			}
			
			// 화면이동 - isRedirext() 값에 따라 sendRedirect 또는 forward를 사용
			// sendRedirect : 새로운 페이지에서는 request와 response객체가 새롭게 생성된다.
			// forward : 현재 실행중인 페이지와 forwad에 의해 호출될 페이지는 request와 response 객체를 공유
			if (forward != null) {
				if (forward.isRedirect()) {
					response.sendRedirect(forward.getNextPath());
				} else {
					dispatcher = request.getRequestDispatcher(forward.getNextPath());
					dispatcher.forward(request, response);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end doProcess
}
