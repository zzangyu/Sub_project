<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	
	<form action="#" method="Post">
		<table width="300" border="1">
			<tr>
				<td colspan="2" align="center">회원 로그인</td>
			</tr>
			<tr>
				<td align="right" width="100">아이디</td>
				<td width="200">&nbsp;&nbsp;
					<input type="text" name="id" size="20">
				</td>
			</tr>
			<tr>
				<td align="right" width="100">비밀번호</td>
				<td width="200">&nbsp;&nbsp;
					<input type="password" name="pass" size="20">
				</td>
			</tr>
			<!-- 버튼 -->
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="로그인">&nbsp;&nbsp;
					<input type="button" value="회원가입" onclick="javascript:window.location='regForm.jsp'">
				</td>
			</tr>
		</table>
	</form>
	
	
</body>
</html>