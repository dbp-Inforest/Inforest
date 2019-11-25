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
         <div class="wrap-menu-desktop how-shadow1">
            <nav class="limiter-menu-desktop p-l-45">
               
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
   <section class="bg-img1 txt-center p-lr-15 p-tb-92" style="background-image: url('images/rank_bg.png'); " >
      <h2 class="ltext-105 cl0 txt-center">
         Rank
      </h2>
   </section>   

      

<!-- Rank -->
<!-- 이미지 크기는 80*80 픽셀 (.jpg) -->
<form class="bg0 p-t-75 p-b-85" style="position:absolute;left:50%;margin:0 0 0 -515px;">
		<div class="container" >
			<div class="row"  style="display:inline">
				<div class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50" style="display:inline;float:left;width:500px">
					<div class="bor10 p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm">
						<h4 class="mtext-109 cl2 p-b-12">
                     Phone
                  </h4>
                  <hr>
                  <table>
                     <tr class="table_head">
                        <th class="column-0">Rank</th>
                                    <th class="column-1" style="padding-left:20px"><p style="padding-left:10px">Image</p></th>
                                    <th class="column-2"><p style="padding-left:40px">Name</p></th>
                        </tr>
                        <tr class="table_row">
                                       <td class="column-0">1</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/phone_img.jpg" alt="IMG">
                                       </div>
                                    </td>
                                    <td class="column-2">iPhone 11</td>   
                              </tr>
                              <tr class="table_row">
                                    <td class="column-0">2</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/phone_img.jpg" alt="IMG">
                                      </div>
                                    </td>
                                    <td class="column-2">iPhone 11 pro</td>
                                </tr>
                                <tr class="table_row">
                                       <td class="column-0">3</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/phone_img.jpg" alt="IMG">
                                       </div>
                                    </td>
                                    <td class="column-2">iPhone 11 pro MAX</td>   
                              </tr>
                              <tr class="table_row">
                                    <td class="column-0">4</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/phone_img.jpg" alt="IMG">
                                      </div>
                                    </td>
                                    <td class="column-2">iPhone XS</td>
                                </tr>
                                <tr class="table_row">
                                       <td class="column-0">5</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/phone_img.jpg" alt="IMG">
                                       </div>
                                    </td>
                                    <td class="column-2">iPhone XS MAX</td>   
                              </tr>
                              <tr class="table_row">
                                    <td class="column-0">6</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/phone_img.jpg" alt="IMG">
                                      </div>
                                    </td>
                                    <td class="column-2">iPhone XR</td>
                                </tr>
                                <tr class="table_row">
                                       <td class="column-0">7</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/phone_img.jpg" alt="IMG">
                                       </div>
                                    </td>
                                    <td class="column-2">iPhone X</td>   
                              </tr>
                              <tr class="table_row">
                                    <td class="column-0">8</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/phone_img.jpg" alt="IMG">
                                      </div>
                                    </td>
                                    <td class="column-2">Galaxy S10e</td>
                                </tr>
                                <tr class="table_row">
                                       <td class="column-0">9</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/phone_img.jpg" alt="IMG">
                                       </div>
                                    </td>
                                    <td class="column-2">Galaxy S10</td>   
                              </tr>
                              <tr class="table_row">
                                    <td class="column-0">10</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/phone_img.jpg" alt="IMG">
                                      </div>
                                    </td>
                                    <td class="column-2">Galaxy S10+</td>
                                </tr>
                  </table>
               </div>
            </div>
          <div class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50" style="display:inline;float:left;width:500px">
					<div class="bor10 p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm">
						<h4 class="mtext-109 cl2 p-b-12">
                     LapTop
                  </h4>
                  <hr>
                  <table>
                     <tr class="table_head">
                        <th class="column-0">Rank</th>
                                    <th class="column-1" style="padding-left:20px"><p style="padding-left:10px">Image</p></th>
                                    <th class="column-2"><p style="padding-left:40px">Name</p></th>
                        </tr>
                        <tr class="table_row">
                                       <td class="column-0">1</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/laptop_img.jpg" alt="IMG">
                                       </div>
                                    </td>
                                    <td class="column-2">울트라PC</td>   
                              </tr>
                              <tr class="table_row">
                                    <td class="column-0">2</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/laptop_img.jpg" alt="IMG">
                                      </div>
                                    </td>
                                    <td class="column-2">울트라기어</td>
                                </tr>
                                <tr class="table_row">
                                       <td class="column-0">3</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/laptop_img.jpg" alt="IMG">
                                       </div>
                                    </td>
                                    <td class="column-2">그램2in1</td>   
                              </tr>
                              <tr class="table_row">
                                    <td class="column-0">4</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/laptop_img.jpg" alt="IMG">
                                      </div>
                                    </td>
                                    <td class="column-2">울트라PC</td>
                                </tr>
                                <tr class="table_row">
                                       <td class="column-0">5</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/laptop_img.jpg" alt="IMG">
                                       </div>
                                    </td>
                                    <td class="column-2">그램2in1</td>   
                              </tr>
                              <tr class="table_row">
                                    <td class="column-0">6</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/laptop_img.jpg" alt="IMG">
                                      </div>
                                    </td>
                                    <td class="column-2">Always</td>
                                </tr>
                                <tr class="table_row">
                                       <td class="column-0">7</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/laptop_img.jpg" alt="IMG">
                                       </div>
                                    </td>
                                    <td class="column-2">Odyssey</td>   
                              </tr>
                              <tr class="table_row">
                                    <td class="column-0">8</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/laptop_img.jpg" alt="IMG">
                                      </div>
                                    </td>
                                    <td class="column-2">삼성노트북3</td>
                                </tr>
                                <tr class="table_row">
                                       <td class="column-0">9</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/laptop_img.jpg" alt="IMG">
                                       </div>
                                    </td>
                                    <td class="column-2">삼성노트북5</td>   
                              </tr>
                              <tr class="table_row">
                                    <td class="column-0">10</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/laptop_img.jpg" alt="IMG">
                                      </div>
                                    </td>
                                    <td class="column-2">Galaxy S10+</td>
                                </tr>
                  </table>
               </div>
            </div>
         </div>
      </div>
      <div class="container">
         <div class="row" style="display:inline">
				<div class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50" style="display:inline;float:left;width:500px">
					<div class="bor10 p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm">
						<h4 class="mtext-109 cl2 p-b-12">
                     Camera
                  </h4>
                  <hr>
                  <table>
                     <tr class="table_head">
                        <th class="column-0">Rank</th>
                                    <th class="column-1" style="padding-left:20px"><p style="padding-left:10px">Image</p></th>
                                    <th class="column-2"><p style="padding-left:40px">Name</p></th>
                        </tr>
                        <tr class="table_row">
                                       <td class="column-0">1</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/camera_img.jpg" alt="IMG">
                                       </div>
                                    </td>
                                    <td class="column-2">iPhone 11</td>   
                              </tr>
                              <tr class="table_row">
                                    <td class="column-0">2</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/camera_img.jpg" alt="IMG">
                                      </div>
                                    </td>
                                    <td class="column-2">iPhone 11 pro</td>
                                </tr>
                                <tr class="table_row">
                                       <td class="column-0">3</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/camera_img.jpg" alt="IMG">
                                       </div>
                                    </td>
                                    <td class="column-2">iPhone 11 pro MAX</td>   
                              </tr>
                              <tr class="table_row">
                                    <td class="column-0">4</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/camera_img.jpg" alt="IMG">
                                      </div>
                                    </td>
                                    <td class="column-2">iPhone XS</td>
                                </tr>
                                <tr class="table_row">
                                       <td class="column-0">5</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/camera_img.jpg" alt="IMG">
                                       </div>
                                    </td>
                                    <td class="column-2">iPhone XS MAX</td>   
                              </tr>
                              <tr class="table_row">
                                    <td class="column-0">6</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/camera_img.jpg" alt="IMG">
                                      </div>
                                    </td>
                                    <td class="column-2">iPhone XR</td>
                                </tr>
                                <tr class="table_row">
                                       <td class="column-0">7</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/camera_img.jpg" alt="IMG">
                                       </div>
                                    </td>
                                    <td class="column-2">iPhone X</td>   
                              </tr>
                              <tr class="table_row">
                                    <td class="column-0">8</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/camera_img.jpg" alt="IMG">
                                      </div>
                                    </td>
                                    <td class="column-2">Galaxy S10e</td>
                                </tr>
                                <tr class="table_row">
                                       <td class="column-0">9</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/camera_img.jpg" alt="IMG">
                                       </div>
                                    </td>
                                    <td class="column-2">Galaxy S10</td>   
                              </tr>
                              <tr class="table_row">
                                    <td class="column-0">10</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/camera_img.jpg" alt="IMG">
                                      </div>
                                    </td>
                                    <td class="column-2">Galaxy S10+</td>
                                </tr>
                  </table>
               </div>
            </div>
            <div class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50"  style="display:inline;float:left;width:500px">
					<div class="bor10 p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm">
						<h4 class="mtext-109 cl2 p-b-12">
                     Tablet
                  </h4>
                  <hr>
                  <table>
                     <tr class="table_head">
                        <th class="column-0">Rank</th>
                                    <th class="column-1" style="padding-left:20px"><p style="padding-left:10px">Image</p></th>
                                    <th class="column-2"><p style="padding-left:40px">Name</p></th>
                        </tr>
                        <tr class="table_row">
                                       <td class="column-0">1</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/tablet_img.jpg" alt="IMG">
                                       </div>
                                    </td>
                                    <td class="column-2">iPhone 11</td>   
                              </tr>
                              <tr class="table_row">
                                    <td class="column-0">2</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/tablet_img.jpg" alt="IMG">
                                      </div>
                                    </td>
                                    <td class="column-2">iPhone 11 pro</td>
                                </tr>
                                <tr class="table_row">
                                       <td class="column-0">3</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/tablet_img.jpg" alt="IMG">
                                       </div>
                                    </td>
                                    <td class="column-2">iPhone 11 pro MAX</td>   
                              </tr>
                              <tr class="table_row">
                                    <td class="column-0">4</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/tablet_img.jpg" alt="IMG">
                                      </div>
                                    </td>
                                    <td class="column-2">iPhone XS</td>
                                </tr>
                                <tr class="table_row">
                                       <td class="column-0">5</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/tablet_img.jpg" alt="IMG">
                                       </div>
                                    </td>
                                    <td class="column-2">iPhone XS MAX</td>   
                              </tr>
                              <tr class="table_row">
                                    <td class="column-0">6</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/tablet_img.jpg" alt="IMG">
                                      </div>
                                    </td>
                                    <td class="column-2">iPhone XR</td>
                                </tr>
                                <tr class="table_row">
                                       <td class="column-0">7</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/tablet_img.jpg" alt="IMG">
                                       </div>
                                    </td>
                                    <td class="column-2">iPhone X</td>   
                              </tr>
                              <tr class="table_row">
                                    <td class="column-0">8</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/tablet_img.jpg" alt="IMG">
                                      </div>
                                    </td>
                                    <td class="column-2">Galaxy S10e</td>
                                </tr>
                                <tr class="table_row">
                                       <td class="column-0">9</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/tablet_img.jpg" alt="IMG">
                                       </div>
                                    </td>
                                    <td class="column-2">Galaxy S10</td>   
                              </tr>
                              <tr class="table_row">
                                    <td class="column-0">10</td>
                                    <td class="column-1" style="padding-left:20px">
                                       <div class="how-itemcart1">
                                          <img src="images/tablet_img.jpg" alt="IMG">
                                      </div>
                                    </td>
                                    <td class="column-2">Galaxy S10+</td>
                                </tr>
                  </table>
               </div>
            </div>
         </div>
      </div>
   </form>



