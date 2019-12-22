<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import = "java.util.List" %>
<%@page import = "model.dao.*" %>
<%@page import = "model.dto.*" %>
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
<body>
   <!-- Header -->
   <header class="header-v3">
      <!-- Header desktop -->
      <div class="container-menu-desktop">
         <div class="wrap-menu-desktop how-shadow1">
            <nav class="limiter-menu-desktop p-l-45">
               
               <!-- Logo desktop -->      
               <a href="home.jsp" class="logo">
                  <img src="images/icons/logo.png" alt="IMG-LOGO">
               </a>
              
               <!-- Menu desktop -->
               <div style="float:left; width:60%">
                  <ul class="main-menu">
                     <li>
                         <a href="<c:url value='/main'/>" style="text-decoration:none">HOME</a>
                     </li>

                     <li>
                      <a href="<c:url value='/rankCont'/>" style="text-decoration:none">RANK</a>
                     </li>

                     <li>
                      <a href="<c:url value='/product'/>" style="text-decoration:none">PRODUCT</a>
                     </li>
                                           
                      <c:if test="${userId == null}">
	                     <li>
	                        <a href="<c:url value='/signIn'/>" style="text-decoration:none">LOGIN</a>
	                     </li> 
	                 </c:if>
	                 
	                 <c:if test="${userId != null}">
	                 	<c:if test="${position == 0}">
	                 		<li>
	                        	<a href="<c:url value='/management'/>" style="text-decoration:none">MANAGEMENT</a>
	                     	</li> 
	                 	</c:if>
	                 	
                   </ul>
               </div>   
               
               <div style="float:right">
               	<ul class="main-menu">
	                 		<li>
		                 		<font style="color:white"><%= session.getAttribute("name") %> 님 안녕하세요. </font> &nbsp;
	                 		</li>
	                 		
	                 		<c:if test="${userId != null}">
			                     <li>
			                       <a href="<c:url value='/mypage'/>" style="text-decoration:none">MY PAGE</a>
			                     </li>
			                    </c:if>
                    
	                 		
	                 		<li>
		                     	<a href="<c:url value='/logout'/>" style="text-decoration:none">LOGOUT</a>
	                     	</li>
	                     						 
	                 </c:if>
	                 	 
	              </ul>
               </div>
               
            </nav>
         </div>   
      </div>