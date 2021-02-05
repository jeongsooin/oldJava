<%@ page import="com.study.jsp.command.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="true"%>
<%@ page errorPage="errorPage.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 내용</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">    
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link href="/docs/4.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/css?family=Playfair+Display:700,900" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" type="text/css" rel="stylesheet">
	<link rel="stylesheet" href="client.css">
    <script src="http://code.jquery.com/jquery.js"></script>
    <script>
    $('#myModal').on('shown.bs.modal', function () {
    	$('#myInput').trigger('focus')
    });
    </script>
    <style>
	    body { padding-bottom: 20px; width: 100%;}
		a:link { text-decoration: none; color: #ffffff; }
		a:visited { text-decoration: none; color: #fffff; }
		a:active { text-decoration: none; color: #fffff; }
		a:hover { text-decoration: none; color: #fffff; }
      .bd-placeholder-img { font-size: 1.125rem; text-anchor: middle; -webkit-user-select: none; -moz-user-select: none; -ms-user-select: none; user-select: none; }     
      @media (min-width: 768px) { .bd-placeholder-img-lg { font-size: 3.5rem; } }          	  
	   *:focus { outline: none; }
	  .modal-body { background-color: #b5c5a1; }
	  .modal-title { font-family: 'Oleo Script', cursive; }
      .modal-footer { font-family: 'Barlow', sans-serif; }
      .modal-body { font-family: 'Noto Serif KR', serif; font-weight: bold; }
	  .material-icons {
		  font-family: 'Material Icons';
		  font-weight: normal;
		  font-style: normal;
		  font-size: 24px;  /* Preferred icon size */
		  display: inline-block;
		  line-height: 1;
		  text-transform: none;
		  letter-spacing: normal;
		  word-wrap: normal;
		  white-space: nowrap;
		  direction: ltr;
		
		  /* Support for all WebKit browsers. */
		  -webkit-font-smoothing: antialiased;
		  /* Support for Safari and Chrome. */
		  text-rendering: optimizeLegibility;
		
		  /* Support for Firefox. */
		  -moz-osx-font-smoothing: grayscale;
		
		  /* Support for IE. */
		  font-feature-settings: 'liga';
		}
		.material-icons {
		  font-family: 'Material Icons';
		  font-weight: normal;
		  font-style: normal;
		  font-size: 24px;  /* Preferred icon size */
		  display: inline-block;
		  line-height: 1;
		  text-transform: none;
		  letter-spacing: normal;
		  word-wrap: normal;
		  white-space: nowrap;
		  direction: ltr;
		
		  /* Support for all WebKit browsers. */
		  -webkit-font-smoothing: antialiased;
		  /* Support for Safari and Chrome. */
		  text-rendering: optimizeLegibility;
		
		  /* Support for Firefox. */
		  -moz-osx-font-smoothing: grayscale;
		
		  /* Support for IE. */
		  font-feature-settings: 'liga';
		}
	.container { position: relative; }
	#nav_top { font-family: 'Barlow', sans-serif; font-weight: bold; font-size: 1.2rem; background-color: #1e4632; opacity: 0.3; color: #fff; }
    .table { margin-bottom: 0px; position: relative; }
    .diaplay_left { position: fixed; top: 100px; width: 80vw; height: 100vh;}
    #display-right { position: fixed; top: 100px; left: 70vw; width: 600px; }
    #table_div3 { position: relative; }
    #content_title { background-color: #b5c5a1; color: #fff; font-weight: bold; }	
    #content_menu { background-color: #b5c5a1; }
    #content_table { width: 60%; margin-left: 100px;}		
    </style>
</head>
<body>
	<nav class="navbar navbar-expand-md navbar-light fixed-top" id="nav_top">
			<a class="nav-link p-2 text-light" href="main.jsp" >Home</a>
  			<a class="nav-link p-2 text-light" href="libraryList.do" >Library</a>
    		<a class="nav-link p-2 text-light" href="list.do">Board</a>
    		<a class="nav-link p-2 text-light" href="noticelist.do">Notice</a>
    		<% if(session.getAttribute("status") == null) 
	  			{
			%> 
			<button type="button" class="btn btn-outline-link text-light" data-toggle="modal" data-target="#exampleModal">Account<i class="material-icons md-light">account_circle</i></button>&nbsp;&nbsp;
			<%
	  	 		} else {
	  	 	%>
	  	 	<a class="nav-link p-2 text-light" href="AccountSetting.jsp">Account<i class="material-icons md-light">account_circle</i></a>
	  	 	<a class="nav-link p-2 text-light"  href="logout.jsp">Logout</a>
	  	 	<%
	  	 		}
	  	 	%>		  			  	
		  	<a class="nav-link disabled mr-sm-5" href="#" aria-disabled="true">&nbsp;</a>
		  	<a class="nav-link disabled mr-sm-5" href="#" aria-disabled="true">&nbsp;</a>
		  	<a class="nav-link disabled mr-sm-5" href="#" aria-disabled="true">&nbsp;</a>
		  	<a class="nav-link disabled mr-sm-5" href="#" aria-disabled="true">&nbsp;</a>
		  	<a class="nav-link disabled mr-sm-5" href="#" aria-disabled="true">&nbsp;</a>
		  	<a class="nav-link disabled mr-sm-5" href="#" aria-disabled="true">&nbsp;</a>
		  	<a class="nav-link disabled mr-sm-5" href="#" aria-disabled="true">&nbsp;</a>
		  	<a class="nav-link disabled mr-sm-5" href="#" aria-disabled="true">&nbsp;</a>
		  	<a class="nav-link disabled mr-sm-5" href="#" aria-disabled="true">&nbsp;</a>
		  	<a class="nav-link disabled mr-sm-5" href="#" aria-disabled="true">&nbsp;</a>
		  	<a class="nav-link disabled mr-sm-5" href="#" aria-disabled="true">&nbsp;</a>
		  	<a class="nav-link disabled mr-sm-5" href="#" aria-disabled="true">&nbsp;</a>
		  	<a class="nav-link disabled mr-sm-5" href="#" aria-disabled="true">&nbsp;</a>
		  	<i class="material-icons md-48-light">chat</i>&nbsp;&nbsp;
		  	<button id="open" class="btn btn-outline-light rounded-pill" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample" onclick="openSocket();">OPEN</button>&nbsp;&nbsp;
			<button id="close" class="btn btn-outline-light rounded-pill my-2 my-sm-0" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample" onclick="closeSocket();">CLOSE</button> 			    
  	</nav>
  	<div class="diaplay_left">
  		<table class="table" id="content_table">
			<thead class="thead">
				<tr class="table_nav" id="content_title">		 
					<th scope="col" colspan="2">#${fcontent_view.fId}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;${fcontent_view.fTitle}</th>
				</tr>
			</thead>
			<tbody>
				<tr scope="col">
					<td colspan="2">Writer&nbsp;&nbsp;| ${fcontent_view.fName}</td>					
				</tr>
				<tr scope="col">
					<td colspan="2">						
						<form action="fileDownload.jsp" method="post">
							${fcontent_view.f_name}
							<input type="hidden" name="file_name" value="${fcontent_view.f_name}">
							<input class="btn btn-outline-success" type="submit" value="Download">
						</form>
					</td>
				</tr>
				<tr scope="col">
					<td colspan="2">${fcontent_view.fContent}</td>
				</tr>
				<tr id="content_menu">				
				<% if(request.getAttribute("writer").equals(session.getAttribute("name")) || session.getAttribute("IsAdmin") != null ) 
		  			{
				%>	
					<td>
						<a class="btn btn-outline-light" href="#">수정</a>&nbsp;&nbsp;
						<a class="btn btn-outline-light" href="delete.do?fId=${content_view.fId}">삭제</a>
					</td>
					<td><a class="btn btn-outline-light" href="libraryList.do?page=<%= session.getAttribute("cpage") %>">목록보기</a></td>				
				<%
		  	 		} else {
		  	 			if(session.getAttribute("status") != null) {
		  	 	%>
		  	 		<td>
		  	 			<a class="btn btn-outline-light" href="#">답변</a>
		  	 		</td>
		  	 		<td>
		  	 			<a class="btn btn-outline-light" href="libraryList.do?page=<%= session.getAttribute("cpage") %>">목록보기</a>
		  	 		</td>  	 		
		  	 	<%
		  	 			} else {
		  	 	%>	
		  	 		<td colspan="2">
		  	 			<a class="btn btn-outline-light" href="libraryList.do?page=<%= session.getAttribute("cpage") %>">목록보기</a>
		  	 		</td>
		  	 	<%
		  	 			}
		  	 		}
		  		 %>																
			</tr>
			</tbody>
		</table>
  	</div>
  	<div id="display-right">
	<div id="chat_box">
				<div class="collapse" id="collapseExample">
					<div class="container" id="_chatbox">
						<h3 class="text-center"></h3>
						<div class="messaging">		      
							<div class="mesgs">
								<div class="msg_history">	
						          	<div id="messages"></div>
						         <div class="type_msg">
						         	<div class="input_msg_write">
						         		<input id="messageinput" type="text" class="write_msg" onkeyup="enterkey()"/>
						         		<button class="msg_send_btn" type="button" onclick="send();"><i class="fa fa-paper-plane-o" aria-hidden="true"></i></button>
						            </div>
						         </div>
						        </div>
						    </div>		      
						</div>
					</div>
				</div>
			</div>		
	</div>
	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">NOTICE</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        정회원만 이용가능 합니다.<br>
	        회원가입 하시겠습니까?<br>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-outline-success"  onclick="javascript:window.location='join.jsp'">Sign Up</button>
	      </div>
	    </div>
	  </div>
	</div>
	<script>
		var webSocket;
		var messages = document.getElementById("messages");	
		var sent_html1 = "<div class='outgoing_msg'><div class='sent_msg'><p>";
		var sent_html2 = "</p><span class='time_date'> 11:01 PM    |    Today</span><br></div></div>";
		var received_html1 = "<div class='incoming_msg'></div><div class='received_msg'><div class='received_withd_msg'><p>";
		var received_html2 = "</p><span class='time_date'> 11:01 PM    |    Today</span><br></div></div></div>";
		function openSocket() {
			$('#open').collapse('show');
			if (webSocket != undefined && webSocket.readyState != WebSocket.CLOSED) {
				writeReponse("WebSocket is already opend.");
				return;
			}
			
			webSocket = new WebSocket("ws://localhost:8081/HW_BBS_Project/websocketendpoint");
			
			webSocket.onopen = function(event) {
				if(event.data == undefined)
					return;
				writeResponse(event.data);
			};
			
			webSocket.onmessage = function(event) {
				writeResponse(event.data);
			};
			
			webSocket.onclose = function(event) {
				writeResponse("Connection closed");
			};			
		}
		
		function send() {
			var id = "<%= session.getAttribute("name") %>";
			var text = document.getElementById("messageinput").value;
			var text_html = sent_html1 + text + sent_html2;
			messages.innerHTML += text_html;			
			webSocket.send(id + " | " + text);
		}
		
		function closeSocket() {
			$('#close').collapse('hide');
			webSocket.close();
		}
		
		function writeResponse(text) {
			var text_html = received_html1 + text + received_html2;
			messages.innerHTML += text_html;
		}
		
		function enterkey() {
	        if (window.event.keyCode == 13) {
	            send();
	        }
	    }
	</script>				
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>		
</body>
</html>