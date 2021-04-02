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
<style>
	#logout-btn {
		border-radius: 10px;
	    background-color: white;
	    border-color: white;
	    font-weight: 600;
	    width: 220px;
	}
</style>
<title>Logout</title>
</head>
<body>
    <%@ include file="navbar.html" %>
	<div class="main-body">
		<form action="./logout" method="post">
			<div class="main-body-text">
				<h3>Logout: </h3>
			</div>
			<br>
			<div class="main-body-text">
				<input type="submit" value="Logout" id="logout-btn"/>
			</div>
		</form>
		<h3 class="main-body-text">${logoutError}</h3>
	</div>
</body>
</html>