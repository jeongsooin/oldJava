<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://www.springframework.org/security/tags" prefix="sec" %>

<%
String name = (String)session.getAttribute("name");
String id = (String)session.getAttribute("id");
String bMenu = (String)session.getAttribute("bMenu");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- jQuery UI 라이브러리 js파일 -->
	<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
    <!-- Bootstrap CSS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <!-- jQuery UI CSS 파일 -->
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
	<!-- jQuery 기본 js 파일 -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<title>Insert title here</title>
<style>
body, ul, li {
	margin:0px; padding:0px; list-style:none;
}

a.top {
  position: fixed;
  left: 90%;
  bottom: 50px;
  display: none;
  font-size:4em;
  color:black;
  text-decoration:none;
}


#bd-example-modal-lg {
	z-index:99999;
	width:100%; margin:auto 0;
}
.jbFixed {
        position: fixed;
        top: 0px;
        opacity:0.8;
      }

div.container-fluid1 {
    width:100%; float:left;
    height:50px;
}

div.container-fluid4 {
    width:100%; float:left;
     
}

div.container-fluid2 {
    width:100%; float:left;
    background:gray;
    z-index:9999;
}

div.container-fluid3 {
    width:100%; float:left;
    background:#2D8FAD;
    z-index:9999;
}


ul.nav-tabs li a {
	font-size:0.8em;
	color:black;
}

div.row {
	width:980px; height:60px; margin:0px auto;
}


.login li a {
	color:black; font-size: 1.5em;
}

.login p {
	color:black; font-size: 1.5em;
}

.login span {
	color:blue; font-weight:bold;
}

.menu li {
	line-height:50px;
}

.menu li a {
	color:white; font-size: 1.5em;
}

.test2 li {
	line-height:50px; float:left;
}

.test2 li a {
	color:white; font-size: 1.5em;
}

/* ul#mainmenu{
	width:100%; height:100%;
} */

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

ul#mainmenu_list{
	margin-right:0px;
}

ul#mainmenu_list li {
	width:12.5%; height:100%; float:left;
	border-right:solid 1px white;
	box-sizing:border-box; text-align:center;
	line-height:50px;
}

ul#mainmenu_list li:last-child{
	border:none;
}

span#menu_icon{
	display:none;
}

div#banner_wrap{
	width:100%; float:left;
    background:#eeeeee;
}

div.bd-example{
	width:980px;  margin:0px auto;
	background:#cccccc;
}

div.bd-example img{
	width:100%; display:block;
}

section#visual img{
	width:100%; display:block;
}

div.contents_wrap{
	width:100%; float:left;
	
}

div#section-02{
background:salmon; height:100vh;
/* justify-content:center;
align-items:center; */
}

div#section-03{
background:white; height:110vh;
justify-content:center;
align-items:center;
}

div#section-01{
background:salmon; height:100vh;
justify-content:center;
align-items:center;
}

div#section-04{
background:white; height:100vh;
justify-content:center;
align-items:center;
}


div.contents_wrap p{
	font-weight:bold; text-align:center;
	font-size:50px;
}

section#contents{
	width:980px; margin:0px auto;
}

 section.contents_box{
	width:40%; height:300px; float:left;
    margin-left:4%; margin-top:30px;
}

html {
	font-size:10px;
}

section {
	font-size:20px;
}

section.contents_box h1 {
 	text-align:center; font-size:2em; font-weight:bold;
}

section.contents_box p {
 	font-size:2rem; text-align:center;
}

section.contents_box span {
 	font-size:2rem; text-align:center;
}

table#noti th {
	font-size:2rem;
}

table#noti td #modal_show {
	font-size:1.5rem; color:black;
}

table#noti td {
	font-size:1.5rem; color:black;
}

div#footer_wrap{
	width:100%; min-height:100px; float:left;
	background:#353535;
}

section#footer{
	width:980px; min-height:100px; margin:0px auto;
	
}

section#footer p{
	color: white; font-size:0.7em;
}

section#footer address{
	color: white; font-size:0.7em;
}

section#footer span{
	color: white; font-size:0.7em;
}

@media screen and (max-width:980px) {
	header, div.bd-example, section#contents, section#footer{
		width:90%;
	}
}

@media screen and (max-width:768px) {
	section.contents_box {
		width:60%; margin-left:6.6%;
	}
}

