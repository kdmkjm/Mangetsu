package com.blog.jsp;

public interface D {
	// DB driver 클래스
	public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	// DB 서버 URL
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	
	// DB 사용자 계정 정보
	public static final String USER = "scott";
	public static final String PASSWD = "tiger";


}
