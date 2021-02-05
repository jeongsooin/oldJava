<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>Insert title here</title>
<!-- <script src="http://code.jquery.com/jquery.js"></script> -->
 <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
     <!-- jQuery UI 라이브러리 js파일 -->
	<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
    <!-- Bootstrap CSS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    	<!-- jQuery UI CSS 파일 -->
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
	<!-- jQuery 기본 js 파일 -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>  
<style>
#exampleModalCenter {
	z-index:99999;
}

#exampleModalLong {
	z-index:999999;
}

#bd-example-modal-xl {
	z-index:999999;
}

.starR{
  background: url('http://miuu227.godohosting.com/images/icon/ico_review.png') no-repeat right 0;
  background-size: auto 100%;
  width: 30px;
  height: 30px;
  display: inline-block;
  text-indent: -9999px;
  cursor: pointer;
}
.starR.on{background-position:0 0;}

</style>

</head>


<script>
   //플러그인 간의 충돌을 제거합니다.
     $.noConflict();
     var J = jQuery;
</script>

<!-- 모달 -->
 <script>
        J(document).ready(function() {
           
        	//$("p[id=review]").each(
            // function(){
	        	J("#modal_show1").click(function() {
	            	$("#exampleModal1").modal("show");
	            });
	            J("#close_modal1").click(function() {
	                J("#exampleModal1").modal("hide");
	            });
            //}
          //)
        });
</script>



<!-- 모달1 -->
<!--  <script>
            J(document).ready(function() {
        	
            	J("#modal_show1").click(function() {
	        		
	                 alert("reviewWrite");

 	                  J.ajax({
	                 	url: "member/reviewRwrite",
	                 	method:"GET",  
	                 	data:'text',
	                 	success:function(data) {
	                 		alert("success");
	                 		J("#review_detail1").html(data); //bID 값이 view로 이동하고 그 값이 Noti_detail에 html로 입력
	                 		J("#exampleModal1").modal("show"); //전체 모달이 보여짐
	                 	}
	                 })  
	            }); 
	        	
	        	
	            J("#close_modal1").click(function() {
	                J("#exampleModal1").modal("hide");
	            });
        });
</script>
 -->
<!-- 모달2 -->
 <script>
        J(document).ready(function() {
        	//$("p[id=review]").each(
            // function(){
	        	J("#modal_show2").click(function() {
	        		
	            	//$("#exampleModal2").modal("show");
	            	 //var BID = $(this).attr("bId");
	                 //var params = "bId=" + BID;
	                 //alert("review");

 	                 J.ajax({
	                 	url: "guest/review",
	                 	method:"GET",  
	                 	data:'text',
	                 	success:function(data) {
	                 		//alert("success");
	                 		J("#review_detail").html(data); //bID 값이 view로 이동하고 그 값이 Noti_detail에 html로 입력
	                 		J("#exampleModal2").modal("show"); //전체 모달이 보여짐
	                 	}
	                 }) 
	            }); 
	        	
	        	
	            J("#close_modal2").click(function() {
	                J("#exampleModal2").modal("hide");
	            });
            //}
          //)
        });
</script>


<script type="text/javascript">
function form_check() {
	
	if(J('#bContent').val().length == 0){
		alert("내용을 입력해주세요.");
		J('#bContent').focus();
		return;
	}	
	//$("#reg_frm").submit();
 	submit_ajax();
}

	function submit_ajax() {
		
		//alert("aaa");
		var form = new FormData(document.getElementById('reg_frm'))
		
	 	J.ajax({
	       	url: 'member/Rwrite',
	       	method:'POST',  
	       	//dataType:'text',
	       	//data: $('#reg_frm').serialize(),
	       	processData:false,
	 		contentType:false,
	 		data:form,
	       	success:function(data) {
	      		 alert("리뷰가 작성되었습니다.");
	      		  //window.close();

	      		//$("#imgCheck").attr("src","/upload/"+ );
	      	}
	     });
      }
</script>

<!-- 별점 -->
<script>
J('.starRev span').click(function(){
	  J(this).parent().children('span').removeClass('on');
	  J(this).addClass('on').prevAll('span').addClass('on');
	  return false;
	
	});
</script>

<!-- 별점선택메뉴 -->
<script>
J(function(){
	J(document).ready(function(){
		J('select[name=StarO]').change(function(){
			J('#Star').val($(this).val());
			J('#Star').attr("readonly", true);
			var sh1=$('#Star').val($(this).val());
			//alert(sh1);
		});
	});
});
</script>

