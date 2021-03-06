<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="boardone.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ include file="view/color.jsp" %>
<%!
	//수정<1>
	int pageSize = 10;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
%>
<%
	//수정<2>
	String pageNum = request.getParameter("pageNum");
	if(pageNum==null) pageNum = "1";
	int currentPage = Integer.parseInt(pageNum);
	int startRow = (currentPage - 1) * pageSize + 1;
	int endRow = currentPage * pageSize;
	int count = 0;
	int number = 0;
	List<BoardVO> articleList = null;
	BoardDAO dbPro = BoardDAO.getInstance();
	count = dbPro.getArticleCount(); //전체글수
	if(count>0){
		articleList = dbPro.getArticles(startRow,endRow);//수정<3>	
	}
	number=count-(currentPage-1)*pageSize;//수정<4>
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="<%=bodyback_c %>">
<div align="center"><b>글목록(전체 글 :<%=count %>)</b>
<table width="700">
	<tr>
		<td align="right" bgcolor="<%= value_c%>">
			<a href="writeForm.jsp">글쓰기</a>
		</td>
	 </tr>
</table>
<%
	if(count==0){
%>
<table width="700" border="1" cellpadding="0" cellspacing="0">
	<tr>
		<td align="center">게시판에 저장된 글이 없습니다.</td>
	</tr>
</table>
<%}else{ %>
<table border="1" width="700" cellspacing="0" cellpadding="0" align="center">
	<tr height="30" bgcolor="<%=value_c%>">
			<td align="center" width="50">번 호</td>
			<td align="center" width="50">제 목</td>
			<td align="center" width="50">작성자</td>			
			<td align="center" width="50">작성일</td>
			<td align="center" width="50">조회</td>
			<td align="center" width="100">IP</td>
	 </tr>
	 <%
	 	for(int i = 0; i <articleList.size();i++){
	 		BoardVO article =(BoardVO)articleList.get(i);
	 %>
	 	<tr height="30">
	 		<td align="center" width="50"><%=number-- %> </td>
	 		<td width="250">
	 		<!-- 수정 <5> -->
	 		<%
	 			int wid = 0;
	 		if(article.getDepth()>0){
	 			wid=7*(article.getDepth());	 		
	 		%><img src="images/level.gif" width="<%=wid %>" height="16">
	 			<img src="images/re.gif">
	 			<%}else{ %>
	 			<img src="images/level.gif" width="<%=wid %>" height="16">
	 			<% }%>
	 		<a href="content.jsp?num=<%=article.getNum()%>&pageNum=1">
	 		<!-- 수정<6> --><%=article.getSubject() %></a>
	 		<%if(article.getReadcount()>=10){%>
	 		<img src="images/hot.gif" boder="0" height="15"><%} %></td>
	 		<td align="center" width="100">
	 			<a href="mailto:<%=article.getEmail() %>">
	 						<%=article.getWriter() %></a></td>
	 		<td align="center" width="150"><%=sdf.format(article.getRegdate()) %> </td>
	 		<td align="center" width="50"><%=article.getReadcount() %></td>
	 		<td align="center" width="100"><%=article.getIp() %></td>
	 	 </tr>
	 <%}%>
</table>
<%} %>
<%if(count>0){
	int pageBlock =5;
	int imsi = count%pageSize == 0 ? 0 : 1;
	int pageCount = count/pageSize + imsi;
	int startPage = (int)((currentPage-1)/pageBlock)*pageBlock+1;
	int endPage = startPage + pageBlock-1;
	if(endPage>pageCount)endPage = pageCount;
	if(startPage>pageBlock){%>
	<a href="list.jsp?pageNum=<%=startPage-pageBlock%>">[이전]</a>
<%
	}
	for(int i=startPage; i<=endPage; i++){%>
	<a href="list.jsp?pageNum=<%=i%>">[<%=i %>]</a>
	<%
	}
	if(endPage<pageCount){%>
	<a href="list.jsp?pageNum=<%=startPage+pageBlock %>">[다음]</a>
	<%
	}
}
	%>
</div>
</body>
</html>
