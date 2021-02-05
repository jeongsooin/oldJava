<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">// payment 실행 전, reservation table에 넣어야 함.
function payment() {
	var totalPrice = $("#totalPrice").val();
	var emailId = $('#idck').val();
	var name = $('#nameInfo').val();
	var phone = $('#phoneInfo').val();
    var IMP = window.IMP; // 생략가능
    IMP.init('imp11026312'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
    var msg;
    
    //IMP.request_pay(param, callback)형식으로 호출
    IMP.request_pay({
        pg : 'kakaopay',
        pay_method : 'card',
        merchant_uid : 'merchant_' + new Date().getTime(),
        name : '식당예약 선결제',
        amount : totalPrice,
		buyer_email : emailId,
		buyer_name : name,
		buyer_tel : phone
		//buyer_addr : 
		//buyer_postcode : '123-456' --%>
	    //m_redirect_url : 'http://www.naver.com'
   }, function(rsp) {
       if ( rsp.success ) {
/*            //[1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
           jQuery.ajax({
               url: "/payments/complete", //cross-domain error가 발생하지 않도록 주의해주세요
               type: 'POST',
               dataType: 'json',
               data: {
                   imp_uid : rsp.imp_uid
                   //기타 필요한 데이터가 있으면 추가 전달
               } 
           }).done(function(data) {
               //[2] 서버에서 REST API로 결제정보확인 및 서비스루틴이 정상적인 경우
               if ( everythings_fine ) {
                   msg = '결제가 완료되었습니다.';
                   msg += '\n고유ID : ' + rsp.imp_uid;
                   msg += '\n상점 거래ID : ' + rsp.merchant_uid;
                   msg += '\결제 금액 : ' + rsp.paid_amount;
                   msg += '카드 승인번호 : ' + rsp.apply_num;
                   
                   alert(msg);
               } else {
                   //[3] 아직 제대로 결제가 되지 않았습니다.
                   //[4] 결제된 금액이 요청한 금액과 달라 결제를 자동취소처리하였습니다.
               }
           });
           //성공시 이동할 페이지*/
           <%-- location.href='<%=request.getContextPath()%>/order/paySuccess?msg='+msg; --%>
    	   
       } else {
			msg = '결제에 실패하였습니다.';
<%--       msg += '에러내용 : ' + rsp.error_msg;
           //실패시 이동할 페이지
           location.href="<%=request.getContextPath()%>/order/payFail";--%>
            alert(msg); 
        }
    });
}


</script>


<div class="modal-header">
<h1 class="modal-title" id="modal">예약하기</h1>
	<button type="button" class="close" onclick="location.reload()" data-dismiss="modal" aria-label="close">
		<span aria-hidden="true">&times;</span>
	</button>
</div>
<div class="modal-body">
	<div id = "rsvPayment">
		<h1>결제!ㅋ</h1>
	</div>
</div>
<div class="modal-footer" id="nextContainer">
	<div id = "rsvPaymentBtn">
		<button type="button" class="btn btn-danger" id="prev2">뒤로</button>
		<button type="button" class="btn btn-primary" id="next3">다음</button>
		<button type="button" class="btn btn-secondary" onclick="location.reload()"  data-dismiss="modal">취소</button>		
	</div>
</div>