@media screen and (max-width:480px) {
	section.contents_box {
		width:100%; margin-left:0px;
	}
	ul#mainmenu_list {
		display:none;
	}
	span#menu_icon {
		display:block;
	}
	
}
</style>

<script>
   //플러그인 간의 충돌을 제거합니다.
     $.noConflict();
     var J = jQuery;
</script>

<!-- 원페이지스크롤작업 -->
<script>
	J(document).ready(function() {
		var navLink1 = J('.menu ul li a');
		
		navLink1.on('click', function(event){
			//alert("aaa");
			event.preventDefault();
			var target = $(this).attr('href');
			var top = $(target).offset().top -50 ;
			J('html,body').animate({scrollTop: top}, 500);
			
		
		});
	});
</script>

<!-- 상단메뉴 고정 -->
<script>
      J(document ).ready( function() {
        var jbOffset = J( '.container-fluid2' ).offset();
        J( window ).scroll( function() {
          if ( J( document ).scrollTop() > jbOffset.top ) {
            J( '.container-fluid2' ).addClass( 'jbFixed' );
            
          }
          else {
            J( '.container-fluid2' ).removeClass( 'jbFixed' );
          }
        });
      } );
</script>

<script>
	function doDisplay() {
		var idck = J('#idck').val();
/* 		alert(idck);  */
		if(idck != null && idck != "anonymousUser") {	
			J('#logout').css('display','none');
			J('#login').css('display','block');
		} 
	}
</script>

<script>
	function imgCheck() {
		 var MENU_CODE = $("#image1").attr('menu_code');
         var params = 'menu_code=' + MENU_CODE;
         var MENU_CODE1 = $("#image2").attr('menu_code');
         var params1 = 'menu_code=' + MENU_CODE1;
         var MENU_CODE2 = $("#image3").attr('menu_code');
         var params2 = 'menu_code=' + MENU_CODE2;
         var MENU_CODE3 = $("#image4").attr('menu_code');
         var params3 = 'menu_code=' + MENU_CODE3;
         var MENU_CODE4 = $("#image5").attr('menu_code');
         var params4 = 'menu_code=' + MENU_CODE4;
         var MENU_CODE5 = $("#image6").attr('menu_code');
         var params5 = 'menu_code=' + MENU_CODE5;
         var MENU_CODE6 = $("#image7").attr('menu_code');
         var params6 = 'menu_code=' + MENU_CODE6;
         var MENU_CODE7 = $("#image8").attr('menu_code');
         var params7 = 'menu_code=' + MENU_CODE7;

         //이미지 불러오기
          J.ajax({
         	url: "guest/image",
         	method:"GET",  
         	data:params,
         	success:function(data) {
        		//alert("success");
        		//$("#imgCheck").attr("src","/upload/"+ );
        		J("#image1").html(data);
        	}
         });
         
          //텍스트 불러오기
          J.ajax({
           	url: "guest/title",
           	method:"GET",  
           	data:params,
           	success:function(data) {
          		//alert("success");
          		//$("#imgCheck").attr("src","/upload/"+ );
          		J("#title").html(data);
          	}
           });
        //이미지 불러오기
          J.ajax({
           	url: "guest/image",
           	method:"GET",  
           	data:params1,
           	success:function(data) {
          		//alert("success");
          		//$("#imgCheck").attr("src","/upload/"+ );
          		J("#image2").html(data);
          	}
           });
        
          //텍스트 불러오기
          J.ajax({
           	url: "guest/title",
           	method:"GET",  
           	data:params1,
           	success:function(data) {
          		//alert("success");
          		//$("#imgCheck").attr("src","/upload/"+ );
          		J("#title1").html(data);
          	}
           });  
        
        //이미지 불러오기
          J.ajax({
             	url: "guest/image",
             	method:"GET",  
             	data:params2,
             	success:function(data) {
            		//alert("success");
            		//$("#imgCheck").attr("src","/upload/"+ );
            		J("#image3").html(data);
            	}
             });
          
        //텍스트 불러오기
          J.ajax({
           	url: "guest/title",
           	method:"GET",  
           	data:params2,
           	success:function(data) {
          		//alert("success");
          		//$("#imgCheck").attr("src","/upload/"+ );
          		J("#title2").html(data);
          	}
           });  
        //이미지 불러오기
          J.ajax({
           	url: "guest/image",
           	method:"GET",  
           	data:params3,
           	success:function(data) {
          		//alert("success");
          		//$("#imgCheck").attr("src","/upload/"+ );
          		J("#image4").html(data);
          	}
           });
        
        //텍스트 불러오기
          J.ajax({
           	url: "guest/title",
           	method:"GET",  
           	data:params3,
           	success:function(data) {
          		//alert("success");
          		//$("#imgCheck").attr("src","/upload/"+ );
          		J("#title3").html(data);
          	}
           });  
        //이미지 불러오기
          J.ajax({
             	url: "guest/image",
             	method:"GET",  
             	data:params4,
             	success:function(data) {
            		//alert("success");
            		//$("#imgCheck").attr("src","/upload/"+ );
            		J("#image5").html(data);
            	}
             });
          
        //텍스트 불러오기
          J.ajax({
           	url: "guest/title",
           	method:"GET",  
           	data:params4,
           	success:function(data) {
          		//alert("success");
          		//$("#imgCheck").attr("src","/upload/"+ );
          		J("#title4").html(data);
          	}
           });
        
        //이미지 불러오기
          J.ajax({
           	url: "guest/image",
           	method:"GET",  
           	data:params5,
           	success:function(data) {
          		//alert("success");
          		//$("#imgCheck").attr("src","/upload/"+ );
          		J("#image6").html(data);
          	}
           });
          
        //텍스트 불러오기
          J.ajax({
           	url: "guest/title",
           	method:"GET",  
           	data:params5,
           	success:function(data) {
          		//alert("success");
          		//$("#imgCheck").attr("src","/upload/"+ );
          		J("#title5").html(data);
          	}
           });
        //이미지 불러오기
          J.ajax({
             	url: "guest/image",
             	method:"GET",  
             	data:params6,
             	success:function(data) {
            		//alert("success");
            		//$("#imgCheck").attr("src","/upload/"+ );
            		J("#image7").html(data);
            	}
             });
         
        //텍스트 불러오기
          J.ajax({
           	url: "guest/title",
           	method:"GET",  
           	data:params6,
           	success:function(data) {
          		//alert("success");
          		//$("#imgCheck").attr("src","/upload/"+ );
          		J("#title6").html(data);
          	}
           });
        
        //이미지 불러오기
          J.ajax({
             	url: "guest/image",
             	method:"GET",  
             	data:params7,
             	success:function(data) {
            		//alert("success");
            		//$("#imgCheck").attr("src","/upload/"+ );
            		J("#image8").html(data);
            	}
             });
        //텍스트 불러오기
          J.ajax({
           	url: "guest/title",
           	method:"GET",  
           	data:params7,
           	success:function(data) {
          		//alert("success");
          		//$("#imgCheck").attr("src","/upload/"+ );
          		J("#title7").html(data);
          	}
           });
          preLoadFn();
	}
