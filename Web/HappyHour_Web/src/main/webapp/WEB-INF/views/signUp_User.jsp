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
<div align="center">
	<h1 width="500">회원 가입</h1>
	<hr>
	<form id="frm" action="/insertMember" method="post">
		<table width="800" border="1" cellpadding="20" cellspacing="0">
			<tr>	
			<th width="20%">아이디</th>
				<td width="80%" style="text-align:left">
					<input type="email" name="email" id="user_email" placeholder="E-mail">
					<input type="button" value="아이디 중복확인" id="idCheckk" onclick="IdCheck();"/>				
				</td>
			</tr>
			
			<tr>	  
	      	<th width="20%">비밀번호</th>
	      		<td width="80%" style="text-align:left">
	      			<input type="password" name="password" id="user_password" placeholder="PASSWORD" required>
	      		</td>
	    	</tr>
	     	
	      	<tr>
	      		<th width="20%">비밀번호 확인</th>
	     		 <td width="80%" style="text-align: left;">
	     		 	<input type="password" name="password_confirm"   placeholder="PASSWORD" required>
	     	 	 </td>
	     	</tr>
	     	      		
	      	<tr>
	      		<th width="20%">이름</th>
	      			<td width="80%" style="text-align: left;">
	      				<input type="text" name="name"  placeholder="Name"size="15">
	      			</td>
	     	</tr> 	
	
	      	<tr>
	      		<th width="20%">휴대폰번호</th>
	      			<td width="80%" style="text-align: left;">
	      				<input type="tel" name="phone"   placeholder="Phone Number"size="12">
	      			</td>
	     	</tr> 
	
	      	<tr>
	      		<th width="20%">이벤트나 할인등 소식을 받으시겠습니까?</th>
					<td width="80%" >	
						<input type="radio" class="form-control"  name="alert" value="Yes">수신 동의&nbsp;&nbsp;	
						<input type="radio" class="form-control"  name="alert" value="No">수신 거부						
					</td>
	     	</tr>	
	
			 <tr>
	      		<th width="20%">	 생년월일 : </th>
					<td width="80%" >	
						<input type="text" name="birth" placeholder="Birth" id="birth" >
						<script>
							$(function() {
								$("#birth").datepicker({
									changeMonth: true, 
					 			    changeYear: true,
					 			    nextText: '다음 달',
					 			    prevText: '이전 달' ,		        
					 			    showButtonPanel: true, 
					 			    currentText: '오늘 날짜', 
					 			    closeText: '닫기', 
					 			    dateFormat: "yy/mm/dd",
					 			    changeMonth: true, 
					 			    yearRange : "1950",
					 			    dayNames: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
					 			    dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], 
					 			    monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
					 			    monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
					 			    buttonImageOnly: true 	  
					  			});
							});
					</script> 
					<br><br>	
					</td>		
			</tr>	
					
			<tr>	
				 <th width="20%">성별 : </th>
					<td width="80%" style="text-align: left;">
						<input type="radio"  id="UserGender" name="gender" value="MALE">남자&nbsp;
						<input type="radio"  id="UserGender" name="gender" value="FEMALE">여자	<br>
					</td>
	     	</tr>
	     	<input type="hidden" name="isadmin" value="NO">
			<input type="hidden" name="isvalid" value="NO">   		
	      
	      	<tr>
	      		<td colspan="2" align="center">
	      			<input type="submit" value="회원가입" id="hwJoin">        
	      		</td>
	    	</tr> 	
		</table>
	</form>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>			
</body>
</html>