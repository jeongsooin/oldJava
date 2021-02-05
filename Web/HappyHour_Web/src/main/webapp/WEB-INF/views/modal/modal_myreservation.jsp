<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="modal-header">
<h1 class="modal-title" id="modal">내 예약</h1>
	<button type="button" class="close" onclick="location.reload()" data-dismiss="modal" aria-label="close">
		<span aria-hidden="true">&times;</span>
	</button>
</div>
<div class="modal-body">
	<div>
		<input type="hidden" id="rsvname" name="rsvname" maxlength="20" readonly>
		<table id="myrsvlist" border="1" style="width:450px">
	<!-- 			 <caption>
				   <button type="button" id="btnAdd">
				     추가
				   </button>
				 </caption>  -->
		</table>
	</div>
</div>
<div class="modal-footer" id="nextContainer">
	<div id = "rsvPaymentBtn">
		<button type="button" class="btn btn-secondary" onclick="location.reload()"  data-dismiss="modal">닫기</button>		
	</div>
</div>