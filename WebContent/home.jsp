<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
      <div class="container-menu-desktop trans-03">
         <div class="wrap-menu-desktop">
            <nav class="limiter-menu-desktop p-l-45">
               
               <!-- Logo desktop -->      
               <a href="home.jsp" class="logo">
                  <img src="images/icons/inforest_logo.png" alt="IMG-LOGO">
               </a>

               <!-- Menu desktop -->
               <div class="menu-desktop">
                  <ul class="main-menu">
                     <li>
                        <a href="<c:url value='/main'/>">HOME</a>
                     </li>

                     <li>
                        <a href="<c:url value='/rank'/>">RANK</a>
                     </li>

                     <li>
                        <a href="<c:url value='/product'/>">PRODUCT</a>
                     </li>

                     <li>
                        <a href="<c:url value='/post'/>">POST</a>
                     </li>

                     <li>
                        <a href="mypage.jsp">MYPAGE</a>
                     </li>        
                     
                       <li>
                        <a href="sign-in.jsp">SIGN IN</a>
                     </li>  
                  </ul>


               </div>    

         <!-- Modal Search -->
      <div class="modal-search-header flex-c-m trans-04 js-hide-modal-search">
         <button class="flex-c-m btn-hide-modal-search trans-04">
            <i class="zmdi zmdi-close"></i>
         </button>

         <form class="container-search-header">
            <div class="wrap-search-header">
               <input class="plh0" type="text" name="search" placeholder="Search...">

               <button class="flex-c-m trans-04">
                  <i class="zmdi zmdi-search"></i>
               </button>
            </div>
         </form>
      </div>
   </header>




   <!-- Slider -->
   <section class="section-slide">
      <div class="wrap-slick1 rs2-slick1">
         <div class="slick1">
         <div class="item-slick1 bg-overlay1" style="background-image: url(images/canon.jpg);" data-thumb="images/canon.jpg" data-caption="New Canon">
               <div class="container h-full"></div>
            </div>
         <div class="wrap-slick1-dots p-lr-10"></div>
      </div>
   </section>
</body>
</html>