<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원가입 폼</title>
<script src = http://code.jquery.com/jquery.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
	<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>  
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script>
		$(document).ready(function() {
			$("#hwJoin").unbind("click").click(function(e) {
				e.preventDefault();
				userCheck();
			 });
		});
		
		function userCheck() {
			var Email = $("#user_email").val();
			var Pw = $("#user_password").val();
			var PwCheck = $("#password_confirm").val();
			var name = $("user_Name").val();
			var phone = $("user_Phone").val();
			
			if (Email.length < 1) {
					alert("아이디는 필수 사항입니다.");
					$('#user_email').focus();
					return;
			} else if (Pw.length < 1) {
				alert("비밀번호는 필수 사항입니다.");
				$('#user_password').focus();
				return;
			} else if (PwCheck.length < 1) {
				alert("비밀번호확인은 필수 사항입니다.")
				$('#password_confirm').focus();
				return;
			} else if (name.length < 1) {
				alert("이름은 필수 사항입니다.")
				$('#user_Name').focus();
				return;
			} else if (phone.length < 1) {
				alert("핸드폰 번호는 필수 사항입니다.")
				$('#user_Phone').focus();
				return;
			} else if (Pw != Pwcheck) {
				alert("비밀번호가 서로 다릅니다. 다시 확인해주세요");
				$('#pw').focus();
				return;
			} else {
				$("#frm").submit();	
				alert("회원가입이 완료되었습니다.")
				location.replace('/mainPage');
			}
	</script>
</head>
<body>
	<form id="frm" action="/insertMember" method="post">
		<table>
			<tr>
      			<th width="20%">아이디</th>
      				<td width="80%" style="text-align: left;">
      					<input type="email" name="email"  placeholder="E-mail" required readonly style="background-color:#efefef;">
      					<button type="button" onclick="idCheck()">아이디 중복확인</button>
      				</td>
			</tr>
			
			<tr>
      			<th width="20%">비밀번호</th>
     				<td width="80%" style="text-align: left;">
      					<input type="password" name="password"  placeholder="PASSWORD" required>
      				</td>
     		</tr>
     		
     		<tr>
      			<th width="20%">비밀번호 확인</th>
      				<td width="80%" style="text-align: left;">
      					<input type="password" name="password_confirm"  placeholder="PASSWORD" required>
      				</td>
     		</tr>

     		<tr>
      			<th width="20%">이름</th>
      				<td width="80%" style="text-align: left;">
      					<input type="text" name="name" size="15">
      				</td>
     		</tr>	

    		<tr>
      			<th width="20%">휴대폰번호</th>
					<td width="80%" style="text-align: left;">
	      				<input type="tel" name="phone" placeholder="Phone Number" size="12">
	      			</td>
     		</tr>
					<input type="hidden" name="isadmin" value="YES">
					<input type="hidden" name="isvalid" value="NO">
					<input type="hidden" name="birth" value="">
					<input type="hidden" name="gender" value="">			
			<tr>
      			<td colspan="2" align="center">
	      			<input type="submit" value="회원가입" id="hwJoin">        
	      		</td>
    		</tr> 	
		</table>
	</form>
</body>
</html>