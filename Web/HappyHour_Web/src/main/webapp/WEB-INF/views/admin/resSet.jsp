<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<!-- calendar를 위한 라이브러리들 지우면 안됨 -->
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src='https://fullcalendar.io/releases/fullcalendar/3.9.0/lib/moment.min.js'></script>
<link href='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.9.0/fullcalendar.min.css'rel='stylesheet'/>
<link href='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.9.0/fullcalendar.print.css' rel='stylesheet' media='print'/>
<script src='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.9.0/fullcalendar.min.js'></script>

<title>Insert title here</title>
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
	width:200px; 
}

div#contents_wrap{
	width:auto; min-height:1000px; float:left;
	/* margin-left:200px; */
}

div.set{
	margin-top:10px;
}

div.button{
	float:right; margin-right:100px;
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

.fc-sun {color:#e31b23}
.fc-sat {color:#007dc3}
.fc-time {display:none}
.fc-title {color:white}

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
</style>

<script>
   //플러그인 간의 충돌을 제거합니다.
     $.noConflict();
     var J = jQuery;
</script>

<script language="javascript">

J(function(){
	J(document).ready(function(){
		J('select[name=Time]').change(function(){
			J('#OPENTIME').val(J(this).val());
			J('#OPENTIME').attr("readonly", true);
			var sh=J('#OPENTIME').val(J(this).val());
			//alert(sh);
		});
	});
});
</script>

<script language="javascript">

J(function(){
	J(document).ready(function(){
		J('select[name=Time1]').change(function(){
			J('#CLOSETIME').val(J(this).val());
			J('#CLOSETIME').attr("readonly", true);
			var sh=J('#CLOSETIME').val(J(this).val());
			//alert(sh);
		});
	});
});
</script>

<script language="javascript">

J(function(){
	J(document).ready(function(){
		J('select[name=Table1]').change(function(){
			J('#MTABLE').val($(this).val());
			J('#MTABLE').attr("readonly", true);
			var sh=J('#MTABLE').val(J(this).val());
			//alert(sh);
		});
	});
});
</script>

<script type="text/javascript">
J(document).ready(function(){
	
	  var date = new Date();
	  var d = date.getDate();
	  var m = date.getMonth();
	  var y = date.getFullYear();
	  
	  
	  J('#calendar').fullCalendar({
	    header: {
	      right: 'custom2 prevYear,prev,next,nextYear'
	    },
	    
 	    monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	    monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	    dayNames:['일요일','월요일','화요일','수요일','목요일','금요일','토요일'],
	    dayNamesShort:['일','월','화','수','목','금','토'],
	    
	    selectable:true,
	    selectHelper:true,
	    select:function(start,end,allDay) {
	    	//var title = prompt('일정을 입력하세요.');
	    	if(title) {
	    		calendar.fullCalendar('renderEvent',
	    				{
	    					title: title,
	    					start: start,
	    					end: end,
	    					allDay: allDay
	    				},
	    			true		
	    		);
	    	}
	    	calendar.fullCalendar('unselect');
	    },
	    editable:true,
	    events: [
	      {
	    	title:'테이블:4',
	    	start: new Date(y,m,28),
	    	end: new Date(y,m,29),
	    	//url:
	      },
	      {
		     title:'오픈:09:00',
		     start: new Date(y,m,28),
		     end: new Date(y,m,29),
		    	//url:
		   },
		   
		   {
		    	title:'클로즈:20:00',
		    	start: new Date(y,m,28),
		    	end: new Date(y,m,29),
		    	//url:
		      }	
	      
	      
	    ],
	    
	    dayClick: function(date, jsEvent, view, resourceObj) {
	    	//alert('Date:' + date.format());
	    	
	    	J('#MDATE').val(date.format());
			J('#MDATE').attr("readonly", true);
			var sh=J('#MDATE').val(date.format());
	    	//alert('Resource ID:' + resourceObj.id);
	    	
	    	J("#exampleModal1").modal("show");
	    	alert('happy');
	    	
	    }
	   
	  }); 
});

</script> 

<script>
function display() {
	 //alert("diplay");
	 var MDATE = J('#MDATE').val($(this).val());
     var params = 'MDATE=' + MDATE;
     //alert(MDATE);
     
     //날짜별 정보 불러오기
      J.ajax({
     	url: "/TDate",
     	method:"GET",  
     	data:params,
     	success:function(data) {
    		alert("success");
    		//$("#imgCheck").attr("src","/upload/"+ );
    		alert(data);
    	}
     });
}
</script>

<script type="text/javascript">
	function form_check() {
		
		if(J('#MDATE').val().length == 0){
			alert("날짜를 입력해주세요.");
			J('#MDATE').focus();
			return;
		}	
		//$("#reg_frm").submit();
	 	submit_ajax();
	}

	function submit_ajax() {
		alert("aaa");
		//var form = new FormData(document.getElementById('date_frm'))
		var formData = $("#date_frm").serialize();
		alert("bbb");
		
	 	J.ajax({
	       	url: '/SDate',
	       	method:'POST',
	       	cache: false,
	 		data:formData,
	       	success:function(data) {
	      		  alert("설정이 등록되었습니다.");
	      		//$("#imgCheck").attr("src","/upload/"+ );
	      	}
	     });
      }
</script>

</head>

<body  onload="display()">
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

<div id="contents_wrap">

    <div class="tab-content" id="nav-tabContent">
      <div>
      <h2>예약설정</h2>
      <hr>
      &nbsp;&nbsp;&nbsp;
      
      <div class="container calendar-container">
	      <div id="calendar" style="max-width:600px; margin:40px auto;"></div>
      </div> 
      
      <form action="/SDate" method="POST" id="date_frm" enctype="multipart/form-data">
       <div class="set"> 
			FROM:
      			<!-- <input type="text" id="testDatepicker"> -->
      			 <input type="text" id="MDATE" name="MDATE">
      	    TO:
      			<!-- <input type="text" id="testDatepicker"> -->
      			 <input type="text" id="Sdate2">
       </div> 
      
<!--       	<section class="set">
      	   날짜 선택:
      			<input type="text" id="testDatepicker">
      			 <input type="text">
		</section> -->
		<div class="set">
			OPEN 시간:
			<input type="text" id="OPENTIME" name="OPENTIME" size="30" value="09:00">
			<select id="Time" name="Time" >
				<option value="09:00" selected="selected" name="09AM">09:00</option>
				<option value="09:30" name="0930AM">09:30</option>
				<option value="10:00" name="10AM">10:00</option>
				<option value="10:30" name="1030AM">10:30</option>
				<option value="11:00" name="11AM">11:00</option>
				<option value="11:30" name="1130AM">11:30</option>
				<option value="12:00" name="12N">12:00</option>
				<option value="12:30" name="1230PM">12:30</option>
				<option value="13:00" name="13PM">13:00</option>
				<option value="13:30" name="1330PM">13:30</option>
				<option value="14:00" name="14PM">14:00</option>
				<option value="14:30" name="1430PM">14:30</option>
				<option value="15:00" name="15PM">15:00</option>
				<option value="15:30" name="1530PM">15:30</option>
				<option value="16:00" name="16PM">16:00</option>
				<option value="16:30" name="1630PM">16:30</option>
				<option value="17:00" name="17PM">17:00</option>
				<option value="17:30" name="1730PM">17:30</option>
				<option value="18:00" name="18PM">18:00</option>
				<option value="18:30" name="1830PM">18:30</option>
				<option value="19:00" name="19PM">19:00</option>
				<option value="19:30" name="1930PM">19:30</option>
				<option value="20:00" name="20PM">20:00</option>
				<option value="20:30" name="2030PM">20:30</option>
				<option value="21:00" name="21PM">21:00</option>
				<option value="21:30" name="2130PM">21:30</option>
				<option value="22:00" name="20PM">22:00</option>
				<option value="22:30" name="2030PM">22:30</option>
				<option value="23:00" name="21PM">23:00</option>
			</select>
		</input>
		</div>
		
		<div class="set">
			CLOSE 시간:
			<input type="text" id="CLOSETIME" name="CLOSETIME" size="30" value="23:00">
			<select id="Time1" name="Time1" >
				<option value="09:00" name="09AM">09:00</option>
				<option value="09:30" name="0930AM">09:30</option>
				<option value="10:00" name="10AM">10:00</option>
				<option value="10:30" name="1030AM">10:30</option>
				<option value="11:00" name="11AM">11:00</option>
				<option value="11:30" name="1130AM">11:30</option>
				<option value="12:00" name="12N">12:00</option>
				<option value="12:30" name="1230PM">12:30</option>
				<option value="13:00" name="13PM">13:00</option>
				<option value="13:30" name="1330PM">13:30</option>
				<option value="14:00" name="14PM">14:00</option>
				<option value="14:30" name="1430PM">14:30</option>
				<option value="15:00" name="15PM">15:00</option>
				<option value="15:30" name="1530PM">15:30</option>
				<option value="16:00" name="16PM">16:00</option>
				<option value="16:30" name="1630PM">16:30</option>
				<option value="17:00" name="17PM">17:00</option>
				<option value="17:30" name="1730PM">17:30</option>
				<option value="18:00" name="18PM">18:00</option>
				<option value="18:30" name="1830PM">18:30</option>
				<option value="19:00" name="19PM">19:00</option>
				<option value="19:30" name="1930PM">19:30</option>
				<option value="20:00" name="20PM">20:00</option>
				<option value="20:30" name="2030PM">20:30</option>
				<option value="21:00" name="21PM">21:00</option>
				<option value="21:30" name="2130PM">21:30</option>
				<option value="22:00" name="22PM">22:00</option>
				<option value="22:30" name="2230PM">22:30</option>
				<option value="23:00" selected="selected" name="23PM">23:00</option>
			</select>
		</input>
		</div>	
		
		<div class="set">
      	   가용테이블 설정:
      		<input type="text" id="MTABLE" name="MTABLE" value="10">
      			<select id="Table1" name="Table1" >
					<option value="1" name="1">1</option>
					<option value="2" name="2">2</option>
					<option value="3" name="3">3</option>
					<option value="4" name="4">4</option>
					<option value="5" name="5">5</option>
					<option value="6" name="6">6</option>
					<option value="7" name="7">7</option>
					<option value="8" name="8">8</option>
					<option value="9" name="9">9</option>
					<option value="10" selected="selected" name="10">10</option>
      			</select>
      		</input>
		</div>	
		
		<div class="button">
			<input type = "button" class="btn btn-primary" id="btn_customer_register" value="입력" onclick ="form_check()"/>
			<input type = "button" class="btn btn-primary" id="btn_customer_register" value="수정" onclick ="form_check()"/>
		</div>
		</form>
      </div>
<!--  <script language="javascript" type="text/javascript">
		buildCalendar();
	  </script> -->
</div>
</div>
<div id="footer_wrap"> 
	<section id="footer">
		<ul>
			<li>
				<p>"(주)웹스리퍼블릭 / 대표자 : 주필규 / 사업자등록번호 : "
					<span class="footer_number">607-88-00037</span>
				</p>
				<address>서울특별시 마포구 합정동 202호</address>
				<span>
					"전화 / "
					<a href="tel:1800-9356">1800-9365</a>
				</span>
				<span>
					"팩스 / "
					<a href="#;">0606-300-9354</a>
				</span>
			</li>
			<li>
				<span class="copyright">COPYRIGHT ⓒWEB'S REPUBLIC INC> ALL RIGHTS RESERVED.</span>
			</li>
		</ul>
	</section>
</div>	


<!-- 수정하기 모달 -->
<div class="modal fade" id="exampleModalCenter" id="exampleModal1" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
     <form action="/Rwrite" method="post" id="reg_frm"  enctype="multipart/form-data">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalCenterTitle">수정하기</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="Noti_detail1">
      </div>
      <div class="modal-footer">
      </div>
      </form>
    </div>
  </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>	


</body>
</html>