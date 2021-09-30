<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<title>jquery 이벤트처리</title>
<script src="../js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("p").mouseenter(function(){
			$(this).text("왓구려, 마우스포인터!!!");
		});
		$("p").mouseleave(function(){
			//<p>엘리먼트에서 마우스 포인터가 나가면 자동실행
			$(this).text("돌아와 마우스포인터!!");
		});
		$("button").dblclick(function(){
			//button 엘리먼트를 더블릭하면 자동실행
			$("body").css("background-color","#cccccc");
		});
	});
</script>
</head>
<body>
	<p>마우스 포인터를 여기에!!!</p>
	<button>더블클릭하시구료</button>
</body>
</html>