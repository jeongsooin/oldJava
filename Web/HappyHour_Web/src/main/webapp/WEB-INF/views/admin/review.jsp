<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<!-- jQuery UI CSS 파일 -->
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
	<!-- jQuery 기본 js 파일 -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>  
</head>

<script>
   //플러그인 간의 충돌을 제거합니다.
     $.noConflict();
     var J = jQuery;
</script>
<script>
J(document).ready(function() {
	
    J("td[id=toggle-control]").each(
    	function(){
			J(this).click(function(){
	    		//alert("bbb");
	    		var bID = J(this).attr("bId");	
	    		//alert("BID: "+BID);
	    		
				J("td[id=MORE]").each(
				  function(){
			    		var bI = J(this).attr("bI");
			    		//alert("BI: " +BI);
			    		
				    if(bID==bI) {
				    	//alert("bi=" + bI);
                      J(this).toggle();
                      
                      //alert("1");
                      
                      J("input[id=imgStar]").each(
        			  	      function(){
        				      var imgStar = J(this).val();
        				      var bISTAR = J(this).attr("bISTAR");
                              //alert("imgStar:"+ imgStar);
                              //alert("bISTAR:" + bISTAR);
                              
                              if(bID==bISTAR) {
                            	  //alert("imgStar:"+ imgStar);
                                  //alert("bISTAR:" + bISTAR);
                                  
                             J("div[id=score]").each(
                    		   
                               function(){
                                  
                               if(imgStar==1) {
        				    	   //alert("★☆☆☆☆");
        				    	   J(this).text("별점: ★☆☆☆☆");
        				    	  //J("#score").html(★☆☆☆☆);
        				        }else if(imgStar==2) {
         				    	   //alert("★★☆☆☆");
         				    	   J(this).text("별점: ★★☆☆☆");
         				    	   //document.getElementById("score").innerHTML = "★★☆☆☆";
         				    	  //J("#score").html(★★☆☆☆);
         				        }else if(imgStar==3) {
         				    	   //alert("★★★☆☆");
         				    	   J(this).text("별점: ★★★☆☆");
         				    	  //J("#score").html(★★★☆☆);
         				        }else if(imgStar==4) {
          				    	   //alert("★★★★☆");
          				    	   J(this).text("별점: ★★★★☆");
          				    	  //J("#score").html(★★★★☆);
          				        }else if(imgStar==5) {
          				    	   //alert("★★★★★");
          				    	   J(this).text("별점: ★★★★★");
          				    	  //J("#score").html(★★★★★);
          				        }  
                    			  	   }
                    			  )
                    			  	     
                              }
        				       
        				      }
        				      );
				      //alert("2");
				  /*     J.ajax({
				         	url: "guest/review",
				         	method:'post',  
				         	datatype:'json',
				         	data : {
				         		"bI" : bI,
				         	},
				         	success:function(data) {
				         		//alert("success");
				         	}
				         }); */
				            
				      return;
				    }
				    
				  }
				  
				 )
			})
    	}
	)
});
</script>

<!--답변은 읽기만 가능 -->
<script language="javascript">
J(function(){
	J(document).ready(function(){
			J('#bReply').attr("readonly", true);
	});
});
</script>

<script>
J(document).ready(function() {
	J("td[id=toggle-control]").each(
	    	function(){
				J(this).click(function(){
					
	//				J("div[id=IdOk]").each(
	//					function(){

								J("td[id=name1]").each(
									function(){
								       //var id = J(this).val();
								       var name = J(this).attr('writer');
								       var idck = J('#idck').val();
								        //alert("작성자" + name);
										//alert("로그인세션" + idck);
								       
								       if(name == idck) {
								    	   J("div[id=IdOk]").each(
											 function(){
											    J(this).show();
													})
										} else {
											//alert("2");
											J("div[id=IdOk]").each(
											   function(){
											     J(this).hide();
													});	
										}
								       //alert("작성자" + id);
								       //alert("작성자" + name);
								});
								//var name = $("#name1").attr('writer');
								//var idck = J('#idck').val();
						 		
							  // alert("작성자" + name);
							   //alert("로그인세션" + idck);
								
							/* if(name == idck) {
								alert("작성자" + id);
								alert("로그인세션" + idck); 
								J(this).show();
							} else {
								J(this).hide();
							} */
							
				//    }
				//	)
	})
}			

)
})
</script>

