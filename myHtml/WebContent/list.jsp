<%@ page contentType="text/html;charset=UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>해당 컨텐츠 제목</title>
<link type="text/css" rel="stylesheet" href="css/default.css">
<link type="text/css" rel="stylesheet" href="css/subpage.css">
<script type="text/javascript" src="js/script.js"></script>
</head>
<body>
	<div id="warp">
		<!-- 요거 앞에 빠져 있음-->
		<%@ include file="headers.html"%>
		<%@ include file="sub_img.html"%>
		<%@ include file="sub_menu.html"%>
		<article id="contents">
			
			<h1>Board</h1>
			<input type="button" value="write" class="btn" />
			<table id="board">
				<tr>
					<th class="tno">NO.</th>
					<th class="twriter">Writer</th>
					<th class="ttitle">Title</th>
					<th class="tread">Read</th>
					<th class="tdate">Date</th>
				</tr>
				<tr>
					<td>39</td>
					<td>John.Na</td>
					<td class="left">JavaLine WebSite is Web Standard Test Site</td>
					<td>20</td>
					<td>2021.09.05</td>
				</tr>
				
			</table>
			<form method="post" name="find_frm" action="#"
				onsubmit="return check()">
				<div id="table_search">
					<select class="select_box" name="find" size="1">
						<option value="writer">이름</option>
						<option value="subject">제목</option>
						<option value="content">내용</option>
					</select> 
					<input type="button" class="input_box" name="find_box" />
					<input type="submit" value="search" class="btn" />
				</div>
			</form>
			<div class="clear"></div>
			<div id="page_control">
				<a href="#">Prev</a><a href="#">1</a><a href="#">2</a> <a href="#">3</a><a
					href="#">4</a><a href="#">5</a> <a href="#">6</a><a href="#">7</a><a
					href="#">8</a> <a href="#">9</a><a href="#">10</a><a href="#">Next</a>
			</div>
		</article>
		<div class="clear"></div>
		<%@ include file="footers.html"%>
	</div>
</body>
</html>