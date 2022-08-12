<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String loginID = (String)session.getAttribute("loginID"); // 로그인 정보 가져온 것.
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<!------------------로그인 성공 했을 때 폼------------------>	
	<%
		if(loginID != null) { // 로그인 되었을 때 화면 출력
		
	%>
	<table width="300" border="1">
		<tr>
			<td colspan="3" align="center">
				<%= loginID%>님 환영합니다.
			</td>
		</tr>
		<tr>
			<td width="100" align="center">
				<a href="modifyForm.jsp">정보수정</a>
			</td>
			<td width="100" align="center">
				<a href="deleteForm.jsp">회원 탈퇴</a>
			</td>
			<td width="100" align="center">
				<a href="logout.jsp">로그아웃</a>
			</td>
		</tr>
	</table>
	
	<!------------------로그인 안되었을 때 폼------------------>	
	<%
		} else { 
	%>



	<form action="loginProc.jsp" method="post">
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
	<%		
		}
	%>
	
	
</body>
</html>