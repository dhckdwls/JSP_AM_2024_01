<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�Խñ� �ۼ� ������</title>
</head>
<body>
	<div>�Խñ� �ۼ� ������</div>
	<div> ������ �Է��ض� <input type="text" name = "title"/></div>
	<form action="http://localhost:8081/JSP_AM_2024_01/article/doWrite"></form>
	<div>������ �Է��ض� <input type="text" name = "body"/></div>
	<input type="submit" value="����">
	<input type="reset"  value ="reset"/>
</body>
</html>