<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/common/head.jsp"></jsp:include>
</head>
<body>
<form method="POST" action="/photo/write" enctype="multipart/form-data">
	제목 : <input type="text" name="pbTitle"><br>
	내용 : <textarea name="pbContent"></textarea><br>
	사진 : <input type="file" name="pbfile"><br>
	<button>글쓰기</button>
</form>
</body>
</html>