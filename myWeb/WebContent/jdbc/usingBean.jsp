<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,jdbc.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP에서 데이터베이스 연동</title>
<link href="style.css" type="text/css">
</head>
<body bgcolor="#FFFFCC"><body bgcolor="#FFFFCC">
	<h2>Beans와 Pool을 사용한 데이터베이스 예제입니다....</h2>
	<br>
	<h3>회원정보</h3>
	<table bordercolor="#0000ff" border="1">
		<tr>
			<td><strong>ID</strong></td>
			<td><strong>PASSWD</strong></td>
			<td><strong>NAME</strong></td>
			<td><strong>MEM_NUM1</strong></td>
			<td><strong>MEM_NUM2</strong></td>
			<td><strong>E_MAIL</strong></td>
			<td><strong>PHONE</strong></td>
			<td><strong>ZIPCODE/ADDRESS</strong></td>
			<td><strong>JOB</strong></td>
		</tr>
		<jsp:useBean id="dao" class="jdbc.TempMemberDAO" scope="page"/>	
		<%
		Vector<TempMemberVO> vlist = dao.getMemberList();
		int counter = vlist.size();
		for(int i =0;i<vlist.size(); i++){
				TempMemberVO vo = vlist.elementAt(i);
		%>
		<tr>
			<td><%=vo.getId() %></td>
			<td><%=vo.getPasswd()%></td>
			<td><%=vo.getName() %></td>
			<td><%=vo.getMem_num1()%></td>
			<td><%=vo.getMem_num2()%></td>
			<td><%=vo.getE_mail() %></td>
			<td><%=vo.getPhone()%></td>
			<td><%=vo.getZipcode()%>/<%=vo.getAddress()%></td>
			<td><%=vo.getJob()%></td>
			<% 
			}
			%>
		</tr>
		</table>
		<br>
		total records : <%=counter %>
</body>
</html>