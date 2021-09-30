<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<title>jQuery Ajax메서드 -$.post()</title>
<style type="text/css">
#result{
	width: 200px;
	height: 200px;
	border: 5px double #6688FF;
}
</style>
<script src="../js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#b1").click(function(){
			$.post("process.jsp",
				{
				name:"kingdora",
				stus:"homebody"
				},
				function(data,status){//으답내용 처리
					if(status == "success")
						$("#result").html(data);				
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