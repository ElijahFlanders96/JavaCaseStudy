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
<title>Equipment</title>
</head>
<body>
	<%@ include file="navbar.html" %>
	<div class="main-body">
	<!-- ADD -->
		<form:form action="./viewMacs" method="post" modelAttribute="machinery">
			<div class="main-body-text">
				<h6>STATUS LEGEND:</h6>
				<p>Enter one of the following digit values for "Status" based on its respective value:</p>
				<p>1: no issues, 2: needs maintenance, 3: requires replacement</p>
				<br>
				<h3>View All Equipment</h3>
			</div>
			<br>
			<div>
			    <input type="submit" value="View Equipment" class="crud-btn"/>
			</div>
			<h3 class="main-body-text">${viewMacSessionError}</h3>
			<div class="main-body-text" >
				<c:forEach items = "${macList}" var="mac">
					<h5><c:out value="${mac}"/></h5>
				</c:forEach>
			</div>
		</form:form>
		<br>
		<form:form action="./addMac" method="post" modelAttribute="machinery">
			<div class="main-body-text">
				<h3>Add New Equipment</h3>
				<label>ID: </label>
				<br>
				<form:input type="number" path="mId"/>
				<form:errors path="mId"/>
			</div>
			<div class="main-body-text">
				<label>Equipment Name: </label>
				<br>
				<form:input path="name"/>
				<form:errors path="name"/>
			</div>
			<div class="main-body-text">
				<label>Status:</label>
				<br>
				<form:input type="number" path="status"/>
				<form:errors path="status"/>
			</div>
			<div class="main-body-text">
				<label>Replacement Cost: </label>
				<br>
				<form:input type="number" step="any" path="replacementCost"/>
				<form:errors path="replacementCost"/>
			</div>
			<div class="main-body-text">
				<label>Store ID: </label>
				<br>
				<form:input type="number" path="storeId"/>
				<form:errors path="storeId"/>
			</div>
			<br>
			<div>
		      <input type="submit" value="Add Equipment" class="crud-btn"/>
		    </div>
		    <h3 class="main-body-text">${addMacNoDuplicate}</h3>
		    <h3 class="main-body-text">${addMacNoZero}</h3>
		    <h3 class="main-body-text">${addMacStatusError}</h3>
		    <h3 class="main-body-text">${addMacStoreError}</h3>
		    <h3 class="main-body-text">${addMacSessionError}</h3>
		   	<h3 class="main-body-text">${addMacError}</h3>
		   	<h3 class="main-body-text">${addMacSuccess}</h3>
		</form:form>
    	<br>
    	<!-- GET -->
    	<form:form action="./getMac" method="post" modelAttribute="machinery">
	    	<div class="main-body-text">
	    		<h3>Get Equipment by ID to View Info</h3>
	    		<label>ID: </label>
	    		<br>
	    		<form:input type="number" path="mId"/>
	    	</div>
	    	<br>
    		<div>
		      <input type="submit" value="Get Equipment" class="crud-btn"/>
		    </div>
		    <br>
		    <h3 class="main-body-text">${getMacSessionError}</h3>
		    <h3 class="main-body-text">${getMacError}</h3>
		    <h5 class="main-body-text">${mId}${name}${status}${replacementCost}${storeId}</h5>
    	</form:form>
	    <br>
	    <!-- UPDATE -->
	    <form:form action="./updateMac" method="post" modelAttribute="machinery">
			<div class="main-body-text">
				<h3>Update Existing Equipment</h3>
				<label>ID: </label>
				<br>
				<form:input type="number" path="mId"/>
				<form:errors path="mId"/>
			</div>
			<div class="main-body-text">
				<label>Equipment Name: </label>
				<br>
				<form:input path="name"/>
				<form:errors path="name"/>
			</div>
			<div class="main-body-text">
				<label>Status: </label>
				<br>
				<form:input type="number" path="status"/>
				<form:errors path="status"/>
			</div>
			<div class="main-body-text">
				<label>Replacement Cost: </label>
				<br>
				<form:input type="number" step="any" path="replacementCost"/>
				<form:errors path="replacementCost"/>
			</div>
			<div class="main-body-text">
				<label>Store ID: </label>
				<br>
				<form:input type="number" path="storeId"/>
				<form:errors path="storeId"/>
			</div>
			<br>
			<div>
		      <input type="submit" value="Update Equipment" class="crud-btn"/>
		    </div>
		    <h3 class="main-body-text">${nullMac}</h3>
		    <h3 class="main-body-text">${updateMacNoZero}</h3>
		    <h3 class="main-body-text">${updateMacStoreError}</h3>
		    <h3 class="main-body-text">${updateMacStatusError}</h3>
		    <h3 class="main-body-text">${updateMacSessionError}</h3>
		   	<h3 class="main-body-text">${updateMacError}</h3>
		   	<h3 class="main-body-text">${updateMacSuccess}</h3>
		</form:form>
	    <br>
	    <!-- REMOVE -->
	    <form:form action="./removeMac" method="post" modelAttribute="machinery">
	    	<div class="main-body-text">
	    		<h3>Remove Equipment by ID</h3>
	    		<label>ID: </label>
	    		<br>
	    		<form:input type="number" path="mId"/>
	    	</div>
	    	<br>
	    	<div>
		      <input type="submit" value="Remove Equipment" class="crud-btn"/>
		    </div>
		    <h3 class="main-body-text">${removeMacSessionError}</h3>
		    <h3 class="main-body-text">${removeMacError}</h3>
		    <h3 class="main-body-text">${removeMacSuccess}</h3>
	    </form:form>
  	</div>
</body>
</html>