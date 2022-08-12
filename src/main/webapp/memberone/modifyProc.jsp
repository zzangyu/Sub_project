<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.memberone.*" %>

<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="dao" class="com.memberone.StudentDAO"/>
<jsp:useBean id="vo" class="com.memberone.StudentVO">
	<jsp:setProperty property="*" name="vo"/>
</jsp:useBean>
<%
    String loginID = (String)session.getAttribute("loginID"); // 로그인 정보 가져온 것.
    vo.setId(loginID);
    dao.updateMember(vo);
%>
<!DOCTYPE html>
<html>
<head>
<title>회원정보 수정 완료</title>
</head>
<meta http-equiv="Refresh" content="3; url=login.jsp"> <!-- content="3" : 3초 -->
<body>
	<div align="center">
		<font size="5" face="궁서체">
			입력하신 내용대로 <b>회원정보가 수정되었습니다.</b><br><br>3초 후에 로그인 페이지로 돌아갑니다.
		</font>
	</div>
</body>
</html>