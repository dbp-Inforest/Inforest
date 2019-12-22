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
	function detailAction(targetUri) {
	    form.action = targetUri;
	    alert(targetUri);
	    form.submit();
	 }
	
	function myCheck(){ //빈칸이면 false되도록 
	    if(form1.review.value == ""){
	         alert("댓글을 입력하십시오.");
	         form1.review.focus();
	         return false;
	      } 
	      
	   var member_id = "<%=(String)session.getAttribute("userId")%>";
	   alert(member_id);
	   
	    if(member_id == "null"){ //로그아웃 상태 
	       alert("로그인을 한 후에 댓글을 작성할 수 있습니다.");
	       history.go(0);
	   }
	    else{ //로그인 
	       alert("로그인상태입니다.");
	       form1.submit();
	    }
	}
</script>
</head>
<body class="animsition">
   <!-- Header import -->
   <jsp:include page="/WEB-INF/views/header.jsp"/>
   
   <!-- Title page -->
   <section class="bg-img1 txt-center p-lr-15 p-tb-92" style="background-image: url('images/product_bg.jpg');">
      <h2 class="ltext-105 cl0 txt-center">
         Product Details
      </h2>
   </section>   
   
<%
System.out.println("camera-detail.jsp");
%>

   	<!-- Product Detail -->
	<section class="sec-product-detail bg0 p-t-65 p-b-60">
			<div>
				<!-- Tab01 -->
				<div class="tab01">
					<!-- Nav tabs -->
					<ul class="nav nav-tabs" role="tablist">
						<!-- 세부적인 product 정보 (phone, laptop, camera, tablet 다 다름 -->
						<li class="nav-item p-b-10" style="font-size:20px">
							Additional information
						</li>
					</ul>

					<!-- Tab panes -->
					<div class="tab-content p-t-43">
						
						<!-- Additional information 내용 -->
						<div class="tab-pane fade show active" id="information" role="tabpanel">
							<div class="row">
								<div class="col-sm-10 col-md-8 col-lg-6 m-lr-auto">
									<ul class="p-lr-28 p-lr-15-sm">
									
						<div>
						<h3 class="mtext-105 cl2 js-name-detail p-b-14"><c:out value="${cameraDetail.brand}"/></h3>
						<h4 class="mtext-105 cl2 js-name-detail p-b-14"><c:out value="${cameraDetail.name}"/></h4>
						<span class="mtext-106 cl2">
							<c:out value="${cameraDetail.price}"/>
						</span>


						<div style="height:50px;">&nbsp;</div>
							<p class="stext-100 cl3 p-t-23">
								 KIND : Camera
							</p>
							<p class="stext-100 cl3 p-t-23">
								 COLOR : <c:out value="${cameraDetail.color}"/>
							</p>
							<p class="stext-100 cl3 p-t-23">
								 WEIGHT : <c:out value="${cameraDetail.weight}"/>g
							</p>
							<p class="stext-100 cl3 p-t-23">
								 RELEASED DATE : <c:out value="${cameraDetail.released_date}"/>
							</p>
						</div>
						
						
						<div style="height:50px;">&nbsp;</div>
							<li class="flex-w flex-t p-b-7">
								<span class="stext-102 cl3 size-205">
												Battery
								</span>

								<span class="stext-102 cl6 size-206">
											<c:out value="${cameraDetail.cBattery}"/> 
								</span>
							</li>

							<li class="flex-w flex-t p-b-7">
								<span class="stext-102 cl3 size-205">
												Lens
								</span>

								<span class="stext-102 cl6 size-206">
											<c:out value="${cameraDetail.cLens}"/> 
								</span>
							</li>

							<li class="flex-w flex-t p-b-7">
								<span class="stext-102 cl3 size-205">
												Display
								</span>

								<span class="stext-102 cl6 size-206">
											<c:out value="${cameraDetail.cDisplay}"/> 
								</span>
							</li>

							<li class="flex-w flex-t p-b-7">
								<span class="stext-102 cl3 size-205">
												Pixel
								</span>

								<span class="stext-102 cl6 size-206">
											<c:out value="${cameraDetail.cPixel}"/> 
								</span>
							</li>

							<li class="flex-w flex-t p-b-7">
								<span class="stext-102 cl3 size-205">
												Vibration
								</span>

								<span class="stext-102 cl6 size-206">
											<c:out value="${cameraDetail.cVibration}"/>
								</span>
							</li>
										
							<li class="flex-w flex-t p-b-7">
								<span class="stext-102 cl3 size-205">
												Burstshot
								</span>

								<span class="stext-102 cl6 size-206">
											<c:out value="${cameraDetail.cBurstshot}"/>
								</span>
							</li>
							
							<div style="height:50px;">&nbsp;</div><div style="height:50px;">&nbsp;</div>
										<!-- 여기 ADD TO 'LIKE' 버튼 누르면 관심 상품에 등록될 수 있도록..? -->
							<form name="form" method="POST" action="<c:url value='/product' />">
								<input type="hidden" name="pId" value="${cameraDetail.productId}" />
								<input type="hidden" name="kind" value="2" />
								<c:if test="${position == 2}">
									<div class="size-204 flex-w flex-m respon6-next">
										<button
											class="flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04 js-addcart-detail">
											ADD TO 'LIKE'</button>
									</div>
								</c:if>
								<c:if test="${position == 0}">
									<div class="size-204 flex-w flex-m respon6-next">
										<button
											class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer"
											onClick="detailAction('<c:url value='/camera_update'/>')">UPDATE
										</button>
										<button
											class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer"
											onClick="detailAction('<c:url value='/deleteProduct'/>')">DELETE
										</button>
									</div>
								</c:if>
							</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	
	
	
	
	
	
	
	
	
	
	




<!-- 리뷰 창 (형식만) -->
<!--  
<div class="bor10 m-t-50 p-t-43 p-b-40">
				<div class="tab01">
					<ul class="nav nav-tabs" role="tablist">
						<li class="nav-item p-b-10">
							Reviews
					</ul>
					-->
						
						<!-- Reviews 내용 -->
						<!-- 
						<div class="tab-pane fade" id="reviews" role="tabpanel">
							<div class="row">
								<div class="col-sm-10 col-md-8 col-lg-6 m-lr-auto">
									<div class="p-b-30 m-lr-15-sm">
										
										<div class="flex-w flex-t p-b-68">
											<div class="wrap-pic-s size-109 bor0 of-hidden m-r-18 m-t-6">
												<img src="images/avatar-01.jpg" alt="AVATAR">
											</div>

											<div class="size-207">
												<div class="flex-w flex-sb-m p-b-17">
													<span class="mtext-107 cl2 p-r-20">
														Ariana Grande
													</span>

													<span class="fs-18 cl11">
														<i class="zmdi zmdi-star"></i>
														<i class="zmdi zmdi-star"></i>
														<i class="zmdi zmdi-star"></i>
														<i class="zmdi zmdi-star"></i>
														<i class="zmdi zmdi-star-half"></i>
													</span>
												</div>

												<p class="stext-102 cl6">
													Quod autem in homine praestantissimum atque optimum est, id deseruit. Apud ceteros autem philosophos
												</p>
											</div>
										</div> -->
										
										<!-- Add review -->
										<!-- 
										<form class="w-full">
											<h5 class="mtext-108 cl2 p-b-7">
												Add a review
											</h5>

											<p class="stext-102 cl6">
												Your email address will not be published. Required fields are marked *
											</p>

											<div class="flex-w flex-m p-t-50 p-b-23">
												<span class="stext-102 cl3 m-r-16">
													Your Rating
												</span>

												<span class="wrap-rating fs-18 cl11 pointer">
													<i class="item-rating pointer zmdi zmdi-star-outline"></i>
													<i class="item-rating pointer zmdi zmdi-star-outline"></i>
													<i class="item-rating pointer zmdi zmdi-star-outline"></i>
													<i class="item-rating pointer zmdi zmdi-star-outline"></i>
													<i class="item-rating pointer zmdi zmdi-star-outline"></i>
													<input class="dis-none" type="number" name="rating">
												</span>
											</div>

											<div class="row p-b-25">
												<div class="col-12 p-b-5">
													<label class="stext-102 cl3" for="review">Your review</label>
													<textarea class="size-110 bor8 stext-102 cl2 p-lr-20 p-tb-10" id="review" name="review"></textarea>
												</div>

												<div class="col-sm-6 p-b-5">
													<label class="stext-102 cl3" for="name">Name</label>
													<input class="size-111 bor8 stext-102 cl2 p-lr-20" id="name" type="text" name="name">
												</div>

												<div class="col-sm-6 p-b-5">
													<label class="stext-102 cl3" for="email">Email</label>
													<input class="size-111 bor8 stext-102 cl2 p-lr-20" id="email" type="text" name="email">
												</div>
											</div>

											<button class="flex-c-m stext-101 cl0 size-112 bg7 bor11 hov-btn3 p-lr-15 trans-04 m-b-10">
												Submit
											</button>
										</form>
									</div>
								</div>
							</div>
						</div>
						</div>
						</div>
						
						 -->
	
</body>
</html>