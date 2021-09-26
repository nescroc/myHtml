<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="util.*"%>
<%
	request.setCharacterEncoding("utf-8");
	int id = Integer.parseInt(request.getParameter("id"));
	Connection conn = null;
	PreparedStatement pstmt = null;
	try{
		conn = DB.getConnection();
		conn.setAutoCommit(false);
		pstmt = conn.prepareStatement("delet from tablement where id = ?");
		pstmt.setInt(1,id);
		int i = pstmt.executeUpdate();
		System.out.println(i+"개 행 삭제");
		conn.commit();	
%>
<result>
	<code>success</code>
	<id><%=id %></id>
</result>
<%}catch(Throwable e){ 
try{conn.rollback();}catch(SQLException ex){}%>
<result><code>error</code>
	<message><![CDATA[<%=e.getMessage()%>]]></message>
</result>
<%
}finally{
	try{if(pstmt!=null)pstmt.close();}catch(SQLException pstmte){}
	try{if(conn!=null)conn.setAutoCommit(true);conn.close();}catch(SQLException pstmte){}
}
%>

