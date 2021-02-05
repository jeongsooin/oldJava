<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



<!-- <script>
   //플러그인 간의 충돌을 제거합니다.
     J.noConflict();
     var J = jQuery;
</script>
 -->
<script>
J(function(){ // Calendar
    J( "#dateInfo" ).datepicker({
    	changeMonth: true, 
        dayNames: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
        dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], 
        monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
    
		minDate: 0,
  		maxDate: 30
    });
});
</script>

<script>
J(function(){ // modal function
    J('#modal_popup_reservation').on('click',function(){
    	
    	var idck = J('#idck').val();
    	
    	if(idck != null && idck != "anonymousUser") {
    		J('#next').prop('disabled', true);
    		J("#modal_myrsv").hide(); // display 속성을 block 으로 바꾼다.
    		J("#modal_pmt").hide(); // display 속성을 block 으로 바꾼다.
			J("#modal_rsv").show(); // display 속성을 block 으로 바꾼다.    		
    		J("#rsvMenuInfo").hide(); // display 속성을 block 으로 바꾼다.
			J("#rsvMenuInfoBtn").hide(); // display 속성을 block 으로 바꾼다.
			J("#rsvPayment").hide(); // display 속성을 block 으로 바꾼다.
			J("#rsvPaymentBtn").hide(); // display 속성을 block 으로 바꾼다.
    		J('#reservationModal').modal({show:true});
		} else {
			alert('로그인을 해주세요.');	
		}	    	
    }); 
    
    J('#modal_popup_myrsv').on('click',function(){
    	
    	var idck = J('#idck').val();
    	
    	if(idck != null && idck != "anonymousUser") {
    		J("#modal_myrsv").show(); // display 속성을 block 으로 바꾼다.
    		J("#modal_pmt").hide(); // display 속성을 block 으로 바꾼다.
			J("#modal_rsv").hide(); // display 속성을 block 으로 바꾼다.    		
    		J('#reservationModal').modal({show:true});
		} else {
			alert('로그인을 해주세요.');	
		}	    	
    }); 
});

