<%@ page import="com.study.springboot.dao.*" %>
<%@ page import="com.study.springboot.android.dto.*" %>
<%@ page import="com.study.springboot.auth.*" %>
<%@ page import="org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	request.setCharacterEncoding("UTF-8");

	String rname = request.getParameter("rname");
	String rtype = request.getParameter("rtype");
    String rpayment = request.getParameter("rpayment");
    String rnum = request.getParameter("rnum");
    String rtable = request.getParameter("rtable");
    String rdate = request.getParameter("rdate");
    String rtime = request.getParameter("rtime");

	ReservationDTO dto = new ReservationDTO();
	
    dto.setRName(rname);
    dto.setRType(rtype);
    dto.setRPayment(rpayment);
    dto.setRnum(Integer.parseInt(rnum));
    dto.setRTable(Integer.parseInt(rtable));
    dto.setRDate(rdate);
    dto.setRTime(rtime);

	ReservationDAO dao = ReservationDAO.getInstance();
		
	
	int resultInt = dao.insertReservationData(dto);
	if (resultInt == 1) {		
		out.println("SUCCESS INSERT");
	} else {
		out.println("FAILED INSERT");
	}
%> 