<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<meta charset="UTF-8">
<title>Contact Us</title>
</head>
<body>
	<div id="container">
		<header>
			<h1>Contact Us</h1>
			Please fill this form in a decent manner
		</header>
		<div>
			<form action="${pageContext.request.contextPath}/contactus" method="post">
				<section>
					Full Name <br> <input name="fullName" type="text" required>
					<br>
				</section>
				<section>
					Email <br> <input name="email" type="email" required><br>
					<br>
					<p>example@example.com</p>
				</section>
				<section>
					Message<br>
					<textarea name="message" required></textarea>
				</section>
				<div id="buttons">
					<button type="submit">Submit</button>
					<a id="redirecButton" href="${pageContext.request.contextPath}/login.jsp">Login as
						Admin</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>