var preLoadFn = function() { // preLoad function
	
	// 1. 예약에 이름과 전화번호를 미리 load합니다.
	var sendData = JSON.stringify({email:J('#idck').val()});
	//alert(sendData);

  	J.ajax({
		type : "POST",
		url : "/reserveInfo",
		data: sendData,
		dataType: "json",
		contentType:"application/json;charset=UTF-8",
		async: true,
		success : function(data){
        	
			var rsvName = data.name;
			var rsvPhone = data.phone;
			
	        J('input[name=name]').attr('value', rsvName),
	        J('input[name=rsvname]').attr('value', rsvName),
	        J('input[name=phone]').attr('value', rsvPhone);
	    },
	    error : function(){
	        alert('통신실패!!:예약 미리등록 ');
	    }	         
	});  
  	
  	// 2. 예약에 메뉴를 미리 load합니다.
  	sendData = "";
  	sendData = JSON.stringify({request:"menu"});

  	J.ajax({
		type : "POST",
		url : "/userMenu",
		data: sendData,
		dataType: "json",
		contentType:"application/json;charset=UTF-8",
		async: true,
		success : function(data){
			
			var result = data.menu;
			var str = '<tr><th>이름</th><th>사진</th><th>상세설명</th><th>가격</th><th>수량</th><th>선택</th></tr><tr>';
			
			J.each(result, function(index, i) {
				str += '<td style="width:60px">' + i.name + '</td>';
				str += '<td style="width:80px"><img src="./upload/' + i.ext + '" width="300"/></td>';
				str += '<td style="width:180px">' + i.dscrp + '</td>';
				str += '<td>' + i.price + '<input type="hidden" id="price'+ i.code +'" value="'+ i.price +'"></td>';
					str += '<td><button type="button" value="amt'+ i.code +'" id="formMbtn">-</button>'+
						'<input type="text" id="amt'+ i.code +'" value="1" style="text-align:center; width:30px"/>'+
						'<button type="button" value="amt'+ i.code +'" id="formPbtn">+</button></td>';
					str += '<td><button type="button" style="width:20px" id="btnAdd" value="' + i.code + '">추가</button>' +
							'<input type="hidden" id="name'+ i.code +'" value="'+ i.name +'"></td></tr>';
			}); 
			J('#test2').append(str);
			
	    },
	    error : function(){
	        alert('통신 실패!!:예약메뉴로드');
	    }	         
	});
  	
  	// 3. 내 예약목록을 미리 load합니다.
  	sendData = "";
  	sendData = JSON.stringify({email:J('#idck').val()});

  	J.ajax({
		type : "POST",
		url : "/reservationList",
		data: sendData,
		dataType: "json",
		contentType:"application/json;charset=UTF-8",
		async: true,
		success : function(data){
			
			var result = data.rsvlist;
			var today = new Date();
			var str = '<tr><th>날짜</th><th>시간</th><th>인원</th><th>예약유형</th><th>결제금액</th></tr><tr>';
			
			J.each(result, function(index, i) {
				iYear = parseInt("20"+i.rdate.substring(0,2));
				iMonth = parseInt(i.rdate.substring(3,5));
				iDay = parseInt(i.rdate.substring(6));
				iHour = parseInt(i.rtime.substring(0,2));
				iMin = parseInt(i.rtime.substring(3));
				convert_date = new Date(iYear, (iMonth-1), iDay, iHour, iMin, 0, 0);

				if (+today == convert_date || +today < convert_date) {
					str += '<td style="width:20%">' + i.rdate + '</td>';
					str += '<td style="width:20%">' + i.rtime + '</td>';
					str += '<td style="width:20%">' + i.rnum + '명</td>';
					str += '<td style="width:20%">' + i.rtype + '</td>';
 					str += '<td style="width:20%">' + i.rpayment + '원</td></tr>';
				}
			}); 
			J('#myrsvlist').append(str);
			
	    },
	    error : function(){
	        alert('통신 실패!!:예약목록로드');
	    }	         
	});
}


