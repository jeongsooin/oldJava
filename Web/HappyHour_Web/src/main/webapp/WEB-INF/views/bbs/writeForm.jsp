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
<style>
#name p {
color:black; text-align:right;margin-right:120px;margin-top:10px;
}
#name span {
color:white; font-weight:bold;
}
#tb {
margin:0px auto;
}
li {
none;
}

#hello{
background-color:blue; width:100%;
}

.login {
color:white;

}

.login span{
color:white;
}

.test{
background-color:#1678CD; width:100%;
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

$(function(){
	$("#files").change(function(){
		var formData = new FormData();
		var file = $("input[name=img]")[0].files[0];
		
		formData.append('uploadfile', $("input[name=img]" )[0].files[0]  );
	 
	$.ajax({
		url: 'fileFormOk',
		type: 'Post',
		data: formData,
		dataType: "data",
		processData: false,
		contentType: false,
		SUCCESS: function(data) {
			if(data == null) 
				alert("파일 업로드 용량 초과");
			var json=JSON.parse(data);
			alert(json.rs);
			var sHTML = "<br><span><img src='"+json.url+"' download>["+json.file+"파일첨부]</a><\/span><br>";
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
<div class="container-fluid">
  		<div class="row">
  		<div class ="col test">
<div id="name">
<p><span><sec:authentication property = "name"/></span> 님 안녕하세요.<p>
</div>
	<div class="container">
		<div class="row">
    		<div class="col-sm-8 test1"></div>
    		<div class="col-sm-4 login" >
    			<a href="./modify"><span>정보수정</span></a> &nbsp;
    			<span> / </span> &nbsp;
				<a href="./logout"><span>로그아웃</span></a>
				
    		</div>
  		</div>
	</div>
</div>
</div>
</div>

<p>
<p>
<ul class="nav nav-pills">
  <li class="nav-item">
    <a class="nav-link active" href="/main">HOME</a>
  </li>
  <li class="nav-item dropdown">
    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">게시판</a>
    <div class="dropdown-menu">
          <a class="dropdown-item" href="/list">전체보기</a>
      <a class="dropdown-item" href="/board1">공지사항</a>
      <a class="dropdown-item" href="/board2">자유게시판</a>
      <a class="dropdown-item" href="/board3">자료실</a>
    </div>
  </li>
</ul>

<p>
<p>

<table width="800" id="tb" cellpadding="0" cellspacing="0" border="1">
	<form action="/write" method="post" id="reg_frm">
	<input type="hidden" name="bId" value="${write.BId}">
	<input type="hidden" name="Id" value="${write.Id}">
	<input type="hidden" name="bGroup" value="${write.BGroup}">
	<input type="hidden" name="bName" value=<sec:authentication property = "name"/>>
<%-- 	    <input type="hidden" name="Id" value="<%=id %>">	 --%>			
	
		<tr>
		<td>작성자</td>
		<td><sec:authentication property = "name"/></td>
		<!-- <td><input type="text" id="bName" name="bName" size="100"> </td> -->
		</tr>
		
		<tr>
		 	<td>그룹선택</td>
			<td> 
			<input type="text" id="bMenu" name="bMenu" size="30" value="자유게시판">
				<select id="Menu" name="Menu" >
						<option value="공지사항" name="Noti">공지사항</option>
						<option value ="자유게시판" selected="selected" name="Free">자유게시판</option>
						<option value="자료실" name="Data">자료실</option>
				</select>
			  </input>
			</td>
		</tr>
		
		<tr>
		<td>제목</td>
		<td><input type="text" id="bTitle" name="bTitle" size="100"> </td>
		</tr>
		
		<tr>
		<td>비밀번호</td>
		<td><input type = "text" id="bPassword" name = "bPassword" size = "50"></td>
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
			<input type="file" class="file" name="img" id="img" accept=".jpg,.jpeg,.png,.gif,.bmp">
			<button type="button" id="img-btn"s>이미지삽입</button>
			<input type="file" class="file" name="files" id="files">
			<button type="button" id="files-btn" style="display:none">파일첨부(10M)</button>
		</td>
		</tr>
		
		<tr>
			<td colspan = "2">
			<div class="container" style="text-align: center;">
				<input class="btn btn-outline-primary col-2" type = "button" value = "입력" onclick ="form_check()"> &nbsp;&nbsp;
				<a class="btn btn-outline-primary col-2" href = "/list">목록보기</a>
			</div>
			</td>
		</tr>
			
	</form>
</table>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>	

</body>
</html>