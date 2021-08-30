<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="actiontag.Customer" %>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="customer" class="actiontag.Customer" scope="page"/>
<jsp:setProperty name="customer" property="*"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer 가입정보</title>
</head>
<body>
	<ul>
		<li><jsp:getProperty name="customer" property="name"/></li>
		<li><jsp:getProperty name="customer" property="email"/> </li>
		<li><jsp:getProperty name="customer" property="phone"/> </li>
		
	</ul>
</body>
</html>