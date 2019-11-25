<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>INFOREST</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
<!--===============================================================================================-->
</head>
<body class="animsition">
		<!-- Header -->
	<header class="header-v3">
		<!-- Header desktop -->
		<div class="container-menu-desktop">
			<div class ="wrap-menu-desktop how-shadow1">
				<nav class="limiter-menu-desktop container">
					
					<!-- Logo desktop -->		
					<a href="home.jsp" class="logo">
                  	<img src="images/icons/logo.png" alt="IMG-LOGO">
               		</a>

					<!-- Menu desktop -->
					<div class="menu-desktop">
						<ul class="main-menu">
						<li>
                       		<a href="<c:url value='/main'/>" style="text-decoration:none">HOME</a>
                     	</li>

	                     <li>
	                  	    <a href="<c:url value='/rank'/>" style="text-decoration:none">RANK</a>
	                     </li>
	
	                     <li>
	                 	     <a href="<c:url value='/product'/>" style="text-decoration:none">PRODUCT</a>
	                     </li>
	
	                     <li>
	                    	  <a href="<c:url value='/post'/>" style="text-decoration:none">POST</a>
	                     </li>
	
	                     <li>
	                     	  <a href="<c:url value='/mypage'/>" style="text-decoration:none">MY PAGE</a>
	                     </li>
	                     
	                     <li>
	                    	    <a href="<c:url value='/signIn'/>" style="text-decoration:none">SIGN IN</a>
	                     </li>
						</ul>
					</div>
				</nav>
			</div>	
		</div>
   </header>


	<!-- Title page -->
	<section class="bg-img1 txt-center p-lr-15 p-tb-92" style="background-image: url('images/product_bg.jpg');">
		<h2 class="ltext-105 cl0 txt-center">
			Camera
		</h2>
	</section>	

	

	
	<!-- Product -->
	<div class="bg0 m-t-23 p-b-140">
		<div class="container">
	</div>
</body>
</html>