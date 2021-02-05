<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="errorPage.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>Login</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>   
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="canonical" href="https://getbootstrap.com/docs/4.1/examples/sign-in/">
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="https://apis.google.com/js/api:client.js"></script>		
	<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
	<script>	
		var googleUser = {};
		var startApp = function() {
			gapi.load('auth2', function() {
				auth2 = gapi.auth2.init({
					client_id: '441716456965-4107va65v9j8tstjjb05baprdupbjmp8.apps.googleusercontent.com',
					cookiepolicy: 'single_host_origin',
				});			
				attachSignin(document.getElementById('glogin'));
			});
		};
	
		function attachSignin(element) {
			auth2.attachClickHandler(element, {},
				function(googleUser) {
					var profile = googleUser.getBasicProfile();
					var name = profile.getName();
					var id = profile.getEmail();
					$("#gname").attr("value", name);
					$("#gid").attr("value", id);
					$("#g_login").submit();
				}, function(error) {
					alert(JSON.stringify(error, undefined, 2));
				});
		}
	</script>
	<script>
		function setSession() {
			if($('#nname').val().length != 0) {
				$("#n_login").submit();
			}
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
		.form-signin p { color : white; }
		.form-signin input[type="email"] {
		  font-family: 'Marvel', sans-serif;
		  margin-bottom: -1px;
		  border-radius: 10px;
		}
		.form-signin input[type="password"] {
		  font-family: 'Marvel', sans-serif;
		  margin-bottom: 10px;
		  border-radius: 10px;
		}
		#signup { 
		  font-family: 'Marvel', sans-serif; 
		  font-weight: none; 
		  color: white;
		}
		#nlogin {
			font-family: 'Marvel', sans-serif;
			margin-bottom: 30px;
			font-weight: none;
		}
		#naverIdLogin {
		  font-family: 'Marvel', sans-serif;
		  margin-top: 10px;
		  font-weight: none;
		}		
		#glogin { 
		  font-family: 'Marvel', sans-serif; 
		  font-weight: none; 
		}
		#kakaoIdLogin { 
		  font-family: 'Marvel', sans-serif;
		  margin-top: 10px;
		  font-weight: 700;
		}
		#copyright { color: black; }
		.nav-link { font-family: 'Oleo Script', cursive; }			  
		.masthead-brand { font-family: 'Yellowtail', cursive; }
    </style>
    <link rel="stylesheet" href="cover.css">  
</head>
<body class="text-center">

	<script>startApp();</script>
	<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
  		<header class="masthead mb-auto">
    		<div class="inner">
      			<h3 class="masthead-brand"><!-- Page Name -->Secret Garden</h3>
      				<nav class="nav nav-masthead justify-content-center">
       					<a class="nav-link active" href="main.jsp"><!-- Demo MainPage (Read Only) -->Take a Look</a>
        				<a class="nav-link" href="#"><!-- Send Email Form -->Contact</a>
     	 			</nav>
    		</div>
  		</header>
  		
  		<main role="main" class="inner cover">
    		<form action="login.do" method="post" class="form-signin" id="nativelogin">	
			  	<h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
			  		<label for="inputEmail" class="sr-only"> Email address</label>	
			  			<input id="id" type="email" name="id" class="form-control" placeholder="Email address" required autofocus><br>
					<label for="inputPassword" class="sr-only">Password</label>
						<input id="pw" type="password" name="pw" class="form-control" placeholder="Password" required>
					<input id="nlogin" class="btn btn-lg btn-light btn-block" type="submit" value="Login with Email">
					<a class="btn btn-outline-link" id="signup" href="join.jsp">Sign Up Here</a>								
					<div id="clossline" ><p>──────────────────</p></div>
					<div id="glogin" class="btn btn-lg btn-danger btn-block">Login with Google</div>					
					<div id="naverIdLogin"><input id="naverIdLogin_loginButton" class="btn btn-lg btn-success btn-block" type="button" value="Login with Naver"></div>
					<div id="kakaoIdLogin"><input id="custom-login-btn" class="btn btn-lg btn-warning btn-block" type="button" value="Login with Kakao" onclick="loginWithKakao();"></div>				
				<p id="copyright" class="mt-5 mb-3 text-muted">&copy; 2019</p>
			</form>
  		</main>

  		<footer class="mastfoot mt-auto">
    		<div class="inner">
      			<!-- Contact information -->
    		</div>
  		</footer>
  	</div>
	
	<form action="setSession.do" method="post" id="g_login" >		
		<input type="hidden" name="name" id="gname" >
		<input type="hidden" name="id" id="gid">
		<input type="hidden" name="type" id="google">
    </form>
    
    <form action="setSession.do" method="post" id="n_login" >		
		<input type="hidden" name="name" id="nname" >
		<input type="hidden" name="id" id="nid">
		<input type="hidden" name="type" id="naver">
    </form>
    <script src="https://code.jquery.com/jquery-1.12.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="naveridlogin_js_sdk_2.0.0.js"></script>
    <script>		
		var naverLogin = new naver.LoginWithNaverId(
			{
				clientId: "maIxpp5ooWi41T23WGxM",
				callbackUrl: "http://localhost:8081/HW_BBS_Project/main.jsp",
				isPopup: false
			}
		);
		
		naverLogin.init();
		
		window.addEventListener('load', function () {
			naverLogin.getLoginStatus(function (status) {
				if (status) { setLoginStatus(); }				
			});
		});	
		
		function setLoginStatus() {
			var name = naverLogin.user.getName();
			var id = naverLogin.user.getEmail();
			$("#nname").attr("value", name);
			$("#nid").attr("value", id);			
		}
		
		setSession();
	</script>
    <script type='text/javascript'>
	  //<![CDATA[
	    Kakao.init('cba149dc050d795af33b91976347d773');
	    function loginWithKakao() {
	      Kakao.Auth.login({
	        success: function(authObj) {
	          window.location.replace("main.jsp");
	        },
	        fail: function(err) {
	          alert(JSON.stringify(err));
	        }
	      });
	    };
	  //]]>
	</script> 	
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>