<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>간단한 표현 언어(EL)예제</title>
</head>
<body>
<h3>간단한 표현언어(EL)예제</h3>
<p> 연산자를 사용한 예와 내장객체 사용한 예</p>
<table border="1">
	<tr> <td><b>표현식</b> </td><td><b>값</b></td> </tr>
	<tr> <td><b>\${2 + 5 }</b> </td><td><b>${2 + 5 }</b></td> </tr>
	<tr> <td><b>\${5/6}</b> </td><td><b>${5/6}</b></td> </tr>
	<%-- <tr> <td><b>\${5 div 6 }</b> </td><td><b>${ 5 div 6 }</b></td> </tr> --%>
	<tr> <td><b>\${4 mod 5 }</b> </td><td><b>${4 mod 5}</b></td> </tr>
	<tr> <td><b>\${2 < 3 }</b> </td><td><b>${2 < 3}</b></td> </tr>
	<tr> <td><b>\${2 gt 3 }</b> </td><td><b>${2 gt 3}</b></td> </tr>
	<tr> <td><b>\${3.1 le 3.2 }</b> </td><td><b>${3.1 le 3.2 }</b></td> </tr>
	<tr> <td><b>\${3.1 < 3.2 }</b> </td><td><b>${3.1 < 3.2 }</b></td> </tr>
	<tr> <td><b>\${(5>3)?5:3}</b> </td><td><b>${(5>3)?5:3}</b></td> </tr>
	<tr> <td><b>\${header["host"]}</b> </td><td><b>${header["host"]}</b></td> </tr>
	<tr>
		<td>\${header["user-agent"] }</td>
		<td>${header["user-agent"] } </td>
	</tr>
</table>
</body>
</html>
