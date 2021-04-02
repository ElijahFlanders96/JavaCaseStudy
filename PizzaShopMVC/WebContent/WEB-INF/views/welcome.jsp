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
<title>Welcome</title>
<style>
      #get-started {
        margin-left: 47%;
        border-radius: 10px;
        background-color: white;
        border-color: white;
        font-weight: 600;
      }
    </style>
</head>
<body>
	<%@ include file="navbar.html" %>
	<div class="main-body">
        <h1 class="main-body-text">Mama Mia!</h1>
        <h2 class="main-body-text">There sure is a lot more to managing a pizza franchise than tossin' dough!</h2>
        <img id="home-img" src="https://media.istockphoto.com/vectors/pizza-restaurant-logo-featuring-chef-spinning-pizza-base-vector-id165081831?k=6&m=165081831&s=170667a&w=0&h=srlDuhzOuCbLnTcAdT8AlRq5mziHoKP7WZXv9IcY7D0="/>
        <h3 class="main-body-text">This app helps you easily manage information from your workplaces,</h3>
        <h3 class="main-body-text">so you have time to do more of what you love:</h3>
        <h1 class="main-body-text">Makin' pizza!</h1>
        <br>
        <a href="login">
        	<input type="submit" value="Get Started" id="get-started" />
        </a>
      </div>
</body>
</html>