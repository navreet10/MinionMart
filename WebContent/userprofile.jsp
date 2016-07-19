
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="en_US"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Profile</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="css/mycss.css" />

</head>
<body style="background-color: #0ca3d2">
<jsp:include page="NavBar.jsp" />
<section class="container">
<div class="login">
<form action="userprofileServlet" method="get">
<h1>Update User Profile</h1>
<div>${result}</div>
<div align="center">User Name:           <input type ="text" name = "username" id="username" value ="${username}"></input>
</div>
<div align="center">User Password:       <input type="text" name ="userpassword" id="userpassword" value=""></input>
</div>
<div align="center">User Address:               <input type="text" name ="useraddress" id="useraddress" value="${useraddress}"></input>
</div>
<div align="center">User Email:          <input type="text" name ="useremail" id="useremail" value="${useremail}"></input>
</div>
<div align="center">User Zipcode:            <input type="text" name ="userzipcode" id="userzipcode" value="${userzipcode}"></input></div>
<br>
<div align="center"><input type="submit" name="submit" id="submit" value="Update"></input></div>

</form>
</div>
</section>
</body>
</html>