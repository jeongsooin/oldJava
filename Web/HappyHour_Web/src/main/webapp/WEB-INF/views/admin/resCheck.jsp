<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://www.springframework.org/security/tags" prefix="sec" %>
<%@page import="java.util.Date" %>
<%@page import="java.text.SimpleDateFormat" %>

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
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<!-- jQuery UI CSS 파일 -->
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
	<!-- jQuery 기본 js 파일 -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
	<!-- jQuery UI 라이브러리 js파일 -->
	<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<title>Insert title here</title>
<style>
body, ul, li {
	margin:0px; padding:0px; list-style:none;
}

html {
	font-size:10px;
}

section {
	font-size:20px;
}

div#header_wrap{
	 width:100%; float:left;
     height:100px; background:#cccccc;
}

header{
	width:980px; height:100px; margin:0px auto;
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

span#menu_icon{
	display:none;
}

div#lMenu {
	min-height:1000px; float:left; background:#353535;
	margin-right:10px; width:210px; 
}

ul#work li a {
	color:white;
}

ul.list-group li span{
	color: black; font-size: 1.5em;
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

section.container-fluid2 span{
    font-size: 0.8em;
} 

section.container-fluid2 option{
    font-size: 0.8em;
} 

#testDatepicker {
    font-size: 0.8em;
}

#rTime{
	font-size: 0.8em;
}

#Time{
	font-size: 0.8em;
}

#Rsearch {
	font-size: 0.8em;
}

section.container-fluid3 {
    width:100%;
    background:#cccccc;
} 

section.container-fluid3 span{
   font-size: 0.8em;
} 

#currentDate{
   font-size: 0.8em;
}

#currentTime{
   font-size: 0.8em;
}  

section#contents{
	width:auto;
}

section.contents_box{
	width:20%; height:250px; float:left;
	background:#cccccc; margin-left:4%; margin-top:30px;
	font-size: 0.8em;
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
	color: white; font-size:0.7em;
}

section#footer address{
	color: white; font-size:0.7em;
}

section#footer span{
	color: white; font-size:0.7em;
}

