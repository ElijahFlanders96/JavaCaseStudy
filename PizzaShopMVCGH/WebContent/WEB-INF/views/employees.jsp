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
<title>Employees</title>
</head>
<body>
	<%@ include file="navbar.html" %>
	<div class="main-body">
	<!-- ADD -->
		<form:form action="./addEmp" method="post" modelAttribute="employee">
			<div class="main-body-text">
				<h3>Add A New Employee</h3>
				<label>ID: </label>
				<br>
				<form:input path="eId"/>
				<form:errors path="eId"/>
			</div>
			<div class="main-body-text">
				<label>First Name: </label>
				<br>
				<form:input path="firstName"/>
				<form:errors path="firstName"/>
			</div>
			<div class="main-body-text">
				<label>Last Name: </label>
				<br>
				<form:input path="lastName"/>
				<form:errors path="lastName"/>
			</div>
			<div class="main-body-text">
				<label>Wage: </label>
				<br>
				<form:input path="wage"/>
				<form:errors path="wage"/>
			</div>
			<div class="main-body-text">
				<label>Position: </label>
				<br>
				<form:input path="position"/>
				<form:errors path="position"/>
			</div>
			<div class="main-body-text">
				<label>Email: </label>
				<br>
				<form:input path="email"/>
				<form:errors path="email"/>
			</div>
			<div class="main-body-text">
				<label>Phone Number: </label>
				<br>
				<form:input path="phoneNumber"/>
				<form:errors path="phoneNumber"/>
			</div>
			<div class="main-body-text">
				<label>Store ID: </label>
				<br>
				<form:input path="storeId"/>
				<form:errors path="storeId"/>
			</div>
			<br>
			<div>
		      <input type="submit" value="Add Employee" class="crud-btn"/>
		    </div>
		    <h3 class="main-body-text">${addEmpStoreError}</h3>
		    <h3 class="main-body-text">${addEmpSessionError}</h3>
		   	<h3 class="main-body-text">${errorMessage}</h3>
		   	<h3 class="main-body-text">${successMessage}</h3>
		</form:form>
    	<br>
    	<!-- GET -->
    	<form:form action="./getEmp" method="post" modelAttribute="employee">
	    	<div class="main-body-text">
	    		<h3>Get Employee by ID to View Info</h3>
	    		<label>ID: </label>
	    		<br>
	    		<form:input path="eId"/>
	    	</div>
	    	<br>
    		<div>
		      <input type="submit" value="Get Employee" class="crud-btn"/>
		    </div>
		    <br>
		    <h3 class="main-body-text">${getEmpSessionError}</h3>
		    <h3 class="main-body-text">${getEmpError}</h3>
		    <h5 class="main-body-text">${eId}${firstName}${lastName}${wage}${position}${email}${phoneNumber}${storeId}</h5>
    	</form:form>
	    <br>
	    <!-- UPDATE -->
	    <form:form action="./updateEmp" method="post" modelAttribute="employee">
			<div class="main-body-text">
				<h3>Update an Existing Employee</h3>
				<label>ID: </label>
				<br>
				<form:input path="eId"/>
				<form:errors path="eId"/>
			</div>
			<div class="main-body-text">
				<label>First Name: </label>
				<br>
				<form:input path="firstName"/>
				<form:errors path="firstName"/>
			</div>
			<div class="main-body-text">
				<label>Last Name: </label>
				<br>
				<form:input path="lastName"/>
				<form:errors path="lastName"/>
			</div>
			<div class="main-body-text">
				<label>Wage: </label>
				<br>
				<form:input path="wage"/>
				<form:errors path="wage"/>
			</div>
			<div class="main-body-text">
				<label>Position: </label>
				<br>
				<form:input path="position"/>
				<form:errors path="position"/>
			</div>
			<div class="main-body-text">
				<label>Email: </label>
				<br>
				<form:input path="email"/>
				<form:errors path="email"/>
			</div>
			<div class="main-body-text">
				<label>Phone Number: </label>
				<br>
				<form:input path="phoneNumber"/>
				<form:errors path="phoneNumber"/>
			</div>
			<div class="main-body-text">
				<label>Store ID: </label>
				<br>
				<form:input path="storeId"/>
				<form:errors path="storeId"/>
			</div>
			<br>
			<div>
		      <input type="submit" value="Update Employee" class="crud-btn"/>
		    </div>
		    <h3 class="main-body-text">${updateEmpSessionError}</h3>
		   	<h3 class="main-body-text">${updateEmpError}</h3>
		   	<h3 class="main-body-text">${updateEmpSuccess}</h3>
		</form:form>
	    <br>
	    <!-- REMOVE -->
	    <form:form action="./removeEmp" method="post" modelAttribute="employee">
	    	<div class="main-body-text">
	    		<h3>Remove Employee by ID</h3>
	    		<label>ID: </label>
	    		<br>
	    		<form:input path="eId"/>
	    	</div>
	    	<br>
	    	<div>
		      <input type="submit" value="Remove Employee" class="crud-btn"/>
		    </div>
		    <h3 class="main-body-text">${removeEmpSessionError}</h3>
		    <h3 class="main-body-text">${removeEmpError}</h3>
		    <h3 class="main-body-text">${removeEmpSuccess}</h3>
	    </form:form>
  	</div>
</body>
</html>