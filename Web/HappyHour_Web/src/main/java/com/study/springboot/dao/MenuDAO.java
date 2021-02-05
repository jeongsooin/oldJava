package com.study.springboot.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.study.springboot.android.dto.MenuDTO;

public class MenuDAO {
	
	public static String driver = "oracle.jdbc.driver.OracleDriver";
	public static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	public static String uid = "scott";
	public static String upw = "tiger";
	
	public static MenuDAO instance = new MenuDAO();
	
	public static MenuDAO getInstance() {
		return instance;
	}
	
	public MenuDTO getMenuInfo(String mid) {
		MenuDTO dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try { 
			
			con = getConnection();
			
			String query = "select * from menu where mid = ?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				int _mid = rs.getInt("mid");
				String menu_name = rs.getString("menu_name");
				String menu_description = rs.getString("menu_description");
				int menu_price = rs.getInt("menu_price");
				String menu_imagename = rs.getString("menu_imagename");
				String menu_extension = rs.getString("menu_extension");
				int menu_code = rs.getInt("menu_code");
				int menu_qty = rs.getInt("menu_qty");
				String mwriter = rs.getString("mwriter");
				
				dto = new MenuDTO(_mid, menu_name, menu_description, menu_price, menu_imagename, menu_extension, menu_code, menu_qty, mwriter);
				return dto;
			} 
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	        try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();	
	        } catch(Exception e){
	            throw new RuntimeException(e.getMessage());
	        }
		}
		
		return dto;
	}
	
	public ArrayList<MenuDTO> getMenuList() {
		
		ArrayList<MenuDTO> mdtos = new ArrayList<MenuDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try { 
			con = getConnection();
			
			String query = "select * from menu order by mid";
			
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	        try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();	
	        } catch(Exception e){
	            throw new RuntimeException(e.getMessage());
	        }
		}
		
		return mdtos;
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