<!-- Ranking -->
<!-- 
<form class="bg0 p-t-75 p-b-85">
      <div class="container">
         <div class="row">
            <div class="col-lg-10 col-xl-7 m-lr-auto m-b-50">
               <div class="m-l-25 m-r--38 m-lr-0-xl">
                  <div class="wrap-table-shopping-cart">
                     <table class="table-shopping-cart">
                        <tr class="table_head">
                           <th class="column-0">Rank</th>
                                    <th class="column-1">Product</th>
                                    <th class="column-2"></th>
                        </tr>
                        <tr class="table_row">
                                       <td class="column-0">1</td>
                                    <td class="column-1">
                                       <div class="how-itemcart1">
                                          <img src="images/item-cart-04.jpg" alt="IMG">
                                       </div>
                                    </td>
                                    <td class="column-2">Fresh Strawberries</td>   
                              </tr>
                              <tr class="table_row">
                                    <td class="column-0">2</td>
                                    <td class="column-1">
                                       <div class="how-itemcart1">
                                          <img src="images/item-cart-05.jpg" alt="IMG">
                                      </div>
                                   </td>
                                    <td class="column-2">Lightweight Jacket</td>
                                </tr>
                           </table>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </form>
 -->



   <!-- Back to top -->
   <div class="btn-back-to-top" id="myBtn">
      <span class="symbol-btn-back-to-top">
         <i class="zmdi zmdi-chevron-up"></i>
      </span>
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
   <script src="vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
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