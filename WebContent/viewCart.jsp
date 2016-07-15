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
<script src="javascripts/home.js"></script>
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
<title>View Cart</title>
</head>
<body class="body">
	
		<div class="container">
			<br> <br>
			<jsp:include page="NavBar.jsp" />

			<div style="background-color: white;" class="panel-body">
				<div class="row">
					<div class="col-sm-2">
						<img src="${gBigUrl}" alt="${user.name}" />
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
					<div class="col-sm-2"></div>
					<div class="col-sm-8">
					<br>
					<a id="showReviews">Items in cart:</a>
					<div id= "reviewsDiv">
						
							
								<c:set var="activity" scope="session" value="${activity}" />
								<c:if test="${activity != null}">
									<div class="panel-group">
										<c:forEach var="activity" items="${activity}">
											<div class="panel panel-primary">
												<div class="panel-heading">
													<table width="100%">
														<tr>
															<td align="left">${activity.restaurant.name}</td>
															<td align="right"><fmt:formatDate type="date"
																						value="${activity.daterate}" /></td>
														</tr>
													</table>

												</div>
												<div class="panel-body" style="background-image: url('../images/rt${activity.restaurant.id}.jpeg');">
													<c:out value="${activity.review}"></c:out>
												</div>
											</div>
										</c:forEach>
									</div>
								</c:if>
								<c:if test="${activity == null}">
									<h2>You are not logged in!!</h2>
								</c:if>
							</div>
					<h3>Items in cart:</h3>
							<form id="cart" action="Checkout" method="post">
							<div>
							<div class="panel-group">
										
											<div class="panel panel-primary">
												<div class="panel-heading"> Item Details:													

												</div>
												<div class="panel-body">
												<table width="100%">
												<c:forEach var="rests" items="${items}">
														<tr>
															<td align="left" colspan="2"><img src="${items.product.produrl}" /><c:out value="${items.product.prodname}"></c:out> </td>
															
														</tr>
														<tr>
															<td align="left" colspan="2">${items.product.proddesc}</td>
															
														</tr>
														<tr>
															<td align="right">Quantity: <input type="text" width="10px" id="qtty${items.cartid}" name="qtty${items.cartid}" value="${items.atty}"></td>
															<td align="left">Price: <input type="text" id="price" disabled="disabled" name="price" value="${items.product.prodprice}"> </td>															
														</tr>
														<tr>
															<td align="right" colspan="2"><input class="delete" type="button" id='delete${items.cartid}' name='delete${items.cartid}' value="Delete"> </td>															
														</tr>
														</c:forEach>
													</table>													
												</div>
												<div class="panel-footer"> 
												<div align="right"> <input type="submit" id='checkout' name='checkout' value="Checkout">
												
												</div>
												</div>
											</div>
										
									</div>						
								
							</div>
							</form>
						
							
						
					</div>
					<div class="col-sm-2"></div>
				</div>

			</div>
		</div>
</body>
</html>