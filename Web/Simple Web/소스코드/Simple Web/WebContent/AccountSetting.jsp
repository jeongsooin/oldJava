<%@page import="com.study.jsp.member.*" %>
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
		#setting_button1{
			font-family: 'Marvel', sans-serif;
			margin-bottom: 10px;
			font-weight: none;
		}
		#setting_button2 {
			font-family: 'Marvel', sans-serif;
			font-weight: none;
		}
		#copyright { color: black; }
		.nav-link { font-family: 'Oleo Script', cursive; }			  
		.masthead-brand { font-family: 'Yellowtail', cursive; }
    </style>
	<link rel="stylesheet" href="cover.css"> 
</head>
<body style='background-color:transparent'>
	<form class="form-signin">	
		<h1 class="h3 mb-3 font-weight-normal">Account Setting</h1>
		<p class="lead" id="setting_button1" > <a href="modifyinfo.do" class="btn btn-lg btn-secondary">Modify Account</a></p><br>
		<p class="lead" id="setting_button2" > <a href="delete.jsp" class="btn btn-lg btn-info">Delete Account</a></p>													
		<p id="copyright" class="mt-5 mb-3 text-muted">&copy; 2019</p>
	</form>
</body>
</html>