<!-- 리뷰쓰기버튼 -->
<script>
J(function(){
	J(document).ready(function(){
		//alert("aaa");
		var idck = J('#idck').val();
		//alert("bbb");
 		//alert(idck); 
 		
 		J("button[id=reviewOk]").each(
			function(){
				if(idck != null && idck != "anonymousUser") {	
					//J('#reviewOk').css('display','block');
					    J(this).show();
				} else {
						J(this).hide();		
				}
	        }
	)
	});
});
</script>

<!-- 메뉴선택 -->
<script language="javascript">
J(function(){
	J(document).ready(function(){
		J('select[name=Menu]').change(function(){
			J('#Menu_Name').val($(this).val());
			J('#Menu_Name').attr("readonly", true);
			var sh=$('#Menu_Name').val($(this).val());
			//alert(sh);
		});
	});
});
</script>


<body>
            <input type="hidden" id="idck" value=<sec:authentication property ="name"/>>
			<h1>${imgFile.MENU_NAME }</h1>
		    <hr>
			<p>${imgFile.MENU_DESCRIPTION }</p>
			<p>리뷰: ★★★★☆</p> 
			<p id="review">
				<button type="button" class="btn btn-primary" id="reviewOk" id="modal_show1" data-toggle="modal" data-target="#exampleModalCenter">리뷰쓰기
				</button>
				<button type="button" class="btn btn-primary" id="modal_show2" data-toggle="modal" data-target="#bd-example-modal-xl">리뷰보기 
				</button> 
			</p>

<!-- Modal 리뷰작성-->
<div class="modal fade" id="exampleModalCenter" id="exampleModal1" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
     <form action="/Rwrite" method="post" id="reg_frm"  enctype="multipart/form-data">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalCenterTitle">리뷰</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="Noti_detail1">
         <input type="hidden" id="bMenu" name="bMenu" size="30" value="리뷰">
        <input type="hidden" id="bName" name="bName" size="30" value=<sec:authentication property = "name"/>>
        작성자: <sec:authentication property = "name"/> <br/>
<!-- 	        <div class="starRev" name="Star" id="Star">
	        <input type="hidden" id="Star" name="Star" size="30" value="1">
	        별점:
			  <span class="starR on" value="1">별1</span>
			  <span class="starR" value="2">별2</span>
			  <span class="starR" value="3">별3</span>
			  <span class="starR" value="4">별4</span>
			  <span class="starR" value="5">별5</span>
			</div> -->
		<input type="hidden" id="Star" name="Star" size="10" value="5">
                  별점: <select id="StarO" name="StarO" >
						<option value=1 name="★☆☆☆☆">★☆☆☆☆</option>
						<option value =2 name="★★☆☆☆">★★☆☆☆</option>
						<option value=3 name="★★★☆☆">★★★☆☆</option>
						<option value=4 name="★★★★☆">★★★★☆</option>
						<option value=5 selected="selected" name="★★★★★">★★★★★</option>
				  </select>
        </input>
        <br/>
        메뉴선택: <input type="hidden" id="Menu_Name" name="Menu_Name" size="30" value="양송이카레2">
                  <select id="Menu" name="Menu" >
						<option value="양송이카레1" name="1">양송이카레1</option>
						<option value ="양송이카레2" selected="selected" name="2">양송이카레2</option>
						<option value="양송이카레3" name="3">양송이카레3</option>
						<option value="양송이카레4" name="4">양송이카레4</option>
				  </select>
				  </input>
	   <br/>
        리뷰쓰기: <br/>
        <textarea name="bContent" id="bContent" id="ir1" cols="100" class="form-control col-11" rows="15"></textarea>
      	<input type="file" class="btn btn-danger" name="img" id="img" accept=".jpg,.jpeg,.png,.gif,.bmp">
        <input type="hidden" name="img_Extension"  id="img_Extension" value="${menuWrite.img_Extension}">
      </div>
      <div class="modal-footer">
        <input type = "button" class="btn btn-primary" id="btn_customer_register" value="입력" onclick ="form_check()"/>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
      </div>
      </form>
    </div>
  </div>
</div>


<!-- Modal 리뷰리스트 -->
<!-- <div class="modal fade" id="exampleModalLong" id="exampleModal2" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content"> -->

<div class="modal fade" id="bd-example-modal-xl" id="exampleModal2" tabindex="-1" role="dialog" aria-labelledby="myExtraLargeModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle"><h2 style="font-weight:bold; float:center">REVIEW</h2> 
        &nbsp;&nbsp;
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
        </h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="review_detail">
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>	
	

</body>
</html>