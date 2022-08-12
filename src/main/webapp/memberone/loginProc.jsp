<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<jsp:useBean id="dao" class="com.memberone.StudentDAO" />
    <%
   		String id = request.getParameter("id");
  		String pass = request.getParameter("pass");
    	
  		int check = dao.loginCheck(id, pass);
    	
    	if(check == 1) { // 로그인 성공
    		session.setAttribute("loginID", id); // session에 속성부여
    		response.sendRedirect("login.jsp");
    	} else if(check == 0) { // 아이디는 존재하지만 비밀번호 오류
    %>
		<script type="text/javascript">
			alert("비밀번호가 틀렸습니다.");
			history.go(-1); /* 바로 이전 페이지로 이동 */
		</script>
	<%
		} else {
	%>
		<script type="text/javascript">
			alert("아이디가 존재하지 않습니다.");
			history.go(-1); /* 바로 이전 페이지로 이동 */
		</script>
	<%
		}
	%>
