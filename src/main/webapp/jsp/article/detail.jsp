<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
Map<String, Object> articleRow = (Map<String, Object>) request.getAttribute("articleRow");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"">
<title>게시물 목록</title>
</head>
<body>

	<h2>상세보기</h2>
	<div>번호 :<%=articleRow.get("id")%>번</div>
	<div>날짜 :<%=articleRow.get("regDate")%></div>
	<div>제목 :<%=articleRow.get("title")%></div>
	<div>내용 :<%=articleRow.get("body")%></div>


</body>
</html>