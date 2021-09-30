<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<title>jQuery Ajax메서드 -load() 응답처리</title>
<script src="../js/jquery-3.6.0.min.js"></script>
<style type="text/css">
#result{
	width:200px;
	height:200px;
	border:5px double #6699FF;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
		//[결과] 버튼을 클릭하면 xhrTest3.txt가 <div id="result">엘리먼트에로드
		$("#b1").click(function(){
			$("#result").load("xhrTest3.txt",function(response,stu,xhr){
				if(stu=="success")//서버요청이 성공시
					alert("로드 성공!"); //메시지 상자 표시
				if(stu=="error")//서버요청 실패시
					alert("에러 : "+xhr.status+" : "+xhr.stu);
			});
		});
});
</script>
</head>
<body>
	<button id="b1">결과</button>
	<div id="result"></div>
</body>
</html>