<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="actiontag.*" %>
<%@ page import="java.util.*" %>
<% 
	ArrayList<String> singer = new ArrayList<String>();
	singer.add("블랙핑크");
	singer.add("원더걸스");
	request.setAttribute("singer", singer);
	
	Customer[] customer = new Customer[2];
	customer[0] = new Customer();
	customer[0].setName("소노공");
	customer[0].setEmail("daughter@daum.net");
	customer[0].setPhone("01095570579");
	customer[1] = new Customer();
	customer[1].setName("청길동");
	customer[1].setEmail("mon@yahoo.kr");
	customer[1].setPhone("01051585457");
	request.setAttribute("customer", customer);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<ul>
	<li>${singer[0]}, ${singer[1]} </li>
</ul>
<ul>
	<li>이름 : ${customer[0].name }</li>
	<li>전화 : ${customer[0].phone }</li>
	<li>메일 : ${customer[0].email }</li>
</ul>
<ul>
	<li>이름 : ${customer[1].name }</li>
	<li>전화 : ${customer[1].phone }</li>
	<li>메일 : ${customer[1].email }</li>
</ul>
</body>
</html>