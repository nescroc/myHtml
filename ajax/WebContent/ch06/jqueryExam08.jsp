<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<title>Insert title here</title>
<style type="text/css">
#result{
	width:200px;
	height: 200px;
	border: 5px double #6699FF;
}
</style>
<script src="../js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#b1").click(function(){
			var query = {name:"kingdora",stus:"homebody"};
			//process.jsp 페이지에 요청데이터를보낸 후 결과를 반환받음
			$.ajax({
				type:"POST",//전송방식
				url: "process.jsp",//요청페이지
				data:query,//전송데이터
				success:function(data){//요청페이지를 실행한 결과
					$("#result").html(data);				
				}
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