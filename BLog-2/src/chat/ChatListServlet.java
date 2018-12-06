package chat;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChatSubmitServlet
 * 
 * 채팅 리스트를 보여줄 때 사용되는 서블릿.
 * 
 * DAO 보면 int 로 받아서 출력하는 거랑
 * chatID로 받아서 출력하는게 있을거임
 * 현재 ID로 받아서 출력하는것만 사용됨.
 */
@WebServlet("/ChatListServlet")
public class ChatListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String listType = request.getParameter("listType");
		if(listType == null || listType.equals("")) response.getWriter().write("");
		else if(listType.equals("today")) response.getWriter().write(getToday());//전부 출력시
		else if(listType.equals("ten")) response.getWriter().write(getTen());//10개만 출력시
		else {
			try {
				Integer.parseInt(listType);
				response.getWriter().write(getID(listType));
			} catch (Exception e) {
				response.getWriter().write("");
			}
		}
	}
	
	public String getToday() {
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		ChatDAO chatDAO = new ChatDAO();
		ArrayList<Chat> chatList = chatDAO.getChatlist(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		
		System.out.println("list 호출 성공");
		
		for(int i = 0 ; i < chatList.size(); i++)
		{
			result.append("[{\"value\": \"" + chatList.get(i).getChaName() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatContent() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatTime() + "\"}]");
			if(i != chatList.size() - 1) result.append(",");
		}
		result.append("], \"last\":\"" + chatList.get(chatList.size()-1).getChatID() + "\"}");
		System.out.println("값 저장 성공");
		return result.toString();
	}
	
	public String getTen() {
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		ChatDAO chatDAO = new ChatDAO();
		ArrayList<Chat> chatList = chatDAO.getChatlistByRecent(10);
		
		System.out.println("list 호출 성공");
		
		for(int i = 0 ; i < chatList.size(); i++)
		{
			result.append("[{\"value\": \"" + chatList.get(i).getChaName() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatContent() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatTime() + "\"}]");
			if(i != chatList.size() - 1) result.append(",");
		}
		result.append("], \"last\":\"" + chatList.get(chatList.size()-1).getChatID() + "\"}");
		System.out.println("값 저장 성공");
		return result.toString();
	}
	
	public String getID(String chatID) {
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		ChatDAO chatDAO = new ChatDAO();
		ArrayList<Chat> chatList = chatDAO.getChatlistByRecent(chatID);
		
		System.out.println("list 호출 성공");
		
		for(int i = 0 ; i < chatList.size(); i++)
		{
			result.append("[{\"value\": \"" + chatList.get(i).getChaName() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatContent() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatTime() + "\"}]");
			if(i != chatList.size() - 1) result.append(",");
		}
		result.append("], \"last\":\"" + chatList.get(chatList.size()-1).getChatID() + "\"}");
		System.out.println("값 저장 성공");
		return result.toString();
	}
}
