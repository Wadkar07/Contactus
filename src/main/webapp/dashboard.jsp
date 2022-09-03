<%@page import="com.jspproject.model.Request" %>
<%@page import="com.jspproject.dao.RequestDao" %>
<%@page import="java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<center>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>

	<body>
		<h1>Active</h1>
		<table border="1">
			<tr>
				<td>Full Name</td>
				<td>Email</td>
				<td>Message</td>
				<td>Status</td>
			</tr>
			<c:forEach items="${activeRequests}" var="req">
				<tr>
					<td>${req.getFullName()}</td>
					<td>${req.getEmail()}</td>
					<td>${req.getMessage()}</td>
					<td>
						<form action="${pageContext.request.contextPath}/dashboard"
							method="post">
							<button name="id" value="${req.getId()}">Archive</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
		<h1>Archive</h1>
		<table border="1">
			<tr>
				<td>Full Name</td>
				<td>Email</td>
				<td>Message</td>
				<td>Status</td>
			</tr>
			<c:forEach items="${archiveRequests}" var="req">
				<tr>
					<td>${req.getFullName()}</td>
					<td>${req.getEmail()}</td>
					<td>${req.getMessage()}</td>
					<td>
						<form action="${pageContext.request.contextPath}/dashboard"
							method="post">
							<button name="id" value="${req.getId()}">Active</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>

	</body>
</center>

</html>