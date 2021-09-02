<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	  request.setCharacterEncoding("UTF-8");
      String userid= request.getParameter("userid");
      String passwd= request.getParameter("passwd");
      out.println(userid+"\t"+passwd);      
%>