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
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" type="text/css" rel="stylesheet">
	<link rel="stylesheet" href="client.css">
	<title>Chat Client</title>
	<style>
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
	</style>	
</head>
<body>
<%
	String name = (String)session.getAttribute("name");
	session.setAttribute("uid", name);
%>
	<div id="chat_box">
		<div id="chat_button">
		  <button id="open" class="btn btn-outline-info rounded-pill" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample" onclick="openSocket();"><i class="material-icons">chat</i>OPEN</button>&nbsp;&nbsp;
		  <button id="close" class="btn btn-outline-secondary rounded-pill" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample" onclick="closeSocket();"><i class="material-icons">chat_bubble</i>CLOSE</button>	  
		</div>
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
	<script>
		var webSocket;
		var messages = document.getElementById("messages");	
		var sent_html1 = "<div class='outgoing_msg'><div class='sent_msg'><p>";
		var sent_html2 = "</p><br></div></div>";
		var received_html1 = "<div class='incoming_msg'><div class='received_msg'><div class='received_withd_msg'><p>";
		var received_html2 = "</p><br></div></div></div>";
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
			var text = document.getElementById("messageinput").value;
			var text_html = sent_html1 + text + sent_html2;
			messages.innerHTML += text_html;
			webSocket.send(text);		
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
</body>
</html>