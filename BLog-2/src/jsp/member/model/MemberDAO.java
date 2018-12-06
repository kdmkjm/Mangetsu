package jsp.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import javax.naming.NamingException;

import jsp.util.DBConnection;

public class MemberDAO {
	private static MemberDAO instance;

	// singleton
	private MemberDAO() {
	}

	public static MemberDAO getInstance() {
		if (instance == null)
			instance = new MemberDAO();
		return instance;
	}

	// 회원정보를 JSP_MEMBER 테이블에 저장하는 메서드
	public void insertMember(MemberBean member) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 커넥션을 가져온다.
			conn = DBConnection.getConnection();
			System.out.println("connection OK");
			// 자동 커밋을 false로 한다.
			conn.setAutoCommit(false);

			// 쿼리 생성한다.
			// 가입일의 경우 자동으로 세팅되게 하기 위해 sysdate를 사용
			StringBuffer sql = new StringBuffer();
			sql.append("insert into JSP_MEMBER(Userno,id,password,name,mail,phone,address,reg)" + " values");
			sql.append("( SEQ_jsp_member_Userno.nextval , ?, ?, ?, ?, ?, ?, sysdate)");
			/*
			 * StringBuffer에 담긴 값을 얻으려면 toString()메서드를 이용해야 한다.
			 */
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getMail1() + "@" + member.getMail2());
			pstmt.setString(5, member.getPhone());
			pstmt.setString(6, member.getAddress2() + ":" + member.getAddress3());
