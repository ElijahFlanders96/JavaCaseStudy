<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<spring:url value="/resources/css/styles.css" var="mainCss"/>
<link href="${mainCss}" rel="stylesheet"/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<title>Login</title>
</head>
<body>
    <%@ include file="navbar.html" %>
	<h1>login page</h1>
	<form action="./login" method="post">
		<div>
			<label>Employee id</label>
			<input type="text" name="eId"/>
		</div>
		<div>
			<label>email</label>
			<input type="text" name="email"/>
		</div>
		<div>
			<input type="submit" value="login"/>
		</div>
	</form>
	<h3>${loginFailedMessage}</h3>
	<h3>${loginSuccessMessage} ${currentUser.firstName}</h3>
</body>
</html>