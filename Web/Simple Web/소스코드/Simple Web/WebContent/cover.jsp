<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="errorPage.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<title>Cover Page</title>
	<link href="/docs/4.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="cover.css">
	<style>
	/*
		font-family: 'Alegreya Sans SC', sans-serif; / 각지고 글자 크기가 조금씩 다름 / 세련됨 / 가독성 좋음 
		font-family: 'Oleo Script', cursive; /Lobster Two와 유사하나 좀 더 심플
		font-family: 'Oswald', sans-serif; / Marvel과 유사
		font-family: 'Lobster Two', cursive; / Yellowtail과 어울리는 Border & Cursive & Semi-italic	
		font-family: 'Yellowtail', cursive; / 필기체 스타일 
		font-family: 'Marvel', sans-serif; / 슬림, 자간좁음/ 도시적 스타일
		font-family: 'Barlow', sans-serif; / 가독성 좋은 폰트
		font-family: 'Barlow Condensed', sans-serif; / 가독성 좋은 폰트
		--- Korean Fonts ---
		font-family: 'Nanum Myeongjo', serif; / 클래식 명조
		font-family: 'Do Hyeon', sans-serif; / 네모네모한 굵은 글씨
		font-family: 'Noto Serif KR', serif; / 클래식한 명조보다 진하고 레이아웃 선명
		font-family: 'Song Myung', serif; / 옛날 서적 명조체같은 느낌
	*/
	@import url('https://fonts.googleapis.com/css?family=Alegreya+Sans+SC|Do+Hyeon|Lobster+Two|Marvel|Nanum+Myeongjo|Noto+Serif+KR|Oleo+Script|Oswald|Song+Myung|Yellowtail');
	.bd-placeholder-img { font-size: 1.125rem; text-anchor: middle; -webkit-user-select: none; -moz-user-select: none; -ms-user-select: none; user-select: none; }
	@media (min-width: 768px) { .bd-placeholder-img-lg { font-size: 3.5rem; } }	
	.nav-link { font-family: 'Oleo Script', cursive; }			  
	.masthead-brand { font-family: 'Yellowtail', cursive; }
	.cover-heading { font-family: 'Lobster Two', cursive; }
	.lead { font-family: 'Marvel', sans-serif; }
	</style>
</head>

<body class="text-center">
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
    		<h1 class="cover-heading">Please Sign Up</h1>
    			<p class="lead"><!-- 소개 문구 -->Explore and visit our Secret Garden.</p>
    			<p class="lead"> <a href="login.jsp" class="btn btn-lg btn-secondary">Enter the Garden</a> </p>
  		</main>

  		<footer class="mastfoot mt-auto">
    		<div class="inner">
      			<!-- Contact information -->
    		</div>
  		</footer>
	</div>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>