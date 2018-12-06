//package com.blog.frontcontroller;
//
//import java.io.IOException;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.blog.command.*;
//
///**
// * Servlet implementation class FrontController
// */
////@WebServlet("*.blog")
//public class FrontController extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	public FrontController() {
//		super();
//	}
//
//	protected void blogGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		actionblog(request, response);
//	}
//
//	protected void blogPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		actionblog(request, response);
//	}
//
//	protected void actionblog(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		System.out.println("actionblog() 호출");
//
//		request.setCharacterEncoding("UTF-8");
//
//		// URL 로부터 URI, ContextPath, Command 분리
//		String uri = request.getRequestURI();
//		String conPath = request.getContextPath();
//		String com = uri.substring(conPath.length());
//
//		// 테스트 출력
//		System.out.println("uri: " + uri);
//		System.out.println("conPath: " + conPath);
//		System.out.println("com: " + com);
//
//		// 컨트롤러는 어떠한 Command 수행할지
//		// 어떤 view 에 forward 할지 결정
//		String viewPage = null; // 어떤 페이지 (view)?
//		Command command = null; // 어떤 로직을 수행할지?
//
//		// 컨트롤러는 커맨드에 따라, 로직을 수행하고
//		// 결과를 내보낼 view 결정한다
//
//		if (com.equals("/postList.blog")) { // 게시물 리스트
//			command = new ListCommand();
//			command.execute(request, response);
//			viewPage = "postList.jsp";
//		} else if (com.equals("/writeOut.blog")) { // 게시물 작성
//			command = new writeCommand();
//			command.execute(request, response);
//			viewPage = "writeOut.jsp";
//		} else if (com.equals("/find.blog")) { // 게시물 제목으로 찾기
//			command = new FindCommand();
//			command.execute(request, response);
//			viewPage = "find.jsp";
//		} else if (com.equals("/delete.blog")) { // 게시물 삭제
//			command = new DeleteCommand();
//			command.execute(request, response);
//			viewPage = "delete.jsp";
//		} else if (com.equals("/postView.blog")) { // 게시물 내용
//			command = new PostViewCommand();
//			command.execute(request, response);
//			viewPage = "postView.jsp";
//		} else if (com.equals("/reWriteOut.blog")) { // 게시글 수정
//			command = new rewriteCommand();
//			command.execute(request, response);
//			viewPage = "reWriteOut.jsp";
//		} else if (com.equals("/reWrite.blog")) { // 게시글 내용 불러오기
//			command = new rewriteinsertCommand();
//			command.execute(request, response);
//			viewPage = "reWrite.jsp";
//		} else if (com.equals("/blog_Main.blog")) { // 블로그 메인 화면 -- 회원 테이블과 연결
//			command = new blogmainCommand();
//			command.execute(request, response);
//			viewPage = "blog_Main.jsp";
//		} else if (com.equals("/ComView.blog")) { // 댓글 뷰
//			command = new ComViewCommand();
//			command.execute(request, response);
//			viewPage = "ComView.jsp";
//		} else if (com.equals("/ComWrite.blog")) { // 댓글 작성
//			command = new ComWriteCommand();
//			command.execute(request, response);
//			viewPage = "ComWrite.jsp";
//		}
//
//		// request 를 forward 해줌
//		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
//		dispatcher.forward(request, response);
//	}
//
//}
