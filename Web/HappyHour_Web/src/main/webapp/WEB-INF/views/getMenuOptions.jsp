<%@ page import="com.study.springboot.dao.*" %>
<%@ page import="com.study.springboot.android.dto.*" %>
<%@ page import="com.study.springboot.auth.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	
	String mid = request.getParameter("mid");
	String returns = "";
	
	MenuDAO dao = MenuDAO.getInstance();
	MenuDTO menuDTO = dao.getMenuInfo(mid);
	
	if (menuDTO != null) {
		returns = "YES " + 										// splitResult[0]
				  menuDTO.getMId() + " " + 						// splitResult[1]
				  menuDTO.getMENU_NAME() + " " + 				// splitResult[2]
				  menuDTO.getMENU_DESCRIPTION() +  " " + 		// splitResult[3]
				  menuDTO.getMENU_PRICE() +  " " + 				// splitResult[4]
				  menuDTO.getMENU_IMAGENAME() + " " + 			// splitResult[5]
				  menuDTO.getMENU_EXTENSION() + " " + 			// splitResult[6]
				  menuDTO.getMENU_CODE() + " " + 				// splitResult[7]
				  menuDTO.getMENU_QTY() + " " + 				// splitResult[8]
				  menuDTO.getMWRITER();							// splitResult[9]

	} else {
		returns = "NO MENU INFO";
		out.print(returns);
	}

%>