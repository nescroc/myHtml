<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="actiontag.Customer" %>
<%@ page import="java.util.*" %>
<%
	Customer customer = new Customer();
	customer.setName("손오공");
	customer.setEmail("son@naver.com");
	customer.setPhone("01042820579");
	request.setAttribute("customer", customer);
	
	HashMap<String,String> map = new HashMap<String,String>();
	map.put("name", "소나타");
	map.put("maker", "현대자동차");
	request.setAttribute("car",map);
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EEEEL Example</title>
</head>
<body>
	<ul>
		<li>이름 : ${customer.name} </li>
		<li>메일 : ${customer.email} </li>
		<li>전화 : ${customer.phone} </li>
	</ul>
	<br>
	<ul>
		<li>자동차 : ${car.name} </li>
		<li>제조사 : ${car.maker} </li>		
	</ul>
</body>
</html>