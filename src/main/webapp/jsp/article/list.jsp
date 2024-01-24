<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
List<Map<String, Object>> articleRows = (List<Map<String, Object>>) request.getAttribute("articleRows");
int cPage = (int) request.getAttribute("page");
int totalPage = (int) request.getAttribute("totalPage");
int totalCnt = (int) request.getAttribute("totalCnt");
int itemsInAPage = (int) request.getAttribute("itemsInAPage");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"">
<title>게시물 목록</title>
</head>
<body>
	<div>
		<a href="../home/main">메인으로 이동</a>
	</div>
	<div>
		<a href="write">글쓰기</a>
	</div>

	<h2>게시물 목록</h2>

	<table style="border-collapse: collapse; border-color: green"
		border="1px">
		<thead>
			<tr>
				<th>번호</th>
				<th>작성날짜</th>
				<th>제목</th>
				<th>수정</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (Map<String, Object> articleRow : articleRows) {
			%>
			<tr style="text-align: center;">
				<td><%=articleRow.get("id")%></td>
				<td><%=articleRow.get("regDate")%></td>
				<td><a href="detail?id=<%=articleRow.get("id")%>"><%=articleRow.get("title")%></a></td>
				<td><a href="modify?id=<%=articleRow.get("id")%>">수정</a></td>
				<td><a href="doDelete?id=<%=articleRow.get("id")%>">del</a></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>

	<style type="text/css">
.page {
	font-size: 1.4rem;
}

.page>a {
	color: black;
	text-decoration: none;
}

.page>a.cPage {
	color: red;
	text-decoration: underline;
}
</style>

<div class="page">
	<% if (totalCnt != 0) {%>
	
	<% int pageCount = totalCnt / itemsInAPage + (totalCnt % itemsInAPage == 0 ? 0 :1);%>
	
	<%int pageBlock = 10; %>
	
	<%int startPage = ((cPage - 1) / pageBlock) * pageBlock + 1; %>
	
	<%int endPage = startPage + pageBlock - 1; %>
	
	<% if (endPage > pageCount){%>
		<%endPage = pageCount; %>
	<% }%>
	
	<% if (startPage > pageBlock){%>
		<a href="list?page=<%=startPage - pageBlock%>">이전 10개</a>
	<% }%>
	
	<% for (int i = startPage; i <= endPage; i++){%>
		<a class="<%=cPage == i ? "cPage" : ""%>" href="list?page=<%=i%>"><%=i%></a>
	<% }%>
	
	<% if (endPage < pageCount){%>
		<a href="list?page=<%=startPage + pageBlock%>">다음 10개</a>
	<% }%>
	
	
	<%} %>
	
	</div>
	
	<a href="list?page=1">맨 앞으로</a>
	<a href="list?page=<%=cPage - 10 <= 1 ? 1 : cPage - 10%>">10페이지 전</a>
	<a href="list?page=<%=cPage - 1 <= 1 ? 1 : cPage - 1%>">이전페이지</a>

	<div class="page">
		<%
		for (int i = 1; i <= totalPage; i++) {
		%>
		<a class="<%=cPage == i ? "cPage" : ""%>" href="list?page=<%=i%>"><%=i%></a>
		<%
		}
		%>
	</div>
	<a href="list?page=<%=cPage + 1 <= totalPage ? cPage + 1 : totalPage%>">다음페이지</a>
	<a href="list?page=<%=cPage + 10 <= totalPage ? cPage + 10 : totalPage%>">10페이지 후</a>
	






</body>
</html>