@media screen and (max-width:980px) {
	header, section#visual, section#contents, section#footer{
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
</style>

<script>
   //플러그인 간의 충돌을 제거합니다.
     $.noConflict();
     var J = jQuery;
</script>

<!-- 30분 뒤 자동 새로고침 -->
<script>
	function pagestart() {
		//window.setTimeout("pagereload()", 18000000);
		window.setTimeout("pagereload()", 60000); //1분
	}
	
	function pagereload() {
		location.reload();
	}

</script>


<script language="javascript">

J(function(){
	J(document).ready(function(){
		J('select[name=Time]').change(function(){
			J('#rTime').val(J(this).val());
			J('#rTime').attr("readonly", true);
			var sh=J('#rTime').val(J(this).val());
			//alert(sh);
		});
	});
});

</script>

<!-- 리얼타임 시간 구하기 -->

<script type="text/javascript">

function realtimeClock() {
  document.rtcForm.rtcInput.value = getTimeStamp();
  setTimeout("realtimeClock()", 1000);
}

function getTimeStamp() { // 24시간제
  var d = new Date();
/*   var year = d.getFullYear().toString();
  var yearTwo = year.substr(2,4); */
  
  var s =
    /* leadingZeros(yearTwo, 2) + '/' +
    leadingZeros(d.getMonth() + 1, 2) + '/' +
    leadingZeros(d.getDate(), 2) + ', ' + */
    leadingZeros(d.getHours(), 2) + ':' +
    leadingZeros(d.getMinutes(), 2);
    //leadingZeros(d.getMinutes(), 2) + ':' +
    //leadingZeros(d.getSeconds(), 2);

  return s;
}

function leadingZeros(n, digits) {
  var zero = '';
  n = n.toString();

  if (n.length < digits) {
    for (i = 0; i < digits - n.length; i++)
      zero += '0';
  }
  return zero + n;
}

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

</head>

<body onload="realtimeClock();doDisplay();pagestart()">
<input type="hidden" id="idck" value=<sec:authentication property ="name"/>>

<div id="header_wrap">
	<header>
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
					<a class="nav-link" href="/join">JOIN</a>
    		  	</li>
    		  </ul>
    		  
    		  <ul class="nav" id="login" style="display:none">
				<p><span><sec:authentication property = "name"/></span> 님 안녕하세요.<p>
				<li>
					<a class="nav-link" href="/logout">로그아웃</a>
    		  	</li>
    		  </ul>
    		  
    		</div>
    	    </div>
			<span id = "menu_icon">메뉴아이콘</span>
	</header>
</div>

<div id="lMenu">

  <ul class="list-group" style="background:#353535">
	  <li class="list-group-item"  style="background:#353535"><a href="./resCheck"><span style="color:white">오늘예약</span></a></li>
	  <li class="list-group-item"  style="background:#353535"><a href="/resStatus"><span style="color:white">예약현황</span></a></li>
	  <li class="list-group-item"  style="background:#353535"><a href="/resDB"><span style="color:white">고객관리</span></a></li>
	  <li class="list-group-item"  style="background:#353535"><a href="/resMenu"><span style="color:white">메뉴등록</span></a></li>
	  <li class="list-group-item"  style="background:#353535"><a href="/resSet"><span style="color:white">예약설정</span></a></li>
	  <li class="list-group-item"  style="background:#353535"><a href="/resReview"><span style="color:white">리뷰</span></a></li>
	  <li class="list-group-item"  style="background:#353535"><a href="/resReq"><span style="color:white">문의사항</span></a></li>
	  <li class="list-group-item"  style="background:#353535"><a href="/resNoti"><span style="color:white">공지사항</span></a></li>
  </ul>
	
</div>

<div id="contents_wrap">
    <div class="tab-content" id="nav-tabContent">
    <h2>실시간 예약</h2>
      <div>
      	<section id="contents"> 
		 <section class="container-fluid1" >
    	 </section>  
    	 
		   <script language="javascript" type="text/javascript">
				buildCalendar();
	  	   </script>
		  <section class="container-fluid3">

				<form>
					<span>현재날짜: </span> <input type='date' id='currentDate' readonly='readonly' />
					<!-- 현재시간: <input type='time' id='currentTime' /> -->
				</form>
				<form name="rtcForm">
					<span>현재시간: </span> <input type="text" id='currentTime' name="rtcInput" size="20" readonly="readonly" />
				</form>
				
				<script>
				    var curDate = document.getElementById('currentDate');
					curDate.value = new Date().toISOString().substring(0,10);
				</script>
				
				
		  
		  </section>
		   
<%-- 		   <section class="container-fluid3" >	
		   		<input type="checkbox" name="chk_info" value="예약대기" class="check"><span>예약대기</span>
		   		<input type="checkbox" name="chk_info" value="확정" class="check"><span>확정</span>
		   		<input type="checkbox" name="chk_info" value="완료" class="check"><span>완료</span>
		   		<input type="checkbox" name="chk_info" value="취소" class="check"><span>취소</span>
		   		<input type="checkbox" name="chk_info" value="부도" class="check"><span>부도</span>
		   </section> --%>
        
        <section class="container-fluid4" >			   		   
		<c:forEach items="${resCheck}" var ="dto">
			<section class="contents_box" >
				<p><span>번호 : </span> ${dto.RId}</p>
				<p><span>현황 : </span> ${dto.RType}</p>
				<p id="Rtime"><span>예약일자 : </span> ${dto.RDate} </p>
				<input type="hidden" id="resDate" value="${dto.RDate}"/>
				<p><span>예약시간: </span> ${dto.RTime} </p>
				<input type="hidden" id="resTime" value="${dto.RTime}"/>
				<p><span>예약인원 : </span> ${dto.RNum}</p>
				<p><span>예약자 : </span> ${dto.RName}</p>
				<p><span>선결제금액 : </span> ${dto.RPayment}</p>
			</section>
		</c:forEach> 
		</section>

		<!-- value -->
		<section id ="food">
		</section>
		
		<!-- child -->
		<ul id="list">
		
		</ul>
		
		<!-- alert -->
		<div id="noti">
		</div>
		
		<!-- audio -->
		<!-- <div id=Maudio> -->
		 <audio id=Maudio src="/media/food.wav"></audio>
		<!-- </div> --> 
	</section>
	
<!-- Firebase App is always required and must be first -->
<script src="https://www.gstatic.com/firebasejs/6.1.0/firebase-app.js"></script>
<script src="https://www.gstatic.com/firebasejs/6.1.0/firebase-auth.js"></script>
<script src="https://www.gstatic.com/firebasejs/6.1.0/firebase-database.js"></script>
<script>
	
(function() {	
	//파이어 베이스 초기화 코드	
	var firebaseConfig = {
 	    apiKey: "AIzaSyBTDhCV5DFtJy3FJ_UdHPtDRJ0uT6d7_9I",
	    authDomain: "mytest-0710.firebaseapp.com",
	    databaseURL: "https://mytest-0710.firebaseio.com",
	    projectId: "mytest-0710",
	    storageBucket: "mytest-0710.appspot.com",
	    messagingSenderId: "577542291519",
	    appId: "1:577542291519:web:9f9201549f9e6fd0"
	    
		/* apiKey: "AIzaSyC0HvwMv8CKQu326wZiSzaXuvEQAwGL1wo",
		 authDomain: "mytest-ab09a.firebaseapp.com",
		 databaseURL: "https://mytest-ab09a.firebaseio.com",
		 projectId: "mytest-ab09a",
		 storageBucket: "mytest-ab09a.appspot.com",
		 messagingSenderId: "1024916749393",
		 appId: "1:1024916749393:web:614d7a0cf63f1db7"*/

  };
  // Initialize Firebase
 	 firebase.initializeApp(firebaseConfig);
	 
  //Get elements
     var food = document.getElementById('food');
  	 var ulList = document.getElementById('list');
  	 var noti = document.getElementById('noti');

  //Create references
  	 var dbFood = firebase.database().ref().child('foodList');
     var dbList = dbFood.child('0')
    

  //Sync object changes
/*   	dbFood.on('value', snap => {
  		food.innerText = JSON.stringify(snap.val(), null, 2)	
  	}); */
     
  //Sync list changes
   /* 	dbList.on('child_added', snap => {
	   		var li = document.createElement('li');
	   		li.innerText = snap.val();
	   		li.id = snap.key;
	   		ulList.appendChild(li);
   	}); */
   	
   	/* dbList.on('child_changed', snap => {
   		var liChanged = document.getElementById(snap.key);
   		liChanged.innerText = snap.val();
   	})
   	
   	*/
  	
  	 //Sync noti changes
     dbFood.on('child_added', snap => {
    		var Data = snap.val();
    		var Key = snap.key;
   		    var food_table = Data.food_table;
    	    var food_name = Data.food_name;
    	    var food_status = Data.food_status;
    	    
    	    var html = 
    	    	"테이블 번호 : " + food_table + "\n" +
      	      "메뉴명 : " +  food_name +"\n" +
      	      "요리가 완성되었습니다.";
    	    
    	   if(food_status == 3) {
         	  // $("#noti").append(html); 
         	  //modal.innerHTML = html;
         	  var x = document.getElementById('Maudio');   
         	  x.autoplay = true;
              x.load();
               
    	   	  alert(html);
    	   	  
    		   //$("#modal .modal-body").append(html);
    	    }
    	});
   	
   	dbFood.on('child_changed', snap => {
   		var Data = snap.val();
		var Key = snap.key;
		var food_table = Data.food_table;
	    var food_name = Data.food_name;
	    var food_status = Data.food_status;
   		
   		var html =
   			"테이블 번호 : " + food_table + "\n" +
  	    	"메뉴명 : " +  food_name +"\n" +
  	        "요리가 완성되었습니다.";
   		
   		
   	    if(food_status == 3) {
    	  // $("#noti").append(html); 
    	  //$("#modal .modal-body").append(html);
	   	  var x = document.getElementById('Maudio');   
	   	  x.autoplay = true;
          x.load();
    	  alert(html);
    	  
	    }
   		
   	})
   	
   	dbFood.on('child_removed', snap => {
   		var Data = snap.val();
		var Key = snap.key;
		var food_table = Data.food_table;
	    var food_name = Data.food_name;
	    var food_status = Data.food_status;
   		
   		var html =
    	      "테이블 번호 : " + food_table + "\n" +
    	      "메뉴명 : " +  food_name +"\n" +
    	      "요리가 완성되었습니다.";
   
    	  // $("#noti").append(html); 
    	  //$("#modal .modal-body").remove(html);
	   	alert(html);
	   	
	   	
   	})
   	
}());
	
</script>
	
	
   </div>
 </div>
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

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>	


</body>
</html>