<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<spring:url value="/resources/css/styles.css" var="mainCss"/>
<link href="${mainCss}" rel="stylesheet"/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<style>
	#login-btn {
		border-radius: 10px;
	    background-color: white;
	    border-color: white;
	    font-weight: 600;
	    width: 220px;
	}
</style>
<title>Login</title>
</head>
<body>
    <%@ include file="navbar.html" %>
	<div class="main-body">
		<form:form action="./login" method="post" modelAttribute="employee">
			<div class="main-body-text">
				<p>Must log in as a General Manager to interact with this application</p>
				<br>
				<h3>Employee ID: </h3>
				<form:input type="number" path="eId"/>
			</div>
			<br>
			<div class="main-body-text">
				<h3>Email: </h3>
				<form:input type="text" path="email"/>
			</div>
			<br>
			<div class="main-body-text">
				<h3>Password: </h3>
				<form:input type="text" path="password"/>
			</div>
			<br>
			<div class="main-body-text">
				<input type="submit" value="Login" id="login-btn"/>
			</div>
		</form:form>
		<h3 class="main-body-text">${nullID}</h3>
		<h3 class="main-body-text">${loginFailedMessage}</h3>
		<h3 class="main-body-text">${loginSuccessMessage} ${currentUser.firstName}</h3>
	</div>
</body>
</html>