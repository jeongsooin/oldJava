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

<!-- 폼체크 -->
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
		//J("button[id=reviewOk]").each(
        //	function(){
		//var BId = J("#bId").val();
			var BId = J("#bId").val();
			var BContent = J("#bContent").val();
			
			alert("BID:" + BId);
			alert("BCONTENT:" + BContent);
		
			 	J.ajax({
			       	url: '/ReviewModi',
			       	method:'POST',  
			       	datatype:'json',
		         	data : {
		         		"bId" : BId,
		         		"bContent" : BContent,
		         		
		         	},
			       	success:function(data) {
			      		 alert("수정되었습니다.");
			      		  //window.close();
		
			      		//$("#imgCheck").attr("src","/upload/"+ );
			      	}
			     });
        
        	//}
        	//)
      }
</script>


</head>



<body>

	     <input type="hidden" id="bMenu" name="bMenu" size="30" value="리뷰">
         <input type="hidden" id="bName" name="bName" size="30" value=<sec:authentication property = "name"/>>
         <input type="hidden" id="bId" name="bId" size="30" value="${dto.BId}">
        번호: ${dto.BId} <br/>
        작성자: <sec:authentication property = "name"/> <br/>
        <%-- 제목: <textarea name="bTitle" id="bTitle" id="ir1" cols="100" class="form-control col-11" rows="1"> ${dto.BTitle} </textarea> <br/> --%>
		수정내용:
		<textarea name="bContent" id="bContent" id="ir1" cols="100" class="form-control col-11" rows="15"> ${dto.BContent } </textarea>
		<input type = "button" class="btn btn-primary" id="btn_customer_register" value="수정" onclick ="form_check()"/>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
 <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>	
	

</body>
</html>