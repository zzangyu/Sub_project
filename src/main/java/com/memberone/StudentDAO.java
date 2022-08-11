package com.memberone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	} // end idCheck
	
	// 우편번호를 데이터베이스에서 검색해서 Vector에 저장한 후 리턴해주는 메소드 구현
	public Vector<ZipCodeVO> getZipCode(String dong) {
		
		Vector<ZipCodeVO> vecList = new Vector<ZipCodeVO>();
				
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			
			conn = getConnection(); // DB연결 메소드 호출
			pstm = conn.prepareStatement("select * from zipcode where dong like '"+dong+"%'");
			
			
			rs = pstm.executeQuery(); // 변경일땐 executeUpdate
			
			while(rs.next()) {
				ZipCodeVO tempZipCode = new ZipCodeVO();
				tempZipCode.setZipcode(rs.getString("zipcode"));
				tempZipCode.setSido(rs.getString("sido"));
				tempZipCode.setGugun(rs.getString("gugun"));
				tempZipCode.setDong(rs.getString("dong"));
				tempZipCode.setRi(rs.getString("ri"));
				tempZipCode.setBunji(rs.getString("bunji"));
				
				vecList.addElement(tempZipCode);
			}
			
		} catch (SQLException s1) {
			s1.printStackTrace();
		} finally {
			if(rs!=null) try {rs.close();} catch (SQLException s1) { }
			if(pstm!=null) try {pstm.close();} catch (SQLException s2) { }
			if(conn!=null) try {conn.close();} catch (SQLException s3) { }
		}
		
		return vecList;
	}
	
	public boolean memberInsert(StudentVO vo) {
		
		boolean flag = false;
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			
			conn = getConnection(); // DB연결 메소드 호출
			String strQuery = "insert into student values(?,?,?,?,?,?,?,?,?,?)";
			pstm = conn.prepareStatement(strQuery);
			rs = pstm.executeQuery(); // 변경일땐 executeUpdate
			
			pstm.setString(1, vo.getId());
			pstm.setString(2, vo.getPass());
			pstm.setString(3, vo.getName());
			pstm.setString(4, vo.getPhone1());
			pstm.setString(5, vo.getPhone2());
			pstm.setString(6, vo.getPhone3());
			pstm.setString(7, vo.getEmail());
			pstm.setString(8, vo.getZipcode());
			pstm.setString(9, vo.getAddress1());
			pstm.setString(10, vo.getAddress2());
			
			int count = pstm.executeUpdate();
			if (count > 0) {
				flag = true;
			}
			
		} catch (SQLException s1) {
			s1.printStackTrace();
		} finally {
			if(rs!=null) try {rs.close();} catch (SQLException s1) { }
			if(pstm!=null) try {pstm.close();} catch (SQLException s2) { }
			if(conn!=null) try {conn.close();} catch (SQLException s3) { }
		}
		
		return flag;
	}
	
	
}
