<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,inital-scale=1.0"/>
<title>jQuery</title>
<style type="text/css">
#result{
	width:200px;
	height: 250px;
	border: 5px double #6699FF;
}
</style>
<script src="../js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#b1").click(function(){
			$.get("xhrTest3.txt",function(data,status){
				var resultStr ="데이터 : "+data+"\n처리상태 : "+status;
				//resultStr내용을 <div id="result">에 표시
				$("#result").text(resultStr);
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