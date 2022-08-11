<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script type="text/javascript" src="js/regForm.js"></script>
</head>
<body>
	<form action="regProc.jsp" method="Post" name="regForm">
		<table border="1">
			<tr>
				<td colspan="2" align="center">회원가입 정보 입력</td>
			</tr>
			<!-- 아이디 -->
			<tr>
				<td align="right">아이디</td>
				<td>
					<input type="text" name="id">&nbsp;
					<input type="button" value="중복확인" onclick="idCheck(this.form.id.value)">
				</td>
			</tr>
			<!-- 비밀번호 -->
			<tr>
				<td align="right">비밀번호</td>
				<td>
					<input type="password" name="pass">
				</td>
			</tr>
			<!-- 비밀번호확인 -->
			<tr>
				<td align="right">비밀번호 확인</td>
				<td>
					<input type="password" name="repass">
				</td>
			</tr>
			<!-- 이름 -->
			<tr>
				<td align="right">이름</td>
				<td>
					<input type="text" name="name">
				</td>
			</tr>
			<!-- 전화번호 -->
			<tr> 
				<td align="right">전화번호</td>
				<td>
					<select name="phone1">
						<option value="010">010</option>
						<option value="02">02</option>
						<option value="031">031</option>
						<option value="032">031</option>
						<option value="033">033</option>
					</select>-
					<input type="text" name="phone2" size="5">-
					<input type="text" name="phone3" size="5">
				</td>
			</tr>
			<!-- 이메일 -->
			<tr>
				<td align="right">이메일</td>
				<td> <!-- 원래는 type="email"임 -->
					<input type="text" name="email">
				</td>
			</tr>
			<!-- 우편번호 -->
			<tr>
				<td align="right">우편번호</td>
				<td>
					<input type="text" name="zipcode">&nbsp;
					<input type="button" value="우편번호 찾기" onclick="zipCheck()">
				</td>
			</tr>
			<!-- 주소 -->
			<tr>
				<td align="right">주소</td>
				<td>
					<input type="text" name="address1" size="60">
				</td>
			</tr>
			<!-- 상세 주소 -->
			<tr>
				<td align="right">상세 주소</td>
				<td>
					<input type="text" name="address2" size="30">
				</td>
			</tr>
			<!-- 버튼 -->
			<tr>
				<td colspan="3" align="center">
					<input type="button" value="회원가입" onclick="javascript:window.location='regProc.jsp'">&nbsp;&nbsp;
					<input type="reset" value="다시입력">&nbsp;&nbsp;
					<input type="button" value="이전페이지" onclick="javascript:window.location='login.jsp'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>