J(function(){ // 전체적인 예약 과정 함수
	var sendData, status, rname, rdate, rtime, rnum, rtype, rsvPrice, totalPrice;
	var tableTime1, tableTime2, tableTime3, tableTime4, tableTime5;
	var vacantTable1, vacantTable2, vacantTable3, vacantTable4, vacantTable5;
	var menuIndex = 0;
	// 변수를 모두 만들고 시작
	
	J("#chk_table").click(function () { // 테이블 조회 클릭 시!
		
		J('#next1').prop('disabled', true); // 첫 번째 다음버튼 활성화
		
		rname = J('#idck').val();
		rdate = J('#dateInfo').val();
		rtime = J('#timeInfo').val();
		rnum = J('#peopleInfo').val();		
		
        sendData = JSON.stringify({rname:rname, rdate:rdate, rtime:rtime, rnum:rnum});

	  	J.ajax({
			type : "POST",
			url : "/inquiriesOfTable",
			data: sendData,
			dataType: "json",
			contentType:"application/json;charset=UTF-8",
			async: true,
			success : function(data){

				status = data.status;	// 예약 내역 조회.
				
				if (status == "duplicated") {
					alert("예약내역이 존재합니다.");	// 중복된 예약이 존재.
					J("#test1").empty();
				} else {
					if (status == "occupied") {
						alert("대기예약만 가능합니다.");	// 인원수에 맞는 테이블 수가 부족하거나, 만석일 때.
					}
					
					J("#test1").empty();
					
					var btnVal1 = data.tableTime1 + "<br>" + data.vacantTable1;
					var btnVal2 = data.tableTime2 + "<br>" + data.vacantTable2;
					var btnVal3 = data.tableTime3 + "<br>" + data.vacantTable3;
					var btnVal4 = data.tableTime4 + "<br>" + data.vacantTable4;
					var btnVal5 = data.tableTime5 + "<br>" + data.vacantTable5;
			        
		            var str = '<TR>';
		            
		            str += '<TD><button type="button" class="btn btn-primary" id="btnTime1">'+ btnVal1 +'</button></TD>';
	                str += '<TD><button type="button" class="btn btn-primary" id="btnTime2">'+ btnVal2 +'</button></TD>';
	                str += '<TD><button type="button" class="btn btn-primary" id="btnTime3">'+ btnVal3 +'</button></TD>';
	                str += '<TD><button type="button" class="btn btn-primary" id="btnTime4">'+ btnVal4 +'</button></TD>';
	                str += '<TD><button type="button" class="btn btn-primary" id="btnTime5">'+ btnVal5 +'</button></TD>';
	                str += '</TR>';
	                
 	                str += '<TD><input type="hidden" id="time1" value="'+ data.tableTime1 +'"></TD>';
					str += '<TD><input type="hidden" id="time2" value="'+ data.tableTime2 +'"></TD>';
					str += '<TD><input type="hidden" id="time3" value="'+ data.tableTime3 +'"></TD>';
					str += '<TD><input type="hidden" id="time4" value="'+ data.tableTime4 +'"></TD>';
					str += '<TD><input type="hidden" id="time5" value="'+ data.tableTime5 +'"></TD>';
	                str += '</TR>';
		            
					J("#test1").append(str); 
			        
			    	J("#btnTime1").click(function () {
			    		J('#next1').prop('disabled', false);		// 시간 선택 후, 다음 버튼 활성화.
			    		rtime = J('#time1').val();
			     	});
			     	J("#btnTime2").click(function () {
			     		J('#next1').prop('disabled', false);		 
			     		rtime = J('#time2').val();
			     	});
			     	J("#btnTime3").click(function () {
			     		J('#next1').prop('disabled', false);		
			     		rtime = J('#time3').val();
			     	});
			     	J("#btnTime4").click(function () {
			     		J('#next1').prop('disabled', false);		
			     		rtime = J('#time4').val();
			     	});
			     	J("#btnTime5").click(function () {
			     		J('#next1').prop('disabled', false);		
			     		rtime = J('#time5').val();
			     	});
				}				
		    },
		    error : function(){
		        alert('통신실패!!:예약시간조회');
		    }	         
		}); // 테이블 조회 Ajax 
    }); // (테이블 조회 클릭함수 끝)
	
	
	J("#next1").click(function () { // 첫 번째 다음버튼 클릭 시
		
		if (status == "occupied") { // 만석일 경우 대기예약 선택 시
			
			if(rdate != J('#dateInfo').val() || rnum != J('#peopleInfo').val()) {
		    	alert("다시 조회해주십시요."); // 처음 선택 및 조회한 값들이 변하지 않았는지 마지막 확인
			} else {
				var result = confirm(rtime + "에 대기예약 하시겠습니까?");
				// confirm : 선택에 대한 boolean 값 return	
					    		
			    if (result) {
			    	sendData = "";
					sendData = JSON.stringify({rname:rname, rdate:rdate, rtime:rtime, rnum:rnum});
	
				  	J.ajax({
						type : "POST",
						url : "/waiting",
						data: sendData,
						dataType: "json",
						contentType:"application/json;charset=UTF-8",
						async: true,
						success : function(data){
							status = data.status;	// 예약 입력 상황
							
							if (status == 1) { // 알림 서비스 추가 필요!!!
								var rsvResult = confirm("대기예약 완료! 예약내역으로 이동하시겠습니까?");
								if (rsvResult) {
									alert("예약내역으로 이동(예정)");
								} else {
/* 									rdate = ""; rnum = ""; rtime = ""; sendData = ""; status = ""; 
									rname = ""; rtime = ""; rnum = ""; rsvPrice = ""; totalPrice = "";  */
									alert("이용해주셔서 감사합니다.");	
								} 
							} else {
								alert("예약 실패!!");
							}				
					    },
					    error : function(){
					        alert('통신 실패!!:메뉴로가기');
					    }	         
					}); // <대기예약 ajax 입력 요청>		    	
			    	
			    } else {
/* 					rdate = ""; rnum = ""; rtime = ""; sendData = ""; status = ""; 
					rname = ""; rtime = ""; rnum = ""; rsvPrice = ""; totalPrice = ""; 
 */					alert("다음에 뵙겠습니다.");
 
			    } 
				J('#reservationModal').modal("hide");
				location.reload();	
			}

		} else { // 당일 및 일반 예약 선택 시
			if(rdate != J('#dateInfo').val() || rnum != J('#peopleInfo').val()) {
				
		    	alert("다시 조회해주십시요."); // 처음 선택 및 조회한 값들이 변하지 않았는지 마지막 확인
			} else {
				sendData = "";
				sendData = JSON.stringify({rdate:rdate});
		
			  	J.ajax({
					type : "POST",
					url : "/dateCheck", // 날짜를 조회하여 당일예약/ 일반예약 자동구분 : 결제에 필요
					data: sendData,
					dataType: "json",
					contentType:"application/json;charset=UTF-8",
					async: true,
					success : function(data){
						status = data.status;	// 예약 입력 상황 조회.
		
						if (status == "todayReservation") {
							rtype = "당일예약";
						} else if (status == "generalReservation") {
							rtype = "일반예약";
						} else {
							alert("날짜 조회 실패!!");
						}
						
						J("#rsvUserInfo").hide(); // display 속성을 none 으로 바꾼다.
						J("#rsvUserInfoBtn").hide(); // display 속성을 none 으로 바꾼다. 
						J("#rsvMenuInfo").show(); // display 속성을 block 으로 바꾼다.
						J("#rsvMenuInfoBtn").show(); // display 속성을 block 으로 바꾼다.
				    },
				    error : function(){
				        alert('통신 실패!!:예약구분등록');
				    }	         
				}); 
			}
		}
    }); // (next1 클릭 함수의 끝)
    
    J("#next2").click(function () { // 두 번째 다음버튼 클릭 시
    	totalPrice = J("#totalPrice").val();
    	if(totalPrice == 0) {
	    	alert("메뉴를 선택해주십시요."); // 처음 선택 및 조회한 값들이 변하지 않았는지 마지막 확인
		} else {
	    	intTotalPrice = parseInt(totalPrice);
	    	intRsvPrice = intTotalPrice/5; // 예약금 : 총액의 20%
/* 	    	txtTotalPrice = intTotalPrice.valueOf();
	    	txtRsvPrice = intRsvPrice.valueOf();
	    	commaTotalPrice = txtTotalPrice.substring(txtTotalPrice.length-3);
	    	commaRsvPrice = txtRsvPrice */
	    	
	    	var message;
	    	
	    	if (rtype == "당일예약") {
	    		message = "총"+intTotalPrice+"원을 결제하시겠습니까?";
			} else if (rtype == "일반예약") {
				message = "예약금(20%) "+intRsvPrice+"원을 결제하시겠습니까?";
			}
	    
			var rsvResult = confirm(message);
			if (rsvResult) { // 네
				J("#rsvMenuInfo").hide(); // display 속성을 none 으로 바꾼다.
				J("#rsvMenuInfoBtn").hide(); // display 속성을 none 으로 바꾼다. 
				J("#rsvPayment").show(); // display 속성을 block 으로 바꾼다.
				J("#rsvPaymentBtn").show(); // display 속성을 block 으로 바꾼다.
				
	    		J("#modal_pmt").show(); // display 속성을 block 으로 바꾼다.
				J("#modal_rsv").hide(); // display 속성을 none 으로 바꾼다.
				
			} else { // 아니오
				rdate = ""; rnum = ""; rtime = ""; sendData = ""; status = ""; 
				rname = ""; rtime = ""; rnum = ""; rsvPrice = ""; totalPrice = ""; 
				alert("이용해주셔서 감사합니다.");	
				J('#reservationModal').modal("hide");
				location.reload();	
			} 
		}
    }); // (next2 클릭 함수의 끝)

	J("#prev1").click(function () {
		J("#rsvUserInfo").show();
		J("#rsvUserInfoBtn").show(); 
		J("#rsvMenuInfo").hide();
		J("#rsvMenuInfoBtn").hide();			
	}); // 첫 번째 뒤로가기 버튼
	
	J("#prev2").click(function () {
		J("#rsvMenuInfo").show();
		J("#rsvMenuInfoBtn").show(); 
		J("#rsvPayment").hide();
		J("#rsvPaymentBtn").hide();			
	}); // 두 번째 뒤로가기 버튼
});

