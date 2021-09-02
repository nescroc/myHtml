<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
     String address = request.getParameter("address");
     String data = null;

    if("서울".equals(address)){
    	data +="<option>강남구</option>";
    	data +="<option>서초구</option>";
    }else if("경기".equals(address)){
    	data +="<option>성남시</option>";
    	data +="<option>용인시</option>";
    	data +="<option>고양시</option>";
    	
    }else{
    	data +="<option>제주시</option>";
    	data +="<option>서귀포시</option>";
    	
    }

    out.print(data);

%>