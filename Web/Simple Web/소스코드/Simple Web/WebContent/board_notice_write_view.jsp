<%@page import="com.study.jsp.member.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% 
	String id = (String)session.getAttribute("id");
	MemberDAO dao = MemberDAO.getInstance();
	MemberDTO dto = dao.getMember(id);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script> 
	<script src="https://cdn.ckeditor.com/4.11.3/standard/ckeditor.js"></script>
</head>
<body style='background-color:transparent'>
	<form action ="writeBoardNotice.do" method="post">
		<div class="form-group row">
    		<div class="col-sm-5">
    			<input name="bName" class="form-control" type="text" placeholder="<%=session.getAttribute("name")%>" readonly>
    		</div>
  		</div>
  		
  		<div class="form-group row">
    		<div class="col-sm-5">
      			<input name="bTitle" class="form-control" type="text" placeholder="글제목">
    		</div>
  		</div>
  		
  		<div class="form-group row">
    		<div class="col-sm-5">
	    		<textarea class="form-control" rows="10" name="bContent" id="bContent"></textarea>
	    		<script>
	    			CKEDITOR.replace('bContent');
	    		</script>
    		</div>
  		</div>
  		
  		<div class="form-group row">
  			<div class="col-sm-5">
	    		<input class="btn btn-outline-info" type="submit" value="입력">
				<a class="btn btn-outline-secondary" href="noticelist.do" role="button">목록보기</a>
			</div>  		
		</div>
	</form>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>