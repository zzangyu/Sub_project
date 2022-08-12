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
	}
	// ㅡㅡㅡㅡㅡㅡㅡㅡ End idCheck ㅡㅡㅡㅡㅡㅡㅡㅡ
	
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
	// ㅡㅡㅡㅡㅡㅡㅡㅡ End getZipCode ㅡㅡㅡㅡㅡㅡㅡㅡ
	
	public boolean memberInsert(StudentVO vo) {
		
		boolean flag = false;
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			
			conn = getConnection(); // DB연결 메소드 호출
			String strQuery = "insert into student values(?,?,?,?,?,?,?,?,?,?)";
			pstm = conn.prepareStatement(strQuery);
			
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
	// ㅡㅡㅡㅡㅡㅡㅡㅡ End memberInsert ㅡㅡㅡㅡㅡㅡㅡㅡ
	
	/*
	 * 로그인 버튼을 클릭하면 입력한 아이디와 비밀번호를 데이터베이스에 저장되어 있는
	 * 아이디와 비밀번호를 비교해서 같으면 로그인 성공, 다르면 실패처리를 해야함
	 * 데이터베이스에서 아이디와 비밀번호를 비교하여 그 결과를 정수형으로 리턴해주는 메소드를 구현
	 * 1: 로그인성공 / 0: 비밀번호 오류 / -1: 아이디 없음
	 */
	public int loginCheck(String id, String pass) {
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		int check = -1; // 아이디 없음
		
		try {
			
			conn = getConnection();
			String strQuery = "select pass from student where id = ?";
			pstm = conn.prepareStatement(strQuery);
			
			pstm.setString(1, id);
			
			rs = pstm.executeQuery();
			
			if( rs.next()) {
				String dbPass = rs.getString("pass");
				if(pass.equals(dbPass)) {
					check = 1;
				} else {
					check = 0;
				}
			}
			
		} catch (SQLException s1) {
		
			s1.printStackTrace();
		
		} finally {
		
			if(rs!=null) try {rs.close();} catch (SQLException s1) { }
			if(pstm!=null) try {pstm.close();} catch (SQLException s2) { }
			if(conn!=null) try {conn.close();} catch (SQLException s3) { }
		}
		
		return check;
	} 
	// ㅡㅡㅡㅡㅡㅡㅡㅡ End loginCheck ㅡㅡㅡㅡㅡㅡㅡㅡ
	
	public StudentVO getMember(String id) {
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StudentVO vo = null;
		
		try {
			
			conn = getConnection();
			String strQuery = "select * from student where id = ?";
			pstm = conn.prepareStatement(strQuery);
			
			pstm.setString(1, id);
			
			rs = pstm.executeQuery();
			
			if(rs.next()) { // 아이디에 해당하는 회원이 존재한다면
				vo = new StudentVO();
				vo.setId(rs.getString("id"));
				vo.setPass(rs.getString("pass"));
				vo.setName(rs.getString("name"));
				vo.setPhone1(rs.getString("phone1"));
				vo.setPhone2(rs.getString("phone2"));
				vo.setPhone3(rs.getString("phone3"));
				vo.setEmail(rs.getString("email"));
				vo.setZipcode(rs.getString("zipcode"));
				vo.setAddress1(rs.getString("address1"));
				vo.setAddress2(rs.getString("address2"));
			}
			
			
		} catch (SQLException s1) {
		
			s1.printStackTrace();
		
		} finally {
		
			if(rs!=null) try {rs.close();} catch (SQLException s1) { }
			if(pstm!=null) try {pstm.close();} catch (SQLException s2) { }
			if(conn!=null) try {conn.close();} catch (SQLException s3) { }
		}
		
		return vo;
	}
	// ㅡㅡㅡㅡㅡㅡㅡㅡ End getMember ㅡㅡㅡㅡㅡㅡㅡㅡ
	
	// 정보수정 버튼을 클릭했을 경우 데이터베이스에 update를 수행해야한다.
	// 정보수정 처리를 할 수 있는 메소드 구현
	public void updateMember(StudentVO vo) {
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = getConnection();
			String strQuery = "update student set pass=?, phone1=?, phone2=?, phone3=?, email=?, zipcode=?, address1=?, address2=? where id=?";
			pstm = conn.prepareStatement(strQuery);
			
			pstm.setString(1, vo.getPass());
			pstm.setString(2, vo.getPhone1());
			pstm.setString(3, vo.getPhone2());
			pstm.setString(4, vo.getPhone3());
			pstm.setString(5, vo.getEmail());
			pstm.setString(6, vo.getZipcode());
			pstm.setString(7, vo.getAddress1());
			pstm.setString(8, vo.getAddress2());
			pstm.setString(9, vo.getId());
			
			pstm.executeUpdate();
			
		} catch (SQLException s1) {
			s1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstm!=null) try {pstm.close();} catch (SQLException s2) { }
			if(conn!=null) try {conn.close();} catch (SQLException s3) { }
		}
	}
}
