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
</style>

<script>
   //플러그인 간의 충돌을 제거합니다.
     $.noConflict();
     var J = jQuery;
</script>

<script type="text/javascript">
	function date_check() {
		
		if(J('#testDatepicker').val().length == 0){
			alert("날짜를 입력해주세요.");
			J('#testDatepicker').focus();
			return;
		}	
		//$("#reg_frm").submit();
	 	submit_ajax();
	}

	function submit_ajax() {
		alert("aaa");
		//var form = new FormData(document.getElementById('date_frm'))
		var testDatepicker = J('#testDatepicker').val();
		var rTime = J('#rTime').val();
		
		alert(testDatepicker);
		alert(rTime);

		alert("bbb");
		
	 	J.ajax({
	       	url: '/tbView',
	       	method:'POST',
	       	datatype:'json',
         	data : {
         		"RDate" : testDatepicker,
         		"RTime" : rTime
         	},
	       	success:function(data) {
	      		  alert("조회진행");
 	      		  
	      		  /* if() {
	      			  alert("해당 시간에 등록된 예약이 없습니다.")
	      		  } */
	      	}
	     });
      }
</script>

<script>
J(function() {
    J( "#testDatepicker" ).datepicker({
    	changeMonth: true, 
        dayNames: ['일요일', '월요일','화요일', '수요일', '목요일', '금요일', '토요일'],
        dayNamesMin: ['일','월', '화', '수', '목', '금', '토'], 
        monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        dateFormat: "yy-mm-dd",
    });
});
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

<div id="contents_wrap">
    <div class="tab-content" id="nav-tabContent">     
      <h2>예약현황</h2>
      <hr>
      <div class="container">
      		 <form action="search" method="post" class="form-inline" style="float:right; margin-bottom:10px;">	
				<select id="Menu" name="find_field" >
				        <option value="total" name="total">전체검색</option>
						<option value="bTitle" name="bTitle">이메일</option>
						<option value="bContent" name="bContent">이름</option>
						<option value ="bName" name="bName">연락처</option>
				</select> 
				&nbsp;&nbsp;<input name="find_name" id="find_name" class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">	
			    &nbsp;<button class="btn btn-outline-success my-2 my-sm-0" type="submit" onclick="find_check()">검색</button>
			  </form>
    	        <tr>
	    	        <td> <span>일자 :</span>  </td> 
	    	         <td>
	    	            <!-- <input type="text" id="testDatepicker" > -->
	    	            <input type="data" id="testDatepicker" id="RDate">
	    	            
	    	         </td> 
    	        </tr>
    			<tr>
		 			<td><span>시간 : </span></td>
				<td> 
					<input type="text" id="rTime" name="rTime" size="30" value="09:00">
						<select id="Time" name="Time" >
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
						<option value="22:00" name="20PM">22:00</option>
						<option value="22:30" name="2030PM">22:30</option>
						<option value="23:00" name="21PM">23:00</option>
						</select>
			  		</input>
				</td>
				</tr>
				 <tr> 
	    	         <td>
	    	            <input type ="button" value ="조회" id="Rsearch" onclick ="date_check()"/>
	    	         </td> 
    	        </tr>

		  <table class="table table-striped" name="write_frm" width = "500" cellpadding = "0" cellspacing ="0" border="1">
		<tr>
			<th scope="col" style="text-align:center">번호</th>
			<th scope="col" style="text-align:center">현황</th>
			<th scope="col" style="text-align:center">예약일자</th>
			<th scope="col" style="text-align:center">예약시간</th>
			<th scope="col" style="text-align:center">예약인원</th>
			<th scope="col" style="text-align:center">예약자</th>
			<th scope="col" style="text-align:center">선결제금액</th>
		</tr>
		
		<c:forEach items="${TresCheck}" var ="dto">
			<tr>
				<td style="text-align:center">${dto.RId}</td>
				<td style="text-align:center">${dto.RType}</td>
				<td style="text-align:center">${dto.RDate}</td>
				<td style="text-align:center">${dto.RTime}</td>
				<td style="text-align:center"> ${dto.RNum}</td>
				<td style="text-align:center">${dto.RName}</td>
				<td style="text-align:center">${dto.RPayment}</td> 
				
			</tr>

		</c:forEach>
		
		<nav aria-label="page navigation example">
		<div class="pagination">
		<tr>
			<td colspan="7" >
			<!-- 처음 -->
			<c:choose>
				<c:when test = "${(page.curPage -1) < 1}">
					[ &lt;&lt; ]
				</c:when>
				<c:otherwise>
					<a href = "list?page=1">[ &lt;&lt; ]</a>
				</c:otherwise>
			</c:choose>
			
			<!-- 이전 -->
			<c:choose>
				<c:when test="${(page.curPage -1) < 1 }">
					[ &lt;]
				</c:when>
				<c:otherwise>
					<a href="list?page=${page.curPage -1 }">[ &lt;]</a>
				</c:otherwise>
			</c:choose>
			
			<!-- 개별 페이지 -->
			<c:forEach var = "fEach" begin="${page.startPage }" end = "${page.endPage }" step="1">
				<c:choose>
				<c:when test = "${page.curPage == fEach }">
					[${fEach}] &nbsp;
				</c:when>
				<c:otherwise>
					<a href = "list?page=${fEach}">[${fEach} ]</a> &nbsp;
				</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<!-- 다음 -->
			<c:choose>
			<c:when test = "${(page.curPage + 1) > page.totalPage }">
				[&gt;]
			</c:when>
			<c:otherwise>
				<a href = "list?page=${page.curPage +1 }">[&gt;]</a>
			</c:otherwise>
			</c:choose>
			
			<!-- 끝 -->
			<c:choose>
			<c:when test="${page.curPage == page.totalPage }">
				[&gt;&gt;]
			</c:when>
			<c:otherwise>
				<a href = "list?page=${page.totalPage}">[&gt;&gt;]</a>
			</c:otherwise>
			</c:choose>
		</tr>
		</div>
		</nav>
	</table>
	</div>
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

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>	


</body>
</html>