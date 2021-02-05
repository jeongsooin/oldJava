<%@page import="com.study.jsp.member.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="dto" class="com.study.jsp.member.MemberDTO" scope="page"></jsp:useBean>
<jsp:setProperty property="*" name="dto"/>
<%
	String id = (String)session.getAttribute("id");
	String name = (String)request.getParameter("name");
	dto.setId(id);
	dto.setName(name);
	MemberDAO dao = MemberDAO.getInstance();
	String json_data = "";
	
	int resultInt = dao.updateMember(dto);	
	if(resultInt == 1) {
		json_data = "{\"code\":\"success\",\"desc\":\"정보가 수정되었습니다.\"}";
	} else {
		json_data = "{\"code\":\"fail\",\"desc\":\"정보 수정에 실패했습니다.\"}";		
	}
	
	response.setCharacterEncoding("UTF-8");
	out.println(json_data);
%>
