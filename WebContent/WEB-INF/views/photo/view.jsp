<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/common/head.jsp"></jsp:include>
</head>
<body>
<div class="container">
	<h2 align="center">수정페이지</h2>
	<form action="/photo/update" method="POST" enctype="multipart/form-data">
		<table class="table table-border table-hover"> 
			<tr align="center">
				<th>번호</th>
				<td><input readonly value="${pb.pbNum}" name="pbNum"></td>
			</tr>
			<tr align="center">
				<th>제목</th>
				<td><input value="${pb.pbTitle}" name="pbTitle" width="300"></td>
			</tr>
			<tr align="center">
				<th>내용</th>
				<td><textarea name="pbContent" style="width:100%;border:1;overflow:visible;text-overflow:ellipsis;" rows="5">${pb.pbContent}</textarea></td>
			</tr>
			<tr align="center">
				<th>사진이름</th>
				<td><input readonly value="${pb.pbPhotoName}"></td>  
			</tr>
			<tr align="center"> 
				<th colspan="2" align="center">사진</th>
			</tr>
			<tr>
				<td colspan="2"><img src="/resources/upload/${pb.pbPhotoPath}" width="1000" align="middle"></td> 
			</tr>
			<tr>
				<td><input type="file" name="pbfile"></td>
			</tr>
		</table>
		<button>수정하기</button><button type="button" onclick="location.href='/photo/list'">취소</button>
	</form>
</div>
${pb}
</body>
</html>