<!-- 토글-->
<script>
J(document).ready(function() {
    J("button[id=toggle-control1]").each(
    	function(){
			J(this).click(function(){
	    		//alert("bbb");
	    		var BID = J(this).attr("bId");	
	    		//alert("BID: "+BID);
				
	    		J("td[id=MORE1]").each(
				  function(){
					    var bI = J(this).attr("bI");
			    		//alert("BI: " +bI);
			    		
				    if(BID==bI) {
				    	//alert("same");
				        J(this).toggle();
				        return;
				    }
				  }
				 )
			})
    	}
	)
});
</script>

<!-- 폼체크 -->
<script type="text/javascript">
 function modi_check() {
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
			
			//alert("BID:" + BId);
			//alert("BCONTENT:" + BContent);
		
			 	J.ajax({
			       	url: '/RVModi',
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

<body>


	<table class="table table-striped" name="write_frm" width = "700" cellpadding = "0" cellspacing ="0" border="1">
		<tr>
			<th scope="col" style="text-align:center">번호</th>
			<th scope="col" style="text-align:center">메뉴명</th>
			<th scope="col" style="text-align:center">작성자</th>
			<th scope="col" style="text-align:center">내용</th>
			<th scope="col" style="text-align:center">날짜</th>
			<th scope="col" style="text-align:center">더보기</th>
		</tr>
		
		<%-- <c:forEach items="${list2}" var ="dto" varStatus="status"> --%>
		<c:forEach items="${list2}" var ="dto" varStatus="status">
			<tr>
				<td scope="row" style="text-align:center">${dto.BId}</td>
				<c:if test="${0 eq dto.BStep}">
					<td scope="row" style="text-align:center">${dto.MENU_NAME}</td>
				</c:if>
				<c:if test="${1 le dto.BStep}">
					<td scope="row" style="text-align:center">답변</td>
				</c:if>
				<td style="text-align:center" id="name1" writer="${dto.BName}">
					<input type="hidden" id="idck" value=<sec:authentication property ="name"/>>
					<input type="hidden" id="id" value="${dto.BName}">
					${dto.BName}
				</td>
				<td style="text-align:center">
					<c:forEach begin="1" end="${dto.BIndent}">-</c:forEach> <!--댓글쓰면 들여쓰기 되는 원리 -->
			    	<c:choose>
			    	  <c:when test="${fn:length(dto.BContent)>11 }">
			    	  		<c:out value="${fn:substring(dto.BContent,0,10)}"/>....
			    	  </c:when>
			    	  <c:otherwise>
			    	  		<c:out value="${dto.BContent }"/>
			    	  </c:otherwise>
			    	</c:choose>
				</td>
				<td style="text-align:center"> 
				 ${dto.BDate} 
 <%-- 					<c:choose>
					   <c:when test="${fn:length(dto.BDate)>10 }">
							<c:out value="${fn:substring(dto.BDate,0,9)}"/>
					   </c:when>
					</c:choose>  --%>
				</td>
				
				<td style="text-align:center" id="toggle-control" bId="${dto.BId}"><input type = "button" class="btn btn-primary"  bId="${dto.BId}" name ="toggle-control" id="btn_customer_register" value="더보기" onclick =""/>
				</td>
				
			</tr>
			<tr>
			   <td colspan="6" id="MORE" style="display:none" bI="${dto.BId}">
			    <input type="hidden" id="bIcheck" value="${dto.BId}" bI="${dto.BId}">
				 <c:if test="${0 eq dto.BStep}">
				    <input type="hidden" id="imgStar" value="${dto.STAR}" bISTAR="${dto.BId}">
					<div id="score"> ${dto.STAR}
					   </div>
				 </c:if> 
				<div>내용: ${dto.BContent} <br/>
				   <img src="/upload/${dto.IMG_EXTENSION}" id="img01" class="d-block w-50" alt="${dto.MENU_NAME}">
				</div>
				<div id ="IdOk">
					<%-- <input type = "button" class="btn btn-primary"  bId="${dto.BId}" name ="toggle-control" id="btn_customer_register" value="수정" onclick =""/> --%>
					<button type="button" class="btn btn-primary" bId="${dto.BId}" id="toggle-control1" id="modal_show1" data-toggle="modal" onClick="">수정하기</button>
					<input type = "button" class="btn btn-primary"  bId="${dto.BId}" name ="toggle-control" id="btn_customer_register" value="삭제" onclick =""/>
			    </div>
			   </td>
			</tr>
			
			<!-- 본인이 쓴 글 수정하기 -->
			<tr>
				<td colspan="6" id="MORE1" name="write" style="display:none" bI="${dto.BId}">
				수정내용:
				    <div><input type="hidden" id="bIck" value="${dto.BId}"></div>
				    <div><input type="hidden" id="bName" value=<sec:authentication property ="name"/>></div>
				    <div><input type="hidden" id="bGroup" value="${dto.BGroup}"></div>
					<div><input type="hidden" id="bStep" value="${dto.BStep}"></div>
					<div><input type="hidden" id="bIndent" value="${dto.BIndent}"></div>
					<input type="hidden" id="bMenu" name="bMenu" size="30" value="리뷰">
                    <input type="hidden" id="bName" name="bName" size="30" value=<sec:authentication property = "name"/>>
                     <input type="hidden" id="bId" name="bId" size="30" value="${dto.BId}">
					  <textarea name="bContent" id="bContent" id="ir1" cols="100" class="form-control col-11" rows="15" >${dto.BContent}</textarea>			   
						<%-- <div name="BREPLY" BStep="${dto.BStep}" BGroup="${dto.BGroup}" id="BREPLY" >${dto.BContent}</div> --%>
					<%-- <input type = "button" style="margin-top:10px; float:right; margin-right:10px" class="btn btn-primary"  bId="${dto.BId}" name ="toggle-control" id="btn_customer_register" value="수정" onclick =""/> --%>
					<%-- <p id="modi" bId="${dto.BId}"><input type = "button" id="replyModi" style="margin-top:10px; float:right; margin-right:10px" class="btn btn-primary"  BGroup="${dto.BGroup}" name ="toggle-control" id="btn_customer_register" value="수정" onclick =""></p> --%>
					<%-- <button type="button" style="float:right"class="btn btn-primary" bId="${dto.BId}" id="reviewOk" id="modal_show1" data-toggle="modal" data-target="#exampleModalCenter">수정</button> --%>
					<input type = "button" style="float:right" class="btn btn-primary" id="btn_customer_register" value="수정" onclick ="modi_check()"/>
				</td>
				</tr>
		</c:forEach>
		
		<nav aria-label="page navigation example">
		<div class="pagination">
		<tr>
			<td colspan="6" >
			<!-- 처음 -->
			<c:choose>
				<c:when test = "${(page.curPage -1) < 1}">
					[ &lt;&lt; ]
				</c:when>
				<c:otherwise>
					<a href = "list?page=1">[ &lt;&lt; ]</a>
				</c:otherwise>
			</c:choose>
			
			<!-- 이전 -->
			<c:choose>
				<c:when test="${(page.curPage -1) < 1 }">
					[ &lt;]
				</c:when>
				<c:otherwise>
					<a href="list?page=${page.curPage -1 }">[ &lt;]</a>
				</c:otherwise>
			</c:choose>
			
			<!-- 개별 페이지 -->
			<c:forEach var = "fEach" begin="${page.startPage }" end = "${page.endPage }" step="1">
				<c:choose>
				<c:when test = "${page.curPage == fEach }">
					[${fEach}] &nbsp;
				</c:when>
				<c:otherwise>
					<a href = "list?page=${fEach}">[${fEach} ]</a> &nbsp;
				</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<!-- 다음 -->
			<c:choose>
			<c:when test = "${(page.curPage + 1) > page.totalPage }">
				[&gt;]
			</c:when>
			<c:otherwise>
				<a href = "list?page=${page.curPage +1 }">[&gt;]</a>
			</c:otherwise>
			</c:choose>
			
			<!-- 끝 -->
			<c:choose>
			<c:when test="${page.curPage == page.totalPage }">
				[&gt;&gt;]
			</c:when>
			<c:otherwise>
				<a href = "list?page=${page.totalPage}">[&gt;&gt;]</a>
			</c:otherwise>
			</c:choose>
		</tr>
		</div>
		</nav>
	</table>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>	
	

</body>
</html>