J(function(){ // 메뉴 추가 삭제 관련
	var btnId, selectedAmount, selectedMenu, selectedprice, mPrice, existMenu, str, totalPrice;

	J(document).on("click","#btnAdd",function(){ //btnAdd라는 버튼을 눌렀을때 ->이벤트 등록
		btnId = J(this).attr('value');
		selectedMenu = J("#name"+btnId).val();
		existMenu = J("#selectedMenu"+btnId).val();
		selectedAmount = J("#amt"+btnId).val();		
		selectedprice = J("#price"+btnId).val();
		totalPrice = J("#totalPrice").val();
		mPrice = selectedAmount*selectedprice;
		
		if (existMenu == null) {
			str = '<tr>';
			str += '<td style="width:150px">' + selectedMenu + '<input type="hidden" id="selectedMenu'+ btnId +'" value="'+ selectedMenu +'"></td>';
			str += '<td><button type="button" value="'+ btnId +'" id="selectedMbtn">-</button>'+
					'<input type="text" id="selectedAmount'+ btnId +'" value="'+ selectedAmount +'" style="text-align:center; width:30px"/>'+
					'<button type="button" value="'+ btnId +'" id="selectedPbtn">+</button></td>';
			str += '<td><input type="text" id="selectedPrice'+ btnId +'" value="' + mPrice + '"/></td>';
			str += '<td><button type="button" value="'+btnId+'" class="btnDel">삭제</button></td></tr>'; //html변수에 삭제버튼을 대입
			J("#list").append(str);
			
			totalPrice = parseInt(totalPrice);
			mPrice = parseInt(mPrice);
			totalPrice += mPrice;
			J('input[id=totalPrice]').attr('value', totalPrice); // 총계에 값 추가
		} else {
			alert("이미 추가한 메뉴입니다!");
		}
	});
	  
	J("#list").on("click", ".btnDel", function() {  //btnDel이라는 버튼을 눌렀을때 ->이벤트 등록
		btnId = J(this).attr('value');
		var totalPrice = J("#totalPrice").val();
		var delId = J(this).val();
		var delPrice = J("#selectedPrice"+delId).val();
		oriPrice = parseInt(totalPrice);
		minusPrice = parseInt(delPrice);
		oriPrice -= minusPrice;
		J('input[id=totalPrice]').attr('value', oriPrice);
		J(this).parent().parent().remove(); 
	});  
})		

