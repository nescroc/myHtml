<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int bufferSize = out.getBufferSize();
	int remainSize = out.getRemaining();
	int usedSize = bufferSize - remainSize;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
버퍼 전체 크기 : <%=bufferSize %><br>
사용한 버퍼크기 : <%=usedSize %>
남은 버퍼 :	<% out.println(remainSize); %>byte<br>

</body>
</html>