package com.blog.ex;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class DAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String uid = "scott";
	String pwd = "tiger";

	// DAO 객체가 '생성' 될때 connection 생성
	public DAO() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, uid, pwd);
			System.out.println("데이터베이스 연결!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end Constructor()

//
	public int insertPost(int idpost, String title, String story, String postdate, String manipulation_read,
			String writepost) throws SQLException {
		int cnt = 0;

		try {
			String sql = "INSERT INTO post(idpost, title, story, postdate, manipulation_read, " + "writepost) "
					+ "VALUES(SEQ_post_idpost.nextval,?,?,sysdate,?,?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, title);
			pstmt.setString(2, story);
			pstmt.setString(3, manipulation_read);
			pstmt.setString(4, writepost);

			cnt = pstmt.executeUpdate();

		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

		return cnt;
	}

	public int insertPost(BlogDTO dto) throws SQLException {
		int idpost = dto.getIdpost();
		String title = dto.getTitle();
		String story = dto.getStory();
		String postdate = dto.getPostdate();
		String manipulation_read = dto.getManipulation_read();
		String writepost = dto.getWritepost();
//		String memo = dto.getMemo();

		int cnt = this.insertPost(idpost, title, story, postdate, manipulation_read, writepost);
		return cnt;
	}

	public BlogDTO[] createArray(ResultSet rs) throws SQLException {

		ArrayList<BlogDTO> list = new ArrayList<BlogDTO>();

		while (rs.next()) {
			int idpost = rs.getInt(1);
			String title = rs.getString(2);
			String story = rs.getString(3);
			if (story == null)
				story = "";
			String postdate = ""; // null 허용
			if (rs.getDate(4) != null) {
				postdate = new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate(4));

			}
			String manipulation_read = rs.getString(5);
			String writepost = rs.getString(6);
			String cnt_post = "0";
			cnt_post = rs.getString(7);

			BlogDTO dto = new BlogDTO(title, story, manipulation_read, writepost);
			dto.setIdpost(idpost);
			dto.setPostdate(postdate);
			dto.setCnt_post(cnt_post);

			list.add(dto);
		} // end while

		BlogDTO[] arr = new BlogDTO[list.size()];
		list.toArray(arr);
		return arr;
	} // end createArray()

	public BlogDTO[] select() throws SQLException {

		try {
			String sql = "SELECT * FROM post ORDER BY idpost DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			return createArray(rs); // MemberDTO[] 리턴

		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

	} // end select()

	public BlogDTO[] postview(String idpost) throws SQLException {

		try {
			String sql = "UPDATE post SET cnt_post =+"
					+ " (SELECT nvl(cnt_post,0)+1 FROM post WHERE idpost = ?) WHERE idpost = ?	";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idpost);
			pstmt.setString(2, idpost);
			pstmt.execute();
			sql = "SELECT * FROM post ORDER BY idpost DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			return createArray(rs); // MemberDTO[] 리턴

		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

	} // end select()

	// find
	public BlogDTO[] findById(String idpost) throws SQLException {
		try {
			String sql = "SELECT * FROM post WHERE title=? ORDER BY idpost DESC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idpost);
			rs = pstmt.executeQuery();

			BlogDTO arr[] = createArray(rs);
			return arr;
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
	}

	// delete
	public int deleteByID(String idpost) throws SQLException {
		try {
			String sql = "DELETE FROM post_com WHERE writecom=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idpost);
			pstmt.execute();
			sql = "DELETE FROM post WHERE idpost=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idpost);
			int cnt = pstmt.executeUpdate();
			return cnt;
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

	}

	public int rePost(String title, String story, String manipulation_read, String idpost) throws SQLException {
		int cnt = 0;

		try {

			String sql = "UPDATE post SET TITLE=? , STORY=? , manipulation_read=? " + "WHERE IDPOST=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, story);
			pstmt.setString(3, manipulation_read);
			pstmt.setString(4, idpost);

			cnt = pstmt.executeUpdate();

		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		System.out.println("cnt = " + cnt);
		return cnt;
	}

//	public UserDTO loaduserdata(String findUserNo) throws SQLException {
//		UserDTO us = new UserDTO();
////			ArrayList<UserDTO> list = new ArrayList<UserDTO>();
//		try {
//			String sql = "SELECT * FROM userpost Where USERNO=?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, findUserNo);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				String userno = rs.getString(1);
//				String iduser = rs.getString(2);
//				String pwUser = rs.getString(3);
//				String name = rs.getString(4);
//				String Phonenum = rs.getString(5);// null 허용
//				if (Phonenum == null)
//					Phonenum = "";
//				String Email = rs.getString(6);// null 허용
//				if (Email == null)
//					Email = "";
//
//				String registerdate = ""; // null 허용
//				if (rs.getDate(7) != null) {
//					registerdate = new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate(7));
//				}
//				String Status = rs.getString(8);
//				String intro = rs.getString(9);
//				String intro_blog = rs.getString(10);
//				String Cnt_blog = rs.getString(11);
//				String pic_blog = rs.getString(12);
//				if (pic_blog == null)
//					pic_blog = "";
//				UserDTO dto = new UserDTO(iduser, pwUser, name, Phonenum, Email, intro, intro_blog, Cnt_blog, pic_blog);
//				dto.setStatus(Status);
//				dto.setUserno(userno);
//				dto.setRegisterdate(registerdate);
//				us = dto;
////			list.add(dto);
//			}
//
//			return us;
////			UserDTO[] arr = new UserDTO[list.size()];
////			list.toArray(arr);
////			return arr;
//		} finally {
////			if (rs != null)
////				rs.close();
////			if (pstmt != null)
////				pstmt.close();
////			if (conn != null)
////				conn.close();
//		}
//
//	}

	public int comwrite(int idpost_com, String writeCom, String post_com, String Com_story, String Com_name,
			String Com_date) throws SQLException {

		int cnt = 0;

		try {
			String sql = "INSERT INTO post_com(Idpost_com, writecom, post_com, Com_story, Com_name, Com_date )"
					+ "VALUES(SEQ_Post_com_Idpost_com.nextval,?,?,?, ? ,sysdate)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, writeCom);
			pstmt.setString(2, post_com);
			pstmt.setString(3, Com_story);
			pstmt.setString(4, Com_name);

			cnt = pstmt.executeUpdate();

		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

		return cnt;
	}

	public int comwrite(ComDTO dto) throws SQLException {
		int Idpost_com = dto.getIdpost_com();
		String Writecom = dto.getWritecom();
		String Post_com = dto.getPost_com();
		String Com_story = dto.getCom_story();
		String Com_name = dto.getCom_name();
		String Com_date = dto.getCom_date();

		int cnt = this.comwrite(Idpost_com, Writecom, Post_com, Com_story, Com_name, Com_date);
		return cnt;
	}

	public ComDTO[] createArraycom(ResultSet rs) throws SQLException {

		ArrayList<ComDTO> list = new ArrayList<ComDTO>();

		while (rs.next()) {
			int Idpost_com = rs.getInt(1);
			String Writecom = rs.getString(2);
			String Post_com = rs.getString(3);
			String Com_story = rs.getString(4);
			if (Com_story == null)
				Com_story = "";
			String Com_name = rs.getString(5);
			String Com_date = "";
			if (rs.getDate(6) != null) {
				Com_date = new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate(6));
			}

			ComDTO dto = new ComDTO(Writecom, Post_com, Com_story, Com_name);
			dto.setIdpost_com(Idpost_com);
			dto.setCom_date(Com_date);

			list.add(dto);
		} // end while

		ComDTO[] arr = new ComDTO[list.size()];
		list.toArray(arr);
		return arr;
	} // end createArray()

	public ComDTO[] selectcom(String post_com) throws SQLException {

		try {
			String sql = "SELECT * FROM Post_com WHERE Post_com=? ORDER BY Idpost_com DESC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, post_com);
			rs = pstmt.executeQuery();

			return createArraycom(rs); // MemberDTO[] 리턴

		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

	} // end select()

	public int deleteByComId(String delcomnm) throws SQLException {
		try {
			String sql = "DELETE FROM post_com WHERE Idpost_com=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, delcomnm);
			int cnt = pstmt.executeUpdate();
			return cnt;
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

	}
	//============================================================================친구 관련 DAO
	
	
	public int plusfriend(String idme , String idfriend) throws SQLException {

		int cnt = 0;

		try {
			String sql = "INSERT INTO friend(idme, idfriend )"
					+ "VALUES(?,?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, idfriend);
			pstmt.setString(2, idme);
			pstmt.executeUpdate();
			
			sql = "INSERT INTO friend(idme, idfriend )"
					+ "VALUES(?,?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, idme);
			pstmt.setString(2, idfriend);


			cnt = pstmt.executeUpdate();

		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

		return cnt;
	}

	public int plusfriend(friendDTO dto) throws SQLException {
		String idme = dto.getIdme();
		String idfriend = dto.getidfriendr();


		int cnt = this.plusfriend(idme, idfriend);
		return cnt;
	}
	
	public friendDTO[] listfriend(String userno) throws SQLException {

		try {
			String sql = "SELECT * FROM friend WHERE idme=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userno);
			rs = pstmt.executeQuery();

			return Arrayfriend(rs); // MemberDTO[] 리턴

		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

	} // end select()
	
	public friendDTO[] findfriend(String idme , String idfriend) throws SQLException {
		
		try {
			String sql = "SELECT * FROM friend WHERE idme=? and idfriend = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idme);
			pstmt.setString(1, idfriend);
			rs = pstmt.executeQuery();
			
			return Arrayfriend(rs); // MemberDTO[] 리턴
			
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		
	} // end select()
	
	public friendDTO[] Arrayfriend(ResultSet rs) throws SQLException {

		ArrayList<friendDTO> list = new ArrayList<friendDTO>();

		while (rs.next()) {

			String idme = rs.getString(1);
			String idfriend = rs.getString(2);

			friendDTO dto = new friendDTO(idme, idfriend);

			list.add(dto);
		} // end while

		friendDTO[] arr = new friendDTO[list.size()];
		list.toArray(arr);
		return arr;
	} // end createArray()
	
	public int frienddelete(String idme , String idfriend) throws SQLException {
		try {
			String sql = "DELETE FROM friend WHERE idfriend=? and idme = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idme);
			pstmt.setString(2, idfriend);
			pstmt.executeUpdate();
			sql = "DELETE FROM friend WHERE idfriend=? and idme = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idfriend);
			pstmt.setString(2, idme);
			int cnt = pstmt.executeUpdate();
			return cnt;
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

	}
	
	
} // end DAO