</script>

<!-- 모달 -->
 <script>
        J(document).ready(function() {
           
        	J("td[id=code]").each(
        	function(){
        	J(this).click(function() {
            	//$("#exampleModal").modal("show");
                var BID = J(this).attr("bId");
                var params = "bId=" + BID;

                J.ajax({
                	url: "/view",
                	method:"GET",  
                	data:params,
                	success:function(data) {
                		J("#Noti_detail").html(data); //bID 값이 view로 이동하고 그 값이 Noti_detail에 html로 입력
                		J("#exampleModal").modal("show"); //전체 모달이 보여짐
                	}
                }) 
              });
        	}
          );
            J("#close_modal").click(function() {
                J("#exampleModal").modal("hide");
            });
        });
</script> 

<!-- 상단으로 스크롤이동 -->
<script>
J( window ).scroll( function() {
	if ( J( this ).scrollTop() > 600 ) {
		J( '.top' ).fadeIn();
	} else {
		J( '.top' ).fadeOut();
	}
} );

J( '.top' ).click( function() {
	J( 'html, body' ).animate( { scrollTop : 0 }, 400 );
	return false;
} );

</script>

<!-- 모바일에서 접속시 -->
<script>
function isMobile() {
    return /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent);
}

if (isMobile()) {
    // 모바일이면 실행될 코드 들어가는 곳
} else {
    // 모바일이 아니면 실행될 코드 들어가는 곳
}
</script>
</head>
<body onload="doDisplay();imgCheck()">
<input type="hidden" id="idck" value=<sec:authentication property ="name"/>>

