<%@ page import="com.study.springboot.dao.*" %>
<%@ page import="com.study.springboot.android.dto.*" %>
<%@ page import="com.study.springboot.auth.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	request.setCharacterEncoding("UTF-8");

	String email = request.getParameter("email");
    String name = request.getParameter("name");
    String phone = request.getParameter("phone");
    String alert = request.getParameter("alert");
    String birth = request.getParameter("birth");
    String gender = request.getParameter("gender");

	MemberDTO dto = new MemberDTO(); 
		
	dto.setEmail(email);
	dto.setName(name);
	dto.setPhone(phone);
	dto.setAlert(alert);
	dto.setBirth(birth);
	dto.setGender(gender);

	MemberDAO dao = MemberDAO.getInstance();
		
	
	int resultInt = dao.updateMember(dto);
	if (resultInt == MemberDAO.MEMBER_UPDATE_SUCCESS) {		
		out.println("MEMBER_UPDATE_SUCCESS");
	} else {
		out.println("MEMBER_UPDATE_FAILED");
	}
%> 