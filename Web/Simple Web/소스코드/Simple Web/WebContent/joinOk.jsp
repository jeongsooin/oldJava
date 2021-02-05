<%@page import="java.sql.Timestamp" %>
<%@page import="com.study.jsp.member.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="dto" class="com.study.jsp.member.MemberDTO"></jsp:useBean>
<jsp:setProperty property="*" name="dto"/>
<%
	dto.setrDate(new Timestamp(System.currentTimeMillis()));
	MemberDAO dao = MemberDAO.getInstance();
	String json_data = "";
	if(dao.confirmId(dto.getId()) == MemberDAO.MEMBER_EXISTENT) {
		json_data = "{\"code\":\"fail\",\"desc\":\"아이디가 이미 존재 합니다.\"}";
	} else {
		int resultInt = dao.insertMember(dto);
		if (resultInt == MemberDAO.MEMBER_JOIN_SUCCESS) {
			json_data = "{\"code\":\"success\",\"desc\":\"회원가입을 축하 합니다.\"}";
		} else {
			json_data = "{\"code\":\"fail\",\"desc\":\"에러가 발생하여 회원가입에 실패했습니다.\"}";
		}
	}
	response.setCharacterEncoding("UTF-8");
	out.println(json_data);
%>