<div id="scrollTop">
	<a href="#" class="top"><img src="/img/button1.png" alt="맨위로"></a>
	
</div>
<div class="container-fluid1" >
		<div class="row">
    		<div class="col-sm-8 logo">
    		</div>
    		<div class="col-sm-4 login">
    		   <ul class="nav" id="logout">
    			<li>
    				<a class="nav-link" href="/loginForm">LOGIN</a>
				</li>
				<li>
					<a class="nav-link" href="#">ㆍ</a>
				</li>
				<li>
					<a class="nav-link" href="/singUpMain">JOIN</a>
    		  	</li>
    		  </ul>
    		  
    		  <ul class="nav" id="login" style="display:none">
				<p><span><sec:authentication property = "name"/></span> 님 안녕하세요.<p>
    			<li>
    				<a class="nav-link" href="/loginForm">마이페이지</a>
				</li>
				<li>
					<a class="nav-link" href="#">ㆍ</a>
				</li>
				<li>
					<a class="nav-link" href="/logout">로그아웃</a>
    		  	</li>
    		  </ul>
    		  
    		</div>
    	</div>	
 </div>
<div class="container-fluid4" >
  <div class="row">
    		  <ul class="nav">
    		  	<li>
    		  		<a class="nav-link active" href="/">
					   <img src="/img/logo1.png" alt="로고">
			  		</a>
    		  	</li>
    		  </ul>
  </div>
</div>
<div class="container-fluid2" >
    	<div class="row">	
    		<div class="col-sm-8 menu" id="floating-menu">
				<ul class="nav">
				<li class="m">
					<a class="nav-link" href="#section-02" >소개</a>
				</li>
				<li class="m">
					<a class="nav-link" href="#section-03">메뉴</a>
				</li>
				<li class="m">
					<a class="nav-link" href="#section-01">공지사항</a>
				</li>
				<li class="m">
					<a class="nav-link" href="#section-04">오시는길</a>
				</li>

			</ul>
    		</div>
    		<div class="col-sm-4 test2">
    		<ul>
    		    <li>
					<a class="nav-link" id="modal_popup_reservation" href="javascript:void(0);" >예약</a>
				</li>
				<li>
					<a class="nav-link" href="">고객센터</a>
				</li>
			</ul>
    		</div>
    	</div>
</div>

<div id="banner_wrap">
<div class="bd-example">
  <div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
      <li data-target="#carouselExampleCaptions" data-slide-to="0" class="active"></li>
      <li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
      <li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner">
      <div class="carousel-item active">
        <img src="/img/food2.jpg" class="d-block w-100" alt="메인배너1">
        <div class="carousel-caption d-none d-md-block">
          <h5>First slide label</h5>
          <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
        </div>
      </div>
      <div class="carousel-item">
        <img src="/img/food1.jpg" class="d-block w-100" alt="메인배너2">
        <div class="carousel-caption d-none d-md-block">
          <h5>Second slide label</h5>
          <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
        </div>
      </div>
      <div class="carousel-item">
        <img src="/img/food3.jpg" class="d-block w-100" alt="메인배너3">
        <div class="carousel-caption d-none d-md-block">
          <h5>Third slide label</h5>
          <p>Praesent commodo cursus magna, vel scelerisque nisl consectetur.</p>
        </div>
      </div>
    </div>
    <a class="carousel-control-prev" href="#carouselExampleCaptions" role="button" data-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carouselExampleCaptions" role="button" data-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
</div>
</div>

	<div class="contents_wrap" id="section-02">
	  <section id="contents">
		<p>About</p>
		<hr>
		<hr size=10px; width=3px; color="gray" align="center">
		 <img src="/img/intro.png" class="d-block w-100" alt="식당소개">
	   </section>
	</div>


