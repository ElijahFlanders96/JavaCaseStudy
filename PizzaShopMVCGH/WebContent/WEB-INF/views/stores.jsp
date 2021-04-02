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
<title>Stores</title>
</head>
<body>
	<%@ include file="navbar.html" %>
	<div class="main-body">
	<!-- ADD -->
		<form:form action="./addStore" method="post" modelAttribute="store">
			<div class="main-body-text">
				<h3>Add A New Store</h3>
				<label>ID: </label>
				<br>
				<form:input path="sId"/>
				<form:errors path="sId"/>
			</div>
			<div class="main-body-text">
				<label>Store Name: </label>
				<br>
				<form:input path="name"/>
				<form:errors path="name"/>
			</div>
			<div class="main-body-text">
				<label>Address: </label>
				<br>
				<form:input path="address"/>
				<form:errors path="address"/>
			</div>
			<div class="main-body-text">
				<label>General Manager ID: </label>
				<br>
				<form:input path="gmId"/>
				<form:errors path="gmId"/>
			</div>
			<br>
			<div>
		      <input type="submit" value="Add Store" class="crud-btn"/>
		    </div>
		   	<h3 class="main-body-text">${addStoreError}</h3>
		   	<h3 class="main-body-text">${addStoreSuccess}</h3>
		</form:form>
    	<br>
    	<!-- GET -->
    	<form action="./getStore" method="post">
	    	<div class="main-body-text">
	    		<h3>Get Store by ID to View Info</h3>
	    		<label>ID: </label>
	    		<br>
	    		<input type="text" name="getsId"/>
	    	</div>
	    	<br>
    		<div>
		      <input type="submit" value="Get Store" class="crud-btn"/>
		    </div>
		    <br>
		    <h3 class="main-body-text">${getStoreError}</h3>
		    <h5 class="main-body-text">${sId}${name}${address}${gmId}</h5>
    	</form>
	    <br>
	    <!-- UPDATE -->
	    <form:form action="./updateStore" method="post" modelAttribute="store">
			<div class="main-body-text">
				<h3>Update an Existing Store</h3>
				<label>ID: </label>
				<br>
				<form:input path="sId"/>
				<form:errors path="sId"/>
			</div>
			<div class="main-body-text">
				<label>Store Name: </label>
				<br>
				<form:input path="name"/>
				<form:errors path="name"/>
			</div>
			<div class="main-body-text">
				<label>Address: </label>
				<br>
				<form:input path="address"/>
				<form:errors path="address"/>
			</div>
			<div class="main-body-text">
				<label>General Manager ID: </label>
				<br>
				<form:input path="gmId"/>
				<form:errors path="gmId"/>
			</div>
			<br>
			<div>
		      <input type="submit" value="Update Store" class="crud-btn"/>
		    </div>
		   	<h3 class="main-body-text">${updateStoreError}</h3>
		   	<h3 class="main-body-text">${updateStoreSuccess}</h3>
		</form:form>
	    <br>
	    <!-- REMOVE -->
	    <form action="./removeStore" method="post">
	    	<div class="main-body-text">
	    		<h3>Remove Store by ID</h3>
	    		<label>ID: </label>
	    		<br>
	    		<input type="text" name="sId"/>
	    	</div>
	    	<br>
	    	<div>
		      <input type="submit" value="Remove Store" class="crud-btn"/>
		    </div>
		    <h3 class="main-body-text">${removeStoreError}</h3>
		    <h3 class="main-body-text">${removeStoreSuccess}</h3>
	    </form>
  	</div>
</body>
</html>