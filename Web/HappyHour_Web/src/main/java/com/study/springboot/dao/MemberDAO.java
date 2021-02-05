package com.study.springboot.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.study.springboot.auth.PasswordEncoder;
import com.study.springboot.android.dto.MemberDTO;

public class MemberDAO {
	
	public static final int MEMBER_NONEXISTENT = 0;
	public static final int MEMBER_EXISTENT = 1;
	public static final int MEMBER_JOIN_FAIL = 0;
	public static final int MEMBER_JOIN_SUCCESS = 1;
	public static final int MEMBER_UPDATE_SUCCESS = 1;
	public static final int MEMBER_UPDATE_FAILED = 0;
	public static final int MEMBER_LOGIN_PW_NO_GOOD = 0;
	public static final int MEMBER_LOGIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_IS_NOT = -1;
	
	public static String driver = "oracle.jdbc.driver.OracleDriver";
	public static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	public static String uid = "scott";
	public static String upw = "tiger";
	
	public static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	public int insertMember(MemberDTO memberDTO) throws SQLException {
		
		int resultInt = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		
		try {
			con = getConnection();
			con.setAutoCommit(false);
			
			String query = "insert into member "
						 + "(email, password, name, phone, isadmin, isvalid, alert, birth, gender, rsvok, rsvx, deviceid, mid) values "
						 + "(?, ?, ?, ?, ?, ?, ?, ?, ?, 0, 0, 0, member_seq.nextval)";
			
			pstmt = con.prepareStatement(query);
			System.out.println(memberDTO.getPassword());
			pstmt.setString(1, memberDTO.getEmail());
			pstmt.setString(2, memberDTO.getPassword());
			pstmt.setString(3, memberDTO.getName());
			pstmt.setString(4, memberDTO.getPhone());
			pstmt.setString(5, memberDTO.getIsAdmin());
			pstmt.setString(6, memberDTO.getIsValid());
			pstmt.setString(7, memberDTO.getAlert());
			pstmt.setString(8, memberDTO.getBirth());
			pstmt.setString(9, memberDTO.getGender());
			
			int result = pstmt.executeUpdate();
			
			if (memberDTO.getIsAdmin().contains("YES")) {
				query = "insert into user_list " + 
						"(name, password, authority) values " + 
						"(?, ?, ?)";
				pstmt2 = con.prepareStatement(query);
				pstmt2.setString(1, memberDTO.getEmail());
				pstmt2.setString(2, memberDTO.getPassword());
				pstmt2.setString(3, "ROLE_ADMIN");
				
				result = pstmt2.executeUpdate();
				
			} else {
				query = "insert into user_list " + 
						"(name, password, AUTHORITY) values " + 
						"(?, ?, ?)";
				pstmt2 = con.prepareStatement(query);
				pstmt2.setString(1, memberDTO.getEmail());
				pstmt2.setString(2, memberDTO.getPassword());
				pstmt2.setString(3, "ROLE_USER");
				
				result = pstmt2.executeUpdate();
			}
			
			if (result > 0) {
				con.commit();
				resultInt = MEMBER_JOIN_SUCCESS;
			}
			
		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
		} finally { 
			try { 
				if (con != null) con.close();
				if (pstmt != null) pstmt.close();
				if (pstmt2 != null) pstmt2.close();
			} catch (Exception e) { e.printStackTrace(); }			
		}
		
		return resultInt;
	}
	
	public int updateMember(MemberDTO memberDTO) throws SQLException {
		
		int resultInt = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try { 			
			con = getConnection();
			con.setAutoCommit(false);
			
			String query = "update member " +
						   " set name = ?, phone = ?, alert = ?, birth = ?, gender = ?" + 
					       " where email = ?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberDTO.getName());
			pstmt.setString(2, memberDTO.getPhone());
			pstmt.setString(3, memberDTO.getAlert());
			pstmt.setString(4, memberDTO.getBirth());
			pstmt.setString(5, memberDTO.getGender());
			pstmt.setString(6, memberDTO.getEmail());
			
			int result = pstmt.executeUpdate();
			
			if (result > 0) {
				con.commit();
				resultInt = MEMBER_UPDATE_SUCCESS;
			} else {
				resultInt = MEMBER_UPDATE_FAILED;
			}
			
		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
		} finally { 
			try { 
				if (con != null) con.close();
				if (pstmt != null) pstmt.close();
			} catch (Exception e) { e.printStackTrace(); }			
		}
		
		return resultInt;
	}
	
	public int updatePassword(String email, String password) throws SQLException {
		
		int resultInt = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try { 			
			con = getConnection();
			con.setAutoCommit(false);
			
			String query = "update member " +
						   " set password = ? " + 
					       " where email = ?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, password);
			pstmt.setString(2, email);
			
			int result = pstmt.executeUpdate();
			
			if (result > 0) {
				con.commit();
				resultInt = MEMBER_UPDATE_SUCCESS;
			} else {
				resultInt = MEMBER_UPDATE_FAILED;
			}
			
		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
		} finally { 
			try { 
				if (con != null) con.close();
				if (pstmt != null) pstmt.close();
			} catch (Exception e) { e.printStackTrace(); }			
		}
		
		return resultInt;
	}
	