<%-- 	<div class="contents_wrap" id="section-03">
		<p>메뉴</p>
		<div class="container-fluid3" >
    	<div class="row">	
    		<div class="col-sm-8 menu" id="floating-menu">
				<ul class="nav">
				<li class="m">
					<a class="nav-link" href="#">신메뉴</a>
				</li>
				<li class="m">
					<a class="nav-link" href="#">커리Line</a>
				</li>
				<li>
					<a class="nav-link" href="#">세트Line</a>
				</li>
				<li class="m">
					<a class="nav-link" href="#">사이드Line</a>
				</li>
			</ul>
    		</div>
    		<div class="col-sm-4 test2">
    		</div>
    	    </div>		
	</div>
	<section id="contents">
		<section class="contents_box" >
		<img src="/img/m1.png" class="d-block w-100" alt="양송이카레"> 
			<img src="/upload/${dto.MENU_EXTENSION }" width="300" alt="양송이카레"/>
		</section>
		<section class="contents_box">
		    <h1>양송이카레1</h1>
		    <hr>
			<p>향긋한 카레 소스와 영양 가늑 재료가 가득 들어간 카레!</p>
			<p>리뷰: ★★★★☆</p> <p><a href="#">리뷰보기</a></p>
		</section>
		<section class="contents_box">
			<img src="/img/m1.png" class="d-block w-100" alt="기본카레">
		</section>
		<section class="contents_box">
		    <h1>양송이카레2</h1>
		    <hr>
			<p>향긋한 카레 소스와 영양 가늑 재료가 가득 들어간 카레!</p>
			<p>리뷰: ★★★★☆</p> <p><a href="#">리뷰보기</a></p>
		</section>
	</section>
  </div> --%>
  
 <div class="contents_wrap" id="section-03">
 <section id="contents">
 <p>메뉴</p>
  <ul class="nav nav-tabs" id="myTab" role="tablist">
  <li class="nav-item">
    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#new" role="tab" aria-controls="home" aria-selected="true">신메뉴</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#curry" role="tab" aria-controls="profile" aria-selected="false">커리라인</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" id="contact-tab" data-toggle="tab" href="#set" role="tab" aria-controls="contact" aria-selected="false">세트라인</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" id="contact-tab" data-toggle="tab" href="#side" role="tab" aria-controls="contact" aria-selected="false">사이드라인</a>
  </li>
</ul>
<div class="tab-content" id="myTabContent">
  <div class="tab-pane fade show active" id="new" role="tabpanel" aria-labelledby="home-tab">
  		<section class="contents_box" id="image1"  menu_code=1>
			<!-- <img src="/img/m1.png" class="d-block w-100" alt="양송이카레"> -->
			<%-- <img src="/upload/${imgFile.MENU_EXTENSION }" id="imgCheck" MId=82 class="d-block w-100" alt="양송이카레"/> --%>
		</section>
		<section class="contents_box" id="title">
