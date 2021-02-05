<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body>

	<h1>Please Login</h1>
	
	<c:url value="j_spring_security_check" var="loginUrl"/>
	
	<form action="${loginUrl}" method="post">
	
		<c:if test="${param.error != null}">
			<p>Login Error! <br/>${error_message}</p>
		</c:if>
		
		Email    : <input type="text"     name="j_username"> <br>
		Password : <input type="password" name="j_password"> <br>
		<input type="submit" value="로그인"> <br>
	</form>

</body>
</html>