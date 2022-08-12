<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.memberone.*" %>
<jsp:useBean id="dao" class="com.memberone.StudentDAO" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script type="text/javascript" src="js/regForm.js"></script>
</head>
    <%
    	String loginID = (String)session.getAttribute("loginID"); // 로그인 정보 가져온 것.
    	StudentVO vo = dao.getMember(loginID);
    %>
<body>
	<form name="regForm" method="post" action="modifyProc.jsp">
		<table border="1">
			<tr>
				<td colspan="2" align="center">회원정보 수정</td>
			</tr>
			<!-- 아이디 -->
			<tr>
				<td align="right">아이디</td>
				<td>
					<%= vo.getId() %>
				</td>
			</tr>
			<!-- 비밀번호 -->
			<tr>
				<td align="right">비밀번호</td>
				<td>
					<input type="password" name="pass" value="<%= vo.getPass() %>" >
				</td>
			</tr>
			<!-- 비밀번호확인 -->
			<tr>
				<td align="right">비밀번호 확인</td>
				<td>
					<input type="password" name="repass" value="<%= vo.getPass() %>" >
				</td>
			</tr>
			<!-- 이름 -->
			<tr>
				<td align="right">이름</td>
				<td>
					<%= vo.getName() %>
				</td>
			</tr>
			<!-- 전화번호 -->
			<tr> 
				<td align="right">전화번호</td>
				<td>
					<input type="text" name="phone1" size="4" value="<%= vo.getPhone1() %>">-
					<input type="text" name="phone2" size="5" value="<%= vo.getPhone2() %>">-
					<input type="text" name="phone3" size="5" value="<%= vo.getPhone3() %>">
				</td>
			</tr>
			<!-- 이메일 -->
			<tr>
				<td align="right">이메일</td>
				<td> <!-- 원래는 type="email"임 -->
					<input type="text" name="email" value="<%= vo.getEmail() %>">
				</td>
			</tr>
			<!-- 우편번호 -->
			<tr>
				<td align="right">우편번호</td>
				<td>
					<input type="text" name="zipcode" value="<%= vo.getZipcode() %>">&nbsp;
					<input type="button" value="우편번호 찾기" onclick="zipCheck()">
				</td>
			</tr>
			<!-- 주소 -->
			<tr>
				<td align="right">주소</td>
				<td>
					<input type="text" name="address1" size="60" value="<%= vo.getAddress1() %>">
				</td>
			</tr>
			<!-- 상세 주소 -->
			<tr>
				<td align="right">상세 주소</td>
				<td>
					<input type="text" name="address2" size="30" value="<%= vo.getAddress2() %>">
				</td>
			</tr>
			<!-- 버튼 -->
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="정보수정" onclick="updateCheck()">&nbsp;&nbsp;
					<input type="button" value="취소" onclick="javascript:window.location='login.jsp'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>