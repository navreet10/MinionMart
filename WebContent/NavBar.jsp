<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Case</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <link rel="stylesheet" href="css/style.css" />
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">MinionMart</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="<%=request.getContextPath()%>/HomePosts">Shop</a></li>
      <!-- <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Page 1 <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#">N</a></li>
          <li><a href="#">Page 1-2</a></li>
          <li><a href="#">Page 1-3</a></li>
        </ul>
      </li> -->
      
      <li><a href="<%=request.getContextPath()%>/NewsFeed">News Feed</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
    <c:set var="user" scope="session" value="${user}"/>
    <c:if test="${user == null}">
      <li><a href="registration.jsp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
      <li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      <li><div class="search-form">
                <div class="form-group has-feedback">
            		<label for="Search" class="sr-only">Search</label>
            		<input type="text" class="form-control" name="search" id="search" value = "" placeholder="search">
              		<span class="glyphicon glyphicon-search form-control-feedback"></span>
            	</div>
            </div></li>
      </c:if>
      <c:if test="${user != null}"> 
      <li><a href="<%=request.getContextPath()%>/Profile"><span class="glyphicon glyphicon-user"></span> Welcome ${userName} <img src="${gUrl}" alt="${user.username}"/></a></li>
      <li><a href="<%=request.getContextPath()%>/logout"><span class="glyphicon glyphicon-log-in"></span> LogOut</a></li>
      <li><form action="Search" class="search-form">
                <div class="form-group has-feedback">
            		<label for="search" class="sr-only">Search</label>
            		<input type="text" class="form-control" name="search" id="search" placeholder="search">
              		<span class="glyphicon glyphicon-search form-control-feedback"></span>
            	</div>
            </form></li>
      </c:if>
    </ul>
  </div>
</nav>
  


</body>
</html>
