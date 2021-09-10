<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	session.setAttribute("MEMBERID", "sen");
	session.setAttribute("NAME","라일성");
	session.setMaxInactiveInterval(10);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>세션정보저장</title>
</head>
<body>
세션에 정보를 저장했습니다.
</body>
</html>