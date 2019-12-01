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

<script>
	function manageView(kind, targetUri) {
	    alert(targetUri);   
	    form.action = targetUri;
	
	    form.kind2.value = kind;
	    alert(form.kind2.value);
	
	    form.submit();
	 }
</script>


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
               <div class="menu-desktop" style="float:left">
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

                     <li>
                       <a href="<c:url value='/mypage'/>" style="text-decoration:none">MY PAGE</a>
                     </li>
                     <%
                     	if(session.getAttribute("userId") == null) { %>
	                     <li>
	                        <a href="<c:url value='/signIn'/>" style="text-decoration:none">LOGIN</a>
	                     </li> 
	                     </ul> 
                     <% } else {
	                	 	if((int)session.getAttribute("position") == 0) { %>
	                     <li>
	                        <a href="<c:url value='/management'/>" style="text-decoration:none">MANAGEMENT</a>
	                     </li> 
                     	<% } %>	
	                     </ul>
	                     <div class="menu-desktop" style="float:right">
	                     	<font style="color:white"><%= session.getAttribute("userId") %> 님 안녕하세요. </font> &nbsp;
	                     	<a href="<c:url value='/logout'/>" style="text-decoration:none">LOGOUT</a>
	                     </div>
					 <% } %>		 
               </div>   
            </nav>
         </div>   
      </div>
   </header>

   
   <!-- Title page -->
   <section class="bg-img1 txt-center p-lr-15 p-tb-92" style="background-image: url('images/sign_bg.png');">
      <h2 class="ltext-105 cl0 txt-center">
        Management
      </h2>
   </section>   


<div style="height:50px;">&nbsp;</div>

<form name="form" action="<c:url value='/signUpdate'/>">
	<input type="hidden" name="kind2" value="10"/>
	<div class="flex-w flex-c-m m-tb-10">	
		<!-- Product Insert -->
		<div class="flex-c-m stext-106 cl6 size-104 bor4 pointer hov-btn3 trans-04 m-r-8 m-tb-4" style="width:300px;height:200px">
			<input type="button" onClick="manageView(0, '<c:url value='/insertProduct'/>')" style="font-size:25pt; background-color:transparent;" value="Insert Product"/>
		</div>
		
		<!-- Product Update -->
		<div class="flex-c-m stext-106 cl6 size-104 bor4 pointer hov-btn3 trans-04 m-r-8 m-tb-4" style="width:300px;height:200px">
			<input type="button" onClick="manageView(0, '<c:url value='/updateProduct'/>')" style="font-size:25pt; background-color:transparent;" value="Update Product"/>
		</div>
		
		<!-- Product Select -->
		<div class="flex-c-m stext-106 cl6 size-104 bor4 pointer hov-btn3 trans-04 m-r-8 m-tb-4" style="width:300px;height:200px">
			<input type="button" onClick="manageView(0, '<c:url value='/deleteProduct'/>')" style="font-size:25pt; background-color:transparent;" value="Delete Product"/>
		</div>
	</div>
</form>