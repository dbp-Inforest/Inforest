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
	function p_view(kind, targetUri) {
	    alert(targetUri);   
	    form.action = targetUri;

	    form.kind2.value = kind;
	    alert(form.kind2.value);

	    form.submit();
	 }
</script>

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
         Product
      </h2>
   </section>   

   
	<div style="height:50px;">&nbsp;</div><div style="height:50px;">&nbsp;</div>
	
	
	<form name="form" action="<c:url value='/productList'/>">
	<input type="hidden" name="kind2" value="10"/>
	<div class="flex-w flex-c-m m-tb-10"> <!-- a태그로 하거나  -->
		<div class="flex-c-m stext-106 cl6 size-104 bor4 pointer hov-btn3 trans-04 m-r-8 m-tb-4" style="width:300px;height:200px">
			<input type="button" onClick="p_view(0, '<c:url value='/productList'><c:param name='kind' value='0'/></c:url>')" style="font-size:25pt; background-color:transparent;" value="Phone"/>
		</div>
		<div class="flex-c-m stext-106 cl6 size-105 bor4 pointer hov-btn3 trans-04 m-r-8 m-tb-4" style="width:300px;height:200px">
			<input type="button" onClick="p_view(1, '<c:url value='/productList'><c:param name='kind' value='1'/></c:url>')" style="font-size:25pt; background-color:transparent;" value="Laptop"/>
		</div>
		<div class="flex-c-m stext-106 cl6 size-104 bor4 pointer hov-btn3 trans-04 m-r-8 m-tb-4" style="width:300px;height:200px">
			<input type="button" onClick="p_view(2, '<c:url value='/productList'><c:param name='kind' value='2'/></c:url>')" style="font-size:25pt; background-color:transparent;" value="Camera"/>
		</div>
		<div class="flex-c-m stext-106 cl6 size-105 bor4 pointer hov-btn3 trans-04 m-r-8 m-tb-4" style="width:300px;height:200px">
			<input type="button" onClick="p_view(3, '<c:url value='/productList'><c:param name='kind' value='3'/></c:url>')" style="font-size:25pt; background-color:transparent;" value='Tablet'/>
		</div>
	</div>
	</form>
	
	
	
<!-- Product -->
	<div class="bg0 m-t-23 p-b-140">
		<div class="container">
			<div class="flex-w flex-sb-m p-b-52">

				

				<!-- Search product -->
				<!--
				<div class="dis-none panel-search w-full p-t-10 p-b-15">
					<form action="product-search.jsp">
					<div class="bor8 dis-flex p-l-15">
						<select name='searchWhat'>
  						<option value='' selected>-Choose-</option>
  						<option value='0'>Model</option>
  						<option value='1'>Name</option>
						</select>
						<input name="searchWord" class="mtext-107 cl2 size-114 plh2 p-r-15" type="text" name="search-product" placeholder="Search">
						<button class="size-113 flex-c-m fs-16 cl2 hov-cl1 trans-04">
							<i class="zmdi zmdi-search"></i>
						</button>
					</div>	
					</form>
				</div>	
				-->

			<!-- PRODUCT 목록 -->
			<% // for(int i = 0; i < 25; i++) { %>
			<!--  
			<div class="row isotope-grid">
				<div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item 0">--> <!-- 옆에 괄호 안에 phone을 laptop, camera, tablet으로 바꾸면 됨 -->
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
		</div>
	</div>
	</div>
</body>
</html>