<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<spring:url value="/resources/css/styles.css" var="mainCss"/>
<link href="${mainCss}" rel="stylesheet"/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<title>Vehicles</title>
</head>
<body>
	<%@ include file="navbar.html" %>
	<div class="main-body">
	<!-- ADD -->
		<form:form action="./viewCars" method="post" modelAttribute="driverVehicle">
			<div class="main-body-text">
				<h3>View All Vehicles</h3>
			</div>
			<br>
			<div>
			    <input type="submit" value="View Vehicles" class="crud-btn"/>
			</div>
			<h3 class="main-body-text">${viewCarSessionError}</h3>
			<div class="main-body-text" >
				<c:forEach items = "${carList}" var="car">
					<h5><c:out value="${car}"/><h5>
				</c:forEach>
			</div>
		</form:form>
		<br>
		<form:form action="./addCar" method="post" modelAttribute="driverVehicle">
			<div class="main-body-text">
				<h3>Add A New Vehicle</h3>
				<label>ID: </label>
				<br>
				<form:input path="dId"/>
				<form:errors path="dId"/>
			</div>
			<div class="main-body-text">
				<label>Model: </label>
				<br>
				<form:input path="model"/>
				<form:errors path="model"/>
			</div>
			<div class="main-body-text">
				<label>Year: </label>
				<br>
				<form:input path="year"/>
				<form:errors path="year"/>
			</div>
			<div class="main-body-text">
				<label>Color: </label>
				<br>
				<form:input path="color"/>
				<form:errors path="color"/>
			</div>
			<div class="main-body-text">
				<label>Insurance Provider: </label>
				<br>
				<form:input path="insuranceProvider"/>
				<form:errors path="insuranceProvider"/>
			</div>
			<div class="main-body-text">
				<label>Driver ID: </label>
				<br>
				<form:input path="driverId"/>
				<form:errors path="driverId"/>
			</div>
			<br>
			<div>
		      <input type="submit" value="Add Vehicle" class="crud-btn"/>
		    </div>
		    <h3 class="main-body-text">${addCarNoDuplicate}</h3>
		    <h3 class="main-body-text">${addCarNoZero}</h3>
		    <h3 class="main-body-text">${addCarEmpError}</h3>
		    <h3 class="main-body-text">${addCarSessionError}</h3>
		   	<h3 class="main-body-text">${addCarError}</h3>
		   	<h3 class="main-body-text">${addCarSuccess}</h3>
		</form:form>
    	<br>
    	<!-- GET -->
    	<form:form action="./getCar" method="post" modelAttribute="driverVehicle">
	    	<div class="main-body-text">
	    		<h3>Get Vehicle by ID to View Info</h3>
	    		<label>ID: </label>
	    		<br>
	    		<form:input path="dId"/>
	    	</div>
	    	<br>
    		<div>
		      <input type="submit" value="Get Vehicle" class="crud-btn"/>
		    </div>
		    <br>
		    <h3 class="main-body-text">${getCarSessionError}</h3>
		    <h3 class="main-body-text">${getCarError}</h3>
		    <h5 class="main-body-text">${dId}${model}${year}${color}${insuranceProvider}${driverId}</h5>
    	</form:form>
	    <br>
	    <!-- UPDATE -->
	    <form:form action="./updateCar" method="post" modelAttribute="driverVehicle">
			<div class="main-body-text">
				<h3>Update an Existing Vehicle</h3>
				<label>ID: </label>
				<br>
				<form:input path="dId"/>
				<form:errors path="dId"/>
			</div>
			<div class="main-body-text">
				<label>Model: </label>
				<br>
				<form:input path="model"/>
				<form:errors path="model"/>
			</div>
			<div class="main-body-text">
				<label>Year: </label>
				<br>
				<form:input path="year"/>
				<form:errors path="year"/>
			</div>
			<div class="main-body-text">
				<label>Color: </label>
				<br>
				<form:input path="color"/>
				<form:errors path="color"/>
			</div>
			<div class="main-body-text">
				<label>Insurance Provider: </label>
				<br>
				<form:input path="insuranceProvider"/>
				<form:errors path="insuranceProvider"/>
			</div>
			<div class="main-body-text">
				<label>Driver ID: </label>
				<br>
				<form:input path="driverId"/>
				<form:errors path="driverId"/>
			</div>
			<br>
			<div>
		      <input type="submit" value="Update Vehicle" class="crud-btn"/>
		    </div>
		    <h3 class="main-body-text">${updateCarNoDuplicate}</h3>
		    <h3 class="main-body-text">${updateCarNoZero}</h3>
		    <h3 class="main-body-text">${updateCarEmpError}</h3>
		    <h3 class="main-body-text">${updateCarSessionError}</h3>
		   	<h3 class="main-body-text">${updateCarError}</h3>
		   	<h3 class="main-body-text">${updateCarSuccess}</h3>
		</form:form>
	    <br>
	    <!-- REMOVE -->
	    <form:form action="./removeCar" method="post" modelAttribute="driverVehicle">
	    	<div class="main-body-text">
	    		<h3>Remove Vehicle by ID</h3>
	    		<label>ID: </label>
	    		<br>
	    		<form:input path="dId"/>
	    	</div>
	    	<br>
	    	<div>
		      <input type="submit" value="Remove Vehicle" class="crud-btn"/>
		    </div>
		    <h3 class="main-body-text">${removeCarSessionError}</h3>
		    <h3 class="main-body-text">${removeCarError}</h3>
		    <h3 class="main-body-text">${removeCarSuccess}</h3>
	    </form:form>
  	</div>
</body>
</html>