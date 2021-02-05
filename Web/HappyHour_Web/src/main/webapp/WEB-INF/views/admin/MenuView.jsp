<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<!-- jQuery UI CSS 파일 -->
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
	<!-- jQuery 기본 js 파일 -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
	<!-- jQuery UI 라이브러리 js파일 -->
	<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

<style>
body, ul, li {
	margin:0px; padding:0px; list-style:none;
}

div#header_wrap{
	width:100%; height:100px; float:left;
	background:#cccccc;
}

header{
	width:980px; height:100px; margin:0px auto;
}

ul#logo li {
	height:100%; float:left;
	box-sizing:border-box; text-align:center;
	line-height:50px; margin-right:20px;
}

ul#login li {
	height:100%; float:left;
	box-sizing:border-box; text-align:center;
	line-height:50px;
}

ul#login li a {
	color:white;
}

span#menu_icon{
	display:none;
}

div#lMenu {
	min-height:1000px;float:left; background:#353535;
	margin-right:10px; width:210px; 
}

ul#work li a {
	color:white;
}

div#contents_wrap{
	width:auto; min-height:1000px; margin-left:210px;
}

section.container-fluid1 {
    width:100%;
    background:#cccccc;
}

section.container-fluid2 {
    width:100%;
    background:yellow;
} 
section.container-fluid3 {
    width:100%;
    background:#cccccc;
} 

section#contents{
	width:auto;
}

section.contents_box{
	width:20%; height:250px; float:left;
	background:#cccccc; margin-left:4%; margin-top:30px;
}

section#left {
	float:left;
	background:#eeeeee;
}

section#right{
	float:left;margin-left:30px;
	background:#cccccc;
}
table#calendar td{
	width: 60px;
 	height: 60px;
 	text-align: center;
 	font-size: 20px;
 	border:2px;
 	border-radius:8px;
}

div#footer_wrap{
	width:100%; min-height:100px; float:left;
	background:#cccccc;
}

section#footer{
	width:980px; min-height:100px; margin:0px auto;
	
}

section#footer p{
	color: gray;
}

section#footer address{
	color: gray;
}

section#footer span{
	color: gray;
}

@media screen and (max-width:980px) {
	header, section#visual, section#contents{
		width:90%;
	}
}

@media screen and (max-width:768px) {
	section.contents_box {
		width:40%; margin-left:6.6%;
	}
}

@media screen and (max-width:480px) {
	section.contents_box{
		width:100%; margin-left:0px;
	}
	ul#mainmenu_list{
		display:none;
	}
	span#menu_icon{
		display:block;
	}
	
}
 .file{
 display:none;
 }
</style>
</head>
<body>
<div id="header_wrap">
	<header>
		<nav id="mainmenu">
			<ul id="logo">
				<li>
				<a href="">
					<img src="/img/logo1.png" alt="로고">
				</a>
				</li>
			</ul>
			
				<ul id="login">
					<li>
					<a href="/loginForm">LOGIN</a>
					</li>
					<li>
					<span>ㆍ</span>
					</li>
					<li>
					<a href="/join">JOIN</a>
					</li>
				</ul>
			<span id = "menu_icon">메뉴아이콘</span>
		</nav>
	</header>
</div>

<div id="lMenu">
	<ul class="list-group">
	  <li class="list-group-item" style="background:#353535"><a href="admin/resCheck"><span style="color:white">오늘예약</span></a></li>
	  <li class="list-group-item" style="background:#353535"><a href="/resStatus"><span style="color:white">예약현황</span></a></li>
	  <li class="list-group-item" style="background:#353535"><a href="/resDB"><span style="color:white">고객관리</span></a></li>
	  <li class="list-group-item" style="background:#353535"><a href="/resMenu"><span style="color:white">메뉴등록</span></a></li>
	  <li class="list-group-item" style="background:#353535"><a href="/resSet"><span style="color:white">예약설정</span></a></li>
	  <li class="list-group-item" style="background:#353535"><a href="/resReview"><span style="color:white">리뷰</span></a></li>
	  <li class="list-group-item" style="background:#353535"><a href="/resReq"><span style="color:white">문의사항</span></a></li>
	  <li class="list-group-item" style="background:#353535"><a href="/resNoti"><span style="color:white">공지사항</span></a></li>
 	 </ul>
</div>

<br><p>
<hr>

<h2>메뉴 설명서</h2>
<hr>

	<table width ="800" id="tb" cellpadding="0" cellspacing="0" border="1">
    <!-- <input type="hidden" id="idck" value=<sec:authentication property ="name"/>> -->

		<tr>
			<td>번호</td>
			<td>${dto.MId}</td>
		</tr>
		
		<tr>
			<td>메뉴명</td>
			<td>${dto.MENU_NAME}</td>
		</tr>
		<tr>
			<td>메뉴가격</td>
			<td>${dto.MENU_PRICE}</td>
		</tr>
		<tr>
			<td>메뉴수량</td>
			<td>${dto.MENU_QTY}</td>
		</tr>
		<tr>
			<td>메뉴코드</td>
			<td>${dto.MENU_CODE}</td>
		</tr>
		<tr>
			<td>메뉴설명</td>
			<td>
			 	${dto.MENU_DESCRIPTION}
			</td>
		</tr>
		<tr>
			<td>메뉴이미지</td>
			<td>
			 	<img src="/upload/${dto.MENU_EXTENSION }" width="300" />
			</td>
		</tr>
		
		<tr>
 		<td colspan="2" >
 			<div>	
 				<a href = "/MenuModiForm?MId=${dto.MId}&kind=MenuModi">수정</a> &nbsp;&nbsp;
				<a href = "/resMenu?page=<%=session.getAttribute("cpage") %>">목록보기</a> &nbsp;&nbsp;
 			</div>
		
		</td>  	
		</tr>
	</table>



</body>
</html>