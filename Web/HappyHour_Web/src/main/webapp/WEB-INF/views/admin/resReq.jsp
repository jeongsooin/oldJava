<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri = "http://www.springframework.org/security/tags" prefix="sec" %>
<%
String name = (String)session.getAttribute("name");
String id = (String)session.getAttribute("id");
String bMenu = (String)session.getAttribute("bMenu");
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
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
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
	margin-right:10px; width:210px; 
}

ul#work li a {
	color:white;
}

div#contents_wrap{
	width:auto; min-height:1000px; margin-left:210px;
}

section.container-fluid1 {
    width:100%;
    background:#cccccc;
}

section.container-fluid2 {
    width:100%;
    background:yellow;
} 
section.container-fluid3 {
    width:100%;
    background:#cccccc;
} 

section#contents{
	width:auto;
}

section.contents_box{
	width:20%; height:250px; float:left;
	background:#cccccc; margin-left:4%; margin-top:30px;
}

section#left {
	float:left;
	background:#eeeeee;
}

section#right{
	float:left;margin-left:30px;
	background:#cccccc;
}
table#calendar td{
	width: 60px;
 	height: 60px;
 	text-align: center;
 	font-size: 20px;
 	border:2px;
 	border-radius:8px;
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

<script>
   //플러그인 간의 충돌을 제거합니다.
     $.noConflict();
     var J = jQuery;
</script>


<script language="javascript">

J(function(){
	J(document).ready(function(){
		J('select[name=Time]').change(function(){
			J('#rTime').val(J(this).val());
			J('#rTime').attr("readonly", true);
			var sh=J('#rTime').val(J(this).val());
			//alert(sh);
		});
	});
});

</script>
<script>
J(function() {
    J( "#testDatepicker" ).datepicker({
    	changeMonth: true, 
        dayNames: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
        dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], 
        monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
    });
});
</script>

<script type="text/javascript">

J(document).ready(function() {
	
    J("p[id=reply]").each(
			function() {
				J(this).click(function(){
					 var BID = J(this).attr("bId");
			         //alert("BID: " + BID);

				//$("#reg_frm").submit();
					J("textarea[id=BREPLY]").each( 
					  function() {
						  var BGroup = J(this).attr("BGroup");
				    	  //alert("BI: " +BI);
				    	    
				    	    if(BID==BGroup) {
						    	//alert("success");
						    	var BREPLY = J(this).val(); //내용
						        var BID1 = J(this).attr("BID"); //순번
						    	var BSTEP = J("#bStep").val();
						    	var BINDENT = J("#bIndent").val();
					 	    	//var BMENU = J("#BMenu").val();
						    	var BNAME = J("#bName").val(); 
						    	var BMENU = '문의사항';
						    	//var BNAME = '홍길동';
						    	
						    	//var BID1 = J(this).val();
						    	
						    	alert("답변 : " + BREPLY);
						    	alert("순번 : " + BID1);
						    	alert("종류:" + BMENU);
						    	alert("이름:" + BNAME);
						    	
						    	if(J(this).val().length == 0){
									alert("답변을 입력해주세요.");
									J(this).focus();
									return;
								}
						    	alert("bId : "+ BID1);
						    	
						    	J.ajax({
						         	url: "guest/bReply",
						         	method:'post',  
						         	datatype:'json',
						         	data : {
						         		"BId" : BID1,
						         		"bContent" : BREPLY,
						         		"BGroup" : BGroup,
						         		"bStep" : BSTEP,
						                "bIndent" : BINDENT,
						                "bMenu" :BMENU,
						                "bName" :BNAME
						         	},
						         	success:function(data) {
						         		alert("답변이 작성되었습니다.");
						         		//J("td[name=write]").html(BREPLY); //bID 값이 view로 이동하고 그 값이 Noti_detail에 html로 입력
						         	}
						         });
						    	
						    };
						   
					  }
					)
	});
   }
);	
});
</script>

<script>
J(document).ready(function() {
	
	function replyContent() {
		 var bGroup = J("#BREPLY").attr('bGroup');
		 var bStep = J("#BREPLY").attr('bStep');
	     //var params = 'bGroup=' + bGroup + 'bStep=' + bStep;
	     
	     alert("답변:" + bGroup);
	     alert("답변:" + bStep);
	    // alert("답변:" + bParams);
	     
	     
	  //답변 내용 불러오기
/* 	    J.ajax({
	   	url: "/bRP",
	   	method:"GET",  
	   	data:params,
	   	success:function(data) {
	  		//alert("success");
	  		//$("#imgCheck").attr("src","/upload/"+ );
	  		J("#BREPLY").html(data);
	  	}
	   }); */
	}
})
</script>

