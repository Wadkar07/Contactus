<%@page import="com.jspproject.model.Request" %>
<%@page import="com.jspproject.dao.RequestDao" %>
<%@page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="cout" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Dash Board</title>
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
			<cout:forEach items="${activeRequests}" var="contactRequest">
				<tr>
					<td>${contactRequest.getFullName()}</td>
					<td>${contactRequest.getEmail()}</td>
					<td>${contactRequest.getMessage()}</td>
					<td>
						<form action="${pageContext.request.contextPath}/dashboard"
							method="post">
							<button name="id" value="${contactRequest.getId()}">Archive</button>
						</form>
					</td>
				</tr>
			</cout:forEach>
		</table>
		<h1>Archive</h1>
		<table border="1">
			<tr>
				<td>Full Name</td>
				<td>Email</td>
				<td>Message</td>
				<td>Status</td>
			</tr>
			<cout:forEach items="${archiveRequests}" var="contactRequest">
				<tr>
					<td>${contactRequest.getFullName()}</td>
					<td>${contactRequest.getEmail()}</td>
					<td>${contactRequest.getMessage()}</td>
					<td>
						<form action="${pageContext.request.contextPath}/dashboard"
							method="post">
							<button name="id" value="${contactRequest.getId()}">Active</button>
						</form>
					</td>
				</tr>
			</cout:forEach>
		</table>
	</body>
</html>