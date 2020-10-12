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
<img src="/resources/upload/1.jfif">
<div>파일 업로드 프로젝트!</div><br>
${requestScope.msg}<br>
<a href="/photo/list">게시판가기</a>
</body>
</html>