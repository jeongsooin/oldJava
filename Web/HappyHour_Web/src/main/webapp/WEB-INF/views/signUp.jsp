<%@ page import="com.study.springboot.dao.*" %>
<%@ page import="com.study.springboot.android.dto.*" %>
<%@ page import="com.study.springboot.auth.*" %>
<%@ page import="org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	request.setCharacterEncoding("UTF-8");

	String email = request.getParameter("email");
	String password = request.getParameter("password");
    String name = request.getParameter("name");
    String phone = request.getParameter("phone");
    String isadmin = request.getParameter("isadmin");
    String isvalid = request.getParameter("isvalid");
    String alert = request.getParameter("alert");
    String birth = request.getParameter("birth");
    String gender = request.getParameter("gender");

    PasswordEncoder passwordEncoder = PasswordEncoder.getInstance();
    String encryptPassword = passwordEncoder.passwordEncoder().encode(password);
    
	MemberDTO dto = new MemberDTO(); 
		
	dto.setEmail(email);
	dto.setPassword(encryptPassword);
	dto.setName(name);
	dto.setPhone(phone);
	dto.setIsAdmin(isadmin);
	dto.setIsValid(isvalid);
	dto.setAlert(alert);
	dto.setBirth(birth);
	dto.setGender(gender);

	MemberDAO dao = MemberDAO.getInstance();
		
	
	int resultInt = dao.insertMember(dto);
	if (resultInt == MemberDAO.MEMBER_JOIN_SUCCESS) {		
		out.println("MEMBER_JOIN_SUCCESS");
	} else {
		out.println("MEMBER_JOIN_FAILED");
	}
%> 