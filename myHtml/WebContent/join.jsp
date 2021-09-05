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
			<h1>Join Us</h1>
			<form id="join">
				<fieldset>
				<legend>Basic Infomation</legend> <label>User ID</label> <input
					type="text" name="" class="id" /> <input type="button" value="중복체크"
					class="dup" />
				<br>
				<label>Password</label> <input type="password" name="" class="pass" />
				<br>
				<label>Retype Password</label> <input type="password" name=""
					class="pass" />
				<br>
				<label>Name</label> <input type="text" name="" class="nick" />
				<br>
				<label>Email</label> <input type="text" name="" class="email" />
				<br>
				</fieldset>
				<fieldset>
				<legend>Optional</legend> <label>Phone Number</label> <select
					name="">
					<option value="02">02</option>
					<option value="010">010</option>
					<option value="011">011</option>
					<option value="016">016</option>
					<option value="017">017</option>
					<option value="018">018</option>
					<option value="019">019</option>
				</select>- <input type="text" name="" class="phone" />- <input type="text"
					name="" class="phone" />
				<br>
				<label>Postal Code</label> <input type="text" name="" class=""
					readOnly /> <input type="button" value="찾기" onClick="#" />
				<br>
				<label>Address1</label> <input type="text" name="" class="address" />
				<br>
				<label>Address2</label> <input type="text" name="" class="address" />
				</fieldset>
				<div class="clear"></div>
				<div id="buttons">
					<input type="button" value="Submit" class="submit"> <input
						type="button" value="Cancel" class="cancel">
				</div>
			</form>
		</article>
		<div class="clear"></div>
		<%@ include file="footers.html"%>
	</div>
</body>
</html>