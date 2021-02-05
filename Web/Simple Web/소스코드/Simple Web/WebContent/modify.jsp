<%@page import="com.study.jsp.member.*" %>
<%@page import="com.study.jsp.command.*" %>
<%@ page import="java.util.*, java.text.*"  %>
<%@ page import="java.sql.*"%>
<%@ taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% 
	String id = (String)session.getAttribute("id");
	MemberDAO dao = MemberDAO.getInstance();
	MemberDTO dto = dao.getMember(id);
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>   
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="canonical" href="https://getbootstrap.com/docs/4.1/examples/sign-in/">	
	<script src="https://www.google.com/recaptcha/api.js" async defer></script>
	<script src="http://code.jquery.com/jquery.js"></script>
	<script>
		function form_check() {
			if($('#pw').val().length == 0) {
				alert("비밀번호는 필수사항입니다.");
				$('#pw').focus();
				return;
			}
			if($('#pw').val() != $('#pw_check').val()) {
				alert("비밀번호가 일치하지 않습니다.");
				$('#pw').focus();
				return;
			}
			
			submit_ajax();
		}
		
		function submit_ajax() {
			var queryString = $("#reg_frm").serialize();
			$.ajax({
				url:'../HW_BBS_Project/modifyOk.jsp',
				type: 'POST',
				data: queryString,
				success: function(json) {
					console.log(json);
					var result = JSON.parse(json);
					if(result.code=="success") {
						alert(result.desc);
						window.location.replace("main.jsp");
					} else {
						alert(result.desc);
						history.go(-1);
					}
				}
			});
		}
	</script>
	<style>
    	@import url('https://fonts.googleapis.com/css?family=Alegreya+Sans+SC|Do+Hyeon|Lobster+Two|Marvel|Nanum+Myeongjo|Noto+Serif+KR|Oleo+Script|Oswald|Song+Myung|Yellowtail');
    	html,
		body {
		  height: 100%;
		}
		
		body {
		  display: -ms-flexbox;
		  display: flex;
		  -ms-flex-align: center;
		  align-items: center;
		  padding-top: 40px;
		  padding-bottom: 40px;
		  background-color: #08310e;		  
		}
		#content_form {
			max-width: 500px;
		}
		.form-signin {
		  width: 100%;
		  max-width: 330px;
		  padding: 15px;
		  margin: auto;
		  background-color: rgba(255, 255, 255, .2);
  		  border: .05rem solid #fff;
  		  border-radius: 10px;
  		  font-family: 'Lobster Two', cursive;
  		  color: white;
  		  text-align: center;
		}
		.form-signin .checkbox {
		  font-weight: 400;
		}
		.form-signin .form-control {
		  position: relative;
		  box-sizing: border-box;
		  height: auto;
		  padding: 10px;
		  font-size: 16px;
		}
		.form-signin .form-control:focus {
		  z-index: 2;
		}
		.form-signin input[type="email"] {
		  font-family: 'Marvel', sans-serif;
		  margin-bottom: -15px;
		  border-radius: 10px;
		}
		.form-signin input[type="text"] {
		  font-family: 'Marvel', sans-serif;
		  margin-bottom: 10px;
		  border-radius: 10px;
		}
		.form-signin input[type="password"] {
		  font-family: 'Marvel', sans-serif;
		  margin-bottom: 10px;
		  border-radius: 10px;
		}
		#nlogin {
			font-family: 'Marvel', sans-serif;
			margin-bottom: 10px;
			font-weight: none;
		}
		#login {
			font-family: 'Marvel', sans-serif;
			margin-bottom: 10px;
			font-weight: none;
		}
		#content_delete {
			font-family: 'Marvel', sans-serif;
			font-weight: none;
		}
		#content_title {
			font-family: 'Marvel', sans-serif;
			font-weight: none;
			color: black;
		}
		#copyright { color: black; }
		.nav-link { font-family: 'Oleo Script', cursive; }			  
		.masthead-brand { font-family: 'Yellowtail', cursive; }
    </style>
	<link rel="stylesheet" href="cover.css"> 
</head>
<body style='background-color:transparent'>
	<form id="reg_frm" class="form-signin" >	
		<h1 class="h3 mb-3 font-weight-normal">Modify Account Info</h1>
		<label for="inputEmail" class="sr-only"> Email address</label>	
			<input id="id" type="email" name="id" class="form-control" value="<%= dto.getId() %>"><br>
		<label for="inputName" class="sr-only">Name</label>
			<input id="name" type="text" name="name" class="form-control" value="<%= dto.getName() %>" required>
		<label for="inputPassword" class="sr-only">Password</label>
			<input id="pw" type="password" name="pw" class="form-control" placeholder="Password" required>
		<label for="inputPassword" class="sr-only">Password</label>
			<input id="pw_check" type="password" name="pw_check" class="form-control" placeholder="Password Confirm" required>
		<input type="button" id="nlogin" class="btn btn-lg btn-info btn-block" value="Update" onclick="form_check()">
		<input type="button" id="login" class="btn btn-lg btn-light btn-block" value="Cancel" onclick="javascript:window.location='main.jsp'">													
			<p id="copyright" class="mt-5 mb-3 text-muted">&copy; 2019</p>
	</form>
	<form class="form-signin" id="content_form">	
		<h1 class="h3 mb-3 font-weight-normal">My Content List</h1>
		<c:forEach items="${list}" var="dto">
			<a id="content_title" class="btn  btn-light btn-block" href="content_view.do?kind=view&bId=${dto.bId}">${dto.bTitle}</a>
			<a id="content_delete" class="btn btn-info btn-block" href="delete.do?bId=${dto.bId}">Delete</a>
		</c:forEach>														
	</form>
</body>
</html>