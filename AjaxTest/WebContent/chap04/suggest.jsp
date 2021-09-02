<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
     String search = request.getParameter("search");
     String data = "<span>";
     
     //배열
     String [] str = {"홍두깨" , "김홍도" , "수박" ,"홍길동" ,"유관순" ,"김유신"};
     for( int i = 0 ; i < str.length ; i++ ){
    	 if( search != null && search != "" && str[i].contains(search)){
    		 data += str[i]+"<br>";
    	 }
     }
     data += "</span>";

    out.print(data);

%>