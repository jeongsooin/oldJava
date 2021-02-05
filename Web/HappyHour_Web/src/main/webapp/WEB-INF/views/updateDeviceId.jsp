<%@ page import="com.study.springboot.dao.*" %>
<%@ page import="com.study.springboot.android.dto.*" %>
<%@ page import="com.study.springboot.auth.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%

	request.setCharacterEncoding("UTF-8");
	
	String deviceid = request.getParameter("deviceid");
	String email = request.getParameter("email");
	MemberDAO dao = MemberDAO.getInstance();
		
	
	int resultInt = dao.updateDeviceId(email, deviceid);
	
	if (resultInt == MemberDAO.MEMBER_UPDATE_SUCCESS) {		
		out.println("DEVICEID_UPDATE_SUCCESS");
	} else {
		out.println("DEVICEID_UPDATE_FAILED");
	}
%>
