<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="memberone.*" %>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="vo" class="memberone.StudentVO">
<jsp:setProperty property="*" name="vo"/>
</jsp:useBean>
<%
	StudentDAO dao = StudentDAO.getInstance();
	String loginID = (String)session.getAttribute("loginID");
	vo.setId(loginID);
	dao.updateMember(vo);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Process</title>
</head>
<meta http-equiv="Refresh" content="3;url=login.jsp">
<body>
<div align="center">
	<font size="5" face="바탕체">
		입력하신 내용대로 <b> 회운정보가 수정 되었습니다.</b><br>
		3초후에 Logon Page로 이동합니다.
	</font>
</div>
</body>
</html>