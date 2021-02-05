<%@page import="java.util.ArrayList"%>
<%@ page import="com.study.springboot.dao.*" %>
<%@ page import="com.study.springboot.android.dto.*" %>
<%@ page import="com.study.springboot.auth.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%

	request.setCharacterEncoding("UTF-8");
	
	MemberDAO dao = MemberDAO.getInstance();
		
	ArrayList<String> list = new ArrayList<>();
	
	list = dao.getDeviceId();
	
	if (list.size() != 0) {
		String str = "";
		for (int i = 0; i < list.size(); i++) {
			str = str + list.get(i) + " ";
		}
		out.println("DEVICEID_VALUE_YES " + str);
	} else {
		out.println("DEVICEID_VALUE_NO");
	}
%>