<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <title>Sign In</title>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <link rel="stylesheet" type="text/css" href="css/util.css">
   <link rel="stylesheet" type="text/css" href="css/main.css">
<!--===============================================================================================-->



<script>
   function login() {
      if (form.userId.value == "") {
         alert("사용자 ID를 입력하십시오.");
         form.userId.focus();
         return false;
      } 
      if (form.password.value == "") {
         alert("비밀번호를 입력하십시오.");
         form.password.focus();
         return false;
      }      
      form.submit();
   }
   
   function userCreate(targetUri) {
      alert(targetUri);   
      form.action = targetUri;
      form.submit();
   }
</script>


</head>


<body>
      <!-- Header -->
   <header class="header-v3">
      <!-- Header desktop -->
      <div class="container-menu-desktop">
         <div class ="wrap-menu-desktop how-shadow1">
            <nav class="limiter-menu-desktop container">
               
               <!-- Logo desktop -->      
               <a href="home.jsp" class="logo">
                     <img src="images/icons/inforest_logo.png" alt="IMG-LOGO">
                     </a>

               <!-- Menu desktop -->
               <div class="menu-desktop">
                 <ul class="main-menu">
                     <li>
                      <button type="button" onClick="userCreate('<c:url value='/main'/>')" style="color:white">HOME</button>
                     </li>

                     <li>
                       <button type="button" onClick="userCreate('<c:url value='/rank'/>')" style="color:white">RANK</button>
                     </li>

                     <li>
                       <button type="button" onClick="userCreate('<c:url value='/product'/>')" style="color:white">PRODUCT</button>
                     </li>

                     <li>
                        <button type="button" onClick="userCreate('<c:url value='/post'/>')" style="color:white">POST</button>
                     </li>

                     <li>
                        <button type="button" onClick="userCreate('<c:url value='/mypage'/>')" style="color:white">MYPAGE</button>
                     </li>
                     
                     <li>
                        <button type="button" onClick="userCreate('<c:url value='/signIn'/>')" style="color:white">SIGN-IN</button>
                    </li>
                     
                  </ul>
               </div>
            </nav>
         </div>   
      </div>
   </header>

   
   <!-- Title page -->
   <section class="bg-img1 txt-center p-lr-15 p-tb-92" style="background-image: url('images/sign_bg.png');">
      <h2 class="ltext-105 cl0 txt-center">
         Sign In
      </h2>
   </section>   


<div style="height:50px;">&nbsp;</div>


<!-- 로그인 테이블 -->
<div class="container">
<div class="row">
<form class="bg0 p-t-75 p-b-85" name="form" method="POST" action="<c:url value='/login'/>" style="position:absolute;left:50%;margin:0 0 0 -310px;">
   <table style="width:750px; margin-left: auto; margin-right: auto;" class="table-sign">
      <tr class="table_row">
         <td class="column-1" >
            ID
         </td>
         <td class="column-2">
            <input class="stext-104 cl2 plh4 size-sign bor13 p-lr-20 m-r-10 m-tb-5" type="text" name="userId" placeholder="ENTER YOUR ID">
         </td>
      </tr>
      
      <tr class="table_row">
         <td class="column-1">
            PASSWORD
         </td>
         <td class="column-2">
            <input class="stext-104 cl2 plh4 size-sign bor13 p-lr-20 m-r-10 m-tb-5" type="password" name="password" placeholder="ENTER YOUR PASSWORD">
         </td>
      </tr>
                        
      <tr class="table_row">
      <td class="column-1">
         <div class="flex-c-m stext-101 cl2 size-submit bg8 bor13 hov-btn3  trans-04 pointer m-tb-5">
       <button type="button" onClick="userCreate('<c:url value='/signUp'/>')">GO TO SIGN-UP</button>
                 
         </div>
      </td>
      <td class="column-2">
         <div class="flex-c-m stext-101 cl2 size-submit bg8 bor13 hov-btn3  trans-04 pointer m-tb-10">
            <button type="button">LOGIN</button>
         </div>
      </td>
   </tr>
   </table>
</form>
</div>
</div>
</body>
</html>