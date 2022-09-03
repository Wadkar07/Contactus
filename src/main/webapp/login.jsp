<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<meta charset="UTF-8">
<title>Admin Login</title>
</head>
<body>
	<div id="container">
		<header>
			<h1>Admin Login</h1>
			Please login for fetching data
		</header>
		<div>
			<form action="${pageContext.request.contextPath}/login" method="post">
				<section>
					User Name <br> <input name="UserName" type="text" required>
					<br>
				</section>
				<section>
					Password <br> <input name="Password" type="password" required><br>
				</section>
				<div id="buttons">
					<button type="submit">Submit</button>
					<a id="redirecButton" href="${pageContext.request.contextPath}/contactus.jsp">Contact Us</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>