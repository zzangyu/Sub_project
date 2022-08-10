package com.memberone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class StudentDAO {
	
	DataSource ds;

	private Connection getConnection() {
		
		Connection conn = null;
		
		try {
			
			Context initContext = new InitialContext();
			ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/myoracle");
			conn = ds.getConnection();
			
		} catch (Exception e) {
			System.out.println("Connection Exception..");
		}
		
		
		return conn;
		
	} // DB 연결
	
	// ID 체크
	public boolean idCheck(String id) {
		
		boolean result = true;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			
			conn = getConnection(); // DB연결 메소드 호출
			pstm = conn.prepareStatement("select * from student where id=?");
			
			pstm.setString(1, id);
			
			rs = pstm.executeQuery(); // 변경일땐 executeUpdate
			if(!rs.next()) {
				result = false;
			}
			
		} catch (SQLException s1) {
			s1.printStackTrace();
		} finally {
			if(rs!=null) try {rs.close();} catch (SQLException s1) { }
			if(pstm!=null) try {pstm.close();} catch (SQLException s2) { }
			if(conn!=null) try {conn.close();} catch (SQLException s3) { }
		}
		
		
		return result;
	}
	
	
	
}
