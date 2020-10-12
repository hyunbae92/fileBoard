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
<div class="container">
<a href="/photo/list"><div><h2>사진게시판</h2></div></a><br>
<a href="/photo/write"><button class="btn btn-primary">글쓰기</button></a><br>
<div>
	<form method="GET" action="/photo/list">
		제목 : <input type="text" name="pbTitle" id="pbTitle" value="${param.pbTitle}"><br>
		내용 : <input type="text" name="pbContent" id="pbContent" value="${param.pbContent}"><br>
		일자 : <input type="date" name="credat1" id="credat1" value="${param.credat1}"> - 
			  <input type="date" name="credat2" id="credat2" value="${param.credat2}">
				<button class="btn btn-primary" type="button" onclick="resetSearch()">초기화</button><br>
		<button class="btn btn-primary">검색</button>
	</form>
</div>
<br>
<form action="/photo/delete" method="POST">
	<c:if test="$('input[type=checkbox][checked=true]').length != 0">
	<button class="btn btn-primary">삭제</button>
	</c:if> 
	<table class="table table-bordered table-hover">
		<thead>
			<tr>
				<th><input type="checkbox" id="allchk" onchange="allChecked(this)"></th>
				<th data-col="pbNum">번호</th>
				<th data-col="pbTitle">제목</th>
				<th data-col="pbContent">내용</th>
				<th data-col="pbPhotoPath">파일미리보기</th>
				<th data-col="credat">작성일자</th>
				<th data-col="cretim">작성시간</th>
			</tr>
		</thead>
		<c:if test="${empty pbList}">
		<tr>
			<td colspan="6">
		</tr>
		</c:if>
		<c:if test="${!empty pbList}">
			<c:forEach items="${pbList}" var="pb"> 
				
				<tr onclick="update(${pb.pbNum})">
					<th><input type="checkbox" name="pbNums" value="${pb.pbNum}"></th>
				
					<td id="pbNum">${pb.pbNum}</td>
					<td>${pb.pbTitle}</td>
					<td>${pb.pbContent}</td>
					<c:if test="${!empty pb.pbPhotoPath}">
						<td><a href="/resources/upload/${pb.pbPhotoPath}" target="blank"><img src="/resources/upload/${pb.pbPhotoPath}" width="100"></a></td>
					</c:if> 
					<c:if test="${empty pb.pbPhotoPath}">
						<td>사진없음</td>
					</c:if> 
					<td>${pb.credat}</td> 
					<td>${pb.cretim}</td> 
				</tr> 
			</c:forEach>
		</c:if>
	</table>
</form>
<c:if test="${page.startBlock == 1}"></c:if>
<c:if test="${page.startBlock > 1}">
<a href="/photo/list?pageVO.pageNum=${page.startBlock-1}&pbTitle=${param.pbTitle}&pbContent=${param.pbContent}&credat1=${param.credat1}&credat2=${param.credat2}"> ◀ </a>
</c:if>
<c:if test="${page.pageNum == 1}"></c:if>
<c:if test="${page.pageNum > 1}">
<a href="/photo/list?pageVO.pageNum=${page.pageNum-1}&pbTitle=${param.pbTitle}&pbContent=${param.pbContent}&credat1=${param.credat1}&credat2=${param.credat2}"> ◁ </a>
</c:if>
<c:forEach begin="${page.startBlock}" end="${page.endBlock}" var="idx">
	<a href="/photo/list?pageVO.pageNum=${idx}&pbTitle=${param.pbTitle}&pbContent=${param.pbContent}&credat1=${param.credat1}&credat2=${param.credat2}">[${idx}]</a>
</c:forEach>
<c:if test="${page.pageNum == page.maxBlock}"></c:if>
<c:if test="${page.pageNum < page.maxBlock}">
<a href="/photo/list?pageVO.pageNum=${page.pageNum+1}&pbTitle=${param.pbTitle}&pbContent=${param.pbContent}&credat1=${param.credat1}&credat2=${param.credat2}"> ▷ </a>
</c:if>
<c:if test="${page.endBlock == page.maxBlock}"></c:if>
<c:if test="${page.endBlock < page.maxBlock}">
<a href="/photo/list?pageVO.pageNum=${page.endBlock+1}&pbTitle=${param.pbTitle}&pbContent=${param.pbContent}&credat1=${param.credat1}&credat2=${param.credat2}"> ▶ </a>
</c:if>

</div>
<script>
function resetSearch(){
	$('#pbTitle').val('');
	$('#pbContent').val('');
	$('#credat1').val('');
	$('#credat2').val('');
}
function allChecked(obj){
	var chkObjs = document.querySelectorAll('input[name=pbNums]');
	for(var i=0;i<chkObjs.length;i++){
		chkObjs[i].checked = obj.checked;
	}
}
function update(pbNum){
	if(event.target.type == 'checkbox') return;
	location.href = '/photo/view?pbNum=' + pbNum;
}
</script>
</body>
</html>