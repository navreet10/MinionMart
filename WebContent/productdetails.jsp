<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<fmt:setLocale value="en_US" />
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Product Details</title>
<script src="js/shopping.js"></script>
<link href="css/sidebar.css" rel="stylesheet">
<link href="font-awesome-4.6.3/css/font-awesome.min.css"
	rel="stylesheet">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/mycss.css" />

</head>
<jsp:include page="NavBar.jsp" />
<body class=" body placeholder">


	<form action="ProductdetailsServlet" method="get" id="myform"
		name="myform">

		<div style="background-color: white;" class="panel-body">


			<!-- Sidebar -->
			<div id="sidebar-wrapper">

				<ul class="sidebar-nav nav-pills nav-stacked" id="menu">
					<c:forEach var="type" items="${Prodtype}">
						<li class="active"><a
							href="ShoppingServlet?typeid=${type.typeid}">${type.typename }</a>

						</li>

					</c:forEach>
				</ul>

			</div>
			<!-- /#sidebar-wrapper -->
			<!-- Page Content -->


			<div class="row">

				<div class="col-sm-2"></div>

				<div class="col-sm-8">


					<div id="page-content-wrapper">
						<div class="container-fluid">
							<div class="row">
								<div class="col-lg-12">


									<div>
										<img src="${myproductimage}.jpg" width="200px" height="200px"></img>
									</div>



									<div id="productdet">
										<a
											href="ShoppingServlet?productid=<c:out value="${myproductid}"/>">
											${myproductname}</a>
									</div>

									<div>Description: ${myproductdesc}</div>

									<div>Type: ${myproducttype}</div>
									<div>Price: ${myproductprice}</div>

									<div>

										<input type="button" class="addCart"
											name="AddtoCart${myproductid}" id="AddtoCart${myproductid}"
											value="AddtoCart" /> <input type="button" class="addWish"
											name="AddtoWish${myproductid}" id="AddtoWish${myproductid}"
											value="AddtoWishList" />
									</div>


									<table align="center" border="0" class="table">
										<thead>
											<tr>
												<th>Search reviews</th>
											</tr>
										</thead>
										<tbody>
											<tr style="backgroundcolor: #4db6ac">
												<td>Please enter review, at most 141 characters:</td>
											</tr>
											<tr>
												<td><textarea name="limitedtextarea" rows="5" cols="30"
														onKeyDown="limitText(this.form.limitedtextarea,this.form.countdown,141);"
														onKeyUp="limitText(this.form.limitedtextarea,this.form.countdown,141);">
														</textarea></td>
											</tr>
											<tr>
												<td><font size="2">(Maximum characters: 141)<br>
														You have <input readonly type="text" name="countdown"
														size="3" value="141" style="width: 50px; border: none">
														characters left.
												</font><br></td>
											</tr>
											<tr>
												<td><input type="submit" name="method" value="Add"
													class="button" /> <input type="submit" name="method"
													value="Search" class="button" /></td>
											</tr>
										</tbody>
									</table>




									<table border="0" align="center"
										class="table table-bordered table-hover table-strip">
										<thead>
											<tr>
												<th>The reviews of this product</th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
									<table border="1" align="center"
										class="table table-bordered table-hover table-strip">

										<thead>
											<tr>

												<th><div align="center">User</div></th>
												<th><div align="center">Review</div></th>
												<th><div align="center">Review date</div></th>
												<th><div align="center">HelpfulCount</div></th>
												<th><div align="center">Ishelpful</div></th>

											</tr>
										</thead>
										<tbody>


											<c:forEach var="review" items="${Productreviews}">
												<tr>
													
													<td align="center">
													<c:set var="myParam" value="${review.minionuser.useremail}"/>       
       												<img src=${imageurls[myParam] }  width="20" height="20"></img>
													<c:out
															value="${review.minionuser.username}" />
															</td>
													<td align="center">
													<c:set var="mytext"
															value="${review.reviewtext}" />
													<c:out value="${review.reviewtext}" />
													<img
														src=${happysadurls[mytext] } width="20" height="20"></img></td>
													<td align="center"><fmt:formatDate
															value="${review.reviewdate}" pattern="yy-MMM-dd" /></td>

													
													<td align="center"><c:out value="${review.ishelpful}" />
													</td>
													<td align="center"><a
														href=Helpful?reviewid=${review.reviewid}><span
															class="glyphicon glyphicon-thumbs-up"
															Style="font-size: 20px"></span></a> <a
														href=NotHelpful?reviewid=${review.reviewid}><span
															class="glyphicon glyphicon-thumbs-down"
															Style="font-size: 20px"></span></a></td>
												</tr>

											</c:forEach>

										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-2"></div>
			</div>



		</div>




		<!-- /#page-content-wrapper -->

		<!-- /#wrapper -->
		<!-- jQuery -->
		<script src="https://code.jquery.com/jquery-1.11.2.min.js"></script>
		<script
			src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
		<script src="js/sidebarmenu.js"></script>
		<script src="js/shopping.js"></script>

	</form>
</body>
</html>