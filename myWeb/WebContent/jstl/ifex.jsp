<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL EXAMPLE</title>
</head>
<body>
<c:if test="${param.no > 4 }">
이 내용은 안나와요.
</c:if>
<c:if test="${param.type eq 'guest' }">
홈페이지 방문을 환영합니다 <br>
회원가입을 하기 바랍니다.
</c:if>
<c:if test="${param.type eq 'member' }">
홈페이지 방문을 환영합니다 <br>
회원님 사랑합니다.
</c:if>
</body>
</html>