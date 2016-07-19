<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<fmt:setLocale value="en_US"/>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Shopping</title>
     <script  src="js/shopping.js"></script>
    <link href="css/sidebar.css" rel="stylesheet">
    <link href="font-awesome-4.6.3/css/font-awesome.min.css" rel="stylesheet">
   <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/mycss.css" />
    
</head>
<jsp:include page="NavBar.jsp" />
<body class=" body placeholder">    

 
 <form action="ShoppingServlet" method="get"  id="myform" name="myform" >  
              
  <div style="background-color: white;" class="panel-body">
  
 
        <!-- Sidebar -->
        <div id="sidebar-wrapper">
        
            <ul class="sidebar-nav nav-pills nav-stacked" id="menu">
            <c:forEach var="type" items="${Prodtype}">
                <li class="active">
             
                    <a href="ShoppingServlet?typeid=${type.typeid}">${type.typename }</a>
                               
                </li>

                </c:forEach>
            </ul>
         
        </div><!-- /#sidebar-wrapper -->
        <!-- Page Content -->
   

 <div class="row">
  
  <div class="col-sm-2"></div>
  
    <div class="col-sm-8">
      
        <div class="container" name="productlist" id="productlist">
           <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                      <c:forEach var="product" items="${Products}">
                     
                      <div><img src= "${product.produrl }.jpg" width="200px" height="200px" ></img></div>
                     
                      
                      
                      <div id="productdet"><a  href="ShoppingServlet?productid=<c:out value="${product.prodid}"/>"> ${product.prodname}</a></div>
                      
                      <div>Description: ${product.proddesc }</div>            
                                
                      <div>Price: ${product.prodprice }</div>
                      
                      <div>
                      
                        
                      <input type="button" class="addCart" name="AddtoCart${product.prodid}" id="AddtoCart${product.prodid}" value="AddtoCart"/>
                      <input type="button" class="addWish" name="AddtoWish${product.prodid}" id="AddtoWish${product.prodid}" value="AddtoWishList"/></div>
                      
                      </c:forEach>
                    </div>
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
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
    <script src="js/sidebarmenu.js"></script>
    <script  src="js/shopping.js"></script>
    <script  src="js/color.js"></script>
    </form>
</body>
</html>