// 메뉴 수량 선택 함수
J(function(){
	var cal;
	var inputId;

	J(document).on("click","#formPbtn",function(){ 
		inputId = J(this).attr('value');
		cal = J("#"+inputId).val();
		txtval = parseInt(cal);
		txtval += 1;
		if (txtval > 5) {
			txtval -= 1;
			alert("최대주문수량 : 5");
		} 
		J('input[id='+inputId+']').attr('value', txtval);
	});
	
	J(document).on("click","#formMbtn",function(){ 
		inputId = J(this).attr('value');
		cal = J("#"+inputId).val();
		txtval = parseInt(cal);
		txtval -= 1;
		if (txtval < 1) {
			txtval += 1;
			alert("최소주문수량 : 1");
		} 
		J('input[id='+inputId+']').attr('value', txtval);
	});
});

// 선택한 메뉴 수량 변경 함수
J(function(){
	var inputAmount, inputPrice, inputId, calPrice, txtPrice, txtval;

	J(document).on("click","#selectedPbtn",function(){ 
		inputId = J(this).attr('value');
		inputAmount = J("#selectedAmount"+inputId).val();
		inputPrice = J("#price"+inputId).val();
		totalPrice = J("#totalPrice").val();
		txtval = parseInt(inputAmount);
		txtPrice = parseInt(inputPrice);
		totalPrice = parseInt(totalPrice);
		txtval += 1;
		totalPrice += txtPrice;
		if (txtval > 5) {
			txtval -= 1;
			totalPrice -= txtPrice;
			alert("최대주문수량 : 5");
		}
		calPrice = txtval*txtPrice;
		J('input[id=selectedAmount'+inputId+']').attr('value', txtval);
		J('input[id=selectedPrice'+inputId+']').attr('value', calPrice);
		J('input[id=totalPrice]').attr('value', totalPrice); // 총계에 값 변경
	});
	
	J(document).on("click","#selectedMbtn",function(){ 
		inputId = J(this).attr('value');
		inputAmount = J("#selectedAmount"+inputId).val();
		inputPrice = J("#price"+inputId).val();
		totalPrice = J("#totalPrice").val();
		txtPrice = parseInt(inputPrice);
		txtval = parseInt(inputAmount);
		txtval -= 1;
		totalPrice -= txtPrice;
		if (txtval < 1) {
			txtval += 1;
			totalPrice += txtPrice;
			alert("최소주문수량 : 1");
		} 
		calPrice = txtval*txtPrice;
		J('input[id=selectedAmount'+inputId+']').attr('value', txtval);
		J('input[id=selectedPrice'+inputId+']').attr('value', calPrice);
		J('input[id=totalPrice]').attr('value', totalPrice); // 총계에 값 변경
	});
});

