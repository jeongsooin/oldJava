<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://www.springframework.org/security/tags" prefix="sec" %>
<%! String name, id; %>
<%
	name = (String)session.getAttribute("name");
	id = (String)session.getAttribute("id");
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

	<table width ="700" id="tb" cellpadding="0" cellspacing="0" border="1" id="notiView">
		<input type="hidden" id="id" value="${dto.BName}">
		<%-- <input type="hidden" id="idck" value="<%=id%>"> --%>
		<input type="hidden" id="idck" value=<sec:authentication property ="name"/>>

		
		<tr>
			<td style="font-size:15px;">작성자</td>
			<td style="font-size:15px;">${dto.BName}</td>
		</tr>
		<tr>
			<td style="font-size:15px;">내용</td>
			<td style="font-size:15px;">
			 	${dto.BContent}
			</td>
		</tr>
	</table>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
 <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>	
	

</body>
</html>