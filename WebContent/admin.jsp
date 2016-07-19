<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="en_US" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>


<!-- Latest compiled JavaScript -->
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/style.css" />

<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
<link rel="stylesheet"
	href="https://jqueryui.com/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="css/mycss.css" />

<script>
   $(document).ready(function(){
		$( "#accordion" ).accordion();
  });
  </script>
<style>
#draggable {
	width: 0px;
	height: 0px;
	padding: 0em;
}

#resizable {
	width: 150px;
	height: 150px;
	padding: 0.5em;
}

#resizable h3 {
	text-align: center;
	margin: 0;
}

#red, #green, #blue {
	float: left;
	clear: left;
	width: 300px;
	margin: 15px;
}

#swatch {
	width: 120px;
	height: 100px;
	margin-top: 18px;
	margin-left: 350px;
	background-image: none;
}

#red .ui-slider-range {
	background: #ef2929;
}

#red .ui-slider-handle {
	border-color: #ef2929;
}

#green .ui-slider-range {
	background: #8ae234;
}

#green .ui-slider-handle {
	border-color: #8ae234;
}

#blue .ui-slider-range {
	background: #729fcf;
}

#blue .ui-slider-handle {
	border-color: #729fcf;
}
</style>

<title>Admin</title>
</head>
<body>
<body class="body placeholder">

	<div class="container">
		<br> <br>
		<jsp:include page="NavBar.jsp" />

		<div style="background-color: white;" class="panel-body">
			<div class="row">
				<div class="col-sm-2">
					<img src="${images}" alt="${user.username}" />
				</div>
				<div class="col-sm-10"></div>

			</div>
			<c:set var="mes" scope="session" value="${message}" />
			<c:if test="${mes != null}">
				<div class="alert alert-success">
					<strong>${message}</strong> <span id="showSearchTerm"></span>
				</div>
			</c:if>
			<div class="row">
				<div class="col-sm-4">
					<h3>Add Product:</h3>
					<div>
						<form id="target" action="AddProduct" method="post">
							<div class="form-group">
								<label for="typeAssgn">Name:</label> <input type="text"
									name="productName" id="productName" value=""
									class="form-control">
							</div>
							<div class="form-group">
								<label for="typeAssgn">Description:</label> <input type="text"
									name="productDesc" id="productDesc" value=""
									class="form-control">
							</div>
							<div class="form-group">
								<label for="typeAssgn">URL:</label> <input type="text"
									name="productUrl" id="productUrl" value="" class="form-control">
							</div>
							<div class="form-group">
								<label for="typeAssgn">Price:</label> <input type="text"
									name="productPrice" id="productPrice" value=""
									class="form-control">
							</div>
							<div class="form-group">
								<label for="typeAssgn">Price:</label> <select name="typeId"
									id="typeId">
									<c:forEach var="type" items="${types}">
										<option value="${type.typeid}">${type.typename}</option>
									</c:forEach>
								</select>
							</div>
							<input type="submit" name="submit" id="submit" value="Add">

						</form>
					</div>
				</div>
				<div class="col-sm-4">
					<h3>Edit Products:</h3>
					<div>
						<form id="target" action="EditProduct" method="post">
							<div class="form-group">
								<label for="typeAssgn">Product ID:</label> <input type="text"
									name="productId" id="productId" value="${productId}"
									class="form-control">
							</div>
							<input type="submit" name="submit" id="submit"
								value="View Details">
							<c:set var="prod" scope="session" value="${product}" />
							<c:if test="${prod != null}">
								<div class="form-group">
									<label for="typeAssgn">Name:</label> <input type="text"
										name="productName" id="productName" value="${prod.prodname}"
										class="form-control">
								</div>
								<div class="form-group">
									<label for="typeAssgn">Description:</label> <input type="text"
										name="productDesc" id="productDesc" value="${prod.proddesc}"
										class="form-control">
								</div>
								<div class="form-group">
									<label for="typeAssgn">URL:</label> <input type="text"
										name="productUrl" id="productUrl" value="${prod.produrl}"
										class="form-control">
								</div>
								<div class="form-group">
									<label for="typeAssgn">Price:</label> <input type="text"
										name="productPrice" id="productPrice"
										value="${prod.prodprice}" class="form-control">
								</div>
								<input type="submit" name="submit" id="submit" value="Update">
							</c:if>
						</form>
					</div>



				</div>

				<div class="col-sm-4">
					<h3>Administer Orders:</h3>
					<div>
						<form id="target" action="UpdateOrder" method="post">
							<div class="form-group">
								<label for="typeAssgn">Order Name:</label> <input type="text"
									name="orderName" id="orderName" value="${orderName}"
									class="form-control">
							</div>
							<input type="submit" name="submit" id="submit"
								value="View Details">
							<c:set var="order" scope="session" value="${orders}" />
							<c:if test="${order != null}">
								<h3>Order Details:</h3>
								<c:forEach var="order" items="${orders}">
									<div class="form-group">
										<label for="typeAssgn">Name:</label>
										<c:out value="${order.product.prodname}" />
									</div>
									<div class="form-group">
										<label for="typeAssgn">Description:</label>
										<c:out value="${order.product.proddesc}" />
									</div>
									<div class="form-group">
										<label for="typeAssgn">Price:</label>
										<c:out value="${order.product.prodprice}" />
									</div>
									<img src="${order.product.produrl }.jpg" width="200px"
										height="200px"></img>
									<div class="form-group">
										<label for="typeAssgn">Status:</label> <input type="text"
											name="status" id="status" value="${order.status}"
											class="form-control">
									</div>
								</c:forEach>
								<input type="submit" name="submit" id="submit" value="Update">
							</c:if>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>