</script>

<div class="modal-header">
<h1 class="modal-title" id="modal">예약하기</h1>
	<button type="button" class="close" onclick="location.reload()" data-dismiss="modal" aria-label="close">
		<span aria-hidden="true">&times;</span>
	</button>
</div>

<div class="modal-body">
	<div id = rsvUserInfo>
		<div class="form-row">
			<div class="form-group">
				<label>이름</label>
				<input type="text" id="nameInfo" name="name" maxlength="20" readonly>
				
			</div>
			<div class="form-group">
				<label>전화번호</label>
				<input type="text" id="phoneInfo" name="phone" maxlength="20" readonly>
			</div>
		</div>
		
		<div class="form-row">
			<div class="form-group">
                <label>날짜</label>
                <!-- <p><input type="text" id="dateInfo"></p> -->
                <input type="text" id="dateInfo" value="19/07/28">
            </div>
        </div>
        
		<div class="form-row">
			<div class="form-group col-xs-6">
				<label>인원</label>
				<select name="people" class="form-control" id="peopleInfo">
					<option value="1">1명</option>
					<option value="2" selected>2명</option>
					<option value="3">3명</option>
					<option value="4">4명</option>
					<option value="5">5명</option>
					<option value="6">6명</option>
					<option value="7">7명</option>
					<option value="8">8명</option>
					<option value="9">9명</option>
					<option value="10">10명</option>
					<option value="11">11명</option>
					<option value="12">12명</option>
					<option value="13">13명</option>
					<option value="14">대여</option>
				</select>
			</div>
			<div class="form-group col-xs-6">
				<label>방문시간</label>
				<select name="time" class="form-control" id="timeInfo">
					<option value="11:00">11:00 AM</option>
					<option value="11:30">11:30 AM</option>
					<option value="12:00">12:00 PM</option>
					<option value="12:30">12:30 PM</option>
					<option value="13:00">1:00 PM</option>
					<option value="13:30">1:30 PM</option>
					<option value="14:00">2:00 PM</option>
					<option value="14:30">2:30 PM</option>
					<option value="15:00">3:00 PM</option>
					<option value="15:30">3:30 PM</option>
					<option value="16:00">4:00 PM</option>
					<option value="16:30">4:30 PM</option>
					<option value="17:00" selected>5:00 PM</option>
					<option value="17:30">5:30 PM</option>
					<option value="18:00">6:00 PM</option>
					<option value="18:30">6:30 PM</option>
					<option value="19:00">7:00 PM</option>
					<option value="19:30">7:30 PM</option>
					<option value="20:00">8:00 PM</option>
					<option value="20:30">8:30 PM</option>
					<option value="21:00">9:00 PM</option>
					<option value="21:30">9:30 PM</option>
					<option value="22:00">10:00 PM</option>
					<option value="22:30">10:30 PM</option>
					<option value="23:00">11:00 PM</option>
					<option value="23:30">11:30 PM</option>
				</select>
			</div>
		</div>
		<div class="form-group col-xs-12">
			<button type="button" class="btn btn-primary" id="chk_table">테이블 조회</button>
		</div>
		<div>
			<table id = "test1" border="0" style="width:450px"></table>
		</div>
	</div>
	
	<div id = rsvMenuInfo>
		<h1>메뉴</h1>
		<div>
			<table id = "test2"  border="1" style="width:450px"></table>
			<hr>
			<h1>주문내역</h1>	
			<table id="list"></table>
		</div>
	</div>	
