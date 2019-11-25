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
<script>
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
      <div class="container-menu-desktop trans-03">
         <div class="wrap-menu-desktop">
            <nav class="limiter-menu-desktop p-l-45">
               
               <!-- Logo desktop -->      
               <a href="home.jsp" class="logo">
                  <img src="images/icons/inforest_logo.png" alt="IMG-LOGO">
               </a>

               <!-- Menu desktop -->
              
              
 
              <!-- 질문하기 여긴 왜 연결이 안되나요? <c태그>  -->
              
              
               <div class="menu-desktop">
                  <ul class="main-menu">
<<<<<<< HEAD
                    <li>
                         <a href="<c:url value='/main'/>" style="color:white">HOME</a>
=======
                     <li>
                        <a href="home.jsp">HOME</a>
>>>>>>> branch 'master' of https://github.com/dbp-Inforest/Inforest.git
                     </li>

                     <li>
<<<<<<< HEAD
                      <a href="<c:url value='/rank'/>" style="color:white">RANK</a>
                        </li>
=======
                        <a href="rank.jsp">RANK</a>
                     </li>
>>>>>>> branch 'master' of https://github.com/dbp-Inforest/Inforest.git

                     <li>
<<<<<<< HEAD
                      <a href="<c:url value='/product'/>" style="color:white">PRODUCT</a>
                          </li>
=======
                        <a href="product.jsp">PRODUCT</a>
                     </li>
>>>>>>> branch 'master' of https://github.com/dbp-Inforest/Inforest.git

                     <li>
<<<<<<< HEAD
                      <a href="<c:url value='/post'/>" style="color:white">POST</a>
                       </li>
=======
                        <a href="post.jsp">POST</a>
                     </li>
>>>>>>> branch 'master' of https://github.com/dbp-Inforest/Inforest.git

                     <li>
                       <a href="<c:url value='/mypage'/>" style="color:white">MY PAGE</a>
                         </li>
                     
                       <li>
                        <a href="sign-in.jsp">SIGN IN</a>
                     </li>  
                  </ul>
               </div>    
			</nav>
		</div>
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