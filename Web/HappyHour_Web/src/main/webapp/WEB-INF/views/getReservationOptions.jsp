<%@ page import="com.study.springboot.dao.*" %>
<%@ page import="com.study.springboot.android.dto.*" %>
<%@ page import="com.study.springboot.auth.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	
	String Rdate = request.getParameter("date");
	String returns = "";
	
	ReservationDAO dao = ReservationDAO.getInstance();
	ManageRsvDTO manageRsvDTO = dao.getReservationOptions(Rdate);
	
	returns = "MANAGE_RSV_OPTIONS " + // splitResult[0]
			  manageRsvDTO.getMdate() + " " + // splitResult[1]
			  manageRsvDTO.getMtable() +  " " + // splitResult[2]
			  manageRsvDTO.getOpentime() + " " + // splitResult[3]
			  manageRsvDTO.getClosetime(); // splitResult[4]
	
	out.println(returns);
%>