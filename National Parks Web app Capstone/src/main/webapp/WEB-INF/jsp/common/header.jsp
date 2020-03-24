<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<c:url value="/css/style.css" var="stylesheet" />
	<link href="${stylesheet}" rel="stylesheet">
	<meta charset="ISO-8859-1">
	<title>National Park Geek</title>
</head>
<body>
	<div id="navAndHeader">
	 	<header>
	    	<c:url value="/" var="homePageHref" />
	    	<c:url value="/img/logo.png" var="logo" />
	        <a href="${homePageHref}">
	        	<img class="logoImage" src="${logo}" alt="National Park Geek logo" />
	        </a>
	    </header>
	    
	     <nav>
	        <ul id="navigation">
		        <li><a href="${homePageHref}">Home</a></li>
		        <li><a href="<c:url value="/survey"/>">Take Survey</a></li>
		        <li><a href="<c:url value="/allsurveys"/>">Survey Results</a></li>
		    </ul>
	    </nav>
    </div>