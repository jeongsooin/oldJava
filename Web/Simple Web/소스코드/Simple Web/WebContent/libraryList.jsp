<%@page import="com.study.jsp.command.*" %>
<%@ page import="java.sql.*,java.text.SimpleDateFormat,java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
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
		function setOption(optionV) {
			var option = optionV;
			$("#dropdownMenu2").text(option);
			$("#option").attr("value", option);
		}
		$('#myModal').on('shown.bs.modal', function () {
	    	$('#myInput').trigger('focus')
	    });
	</script>
	<title>Library</title>
	<style>
	@import url('https://fonts.googleapis.com/css?family=Alegreya+Sans+SC|Do+Hyeon|Lobster+Two|Marvel|Nanum+Myeongjo|Noto+Serif+KR|Oleo+Script|Oswald|Song+Myung|Yellowtail');
	@import url('https://fonts.googleapis.com/css?family=Barlow|Barlow+Condensed');

	.bd-placeholder-img { font-size: 1.125rem; text-anchor: middle; -webkit-user-select: none; -moz-user-select: none; -ms-user-select: none; user-select: none; }
      
    @media (min-width: 768px) { .bd-placeholder-img-lg { font-size: 3.5rem; } }
	  
	#divtop { width: 100vw; }
	  
	.modal-body { background-color: #b5c5a1 }
	
	body { padding-bottom: 20px; }
	a:link { text-decoration: none; color: #000000; }
	a:visited { text-decoration: none; color: #000000; }
	a:active { text-decoration: none; color: #000000; }
	a:hover { text-decoration: none; color: #b5c5a1; }
    .navbar { margin-bottom: 20px; }
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
    .modal-title { font-family: 'Oleo Script', cursive; }
    .modal-footer { font-family: 'Barlow', sans-serif; }
    .modal-body { font-family: 'Noto Serif KR', serif; font-weight: bold; }
    .thead { background-color: #b5c5a1; color: white; font-family: 'Barlow', sans-serif; border: 4px solid #b5c5a1; }
    .tbody { font-family: 'Barlow', sans-serif; border: 4px solid #fff; }
    #write_button { font-family: 'Barlow', sans-serif; font-weight: bold; }
    #search_form { font-family: 'Barlow', sans-serif; }
    .page-item { font-family: 'Barlow', sans-serif; }
    .container { position: relative; }
    .table { margin-bottom: 0px; position: relative; }
    .diaplay_left { position: fixed; top: 100px; width: 80vw; height: 100vh;}
    #display-right { position: fixed; top: 100px; left: 70vw; width: 600px; }
    #table_div3 { position: relative; }
    #nav_top { font-family: 'Barlow', sans-serif; font-weight: bold; font-size: 1.2rem; background-color: #1e4632; opacity: 0.3; color: #fff; }
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
	<div class="container" id="table_div">
		<table class="table table-hover">
			 <thead class="thead">
			 <tr class="table_nav">		 
				<th scope="col">#</th>
				<th scope="col">Name </th>
				<th scope="col">Title</th>
				<th scope="col">Date</th>
			</tr>
			</thead>
	  		<tbody>
	  		<c:set var="now" value="${now}" />
			<c:forEach items="${list}" var="dto">
				<tr scope="col">
					<th scope="row">${dto.fId}</th>
					<td>${dto.fName}</td>
					<td>
						<a href="file_content_view.do?kind=view&fId=${dto.fId}">${dto.fTitle}</a>
					</td>			
					<td>${dto.fDate}</td>
				</tr>			
			</c:forEach>
			</tbody>
			</table>
		</div>
		<!-- Menu -->
		<div class="container">
		  <nav class="navbar navbar-expand-lg navbar-dark rounded" style="background-color:#b5c5a1;">
		    <a class="navbar-brand" href="#"></a>
		    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample09" aria-controls="navbarsExample09" aria-expanded="false" aria-label="Toggle navigation">
		      <span class="navbar-toggler-icon"></span>
		    </button>
		
		    <div class="collapse navbar-collapse" id="navbarsExample09">
		      <ul class="navbar-nav mr-auto">
		        <li id="write_button" class="nav-item">
		        <% if(session.getAttribute("status") == null) { %>
		          <button type="button" class="btn btn-outline-light" data-toggle="modal" data-target="#exampleModal">POST</button>		          
		        <% } else { %>
		          <a class="btn btn-outline-light" href="file_view.jsp">POST</a>
		        <% } %>
		        <% if(session.getAttribute("IsAdmin") != null ) { %>
		        <a class="btn btn-outline-light" href="board_notice_write_view.jsp">NOTICE</a>
		        <% } %>
		        </li>		        
		      </ul>
		      <form id="search_form" class="form-inline my-2 my-md-0">		      
		      <ul class="navbar-nav mr-auto" >
		      	<li class="nav-item dropdown">		          
		      		<div class="dropdown">
		      			<button class="btn btn-outline-light dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" value="Search Option">Search Option</button>&nbsp;&nbsp;
		      				<div class="dropdown-menu" aria-labelledby="dropdownMenu2">
		      					<button id="option_all" class="dropdown-item" type="button" value="ALL" onclick="setOption('ALL');">ALL</button>
						    	<button id="option_title" class="dropdown-item" type="button" value="Title" onclick="setOption('Title');">Title</button>
						    	<button id="option_content" class="dropdown-item" type="button" value="Content" onclick="setOption('Content');">Content</button>
						    	<button id="option_name" class="dropdown-item" type="button" value="Name" onclick="setOption('Name');">Name</button>
						  </div>
					  </div>
			         </li>
		         </ul>
		        <input id="option" name="option" class="form-control mr-sm-2" type="hidden" value="ALL">		        
		        <input id="word" name="word" class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
      			<button class="btn btn-outline-light my-2 my-sm-0" type="submit">Search</button>
		      </form>
		    </div>
		  </nav>
		</div>		
    	<nav aria-label="..."  id="table_div3">
  			<ul class="pagination justify-content-center">
  				<li class="page-item">
					<a class="page-link" href="libraryList.do?page=1"> &lt;&lt;</a>
    			</li>
    			
    			<li class="page-item">
   				 	<c:choose>
						<c:when test="${(page.curPage - 1) < 1}">
							<a class="page-link" href="libraryList.do?page=1"> &lt;</a>
						</c:when>
						<c:otherwise>
							<a class="page-link" href="libraryList.do?page=${page.curPage - 1}"> &lt;</a>
						</c:otherwise>
					</c:choose>
    			</li>
    			
    			<c:forEach var="fEach" begin="${page.startPage}" end="${page.endPage}" step="1">
				<li class="page-item">
					<c:choose>
						<c:when test="${page.curPage == fEach}">
							<a class="page-link" href="libraryList.do?page=${fEach}"> ${fEach} </a>
						</c:when>					
						<c:otherwise>
							<a class="page-link" href="libraryList.do?page=${fEach}"> ${fEach} </a>
						</c:otherwise>
						</c:choose>
				</li>
				</c:forEach>
				
    		<li class="page-item">
    			<c:choose>
					<c:when test="${(page.curPage + 1) > page.totalPage}">
						<a class="page-link" href="libraryList?page=${page.curPage}"> &gt; </a>
					</c:when>
					<c:otherwise>
						<a class="page-link" href="libraryList?page=${page.curPage + 1}"> &gt; </a>
					</c:otherwise>
				</c:choose>
			</li>
     		
     		<li class="page-item">
     			<a class="page-link" href="libraryList.do?page=${page.endPage}"> &gt;&gt; </a>
    		</li>
  		</ul>
	</nav>
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
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	<script>window.jQuery || document.write('<script src="/docs/4.3/assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
	<script src="/docs/4.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script>
</body>
</html>