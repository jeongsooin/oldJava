<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.study.jsp.command.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>   
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="canonical" href="https://getbootstrap.com/docs/4.1/examples/sign-in/">	
	<script src="https://www.google.com/recaptcha/api.js" async defer></script>
	<script src="http://code.jquery.com/jquery.js"></script>
	<script>
    $('#myModal').on('shown.bs.modal', function () {
    	$('#myInput').trigger('focus')
    });
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
			font-weight: none;
		}
		#copyright { color: black; }
		.nav-link { font-family: 'Oleo Script', cursive; }			  
		.masthead-brand { font-family: 'Yellowtail', cursive; }
    </style>
	<link rel="stylesheet" href="cover.css">
</head>
<body style='background-color:transparent'>
	<form action="memberdelete.do" method="post" class="form-signin" >	
		<h1 class="h3 mb-3 font-weight-normal">Delete Your Account</h1>
		<label for="inputEmail" class="sr-only"> Email address</label>	
			<input id="id" type="email" name="id" class="form-control" placeholder="Email address" required autofocus><br>
		<label for="inputPassword" class="sr-only">Password</label>
			<input id="pw" type="password" name="pw" class="form-control" placeholder="Password" required>
		<p class="lead"><input class="btn btn-lg btn-secondary" type="submit" value="Delete Account"></p>
		<p class="lead"><input class="btn btn-lg btn-secondary" type="reset"  value="Cancle Delete "></p>													
			<p id="copyright" class="mt-5 mb-3 text-muted">&copy; 2019</p>
	</form>
</body>
</html>