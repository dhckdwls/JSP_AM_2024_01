<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시글 작성 페이지</title>
</head>
<body>
	<div>게시글 작성 페이지</div>
	<div> 제목을 입력해라 <input type="text" name = "title"/></div>
	<form action="http://localhost:8081/JSP_AM_2024_01/article/doWrite"></form>
	<div>내용을 입력해라 <input type="text" name = "body"/></div>
	<input type="submit" value="제출">
	<input type="reset"  value ="reset"/>
</body>
</html>