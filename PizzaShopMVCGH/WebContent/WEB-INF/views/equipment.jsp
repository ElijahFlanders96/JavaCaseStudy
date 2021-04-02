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
<title>Equipment</title>
</head>
<body>
	<%@ include file="navbar.html" %>
	<div class="main-body">
    <div>
      <input type="submit" value="Add New Equipment" class="crud-btn"/>
    </div>
    <br>
    <div>
      <input type="submit" value="View Equipment's Info" class="crud-btn"/>
    </div>
    <br>
    <div>
      <input type="submit" value="Update Equipment's Info" class="crud-btn"/>
    </div>
    <br>
    <div>
      <input type="submit" value="Remove Equipment" class="crud-btn"/>
    </div>
  </div>
</body>
</html>