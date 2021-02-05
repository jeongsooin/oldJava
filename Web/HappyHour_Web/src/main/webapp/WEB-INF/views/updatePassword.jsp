<%@ page import="com.study.springboot.dao.*" %>
<%@ page import="com.study.springboot.android.dto.*" %>
<%@ page import="com.study.springboot.auth.*" %>
<%@ page import="org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	request.setCharacterEncoding("UTF-8");

	String type = request.getParameter("delete");
	String email = request.getParameter("email");
    String prePassword = request.getParameter("prePassword");
    String postPassword = request.getParameter("postPassword");
    
    BCryptPasswordEncoder passwordEncoder = PasswordEncoder.getInstance().passwordEncoder();
    String encryptPostPassword = passwordEncoder.encode(postPassword);
    
    MemberDAO dao = MemberDAO.getInstance();

    int loginCheck = dao.loginCheck(email, prePassword);
    
    if (loginCheck > 0) {
    	if(type.contains("YES")) {
    		dao.deleteMember(email);
    		out.println("MEMBER_DELETE_SUCCESS");
    	} else {
	    	int resultInt = dao.updatePassword(email, encryptPostPassword);
	    	if (resultInt == MemberDAO.MEMBER_UPDATE_SUCCESS) {		
	    		out.println("MEMBER_UPDATE_SUCCESS");
	    	} else {
	    		out.println("MEMBER_UPDATE_FAILED");
	    	}	
    	}
    } else {
    	out.println("MEMBER_UPDATE_FAILED");
    }	
%> 