<!-- 		<h1>양송이카레1</h1>
		    <hr>
			<p>향긋한 카레 소스와 영양 가늑 재료가 가득 들어간 카레!</p>
			<p>리뷰: ★★★★☆</p> <p><a href="#">리뷰보기</a></p> -->
		</section>
		<section class="contents_box" id="image2"  menu_code=2>
			<!-- <img src="/img/m1.png"  class="d-block w-100" alt="기본카레"> -->
		</section>
		<section class="contents_box" id="title1">
		    <!-- <h1>양송이카레2</h1>
		    <hr>
			<p>향긋한 카레 소스와 영양 가늑 재료가 가득 들어간 카레!</p>
			<p>리뷰: ★★★★☆</p> <p><a href="#">리뷰보기</a></p> -->
		</section>
  </div>
  <div class="tab-pane fade" id="curry" role="tabpanel" aria-labelledby="profile-tab">
   		<section class="contents_box" id="image3"  menu_code=3>
		<!-- <img src="/img/m1.png" class="d-block w-100" alt="양송이카레"> --> 
		</section>
		<section class="contents_box" id="title2">
		    <!-- <h1>양송이카레1</h1>
		    <hr>
			<p>향긋한 카레 소스와 영양 가늑 재료가 가득 들어간 카레!</p>
			<p>리뷰: ★★★★☆</p> <p><a href="#">리뷰보기</a></p> -->
		</section>
		<section class="contents_box" id="image4"  menu_code=4>
			<!-- <img src="/img/m1.png" class="d-block w-100" alt="기본카레"> -->
		</section>
		<section class="contents_box" id="title3">
		   <!--  <h1>양송이카레2</h1>
		    <hr>
			<p>향긋한 카레 소스와 영양 가늑 재료가 가득 들어간 카레!</p>
			<p>리뷰: ★★★★☆</p> <p><a href="#">리뷰보기</a></p> -->
		</section>
  </div>
  <div class="tab-pane fade" id="set" role="tabpanel" aria-labelledby="contact-tab">
   		<section class="contents_box" id="image5"  menu_code=5>
		<!-- <img src="/img/m1.png" class="d-block w-100" alt="양송이카레">  -->
		</section>
		<section class="contents_box" id="title4">
		   <!--  <h1>양송이카레1</h1>
		    <hr>
			<p>향긋한 카레 소스와 영양 가늑 재료가 가득 들어간 카레!</p>
			<p>리뷰: ★★★★☆</p> <p><a href="#">리뷰보기</a></p> -->
		</section>
		<section class="contents_box" id="image6"  menu_code=6>
			<!-- <img src="/img/m1.png" class="d-block w-100" alt="기본카레"> -->
		</section>
		<section class="contents_box" id="title5">
		    <!-- <h1>양송이카레2</h1>
		    <hr>
			<p>향긋한 카레 소스와 영양 가늑 재료가 가득 들어간 카레!</p>
			<p>리뷰: ★★★★☆</p> <p><a href="#">리뷰보기</a></p> -->
		</section>
  </div>
  <div class="tab-pane fade" id="side" role="tabpanel" aria-labelledby="contact-tab">
   		<section class="contents_box" id="image7"  menu_code=7>
		<!-- <img src="/img/m1.png" class="d-block w-100" alt="양송이카레">  -->
		</section>
		<section class="contents_box" id="title6">
		    <!-- <h1>양송이카레1</h1>
		    <hr>
			<p>향긋한 카레 소스와 영양 가늑 재료가 가득 들어간 카레!</p>
			<p>리뷰: ★★★★☆</p> <p><a href="#">리뷰보기</a></p> -->
		</section>
		<section class="contents_box"id="image8"  menu_code=8>
			<!-- <img src="/img/m1.png" class="d-block w-100" alt="기본카레"> -->
		</section>
		<section class="contents_box" id="title7">
		    <!-- <h1>양송이카레2</h1>
		    <hr>
			<p>향긋한 카레 소스와 영양 가늑 재료가 가득 들어간 카레!</p>
			<p>리뷰: ★★★★☆</p> <p><a href="#">리뷰보기</a></p> -->
		</section>
  </div>
</div>
</section>
 </div>

	<div class="contents_wrap" id="section-01">
		 <p>공지사항</p>
		 <hr>
		 	<div class="container">
		<%-- <input type="hidden" id="bMenu" name="bMenu" value=<%=bMenu%>> --%>
		
	<table class="table table-striped" id="noti" name="write_frm" width = "700" cellpadding = "0" cellspacing ="0" border="1">
		<tr>
			<th scope="col" style="text-align:center; justify-content:center; font-weight:bold">번호</th>
			<th scope="col" style="text-align:center; justify-content:center; font-weight:bold">작성자</th>
			<th scope="col" style="text-align:center; justify-content:center; font-weight:bold">제목</th>
			<th scope="col" style="text-align:center; justify-content:center; font-weight:bold">날짜</th>
			<th scope="col" style="text-align:center; justify-content:center; font-weight:bold">조회수</th>
		</tr>
		
		<c:forEach items="${list1}" var ="dto">
			<tr>
				<td scope="row" style="text-align:center; justify-content:center">${dto.BId}</td>
				<td style="text-align:center; justify-content:center">${dto.BName}</td>
				<td id="code" bId="${dto.BId}" style="text-align:center; justify-content:center;">
					<c:forEach begin="1" end="${dto.BIndent}">-</c:forEach> <!--댓글쓰면 들여쓰기 되는 원리 -->
			    	<%-- <a href = "view?bId=${dto.BId}&kind=view">${dto.BTitle }</a> --%>
			    	 <a href = "#layer2" class="btn-example" id="modal_show" bId="${dto.BId}" data-toggle="modal" data-target="#bd-example-modal-lg">${dto.BTitle }</a> 
				</td>
				<td style="text-align:center; justify-content:center">${dto.BDate}</td>
				<td style="text-align:center; justify-content:center">${dto.BHit}</td> 
			</tr>
		</c:forEach>
			
		<nav aria-label="page navigation example">
		<div class="pagination">
		<tr>
			<td colspan="5" >
			<!-- 처음 -->
			<c:choose>
				<c:when test = "${(page.curPage -1) < 1}">
					[ &lt;&lt; ]
				</c:when>
				<c:otherwise>
					<a href = "board2?page=1">[ &lt;&lt; ]</a>
				</c:otherwise>
			</c:choose>
			
			<!-- 이전 -->
			<c:choose>
				<c:when test="${(page.curPage -1) < 1 }">
					[ &lt;]
				</c:when>
				<c:otherwise>
					<a href="board2?page=${page.curPage -1 }">[ &lt;]</a>
				</c:otherwise>
			</c:choose>
			
			<!-- 개별 페이지 -->
			<c:forEach var = "fEach" begin="${page.startPage }" end = "${page.endPage }" step="1">
				<c:choose>
				<c:when test = "${page.curPage == fEach }">
					[${fEach}] &nbsp;
				</c:when>
				<c:otherwise>
					<a href = "board2?page=${fEach}">[${fEach} ]</a> &nbsp;
				</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<!-- 다음 -->
			<c:choose>
			<c:when test = "${(page.curPage + 1) > page.totalPage }">
				[&gt;]
			</c:when>
			<c:otherwise>
				<a href = "board2?page=${page.curPage +1 }">[&gt;]</a>
			</c:otherwise>
			</c:choose>
			
			<!-- 끝 -->
			<c:choose>
			<c:when test="${page.curPage == page.totalPage }">
				[&gt;&gt;]
			</c:when>
			<c:otherwise>
				<a href = "board2?page=${page.totalPage}">[&gt;&gt;]</a>
			</c:otherwise>
			</c:choose>
		</tr>
		</div>
		</nav>
	</table>
	</div>
		 		
	</div>

	<div class="contents_wrap" id="section-04">
		<p>지도</p>
			<hr>
		<section id="contents">
			<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3166.230094629642!2d126.87673151564636!3d37.478896236899416!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x357b61e3354204f9%3A0x12b02f6401815f80!2z7ZWc6rWt7IaM7ZSE7Yq47Juo7Ja07J247J6s6rCc67Cc7JuQ!5e0!3m2!1sko!2skr!4v1558926719336!5m2!1sko!2skr" 
					width="1000" height="600" style="float:center" frameborder="0" style="border:0" allowfullscreen></iframe>
		</section>

	</div>


