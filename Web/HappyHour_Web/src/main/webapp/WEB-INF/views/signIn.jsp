<%@ page import="com.study.springboot.dao.*" %>
<%@ page import="com.study.springboot.android.dto.*" %>
<%@ page import="com.study.springboot.auth.*" %>
<%@ page import="org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	request.setCharacterEncoding("UTF-8");
	String returns = "";
	
	String email = request.getParameter("email");
	String password = request.getParameter("password");


	MemberDAO dao = MemberDAO.getInstance();
		
	int resultInt = dao.loginCheck(email, password);
	
	if (resultInt == MemberDAO.MEMBER_LOGIN_SUCCESS) {
		
		MemberDTO dto = dao.getMember(email);
		returns = "MEMBER_LOGIN_SUCCESS " + 
				  dto.getEmail() + " " + dto.getPassword() +  " " + dto.getName() + " " +
		          dto.getPhone() + " " + dto.getIsAdmin() + " " + dto.getIsValid() + " " + 
				  dto.getAlert() + " " + dto.getBirth() + " " + dto.getGender() + " " + 
		          dto.getRsvok() + " " + dto.getRsvx() + " " + dto.getDeviceid();
		
		out.println(returns);
	
	} else if (resultInt == MemberDAO.MEMBER_LOGIN_IS_NOT) {
		out.println("MEMBER_LOGIN_FAILED INVALID_EMAIL");
	} else if (resultInt == MemberDAO.MEMBER_LOGIN_PW_NO_GOOD) {
		out.println("MEMBER_LOGIN_FAILED INVALID_PASSWORD");
	} else { 
		out.println("MEMBER_LOGIN_FAILED ERROR");
	}
%>