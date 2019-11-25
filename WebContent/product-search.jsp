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
         Product Search
      </h2>
   </section>   

   
   <!-- Product -->
   <div class="bg0 m-t-23 p-b-140">
      <div class="container">
         <div class="flex-w flex-sb-m p-b-52">
            <div class="flex-w flex-l-m filter-tope-group m-tb-10">
               <button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 how-active1" data-filter="*">
                  Result
               </button>
            </div>
         </div>

         <!-- PRODUCT 목록 -->
         <% // for(int i = 0; i < 25; i++) { %>
         <!--  
         <div class="row isotope-grid">
            <div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item phone">--> <!-- 옆에 괄호 안에 phone을 laptop, camera, tablet으로 바꾸면 됨 -->
               <!-- Block2 -->
               <!--  
               <div class="block2">
                  <div class="block2-pic hov-img0">
                     <img src="images/product-01.jpg" alt="IMG-PRODUCT">
                  </div>

                  <div class="block2-txt flex-w flex-t p-t-14">
                     <div class="block2-txt-child1 flex-col-l ">
                        <a href="product-detail.jsp" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">-->
                           <!-- phone 제품명 가져오기 (name) -->
                        <!--
                        </a>

                        <span class="stext-105 cl3">  -->
                           <!-- phone 가격 가져오기 (price) -->
                           <!-- 
                        </span>
                     </div>

                     <div class="block2-txt-child2 flex-r p-t-3">
                        <a href="#" class="btn-addwish-b2 dis-block pos-relative js-addwish-b2">
                           <img class="icon-heart1 dis-block trans-04" src="images/icons/icon-heart-01.png" alt="ICON">
                           <img class="icon-heart2 dis-block trans-04 ab-t-l" src="images/icons/icon-heart-02.png" alt="ICON">
                        </a>
                     </div>
                  </div>
               </div>
            </div> -->
            <%//} %>
            
            
         <div class="row isotope-grid">
            <div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item phone">
               <!-- Block2 -->
               <div class="block2">
                  <div class="block2-pic hov-img0">
                     <img src="images/product-01.jpg" alt="IMG-PRODUCT">
                  </div>

                  <div class="block2-txt flex-w flex-t p-t-14">
                     <div class="block2-txt-child1 flex-col-l ">
                        <a href="product-detail.jsp" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                           Esprit Ruffle Shirt
                        </a>

                        <span class="stext-105 cl3">
                           $16.64
                        </span>
                     </div>

                     <div class="block2-txt-child2 flex-r p-t-3">
                        <a href="#" class="btn-addwish-b2 dis-block pos-relative js-addwish-b2">
                           <img class="icon-heart1 dis-block trans-04" src="images/icons/icon-heart-01.png" alt="ICON">
                           <img class="icon-heart2 dis-block trans-04 ab-t-l" src="images/icons/icon-heart-02.png" alt="ICON">
                        </a>
                     </div>
                  </div>
               </div>
            </div>

            <div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item phone">
               <!-- Block2 -->
               <div class="block2">
                  <div class="block2-pic hov-img0">
                     <img src="images/product-02.jpg" alt="IMG-PRODUCT">
                  </div>

                  <div class="block2-txt flex-w flex-t p-t-14">
                     <div class="block2-txt-child1 flex-col-l ">
                        <a href="product-detail.jsp" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                           Herschel supply
                        </a>

                        <span class="stext-105 cl3">
                           $35.31
                        </span>
                     </div>

                     <div class="block2-txt-child2 flex-r p-t-3">
                        <a href="#" class="btn-addwish-b2 dis-block pos-relative js-addwish-b2">
                           <img class="icon-heart1 dis-block trans-04" src="images/icons/icon-heart-01.png" alt="ICON">
                           <img class="icon-heart2 dis-block trans-04 ab-t-l" src="images/icons/icon-heart-02.png" alt="ICON">
                        </a>
                     </div>
                  </div>
               </div>
            </div>

            <div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item laptop">
               <!-- Block2 -->
               <div class="block2">
                  <div class="block2-pic hov-img0">
                     <img src="images/product-03.jpg" alt="IMG-PRODUCT">
                  </div>

                  <div class="block2-txt flex-w flex-t p-t-14">
                     <div class="block2-txt-child1 flex-col-l ">
                        <a href="product-detail.jsp" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                           Only Check Trouser
                        </a>

                        <span class="stext-105 cl3">
                           $25.50
                        </span>
                     </div>

                     <div class="block2-txt-child2 flex-r p-t-3">
                        <a href="#" class="btn-addwish-b2 dis-block pos-relative js-addwish-b2">
                           <img class="icon-heart1 dis-block trans-04" src="images/icons/icon-heart-01.png" alt="ICON">
                           <img class="icon-heart2 dis-block trans-04 ab-t-l" src="images/icons/icon-heart-02.png" alt="ICON">
                        </a>
                     </div>
                  </div>
               </div>
            </div>

            <div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item phone">
               <!-- Block2 -->
               <div class="block2">
                  <div class="block2-pic hov-img0">
                     <img src="images/product-04.jpg" alt="IMG-PRODUCT">
                  </div>

                  <div class="block2-txt flex-w flex-t p-t-14">
                     <div class="block2-txt-child1 flex-col-l ">
                        <a href="product-detail.jsp" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                           Classic Trench Coat
                        </a>

                        <span class="stext-105 cl3">
                           $75.00
                        </span>
                     </div>

                     <div class="block2-txt-child2 flex-r p-t-3">
                        <a href="#" class="btn-addwish-b2 dis-block pos-relative js-addwish-b2">
                           <img class="icon-heart1 dis-block trans-04" src="images/icons/icon-heart-01.png" alt="ICON">
                           <img class="icon-heart2 dis-block trans-04 ab-t-l" src="images/icons/icon-heart-02.png" alt="ICON">
                        </a>
                     </div>
                  </div>
               </div>
            </div>

            <div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item phone">
               <!-- Block2 -->
               <div class="block2">
                  <div class="block2-pic hov-img0">
                     <img src="images/product-05.jpg" alt="IMG-PRODUCT">
                  </div>

                  <div class="block2-txt flex-w flex-t p-t-14">
                     <div class="block2-txt-child1 flex-col-l ">
                        <a href="product-detail.jsp" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                           Front Pocket Jumper
                        </a>

                        <span class="stext-105 cl3">
                           $34.75
                        </span>
                     </div>

                     <div class="block2-txt-child2 flex-r p-t-3">
                        <a href="#" class="btn-addwish-b2 dis-block pos-relative js-addwish-b2">
                           <img class="icon-heart1 dis-block trans-04" src="images/icons/icon-heart-01.png" alt="ICON">
                           <img class="icon-heart2 dis-block trans-04 ab-t-l" src="images/icons/icon-heart-02.png" alt="ICON">
                        </a>
                     </div>
                  </div>
               </div>
            </div>

            <div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item watches">
               <!-- Block2 -->
               <div class="block2">
                  <div class="block2-pic hov-img0">
                     <img src="images/product-06.jpg" alt="IMG-PRODUCT">
                  </div>

                  <div class="block2-txt flex-w flex-t p-t-14">
                     <div class="block2-txt-child1 flex-col-l ">
                        <a href="product-detail.jsp" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                           Vintage Inspired Classic 
                        </a>

                        <span class="stext-105 cl3">
                           $93.20
                        </span>
                     </div>

                     <div class="block2-txt-child2 flex-r p-t-3">
                        <a href="#" class="btn-addwish-b2 dis-block pos-relative js-addwish-b2">
                           <img class="icon-heart1 dis-block trans-04" src="images/icons/icon-heart-01.png" alt="ICON">
                           <img class="icon-heart2 dis-block trans-04 ab-t-l" src="images/icons/icon-heart-02.png" alt="ICON">
                        </a>
                     </div>
                  </div>
               </div>
            </div>

            <div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item phone">
               <!-- Block2 -->
               <div class="block2">
                  <div class="block2-pic hov-img0">
                     <img src="images/product-07.jpg" alt="IMG-PRODUCT">
                  </div>

                  <div class="block2-txt flex-w flex-t p-t-14">
                     <div class="block2-txt-child1 flex-col-l ">
                        <a href="product-detail.jsp" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                           Shirt in Stretch Cotton
                        </a>

                        <span class="stext-105 cl3">
                           $52.66
                        </span>
                     </div>

                     <div class="block2-txt-child2 flex-r p-t-3">
                        <a href="#" class="btn-addwish-b2 dis-block pos-relative js-addwish-b2">
                           <img class="icon-heart1 dis-block trans-04" src="images/icons/icon-heart-01.png" alt="ICON">
                           <img class="icon-heart2 dis-block trans-04 ab-t-l" src="images/icons/icon-heart-02.png" alt="ICON">
                        </a>
                     </div>
                  </div>
               </div>
            </div>

            <div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item phone">
               <!-- Block2 -->
               <div class="block2">
                  <div class="block2-pic hov-img0">
                     <img src="images/product-08.jpg" alt="IMG-PRODUCT">
                  </div>

                  <div class="block2-txt flex-w flex-t p-t-14">
                     <div class="block2-txt-child1 flex-col-l ">
                        <a href="product-detail.jsp" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                           Pieces Metallic Printed
                        </a>

                        <span class="stext-105 cl3">
                           $18.96
                        </span>
                     </div>

                     <div class="block2-txt-child2 flex-r p-t-3">
                        <a href="#" class="btn-addwish-b2 dis-block pos-relative js-addwish-b2">
                           <img class="icon-heart1 dis-block trans-04" src="images/icons/icon-heart-01.png" alt="ICON">
                           <img class="icon-heart2 dis-block trans-04 ab-t-l" src="images/icons/icon-heart-02.png" alt="ICON">
                        </a>
                     </div>
                  </div>
               </div>
            </div>

            <div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item tablet">
               <!-- Block2 -->
               <div class="block2">
                  <div class="block2-pic hov-img0">
                     <img src="images/product-09.jpg" alt="IMG-PRODUCT">
                  </div>

                  <div class="block2-txt flex-w flex-t p-t-14">
                     <div class="block2-txt-child1 flex-col-l ">
                        <a href="product-detail.jsp" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                           Converse All Star Hi Plimsolls
                        </a>

                        <span class="stext-105 cl3">
                           $75.00
                        </span>
                     </div>

                     <div class="block2-txt-child2 flex-r p-t-3">
                        <a href="#" class="btn-addwish-b2 dis-block pos-relative js-addwish-b2">
                           <img class="icon-heart1 dis-block trans-04" src="images/icons/icon-heart-01.png" alt="ICON">
                           <img class="icon-heart2 dis-block trans-04 ab-t-l" src="images/icons/icon-heart-02.png" alt="ICON">
                        </a>
                     </div>
                  </div>
               </div>
            </div>

            <div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item phone">
               <!-- Block2 -->
               <div class="block2">
                  <div class="block2-pic hov-img0">
                     <img src="images/product-10.jpg" alt="IMG-PRODUCT">
                  </div>

                  <div class="block2-txt flex-w flex-t p-t-14">
                     <div class="block2-txt-child1 flex-col-l ">
                        <a href="product-detail.jsp" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                           Femme T-Shirt In Stripe
                        </a>

                        <span class="stext-105 cl3">
                           $25.85
                        </span>
                     </div>

                     <div class="block2-txt-child2 flex-r p-t-3">
                        <a href="#" class="btn-addwish-b2 dis-block pos-relative js-addwish-b2">
                           <img class="icon-heart1 dis-block trans-04" src="images/icons/icon-heart-01.png" alt="ICON">
                           <img class="icon-heart2 dis-block trans-04 ab-t-l" src="images/icons/icon-heart-02.png" alt="ICON">
                        </a>
                     </div>
                  </div>
               </div>
            </div>

            <div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item laptop">
               <!-- Block2 -->
               <div class="block2">
                  <div class="block2-pic hov-img0">
                     <img src="images/product-11.jpg" alt="IMG-PRODUCT">
                  </div>

                  <div class="block2-txt flex-w flex-t p-t-14">
                     <div class="block2-txt-child1 flex-col-l ">
                        <a href="product-detail.jsp" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                           Herschel supply 
                        </a>

                        <span class="stext-105 cl3">
                           $63.16
                        </span>
                     </div>

                     <div class="block2-txt-child2 flex-r p-t-3">
                        <a href="#" class="btn-addwish-b2 dis-block pos-relative js-addwish-b2">
                           <img class="icon-heart1 dis-block trans-04" src="images/icons/icon-heart-01.png" alt="ICON">
                           <img class="icon-heart2 dis-block trans-04 ab-t-l" src="images/icons/icon-heart-02.png" alt="ICON">
                        </a>
                     </div>
                  </div>
               </div>
            </div>

            <div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item laptop">
               <!-- Block2 -->
               <div class="block2">
                  <div class="block2-pic hov-img0">
                     <img src="images/product-12.jpg" alt="IMG-PRODUCT">
                  </div>

                  <div class="block2-txt flex-w flex-t p-t-14">
                     <div class="block2-txt-child1 flex-col-l ">
                        <a href="product-detail.jsp" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                           Herschel supply
                        </a>

                        <span class="stext-105 cl3">
                           $63.15
                        </span>
                     </div>

                     <div class="block2-txt-child2 flex-r p-t-3">
                        <a href="#" class="btn-addwish-b2 dis-block pos-relative js-addwish-b2">
                           <img class="icon-heart1 dis-block trans-04" src="images/icons/icon-heart-01.png" alt="ICON">
                           <img class="icon-heart2 dis-block trans-04 ab-t-l" src="images/icons/icon-heart-02.png" alt="ICON">
                        </a>
                     </div>
                  </div>
               </div>
            </div>

            <div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item phone">
               <!-- Block2 -->
               <div class="block2">
                  <div class="block2-pic hov-img0">
                     <img src="images/product-13.jpg" alt="IMG-PRODUCT">
                  </div>

                  <div class="block2-txt flex-w flex-t p-t-14">
                     <div class="block2-txt-child1 flex-col-l ">
                        <a href="product-detail.jsp" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                           T-Shirt with Sleeve
                        </a>

                        <span class="stext-105 cl3">
                           $18.49
                        </span>
                     </div>

                     <div class="block2-txt-child2 flex-r p-t-3">
                        <a href="#" class="btn-addwish-b2 dis-block pos-relative js-addwish-b2">
                           <img class="icon-heart1 dis-block trans-04" src="images/icons/icon-heart-01.png" alt="ICON">
                           <img class="icon-heart2 dis-block trans-04 ab-t-l" src="images/icons/icon-heart-02.png" alt="ICON">
                        </a>
                     </div>
                  </div>
               </div>
            </div>

            <div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item phone">
               <!-- Block2 -->
               <div class="block2">
                  <div class="block2-pic hov-img0">
                     <img src="images/product-14.jpg" alt="IMG-PRODUCT">
                  </div>

                  <div class="block2-txt flex-w flex-t p-t-14">
                     <div class="block2-txt-child1 flex-col-l ">
                        <a href="product-detail.jsp" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                           Pretty Little Thing
                        </a>

                        <span class="stext-105 cl3">
                           $54.79
                        </span>
                     </div>

                     <div class="block2-txt-child2 flex-r p-t-3">
                        <a href="#" class="btn-addwish-b2 dis-block pos-relative js-addwish-b2">
                           <img class="icon-heart1 dis-block trans-04" src="images/icons/icon-heart-01.png" alt="ICON">
                           <img class="icon-heart2 dis-block trans-04 ab-t-l" src="images/icons/icon-heart-02.png" alt="ICON">
                        </a>
                     </div>
                  </div>
               </div>
            </div>

            <div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item watches">
               <!-- Block2 -->
               <div class="block2">
                  <div class="block2-pic hov-img0">
                     <img src="images/product-15.jpg" alt="IMG-PRODUCT">
                  </div>

                  <div class="block2-txt flex-w flex-t p-t-14">
                     <div class="block2-txt-child1 flex-col-l ">
                        <a href="product-detail.jsp" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                           Mini Silver Mesh Watch
                        </a>

                        <span class="stext-105 cl3">
                           $86.85
                        </span>
                     </div>

                     <div class="block2-txt-child2 flex-r p-t-3">
                        <a href="#" class="btn-addwish-b2 dis-block pos-relative js-addwish-b2">
                           <img class="icon-heart1 dis-block trans-04" src="images/icons/icon-heart-01.png" alt="ICON">
                           <img class="icon-heart2 dis-block trans-04 ab-t-l" src="images/icons/icon-heart-02.png" alt="ICON">
                        </a>
                     </div>
                  </div>
               </div>
            </div>

            <div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item phone">
               <!-- Block2 -->
               <div class="block2">
                  <div class="block2-pic hov-img0">
                     <img src="images/product-16.jpg" alt="IMG-PRODUCT">
                  </div>

                  <div class="block2-txt flex-w flex-t p-t-14">
                     <div class="block2-txt-child1 flex-col-l ">
                        <a href="product-detail.jsp" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                           Square Neck Back
                        </a>

                        <span class="stext-105 cl3">
                           $29.64
                        </span>
                     </div>

                     <div class="block2-txt-child2 flex-r p-t-3">
                        <a href="#" class="btn-addwish-b2 dis-block pos-relative js-addwish-b2">
                           <img class="icon-heart1 dis-block trans-04" src="images/icons/icon-heart-01.png" alt="ICON">
                           <img class="icon-heart2 dis-block trans-04 ab-t-l" src="images/icons/icon-heart-02.png" alt="ICON">
                        </a>
                     </div>
                  </div>
               </div>
            </div>
         </div>

         <!-- Load more -->
         <div class="flex-c-m flex-w w-full p-t-45">
            <a href="#" class="flex-c-m stext-101 cl5 size-103 bg2 bor1 hov-btn1 p-lr-15 trans-04">
               Load More
            </a>
         </div>
      </div>
   </div>
      
   <!-- Back to top -->
   <div class="btn-back-to-top" id="myBtn">
      <span class="symbol-btn-back-to-top">
         <i class="zmdi zmdi-chevron-up"></i>
      </span>
   </div>

   <!-- Modal1 -->
   <div class="wrap-modal1 js-modal1 p-t-60 p-b-20">
      <div class="overlay-modal1 js-hide-modal1"></div>

      <div class="container">
         <div class="bg0 p-t-60 p-b-30 p-lr-15-lg how-pos3-parent">
            <button class="how-pos3 hov3 trans-04 js-hide-modal1">
               <img src="images/icons/icon-close.png" alt="CLOSE">
            </button>

            <div class="row">
               <div class="col-md-6 col-lg-7 p-b-30">
                  <div class="p-l-25 p-r-30 p-lr-0-lg">
                     <div class="wrap-slick3 flex-sb flex-w">
                        <div class="wrap-slick3-dots"></div>
                        <div class="wrap-slick3-arrows flex-sb-m flex-w"></div>

                        <div class="slick3 gallery-lb">
                           <div class="item-slick3" data-thumb="images/product-detail-01.jpg">
                              <div class="wrap-pic-w pos-relative">
                                 <img src="images/product-detail-01.jpg" alt="IMG-PRODUCT">

                                 <a class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04" href="images/product-detail-01.jpg">
                                    <i class="fa fa-expand"></i>
                                 </a>
                              </div>
                           </div>

                           <div class="item-slick3" data-thumb="images/product-detail-02.jpg">
                              <div class="wrap-pic-w pos-relative">
                                 <img src="images/product-detail-02.jpg" alt="IMG-PRODUCT">

                                 <a class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04" href="images/product-detail-02.jpg">
                                    <i class="fa fa-expand"></i>
                                 </a>
                              </div>
                           </div>

                           <div class="item-slick3" data-thumb="images/product-detail-03.jpg">
                              <div class="wrap-pic-w pos-relative">
                                 <img src="images/product-detail-03.jpg" alt="IMG-PRODUCT">

                                 <a class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04" href="images/product-detail-03.jpg">
                                    <i class="fa fa-expand"></i>
                                 </a>
                              </div>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
               
               <div class="col-md-6 col-lg-5 p-b-30">
                  <div class="p-r-50 p-t-5 p-lr-0-lg">
                     <h4 class="mtext-105 cl2 js-name-detail p-b-14">
                        Lightweight Jacket
                     </h4>

                     <span class="mtext-106 cl2">
                        $58.79
                     </span>

                     <p class="stext-102 cl3 p-t-23">
                        Nulla eget sem vitae eros pharetra viverra. Nam vitae luctus ligula. Mauris consequat ornare feugiat.
                     </p>
                     
                     <!--  -->
                     <div class="p-t-33">
                        <div class="flex-w flex-r-m p-b-10">
                           <div class="size-203 flex-c-m respon6">
                              Size
                           </div>

                           <div class="size-204 respon6-next">
                              <div class="rs1-select2 bor8 bg0">
                                 <select class="js-select2" name="time">
                                    <option>Choose an option</option>
                                    <option>Size S</option>
                                    <option>Size M</option>
                                    <option>Size L</option>
                                    <option>Size XL</option>
                                 </select>
                                 <div class="dropDownSelect2"></div>
                              </div>
                           </div>
                        </div>

                        <div class="flex-w flex-r-m p-b-10">
                           <div class="size-203 flex-c-m respon6">
                              Color
                           </div>

                           <div class="size-204 respon6-next">
                              <div class="rs1-select2 bor8 bg0">
                                 <select class="js-select2" name="time">
                                    <option>Choose an option</option>
                                    <option>Red</option>
                                    <option>Blue</option>
                                    <option>White</option>
                                    <option>Grey</option>
                                 </select>
                                 <div class="dropDownSelect2"></div>
                              </div>
                           </div>
                        </div>

                        <div class="flex-w flex-r-m p-b-10">
                           <div class="size-204 flex-w flex-m respon6-next">
                              <div class="wrap-num-product flex-w m-r-20 m-tb-10">
                                 <div class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m">
                                    <i class="fs-16 zmdi zmdi-minus"></i>
                                 </div>

                                 <input class="mtext-104 cl3 txt-center num-product" type="number" name="num-product" value="1">

                                 <div class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m">
                                    <i class="fs-16 zmdi zmdi-plus"></i>
                                 </div>
                              </div>

                              <button class="flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04 js-addcart-detail">
                                 Add to cart
                              </button>
                           </div>
                        </div>   
                     </div>

                     <!--  -->
                     <div class="flex-w flex-m p-l-100 p-t-40 respon7">
                        <div class="flex-m bor9 p-r-10 m-r-11">
                           <a href="#" class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 js-addwish-detail tooltip100" data-tooltip="Add to Wishlist">
                              <i class="zmdi zmdi-favorite"></i>
                           </a>
                        </div>

                        <a href="#" class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 m-r-8 tooltip100" data-tooltip="Facebook">
                           <i class="fa fa-facebook"></i>
                        </a>

                        <a href="#" class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 m-r-8 tooltip100" data-tooltip="Twitter">
                           <i class="fa fa-twitter"></i>
                        </a>

                        <a href="#" class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 m-r-8 tooltip100" data-tooltip="Google Plus">
                           <i class="fa fa-google-plus"></i>
                        </a>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </div>
</body>
</html>