<div id="footer_wrap"> 
	<section id="footer">
		<ul>
			<li>
				<p>(주)웹스리퍼블릭 / 대표자 : 주필규 / 사업자등록번호 : 
					607-88-00037
				</p>
				<address>서울특별시 마포구 합정동 202호</address>
				<span>
					전화 / 
					<a href="tel:1800-9356">1800-9365</a>
				</span>
				<span>
					팩스 / 
					<a href="#;">0606-300-9354</a>
				</span>
			</li>
			<li>
				<span class="copyright">COPYRIGHT ⓒWEB'S REPUBLIC INC> ALL RIGHTS RESERVED.</span>
			</li>
		</ul>
	</section>
</div>	

<!-- Modal -->
<!--       <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">공지 상세보기</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body" id="Noti_detail">
				
				</div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" id="closeBtn" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>   -->

<!-- <div class="modal fade bd-example-modal-lg" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
  <div class="modal-content"> -->

<div class="modal fade" id="bd-example-modal-lg" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
     <div class="modal-header">
       <h5 class="modal-title" id="exampleModalLabel">공지 상세보기</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                 <span aria-hidden="true">&times;</span>
             </button>
    </div>
    <div class="modal-body" id="Noti_detail">
    </div>
    <div class="modal-footer">
          <button type="button" class="btn btn-secondary" id="closeBtn" data-dismiss="modal">Close</button>
    </div>
  </div>
</div>
</div>


<!-- JUN part -->
<!-- Modal -->
<div class="modal modal-center fade" id="reservationModal" tabindex="-1" role="dialog" aria-labelledby="reservationModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-80size modal-center" role="document">
    <div class="modal-content modal-80size" id="modal_rsv">
    	<jsp:include page="../views/modal/modal_reservation.jsp" flush="true"></jsp:include>
    </div>
    <div class="modal-content modal-80size" id="modal_pmt">
    	<jsp:include page="../views/modal/modal_payment.jsp" flush="true"></jsp:include>
    </div>
    <div class="modal-content modal-80size" id="modal_myrsv">
    	<jsp:include page="../views/modal/modal_myreservation.jsp" flush="true"></jsp:include>
    </div>
  </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>	

</body>
</html>