<script>
J(document).ready(function() {
	
    J("td[id=toggle-control]").each(
    	function(){
			J(this).click(function(){
	    		//alert("bbb");
	    		var BID = J(this).attr("bId");	
	    		//alert("BID: "+BID);
	    		
				J("td[id=MORE]").each(
				  function(){
			    		var Bgroup = J(this).attr("BGroup");
			    		//alert("BI: " +BI);
			    		
				    if(BID==Bgroup) {
				    	//alert("success");
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

<!-- 모달 -->
 <script>
        J(document).ready(function() {
           
        	J("button[id=reviewOk]").each(
        	function(){
        	J(this).click(function() {
            	//$("#exampleModal").modal("show");
                var BID = J(this).attr("bId");
                var params = "bId=" + BID;
                alert(BID);

                J.ajax({
                	url: "/ReviewModiForm",
                	method:"GET",  
                	data:params,
                	success:function(data) {
                		J("#Noti_detail1").html(data); //bID 값이 view로 이동하고 그 값이 Noti_detail에 html로 입력
                		J("#exampleModal").modal("show"); //전체 모달이 보여짐
                	}
                });
                
                
              });
        	}
          );
            J("#close_modal").click(function() {
                J("#exampleModal").modal("hide");
            });
        });
</script> 


</head>

<body>
<div id="header_wrap">
	<header>
		<nav id="mainmenu">
			<ul id="logo">
				<li>
				<a href="">
					<img src="/img/logo1.png" alt="로고">
				</a>
				</li>
			</ul>
			
				<ul id="login">
					<li>
					<a href="/loginForm">LOGIN</a>
					</li>
					<li>
					<span>ㆍ</span>
					</li>
					<li>
					<a href="/join">JOIN</a>
					</li>
				</ul>
			<span id = "menu_icon">메뉴아이콘</span>
		</nav>
	</header>
</div>

<div id="lMenu">
	<ul class="list-group">
      <li class="list-group-item" style="background:#353535"><a href="admin/resCheck"><span style="color:white">오늘예약</span></a></li>
	  <li class="list-group-item" style="background:#353535"><a href="/resStatus"><span style="color:white">예약현황</span></a></li>
	  <li class="list-group-item" style="background:#353535"><a href="/resDB"><span style="color:white">고객관리</span></a></li>
	  <li class="list-group-item" style="background:#353535"><a href="/resMenu"><span style="color:white">메뉴등록</span></a></li>
	  <li class="list-group-item" style="background:#353535"><a href="/resSet"><span style="color:white">예약설정</span></a></li>
	  <li class="list-group-item" style="background:#353535"><a href="/resReview"><span style="color:white">리뷰</span></a></li>
	  <li class="list-group-item" style="background:#353535"><a href="/resReq"><span style="color:white">문의사항</span></a></li>
	  <li class="list-group-item" style="background:#353535"><a href="/resNoti"><span style="color:white">공지사항</span></a></li>
 	 </ul>
</div>

<div id="contents_wrap">
    <div class="tab-content" id="nav-tabContent">
    <h2>문의사항</h2>
      <hr>
      <div>
      	&nbsp;&nbsp;&nbsp;
      		<div class="container">
      		<form action="search" method="post" class="form-inline" style="float:right; margin-bottom:10px;">	
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
		<%-- <input type="hidden" id="bMenu" name="bMenu" value=<%=bMenu%>> --%>
	<table class="table table-striped" name="write_frm" width = "700" cellpadding = "0" cellspacing ="0" border="1">
		<tr>
			<th scope="col" style="text-align:center">번호</th>
			<th scope="col" style="text-align:center">작성자</th>
			<th scope="col" style="text-align:center">제목</th>
			<th scope="col" style="text-align:center">날짜</th>
			<th scope="col" style="text-align:center">답변여부</th>
			<th scope="col" style="text-align:center">더보기</th>
		</tr>
		
		<c:forEach items="${list2}" var ="dto">
		<c:if test="${0 eq dto.BStep}">
			<tr>
				<td scope="row" style="text-align:center">${dto.BId}</td>
				<td style="text-align:center">${dto.BName}</td>
				<td  style="text-align:center">
					<c:forEach begin="1" end="${dto.BIndent}">-</c:forEach> <!--댓글쓰면 들여쓰기 되는 원리 -->
			    	<c:choose>
			    	  <c:when test="${fn:length(dto.BTitle)>11 }">
			    	  		<c:out value="${fn:substring(dto.BTitle,0,10)}"/>....
			    	  </c:when>
			    	  <c:otherwise>
			    	  		<c:out value="${dto.BTitle }"/>
			    	  </c:otherwise>
			    	</c:choose>
				</td>
				<td style="text-align:center">${dto.BDate}</td>
				<td style="text-align:center"></td>
				<td style="text-align:center" id="toggle-control" bId="${dto.BId}"><input type = "button" class="btn btn-primary"  bId="${dto.BId}" name ="toggle-control" id="btn_customer_register" value="더보기" onclick =""/>
			</tr>
			</c:if>
			
			<c:if test="${0 eq dto.BStep}">
				<tr>
					   <td colspan="6" id="MORE" style="display:none" BGroup="${dto.BGroup}">
					    <div><input type="hidden" id="bIcheck" value="${dto.BGroup}"></div>
						<div>제목: ${dto.BTitle}</div>
						<div>내용: ${dto.BContent} <br/>
						</div>
					   </td>
				</tr>
			</c:if>
			
			<c:if test="${1 eq dto.BStep}">
			<tr>
					<td colspan="6" id="MORE" name="write" style="display:none" BID="${dto.BId}" BStep="${dto.BStep}" BGroup="${dto.BGroup}">
					답변:
					    <div><input type="hidden" id="bIck" value="${dto.BId}"></div>
					    <div><input type="hidden" id="bName" value=<sec:authentication property ="name"/>></div>
					    <div><input type="hidden" id="bStep" value="${dto.BStep}"></div>
					    <div><input type="hidden" id="bGroup" value="${dto.BGroup}"></div>
						<div><input type="hidden" id="bStep" value="${dto.BStep}"></div>
						<div><input type="hidden" id="bIndent" value="${dto.BIndent}"></div>
						<input type="hidden" id="BMenu" name="bMenu" size="30" value="문의사항">
						   	  <c:out value="${dto.BContent}"/>					   
							<%-- <div name="BREPLY" BStep="${dto.BStep}" BGroup="${dto.BGroup}" id="BREPLY" >${dto.BContent}</div> --%>
						<%-- <input type = "button" style="margin-top:10px; float:right; margin-right:10px" class="btn btn-primary"  bId="${dto.BId}" name ="toggle-control" id="btn_customer_register" value="수정" onclick =""/> --%>
						<%-- <p id="modi" bId="${dto.BId}"><input type = "button" id="replyModi" style="margin-top:10px; float:right; margin-right:10px" class="btn btn-primary"  BGroup="${dto.BGroup}" name ="toggle-control" id="btn_customer_register" value="수정" onclick =""></p> --%>
						<button type="button" style="float:right"class="btn btn-primary" bId="${dto.BId}" id="reviewOk" id="modal_show1" data-toggle="modal" data-target="#exampleModalCenter">수정하기</button>
					</td>
				</tr>
				</c:if>
				
				<c:if test="${0 eq dto.BStep}">
						<tr>
						<td colspan="6" id="MORE" name="write" style="display:none" BID="${dto.BId}" BGroup="${dto.BGroup}">
						답변쓰기: <br/>
						    <div><input type="hidden" id="bIck" value="${dto.BGroup}"></div>
						    <div><input type="hidden" id="bName" value=<sec:authentication property ="name"/>></div>
						    <div><input type="hidden" id="bIck" value="${dto.BId}"></div>
						    <div><input type="hidden" id="bGroup" value="${dto.BGroup}"></div>
							<div><input type="hidden" id="bStep" value="${dto.BStep}"></div>
							<div><input type="hidden" id="bIndent" value="${dto.BIndent}"></div>
							
							<p id="replycontent" BID="${dto.BId}" BGroup="${dto.BGroup}"><textarea name="BREPLY" BID="${dto.BId}" BGroup="${dto.BGroup}" id="BREPLY" id="ir1" cols="100" class="form-control col-11" rows="5"></textarea></p>
							<%-- <input type = "button" style="margin-top:10px; float:right; margin-right:10px" class="btn btn-primary"  bId="${dto.BId}" name ="toggle-control" id="btn_customer_register" value="수정" onclick =""/> --%>
							<p id="reply" bId="${dto.BId}"><input type = "button" id="replyWrite" style="margin-top:10px; float:right; margin-right:10px" class="btn btn-primary"  BGroup="${dto.BGroup}" name ="toggle-control" id="btn_customer_register" value="답변" onclick =""></p>
						</td>
						<!-- <p>소중한 리뷰 감사드립니다.
		                    	더나은 서비스를 제공할 수 있도록 언제나 최선을 다하겠습니다.</p> -->
					</tr>
			</c:if>
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
	</div>
      </div>
    </div>
</div>

<div id="footer_wrap"> 
	<section id="footer">
		<ul>
			<li>
				<p>"(주)웹스리퍼블릭 / 대표자 : 주필규 / 사업자등록번호 : "
					<span class="footer_number">607-88-00037</span>
				</p>
				<address>서울특별시 마포구 합정동 202호</address>
				<span>
					"전화 / "
					<a href="tel:1800-9356">1800-9365</a>
				</span>
				<span>
					"팩스 / "
					<a href="#;">0606-300-9354</a>
				</span>
			</li>
			<li>
				<span class="copyright">COPYRIGHT ⓒWEB'S REPUBLIC INC> ALL RIGHTS RESERVED.</span>
			</li>
		</ul>
	</section>
</div>	
<!-- 수정하기 모달 -->
<div class="modal fade" id="exampleModalCenter" id="exampleModal1" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
     <form action="/Rwrite" method="post" id="reg_frm"  enctype="multipart/form-data">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalCenterTitle">수정하기</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="Noti_detail1">
      </div>
      <div class="modal-footer">
      </div>
      </form>
    </div>
  </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>	


</body>
</html>