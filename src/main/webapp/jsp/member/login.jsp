<%@ page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인페이지</title>
</head>
<body>
	<h1>로그인 페이지</h1>
	
	<script type="text/javascript">
		var LoginForm__submitDone = false;

		function LoginForm__submit(form) {
			if (LoginForm__submitDone) {
				alert('이미 처리중입니다');
				return;
			}
			// 			form.loginId.value = form.loginId.value.trim();
			var loginId = form.loginId.value.trim();
			var loginPw = form.loginPw.value.trim();
			

			console.log('form.loginId.value : ' + loginId);
			console.log('form.loginPw.value : ' + loginPw);
			

			if (loginId.length == 0) {
				alert('아이디를 입력해주세요');
				form.loginId.focus();
				return;
			}
			if (loginPw.length == 0) {
				alert('비밀번호를 입력해주세요');
				form.loginPw.focus();
				return;
			}

			LoginForm__submitDone = true;
			form.submit();

		}
	</script>
	
		<form method="POST" action="doLogin"
		onsubmit="LoginForm__submit(this); return false;">
		<div>
			로그인 아이디 : <input autocomplete="off" type="text"
				placeholder="아이디를 입력해주세요" name="loginId" />
		</div>
		<div>
			로그인 비밀번호 : <input autocomplete="off" type="text"
				placeholder="비밀번호를 입력해주세요" name="loginPw" />
		</div>
		
		
		<button type="submit">로그인</button>
	</form>
	
	</form>
</body>
</html>