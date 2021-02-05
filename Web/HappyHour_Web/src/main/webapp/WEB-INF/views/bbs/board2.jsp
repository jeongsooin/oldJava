<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://www.springframework.org/security/tags" prefix="sec" %>
<%
String log = (String)session.getAttribute("ValidMem");
String name = (String)session.getAttribute("name");
String id = (String)session.getAttribute("username");
String bMenu = (String)session.getAttribute("bMenu");
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery.js"></script>
 <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<style>
.login{
	color:black; text-align: right;
}

.nav1{
	text-align: right;
}
#write{
float:right;margin-bottom:10px; margin-top:0px;
margin-right:10px;
}
#name p {
color:black; text-align:right;margin-right:120px;margin-top:10px;
}
#name span {
color:white; font-weight:bold;
}
.login {
color:white;

}

.login span{
color:white; font-size: 20px; 
}

.test{
background-color:#1678CD; width:100%;
 }
 
#name p{
 color:black; font-size: 20px;
 }
.test p{
 font-weight: bold; font-size: 40px; text-align:center; margin-top: 10px;
 color: white;
 }
</style>

<script>
	function doDisplay() {
		var idck = $('#idck').val();
/* 		alert(idck);  */
		if(idck != null && idck != "anonymousUser") {	
			$('#logout').css('display','none');
			$('#login').css('display','block');
		} 
	}
</script>
</head>
<body onload="doDisplay()">
<%-- <input type="hidden" id="idck" value="<%=name%>"> --%>
<input type="hidden" id="idck" value=<sec:authentication property ="name"/>>

<div class="container-fluid">
  		<div class="row">
  			<div class ="col test"><p>게시판</p>
<div class="container-fluid" >
		<div class="row">
    		<div class="col-sm-8 test1"></div>
    		<div class="col-sm-4 login">

  <div id ="logout">	
    	<a href="loginForm"><span>로그인</span></a> &nbsp;
    	<span> / </span> &nbsp;
		<a href="join"><span>회원가입</span></a> --%>
  </div>	
	  
  <div id ="login" style="display:none">
		<div class="row">
		<div id="name">
			<p><span><sec:authentication property = "name"/></span> 님 안녕하세요.<p>
		</div>
		 		<a href="./modify"><span>마이페이지</span></a> &nbsp;
		    	<span> / </span> &nbsp;
				<c:url value="/logout" var="logoutUrl"/>
				<a href="${logoutUrl}"><span>로그아웃</span></a>			
   </div>
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
    <div class="dropdown-menu" name="Menu">
       <input type="button"  class="dropdown-item" value="전체보기" onclick="javascript:window.location ='./list'" name="All" >
       <input type="button" class="dropdown-item" value="공지사항" onclick="javascript:window.location ='./board1'" name="Noti">
       <input type="button" class="dropdown-item" Value="자유게시판" onclick="javascript:window.location ='./board2'" name="Free">
       <input type="button" class="dropdown-item" value="자료실" onclick="javascript:window.location ='./board3'" name="Data">        
    </div>
  </li>
</ul>

<script>
function find_check() {
	
	if($('#find_name').val().length == 0){
		alert("검색어를 입력하세요.");
		$('#find_name').focus();
		return;
	}
}

</script>

<nav class="navbar navbar-light bg-light">
  <a class="navbar-brand">자유게시판</a>
  <form action="search" method="post" class="form-inline">	
				<select id="Menu" name="find_field" >
				        <option value="total" name="total">전체검색</option>
						<option value="bTitle" name="bTitle">제목</option>
						<option value="bContent" name="bContent">내용</option>
						<option value ="bName" name="bName">작성자</option>
						<option value="bTitCon" name="bTitCon">제목+내용</option>
				</select> 
	&nbsp;&nbsp;<input name="find_name" id="find_name" class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">	
    &nbsp;<button class="btn btn-outline-success my-2 my-sm-0" type="submit" onclick="find_check()">검색</button>
  </form>
</nav>

<p>
	<div class="container">
		<%-- <input type="hidden" id="bMenu" name="bMenu" value=<%=bMenu%>> --%>
		
	<table class="table table-striped" name="write_frm" width = "700" cellpadding = "0" cellspacing ="0" border="1">
		<tr>
			<th scope="col">번호</th>
			<th scope="col">작성자</th>
			<th scope="col">제목</th>
			<th scope="col">날짜</th>
			<th scope="col">조회수</th>
		</tr>
		
		<c:forEach items="${list2}" var ="dto">
			<tr>
				<td scope="row" >${dto.BId}</td>
				<td>${dto.BName}</td>
				<td>
					<c:forEach begin="1" end="${dto.BIndent}">-</c:forEach> <!--댓글쓰면 들여쓰기 되는 원리 -->
			    	<a href = "member/view?bId=${dto.BId}&kind=view">${dto.BTitle }</a>
				</td>
				<td>${dto.BDate}</td>
				<td>${dto.BHit}</td> 
				
			</tr>
		</c:forEach>
		
		<tr>
			<!-- <td colspan="5"> <a href = "write_view.do">글작성</a></td> -->
			<input type ="button" value = "글작성" id="write" onclick ="javascript:window.location = 'member/writeForm'">&nbsp;&nbsp;&nbsp;
		</tr>
		
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

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
 <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>	

</body>
</html>