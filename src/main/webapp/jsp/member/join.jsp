<%@ page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
</head>
<body>

	<h2>회원가입</h2>

	<form method="POST" action="DoJoin">
		<div>
			아이디 : <input type="text" placeholder="아이디를 입력해주세요" name="loginId" />
		</div>
		<div>
			비밀번호 :
			<input type="password" placeholder="비밀번호를 입력해주세요" name="loginPw">
		</div>
		<div>비밀번호 확인 :
			<input type="password" placeholder="비밀번호 확인" name="loginPwConfirm" />	
		</div>
		<div>
			이름 : 
			<input type="text" placeholder="이름을 입력해주세요"  name="name">
		</div>
		<button type="submit">회원가입</button><a href="../home/main">회원가입 취소</a>
	</form>


	<div>
		<a style="color: green" href="../home/main">리스트로 돌아가기</a>
	</div>

</body>
</html>