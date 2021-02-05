package com.study.springboot.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.study.springboot.android.dto.ManageRsvDTO;
import com.study.springboot.android.dto.ReservationDTO;

public class ReservationDAO {
	
	public static String driver = "oracle.jdbc.driver.OracleDriver";
	public static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	public static String uid = "scott";
	public static String upw = "tiger";
	
	public static ReservationDAO instance = new ReservationDAO();
	
	public static ReservationDAO getInstance() {
		return instance;
	}
	
	public int insertReservationData(ReservationDTO rsvDTO) throws SQLException {
		
		int resultInt = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			con.setAutoCommit(false);
			
			String query = "insert into reservation "
						 + "(rname, rtype, rpayment, rnum, rtable, rdate, rtime) values "
						 + "(?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, rsvDTO.getRName());
			pstmt.setString(2, rsvDTO.getRType());
			pstmt.setString(3, rsvDTO.getRPayment());
			pstmt.setInt(4, rsvDTO.getRnum());
			pstmt.setInt(5, rsvDTO.getRTable());
			pstmt.setString(6, rsvDTO.getRDate());
			pstmt.setString(7, rsvDTO.getRTime());

			
			int result = pstmt.executeUpdate();
			
			if (result > 0) {
				con.commit();
				resultInt = 1;
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
	
	public ManageRsvDTO getReservationOptions(String Rdate) {
		
		ManageRsvDTO mdto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = getConnection();
			
			String query = "select * from managersv where mdate = ?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, Rdate);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String mdate = rs.getString("mdate");
				int mtable = rs.getInt("mtable");
				String opentime = rs.getString("opentime");
				String closetime = rs.getString("closetime");
				
				mdto = new ManageRsvDTO(mdate, mtable, opentime, closetime);	
			} else {
				mdto = new ManageRsvDTO(Rdate, 10, "09:00", "23:00");
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
