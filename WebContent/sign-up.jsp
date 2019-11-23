<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <title>SIGN-UP</title>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <link rel="stylesheet" type="text/css" href="css/util.css">
   <link rel="stylesheet" type="text/css" href="css/main.css">
<!--===============================================================================================-->

<script>
function userCreate() {
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
   if (form.name.value == "") {
      alert("이름을 입력하십시오.");
      form.name.focus();
      return false;
   }
   form.submit();
}

function userList(targetUri) {
	alert(targetUri);	
	form.action = targetUri;
	form.submit();
}
	
</script>




</head>
<body class="animsition">
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


   <!-- Cart -->
    <div class="wrap-header-cart js-panel-cart">
      <div class="s-full js-hide-cart"></div>

      <div class="header-cart flex-col-l p-l-65 p-r-25">
         <div class="header-cart-title flex-w flex-sb-m p-b-8">
            <span class="mtext-103 cl2">
               ♡ LIKE ♡
            </span>

            <div class="fs-35 lh-10 cl2 p-lr-5 pointer hov-cl1 trans-04 js-hide-cart">
               <i class="zmdi zmdi-close"></i>
            </div>
         </div>
         
         <div class="header-cart-content flex-w js-pscroll">
            <ul class="header-cart-wrapitem w-full">
               <li class="header-cart-item flex-w flex-t m-b-12">
                  <div class="header-cart-item-img">
                     <img src="images/item-cart-01.jpg" alt="IMG">
                  </div>

                  <div class="header-cart-item-txt p-t-8">
                     <a href="#" class="header-cart-item-name m-b-18 hov-cl1 trans-04">
                        White Shirt Pleat
                     </a>

                     <span class="header-cart-item-info">
                        1 x $19.00
                     </span>
                  </div>
                 </li>

               <li class="header-cart-item flex-w flex-t m-b-12">
                  <div class="header-cart-item-img">
                     <img src="images/item-cart-02.jpg" alt="IMG">
                  </div>

                  <div class="header-cart-item-txt p-t-8">
                     <a href="#" class="header-cart-item-name m-b-18 hov-cl1 trans-04">
                        Converse All Star
                     </a>

                     <span class="header-cart-item-info">
                        1 x $39.00
                     </span>
                  </div>
               </li>

               <li class="header-cart-item flex-w flex-t m-b-12">
                  <div class="header-cart-item-img">
                     <img src="images/item-cart-03.jpg" alt="IMG">
                  </div>

                  <div class="header-cart-item-txt p-t-8">
                     <a href="#" class="header-cart-item-name m-b-18 hov-cl1 trans-04">
                        Nixon Porter Leather
                     </a>

                     <span class="header-cart-item-info">
                        1 x $17.00
                     </span>
                  </div>
               </li>
            </ul>
         </div>
      </div>
   </div>

   <!-- Title page -->
   <section class="bg-img1 txt-center p-lr-15 p-tb-92" style="background-image: url('images/sign_bg.png');">
      <h2 class="ltext-105 cl0 txt-center">
         Sign Up
      </h2>
   </section>   


<div style="height:50px;">&nbsp;</div>


<!-- 회원가입 테이블 -->
<div class="container">
<div class="row">
<div class="col-lg-10 col-xl-7 m-lr-auto m-b-50">
<div class="m-l-25 m-r--38 m-lr-0-xl">
<form class="bg0 p-t-75 p-b-85" name="form" method="POST" action="<c:url value='/register' />">
   <table style="width:100%; margin-left: auto; margin-right: auto;" class="table-sign">
   <tr class="table_row">
		<td class="column-1" >
			ID
		</td>
		<td class="column-2">
			<input class="stext-104 cl2 plh4 size-sign bor13 p-lr-20 m-r-10 m-tb-5" type="text" name="userId" placeholder="">
		</td>
	</tr>

	<tr class="table_row">
		<td class="column-1">
			PASSWORD
		</td>
		<td class="column-2">
			<input class="stext-104 cl2 plh4 size-sign bor13 p-lr-20 m-r-10 m-tb-5" type="password" name="password" placeholder="">
		</td>
	</tr>
	
	<tr class="table_row">
		<td class="column-1">
			NAME
		</td>
		<td class="column-2">
			<input class="stext-104 cl2 plh4 size-sign bor13 p-lr-20 m-r-10 m-tb-5" type="text" name="name" placeholder="">
		</td>
	</tr>
	
	<tr class="table_row">
		<td class="column-1">
			AGE
		</td>
		<td class="column-2">
			<input class="stext-104 cl2 plh4 size-sign bor13 p-lr-20 m-r-10 m-tb-5" type="text" name="age" placeholder="">
		</td>
	</tr>
	
	<tr class="table_row">
		<td class="column-1">
			GENDER
		</td>
		<td class="column-2" >
		<div class="flex-l-m flex-w w-full p-t-10 m-lr--7">		
			&nbsp;&nbsp;&nbsp;<input type="radio" name="gender" value="0"> &nbsp; WOMAN &nbsp;&nbsp;&nbsp;
			<input type="radio" name="gender" value="1"> &nbsp; MAN
			<!-- <input class="stext-104 cl2 plh4 size-sign bor13 p-lr-20 m-r-10 m-tb-5" type="text" name="coupon" placeholder=""> -->
		</div>
		</td>
	</tr>
	
	<tr class="table_row">
		<td class="column-1">
			POSITION
		</td>
		<td class="column-2">
		<div class="flex-l-m flex-w w-full p-t-10 m-lr--7">	
		 	&nbsp;&nbsp;&nbsp;<input type="radio" name="position" value="0">&nbsp; ADMIN &nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;<input type="radio" name="position" value="1">&nbsp; EDITOR &nbsp;&nbsp;&nbsp;
			<input type="radio" name="position" value="2">&nbsp; USER
			<!-- <input class="stext-104 cl2 plh4 size-sign bor13 p-lr-20 m-r-10 m-tb-5" type="text" name="coupon" placeholder=""> -->
		</div>	
		</td>
	</tr>
	
	<tr class="table_row">
		<td class="column-1">
			INTRODUCE
		</td>
		<td class="column-2">
			<textarea class="stext-104 cl2 plh4 size-introduce bor13 p-lr-20 m-r-10 m-tb-5" type="text" name="introduce" placeholder="" rows="5">
			</textarea>
		</td>
	</tr>
								
	<tr class="table_row">
		<td class="column-1">
			<div class="flex-c-m stext-101 cl2 size-submit bg8 bor13 hov-btn3  trans-04 pointer m-tb-5">
				 <button type="button" onClick="userList('<c:url value='/signIn'/>')">GO TO LOGIN</button>
			</div>
		</td>
		<td class="column-2">
			<div class="flex-c-m stext-101 cl2 size-submit bg8 bor13 hov-btn3  trans-04 pointer m-tb-10">
				<button type="button" value="회원 가입" onClick="userCreate()">SIGN UP</button>
			</div>
		</td>
   </tr>
   </table>
   </form>
   </div>
   </div>
   </div>
   </div>

   



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