</div>
<div class="modal-footer" id="nextContainer">
	<div id = rsvUserInfoBtn>
		<button type="button" class="btn btn-primary" id="next1">다음</button>
		<button type="button" class="btn btn-secondary" onclick="location.reload()" data-dismiss="modal">취소</button>
	</div>
	<div id = "rsvMenuInfoBtn">
		<div id="totalPriceBox">	
			<h1>총계 : <input type="text" id="totalPrice" value="0" style="text-align:right" readonly>원</h1>
			<hr size="100%" >
		</div>
		<div id="menuButtonBox">
			<button type="button" class="btn btn-danger" id="prev1">뒤로</button>
			<button type="button" class="btn btn-primary" id="next2">다음</button>
			<button type="button" class="btn btn-secondary" onclick="location.reload()" data-dismiss="modal">취소</button>
		</div>		
	</div>
	<div id = "rsvPaymentBtn">
		<button type="button" class="btn btn-danger" id="prev2">뒤로</button>
		<button type="button" class="btn btn-primary" id="next3">다음</button>
		<button type="button" class="btn btn-secondary" onclick="location.reload()"  data-dismiss="modal">취소</button>		
	</div>
</div>

<!-- // 메뉴 로드를 위한 테이블
	
	ID = 사용자 아이디
	FOOD_TABLE = 테이블 번호 : 여기서 지정하지 않는다. 쿼리에서 조회 후 지정 및 콜백
	FOOD_NAME = 메뉴의 이름
	FOOD_AMOUNT = 고른 메뉴의 개수, 가격 산정할 때 수식에 포함
	FOOD_PRICE = 고른 메뉴의 가격 총합 / 체크해서 보내면 합산 / 
					일반예약의 경우 20% 부과 / 취소 예약시간 1시간 전까지 가능, 그 이내 취소 불가
	FOOD_STATUS = 1. 주문 / 쿼리에서
	EXPd_TIME = 현재시간 + 20 min/ 쿼리에서
	
	로그인한 아이디		
	테이블 번호		
	메뉴이름		
	메뉴개수		
	메뉴 가격		
	주문현황(1주문,2요리시작,3완료)	
	요리완료예정시간(+20min)
				 
	1. reservation DB에 기록하는 동시에
	2. Firebase에 넣어야 함
	
	메뉴 선택에 대한 Logic 작성-->