//            pstmt.setString(8, "0");
			// 쿼리 실행
			pstmt.executeUpdate();
			// 완료시 커밋
			conn.commit();

		} catch (ClassNotFoundException | NamingException | SQLException sqle) {
			// 오류시 롤백
			conn.rollback();

			throw new RuntimeException(sqle.getMessage());
		} finally {
			// Connection, PreparedStatement를 닫는다.
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		} // end try~catch
	} // end insertMember()

	public int loginCheck(String id, String pw) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String dpw = "";// dpw 는 database 내의 password값
						// pw 는 입력받은 pw값
		// String chk = "";
		int x = -1;

		try {
			StringBuffer query = new StringBuffer();// 패스워드 체크 쿼리
			// StringBuffer query2 = new StringBuffer();//이메일 체크 쿼리
			query.append("SELECT PASSWORD FROM JSP_MEMBER WHERE ID=?");
			// query2.append("SELECT MAILCHECK FROM JSP_MEMBER WHERE ID=?");
			conn = DBConnection.getConnection();

			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dpw = rs.getString("password");

				if (dpw.equals(pw)) {
					x = 1;
				} else
					x = 0;
			} else {
				x = -1;
			}
			return x;
		} catch (Exception sqle) {
			throw new RuntimeException(sqle.getMessage());
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}// end loginCheck()

	public MemberBean getUserInfo(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberBean member = null;

		try {
			// 쿼리
			StringBuffer query = new StringBuffer();
			query.append("SELECT * FROM JSP_MEMBER WHERE ID=?");

			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) // 회원정보를 DTO에 담는다.
			{
				// 이메일을 @ 기준으로 자른다.
				String mail = rs.getString("mail");
//                String address = rs.getString("address");
				int idx = mail.indexOf("@");
//                int idx2 = address.indexOf(":");
				String mail1 = mail.substring(0, idx);
				String mail2 = mail.substring(idx + 1);

//                String address2 = address.substring(0, idx2);
//                String address3 = address.substring(idx2+1);
				// 자바빈에 정보를 담는다.
				member = new MemberBean();
				member.setUserno(rs.getString("userno"));
				member.setId(rs.getString("id"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setMail1(mail1);
				member.setMail2(mail2);
				member.setPhone(rs.getString("phone"));
				member.setAddress2(rs.getString("address"));
//                member.setAddress3(address3);
				member.setReg(rs.getTimestamp("reg"));
				member.setMailcheck(rs.getString("mailcheck"));
				member.setStatus(rs.getString("status"));
				member.setIntro(rs.getString("intro"));
				member.setIntro_blog(rs.getString("intro_blog"));
				member.setPic_blog(rs.getString("pic_blog"));
			}

			return member;

		} catch (Exception sqle) {
			throw new RuntimeException(sqle.getMessage());
		} finally {
			// Connection, PreparedStatement를 닫는다.
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	} // end getUserInfo

	public void updateMember(MemberBean member) throws SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			StringBuffer query = new StringBuffer();
			query.append("UPDATE JSP_MEMBER SET");
			query.append(" PASSWORD=?, MAIL=?, PHONE=?, ADDRESS=?");
			query.append(" WHERE ID=?");

			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(query.toString());

			// 자동 커밋을 false로 한다.
			conn.setAutoCommit(false);

			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getMail1() + "@" + member.getMail2());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getAddress2() + ":" + member.getAddress3());
			pstmt.setString(5, member.getId());

			pstmt.executeUpdate();
			// 완료시 커밋
			conn.commit();

		} catch (Exception sqle) {
			conn.rollback(); // 오류시 롤백
			throw new RuntimeException(sqle.getMessage());
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	} // end updateMember

	/**
	 * 회원정보를 삭제한다.
	 * 
	 * @param id 회원정보 삭제 시 필요한 아이디
	 * @param pw 회원정보 삭제 시 필요한 비밀번호
	 * @return x : deleteMember() 수행 후 결과값
	 */
	@SuppressWarnings("resource")
	public int deleteMember(String id, String pw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String dbpw = ""; // DB상의 비밀번호를 담아둘 변수
		int x = -1;

		try {
			// 비밀번호 조회
			StringBuffer query1 = new StringBuffer();
			query1.append("SELECT PASSWORD FROM JSP_MEMBER WHERE ID=?");

			// 회원 삭제
			StringBuffer query2 = new StringBuffer();
			query2.append("DELETE FROM JSP_MEMBER WHERE ID=?");

			conn = DBConnection.getConnection();

			// 자동 커밋을 false로 한다.
			conn.setAutoCommit(false);

			// 1. 아이디에 해당하는 비밀번호를 조회한다.
			pstmt = conn.prepareStatement(query1.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dbpw = rs.getString("password");
				if (dbpw.equals(pw)) // 입력된 비밀번호와 DB비번 비교
				{
					// 같을경우 회원삭제 진행
					pstmt = conn.prepareStatement(query2.toString());
					pstmt.setString(1, id);
					pstmt.executeUpdate();
					conn.commit();
					x = 1; // 삭제 성공
				} else {
					x = 0; // 비밀번호 비교결과 - 다름
				}
			}

			return x;

		} catch (Exception sqle) {
			try {
				conn.rollback(); // 오류시 롤백
			} catch (SQLException e) {
				e.printStackTrace();
			}
			throw new RuntimeException(sqle.getMessage());
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	} // end deleteMember

	public ArrayList<MemberBean> getMemberList() {
		ArrayList<MemberBean> memberList = new ArrayList<MemberBean>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberBean member = null;

		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT * FROM JSP_MEMBER");

			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(query.toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				member = new MemberBean();
				member.setUserno(rs.getString("userno"));
				member.setId(rs.getString("id"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setMail1(rs.getString("mail"));
				member.setPhone(rs.getString("phone"));
				member.setAddress2(rs.getString("address"));
				member.setReg(rs.getTimestamp("reg"));
				member.setMailcheck(rs.getString("mailcheck"));
				member.setStatus(rs.getString("status"));
				member.setIntro(rs.getString("intro"));
				member.setIntro_blog(rs.getString("intro_blog"));
				member.setPic_blog(rs.getString("pic_blog"));
                memberList.add(member);
			}

			return memberList;

		} catch (Exception sqle) {
			throw new RuntimeException(sqle.getMessage());
		} finally {
			// Connection, PreparedStatement를 닫는다.
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	} // end getMemberList

	public boolean duplicateIdCheck(String id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		boolean x = false;

		try {
			// 쿼리
			StringBuffer query = new StringBuffer();
			query.append("SELECT ID FROM JSP_MEMBER WHERE ID=?");

			conn = DBConnection.getConnection();
			pstm = conn.prepareStatement(query.toString());
			pstm.setString(1, id);
			rs = pstm.executeQuery();

			if (rs.next())
				x = true; // 해당 아이디 존재

			return x;

		} catch (Exception sqle) {
			throw new RuntimeException(sqle.getMessage());
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
					pstm = null;
				}
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	} // end duplicateIdCheck()

}