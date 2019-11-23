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
                        <a href="home.jsp">HOME</a>
                     </li>

                     <li>
                        <a href="rank.jsp">RANK</a>
                     </li>

                     <li>
                        <a href="product.jsp">PRODUCT</a>
                     </li>

                     <li>
                        <a href="post.jsp">POST</a>
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
   



<!--===============================================================================================-->   
   <script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
   <script src="vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
   <script src="vendor/bootstrap/js/popper.js"></script>
   <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
   <script src="vendor/select2/select2.min.js"></script>
   <script>
      $(".js-select2").each(function(){
         $(this).select2({
            minimumResultsForSearch: 20,
            dropdownParent: $(this).next('.dropDownSelect2')
         });
      })
   </script>
<!--===============================================================================================-->
   <script src="vendor/daterangepicker/moment.min.js"></script>
   <script src="vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
   <script src="vendor/slick/slick.min.js"></script>
   <script src="js/slick-custom.js"></script>
<!--===============================================================================================-->
   <script src="vendor/parallax100/parallax100.js"></script>
   <script>
        $('.parallax100').parallax100();
   </script>
<!--===============================================================================================-->
   <script src="vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
   <script>
      $('.gallery-lb').each(function() { // the containers for all your galleries
         $(this).magnificPopup({
              delegate: 'a', // the selector for gallery item
              type: 'image',
              gallery: {
                 enabled:true
              },
              mainClass: 'mfp-fade'
          });
      });
   </script>
<!--===============================================================================================-->
   <script src="vendor/isotope/isotope.pkgd.min.js"></script>
<!--===============================================================================================-->
   <script src="vendor/sweetalert/sweetalert.min.js"></script>
   <script>
      $('.js-addwish-b2').on('click', function(e){
         e.preventDefault();
      });
      $('.js-addwish-b2').each(function(){
         var nameProduct = $(this).parent().parent().find('.js-name-b2').jsp();
         $(this).on('click', function(){
            swal(nameProduct, "is added to wishlist !", "success");
            $(this).addClass('js-addedwish-b2');
            $(this).off('click');
         });
      });
      $('.js-addwish-detail').each(function(){
         var nameProduct = $(this).parent().parent().parent().find('.js-name-detail').jsp();
         $(this).on('click', function(){
            swal(nameProduct, "is added to wishlist !", "success");
            $(this).addClass('js-addedwish-detail');
            $(this).off('click');
         });
      });
      /*---------------------------------------------*/
      $('.js-addcart-detail').each(function(){
         var nameProduct = $(this).parent().parent().parent().parent().find('.js-name-detail').jsp();
         $(this).on('click', function(){
            swal(nameProduct, "is added to cart !", "success");
         });
      });
   </script>
<!--===============================================================================================-->
   <script src="vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
   <script>
      $('.js-pscroll').each(function(){
         $(this).css('position','relative');
         $(this).css('overflow','hidden');
         var ps = new PerfectScrollbar(this, {
            wheelSpeed: 1,
            scrollingThreshold: 1000,
            wheelPropagation: false,
         });
         $(window).on('resize', function(){
            ps.update();
         })
      });
   </script>
<!--===============================================================================================-->
   <script src="js/main.js"></script>

</body>
</html>