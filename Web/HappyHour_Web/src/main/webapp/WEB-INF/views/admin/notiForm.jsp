<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://www.springframework.org/security/tags" prefix="sec" %>
<%-- <%
if(session.getAttribute("ValidMem") == null) {
	 /* response.sendRedirect("list.do?page="); */
	 response.sendRedirect("/loginForm");

%>
<%

}
String name = (String)session.getAttribute("name");
String id = (String)session.getAttribute("id");
String bMenu = (String)session.getAttribute("bMenu");
%> --%>
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
<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript" src="./naver-editor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
function form_check() {
	
	if($('#bTitle').val().length == 0){
		alert("제목을 적어주세요.");
		$('#bTitle').focus();
		return;
	}
	
	//oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);

	$("#reg_frm").submit();	
	
	//document.reg_frm.submit();
}

 $(function () {
	$('#img-btn').click(function (e) {
		e.preventDefault();
	$('#img').click();
	});
 });

 $(function () {
	$('#files-btn').click(function (e) {
		e.preventDefault();
	$('#files').click();
	});
 });

$(function () {
	
	$("#img").change(function() {
		var formData = new FormData();
		var file = $("input[name=img]")[0].files[0];
		
		formData.append('uploadfile', $("input[name=img]" )[0].files[0] );
		
	 
	$.ajax({
		url: 'imgFormOk',
		type: 'Post',
		data: formData,
		dataType: "data",
		processData: false,
		contentType: false,
		SUCCESS: function(data) {
			var json = JSON.parse(data);
			alert(json.rs);
			var img = json.url;
			var sHTML = "<br><span><img src='"+img+"' width='300'><\/span><br>";
			oEditors.getById["ir1"].exec("PASTE_HTML", [sHTML]);
			window.location.replace("write_view");
			}
	});
  
   });

})

</script>

<script language="javascript">

$(function(){
	$(document).ready(function(){
		$('select[name=Menu]').change(function(){
			$('#bMenu').val($(this).val());
			$('#bMenu').attr("readonly", true);
			var sh=$('#bMenu').val($(this).val());
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
    <h2>공지추가</h2>
      <hr>
      <div>
      	&nbsp;&nbsp;&nbsp;
      	<div class="container">
			<table width="900" id="tb" cellpadding="0" cellspacing="0" border="1">
				<form action="/write" method="post" id="reg_frm">
				<input type="hidden" name="bId" value="${write.BId}">
				<input type="hidden" name="Id" value="${write.Id}">
				<input type="hidden" name="bGroup" value="${write.BGroup}">
				<input type="hidden" id="bMenu" name="bMenu" size="30" value="공지사항">
				<input type="hidden" name="bName" value=<sec:authentication property = "name"/>>
					<tr>
					<td>작성자</td>
					<td><sec:authentication property = "name"/></td>
					<!-- <td><input type="text" id="bName" name="bName" size="100"> </td> -->
					</tr>
					
					<tr>
					<td>제목</td>
					<td><input type="text" id="bTitle" name="bTitle" size="100"> </td>
					</tr>

					<tr>
					<td>내용</td>
					<td>
					<textarea name="bContent" id="ir1" cols="80" class="form-control col-11" rows="15"></textarea>
						<script type="text/javascript">
										var oEditors = [];
										    nhn.husky.EZCreator.createInIFrame({
				    						oAppRef: oEditors,
				    						elPlaceHolder: "ir1",
				    						sSkinURI: "/naver-editor/SmartEditor2Skin.html",
				    						fCreator: "createSEditor2"
										});
						</script>
					</td>
					</tr>
					<tr>
						<td colspan = "2">
						<div class="container" style="text-align: center;">
							<input class="btn btn-outline-primary col-2" type = "button" value = "입력" onclick ="form_check()"> &nbsp;&nbsp;
							<a class="btn btn-outline-primary col-2" href = "./resNoti">목록보기</a>
						</div>
						</td>
					</tr>
				</form>
			</table>
		</div>
	</div>
	</div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>	

</body>
</html>