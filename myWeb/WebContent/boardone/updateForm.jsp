<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="boardone.*" %>
<%@ include file="view/color.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="script.js"></script>
</head>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	String pageNum = request.getParameter("pageNum");
	try{
	BoardDAO dbPro = BoardDAO.getInstance();
	BoardVO article = dbPro.getArticle(num);
%>
<body bgcolor="<%=bodyback_c %>">
<div align="center">
<b>글수정</b>
<br>
<form method="post" name="writeForm" action="updateProc.jsp?pageNum=<%=pageNum%>" onsubmit="return writeSave()">
<table width="500" border="1" cellspacing="0" cellpadding="0" bgcolor="<%=bodyback_c%>" align="center">
	<tr>
				<td width="70" bgcolor="<%=value_c%>" align="center">이름</td>
				<td width="330"><input type="text" size="12" maxlength="12"
					name="writer" value="<%=article.getWriter()%>"/></td>
				<td><input type="hidden" name="num" value="<%=article.getNum()%>"/></td>
			</tr>
			
			<tr>
				<td width="70" bgcolor="<%=value_c%>" align="center">제목</td>
				<td width="330">
				<input type="text" size="40" maxlength="50"	name="subject" value="<%=article.getSubject() %>" />
				
				</td>
			</tr>
			<tr>
				<td width="70" bgcolor="<%=value_c%>" align="center">이메일</td>
				<td width="330"><input type="text" size="30" maxlength="30"
					name="email" value="<%=article.getEmail()%>"/></td>
			</tr>
			
			<tr>
				<td width="70" bgcolor="<%=value_c%>" align="center">내용</td>
				<td width="330"><textarea name="content" rows="13" cols="50" ><%=article.getContent() %></textarea>
				</td>
			</tr>
			<tr>
				<td width="70" bgcolor="<%=value_c%>" align="center">비밀번호</td>
				<td width="330"><input type="password" size="10" maxlength="10"
					name="pass" /></td>
			</tr>
			<tr>
				<td colspan="2" bgcolor="<%=value_c %>" align="center"><input
					type="submit" value="글수정" /> <input type="reset" value="초기화" /> <input
					type="button" value="목록" onclick="document.location.href='list.jsp?pageNum=<%=pageNum %>'" /></td>
			</tr>
</table>
</form>
</div>
<%}catch(Exception e){e.printStackTrace();} %>
</body>
</html>