	public int updateMemberRSV(int rsvok, int rsvx, String email) throws SQLException {
		
		int resultInt = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try { 			
			con = getConnection();
			con.setAutoCommit(false);
			
			String query = "update member " +
						   " set rsvok = ?, rsvx = ?" + 
					       " where email = ?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, rsvok);
			pstmt.setInt(2, rsvx);
			pstmt.setString(6, email);
			
			int result = pstmt.executeUpdate();
			
			if (result > 0) {
				con.commit();
				resultInt = MEMBER_UPDATE_SUCCESS;
			} else {
				resultInt = MEMBER_UPDATE_FAILED;
			}
			
		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
		} finally { 
			try { 
				if (con != null) con.close();
				if (pstmt != null) pstmt.close();
			} catch (Exception e) { e.printStackTrace(); }			
		}
		
		return resultInt;
	}
	
	public int updateDeviceId(String email, String deviceid) throws SQLException {
		
		int resultInt = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try { 			
			con = getConnection();
			con.setAutoCommit(false);
			
			String query = "update member " +
						   " set deviceid = ? " + 
					       " where email = ?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, deviceid);
			pstmt.setString(2, email);
			
			int result = pstmt.executeUpdate();
			
			if (result > 0) {
				con.commit();
				resultInt = MEMBER_UPDATE_SUCCESS;
			} else {
				resultInt = MEMBER_UPDATE_FAILED;
			}
			
		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
		} finally { 
			try { 
				if (con != null) con.close();
				if (pstmt != null) pstmt.close();
			} catch (Exception e) { e.printStackTrace(); }			
		}
		
		return resultInt;
	}
	
	public ArrayList<MemberDTO> getMemberList() {
		
		ArrayList<MemberDTO> mdtos = new ArrayList<MemberDTO>();
		
		return mdtos;
	}
	
	public MemberDTO getMember(String email) {
		
		MemberDTO mdto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = getConnection();
			
			String query = "select * from member where email = ?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String _email = rs.getString("email");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String isadmin = rs.getString("isadmin");
				String isvalid = rs.getString("isvalid");
				String alert = rs.getString("alert");
				String birth = rs.getString("birth");
				String gender = rs.getString("gender");
				String rsvok = rs.getString("rsvok");
				String rsvx = rs.getString("rsvx");
				String deviceid = rs.getString("deviceid");
				int mid = rs.getInt("mid");
				
				mdto = new MemberDTO(_email, password, phone, name, isadmin, isvalid, alert, birth, gender, rsvok, rsvx, deviceid, mid);
			} 
			return mdto;
		} catch (Exception sqle) {
	        throw new RuntimeException(sqle.getMessage());
	    } finally {
	        try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();	
	        } catch(Exception e){
	            throw new RuntimeException(e.getMessage());
	        }
	    }
	}
	
	public ArrayList<String> getDeviceId() {
		ArrayList<String> deviceIdList = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = getConnection();
			
			String query = "select deviceid from member where isadmin = 'NO'";
			
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				deviceIdList.add(rs.getString("deviceid"));
			} 
			return deviceIdList;
		} catch (Exception sqle) {
	        throw new RuntimeException(sqle.getMessage());
	    } finally {
	        try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();	
	        } catch(Exception e){
	            throw new RuntimeException(e.getMessage());
	        }
	    }
	}
	
	public int deleteMember(String email) throws SQLException {
		
		int resultInt = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			
			String query = "delete from member where email = ? ";

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, email);
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				query = "delete from user_list where email = ?";
				pstmt1 = con.prepareStatement(query);
				pstmt1.setString(1, email);
				result = pstmt1.executeUpdate();
			} else {
				con.rollback();
			}
			
		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
		} finally { 
			try { 
				if (con != null) con.close();
				if (pstmt != null) pstmt.close();
				if (pstmt1 != null) pstmt1.close();
			} catch (Exception e) { e.printStackTrace(); }			
		}
		
		return resultInt;
	}
	
	public int loginCheck(String email, String password) {
		
		int resultInt = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbPassword = "";
		
		PasswordEncoder passwordEncoder = PasswordEncoder.getInstance();
		
		try {
			
			con = getConnection();
			
			String query = "select password from member where email = ?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dbPassword = rs.getString("password");
								
				if (passwordEncoder.passwordEncoder().matches(password, dbPassword)) { 
					resultInt = MEMBER_LOGIN_SUCCESS; 
				} else { 
					resultInt = MEMBER_LOGIN_PW_NO_GOOD; 
				}
				
			} else {
				resultInt = MEMBER_LOGIN_IS_NOT;
			}
			
			return resultInt;
		} catch (Exception sqle) {
	        sqle.printStackTrace();
	    } finally {
	        try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();	
	        } catch(Exception e){
	            throw new RuntimeException(e.getMessage());
	        }
	    }
		return resultInt;
	}
	
	public int confirmEmail(String email) {
		
		int resultInt = 0;
		
		return resultInt;
	}
	
	private Connection getConnection() {
		
		Connection con = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, uid, upw);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return con;
	}
}
