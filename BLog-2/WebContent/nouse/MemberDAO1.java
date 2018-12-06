package com.test.ex;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class MemberDAO1 {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String uid = "scott";
	String pwd = "tiger";
	
	// DAO 객체가 '생성' 될때 connection 생성
	public MemberDAO1() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, uid, pwd);
			System.out.println("데이터베이스 연결!!");
		} catch (Exception e) {			
			e.printStackTrace();
		}	
	} // end Constructor()
	
//
	public int insertPost(int idpost, String title, String story, String picfile_post,
			 String postdate	, String manipulation_read, String writepost) throws SQLException{
		int cnt = 0;
		
		try {
			String sql = "INSERT INTO post(idpost, title, story, picfile_post, postdate, manipulation_read, "
					+ "writepost) " +
					"VALUES(SEQ_post_idpost.nextval, ?, ?, ?, sysdate ,  ?, ? )";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, title);
			pstmt.setString(2, story);
			pstmt.setString(3, picfile_post);
			pstmt.setString(4, manipulation_read);
			pstmt.setString(5, writepost);
			
			cnt = pstmt.executeUpdate();
			
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		
		return cnt;
	}
	
	public int insertPost(MemberDTO1 dto) throws SQLException{
		int idpost = dto.getIdpost();
		String title = dto.getTitle();
		String story = dto.getStory();
		String picfile_post = dto.getPicfile_post();
		String postdate = dto.getPostdate(); 
		String manipulation_read = dto.getManipulation_read();
		String writepost = dto.getWritepost();
//		String memo = dto.getMemo();
		
		int cnt = this.insertPost(idpost, title, story, picfile_post, postdate, manipulation_read, writepost);
		return cnt;
	}
	
	
	public MemberDTO1[] createArray(ResultSet rs) throws SQLException{
		
		ArrayList<MemberDTO1> list = new ArrayList<MemberDTO1>();
		
		while(rs.next()) {
			int idpost = rs.getInt(1);
			String title = rs.getString(2);
			String story = rs.getString(3);
			String picfile_post = rs.getString(4);
				if(picfile_post == null) picfile_post = "";
				String postdate = "";  // null 허용
				if(rs.getDate(5) != null) {
					postdate =
							new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate(5));

				}
			String manipulation_read = rs.getString(6);  // null 허용
			String writepost = rs.getString(7);  // null 허용
			
			MemberDTO1 dto = new MemberDTO1(title, story, picfile_post, manipulation_read, writepost);
			dto.setIdpost(idpost);
			dto.setPostdate(postdate);
			
			list.add(dto);
		} // end while
		
		
		MemberDTO1 [] arr = new MemberDTO1[list.size()];
		list.toArray(arr);
		return arr;
	} // end createArray()
	
	
	public MemberDTO1[] select() throws SQLException{
		
		try {
			String sql = "SELECT * FROM post ORDER BY idpost DESC"; 
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			return createArray(rs);  // MemberDTO[] 리턴
			
		} finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		
		
	} // end select()
	
	
	// find
	public MemberDTO1[] findById(String idpost) throws SQLException{
		try{
			String sql="SELECT * FROM post WHERE title=? ORDER BY idpost DESC";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, idpost);
			rs = pstmt.executeQuery();	
			
			MemberDTO1 arr[] = createArray(rs); 
			return arr;
		}finally{
			if(rs !=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn !=null) conn.close();
		}
	}
	
	// delete
	public int deleteByID(String idpost) throws SQLException{
		try{
			String sql="DELETE FROM post WHERE idpost=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, idpost);
			int cnt = pstmt.executeUpdate();
			return cnt;
		}finally{
			if(pstmt !=null) pstmt.close();
			if(conn !=null) conn.close();
		}

	}
	
	//update
//	public int updatePost(MemberDTO dto) throws SQLException{
//		int cnt = 0;
//		
//		try {
//			String sql = "UPDATE post SET TITLE=? ,STORY=?, PICIFLE_POST=?, manipulation_read=? "
//					+ "WHERE IDPOST=?";
//			
//			pstmt = conn.prepareStatement(sql);
//			
//			pstmt.setString(1, title);
//			pstmt.setString(2, story);
//			pstmt.setString(3, picfile_post);
//			pstmt.setString(4, manipulation_read);
//			pstmt.setString(5, idpost);
//			
//			cnt = pstmt.executeUpdate();
//			
//		} finally {
//			if(pstmt != null) pstmt.close();
//			if(conn != null) conn.close();
//		}
//		
//		return cnt;
//	}
	
//	String title, String story, String picfile_post , String manipulation_read
//	,	String idpost
	
	public int updatePost(String title, String story, String picfile_post,
			 String manipulation_read, String idpost) throws SQLException{
		int cnt = 0;
		
		try {


			String sql = "UPDATE post SET TITLE=? ,STORY=?, PICFILE_POST=?, manipulation_read=? "
					+ "WHERE IDPOST=?";
			
			pstmt = conn.prepareStatement(sql);
			picfile_post = "adress";
			pstmt.setString(1, title);
			pstmt.setString(2, story);
			pstmt.setString(3, picfile_post);
			pstmt.setString(4, manipulation_read);
			pstmt.setString(5, idpost);
			
			cnt = pstmt.executeUpdate();
			
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		System.out.println("cnt = "+ cnt);
		return cnt;
	}
	
//	public int updatePost(MemberDTO dto) throws SQLException{
//		
//		String title = dto.getTitle();
//		String story = dto.getStory();
//		String picfile_post = dto.getPicfile_post();
//		String manipulation_read = dto.getManipulation_read();
//		String writepost = dto.getWritepost();
////		String memo = dto.getMemo();
//		
//		int cnt = this.updatePost(title, story, picfile_post, manipulation_read , writepost);
//		return cnt;
//	}
	
	
	
} // end DAO
