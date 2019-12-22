<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import = "model.dto.*" %>
<%@page import = "model.dao.*" %>
<%@page import ="controller.product.*" %>

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
	
	function deleteAction(targetUri) {
	    form1.action = targetUri;
	    alert(targetUri);
	    form1.submit();
	 }
	function myCheck(targetUri){ //빈칸이면 false되도록 
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
	  	   form1.action = targetUri;
	       form1.submit();
	    }
	}
</script>

</head>
<body class="animsition">

	<!-- Header import -->
	<jsp:include page="/WEB-INF/views/header.jsp" />

	<!-- Title page -->
	<section class="bg-img1 txt-center p-lr-15 p-tb-92"
		style="background-image: url('images/product_bg.jpg');">
	<h2 class="ltext-105 cl0 txt-center">Product Details</h2>
	</section>

	<!-- Product Detail -->
	<section class="sec-product-detail bg0 p-t-65 p-b-60">
	<div>
		<!-- Tab01 -->
		<div class="tab01">

			<!-- Nav tabs -->
			<ul class="nav nav-tabs" role="tablist">
				<!-- 세부적인 product 정보 (phone, laptop, camera, tablet 다 다름 -->
				<li class="nav-item p-b-10" style="font-size: 20px">Additional
					information</li>
			</ul>

			<!-- Tab panes -->
			<div class="tab-content p-t-43">

				<!-- Additional information 내용 -->
				<div class="tab-pane fade show active" id="information"
					role="tabpanel">
					<div class="row">
						<div class="col-sm-10 col-md-8 col-lg-6 m-lr-auto">
							<ul class="p-lr-28 p-lr-15-sm">
								<div>
									<h3 class="mtext-105 cl2 js-name-detail p-b-14">
										<c:out value="${phoneDetail.brand}" />
									</h3>
									<h4 class="mtext-105 cl2 js-name-detail p-b-14">
										<c:out value="${phoneDetail.name}" />
									</h4>
									<span class="mtext-106 cl2"> <c:out
											value="${phoneDetail.price}" />
									</span>

									<div style="height: 50px;">&nbsp;</div>
									<p class="stext-100 cl3 p-t-23">KIND : Phone</p>
									<p class="stext-100 cl3 p-t-23">
										COLOR :
										<c:out value="${phoneDetail.color}" />
									</p>
									<p class="stext-100 cl3 p-t-23">
										WEIGHT :
										<c:out value="${phoneDetail.weight}" />
										g
									</p>
									<p class="stext-100 cl3 p-t-23">
										RELEASED DATE :
										<c:out value="${phoneDetail.released_date}" />
									</p>
								</div>

								<div style="height: 50px;">&nbsp;</div>

								<li class="flex-w flex-t p-b-7"><span
									class="stext-102 cl3 size-205"> Battery </span> <span
									class="stext-102 cl6 size-206"> <c:out
											value="${phoneDetail.pBattery}" />
								</span></li>

								<li class="flex-w flex-t p-b-7"><span
									class="stext-102 cl3 size-205"> Memory </span> <span
									class="stext-102 cl6 size-206"> <c:out
											value="${phoneDetail.pMemory}" />
								</span></li>

								<li class="flex-w flex-t p-b-7"><span
									class="stext-102 cl3 size-205"> Display </span> <span
									class="stext-102 cl6 size-206"> <c:out
											value="${phoneDetail.pDisplay}" />
								</span></li>

								<li class="flex-w flex-t p-b-7"><span
									class="stext-102 cl3 size-205"> Ram </span> <span
									class="stext-102 cl6 size-206"> <c:out
											value="${phoneDetail.pRAM}" />
								</span></li>

								<li class="flex-w flex-t p-b-7"><span
									class="stext-102 cl3 size-205"> Size </span> <span
									class="stext-102 cl6 size-206"> <c:out
											value="${phoneDetail.pSize}" />
								</span></li>

								<li class="flex-w flex-t p-b-7"><span
									class="stext-102 cl3 size-205"> Camera </span> <span
									class="stext-102 cl6 size-206"> <c:out
											value="${phoneDetail.pCamera}" />
								</span></li>
						</div>    
						</ul>

						<div style="height: 50px;">&nbsp;</div>
						<div style="height: 50px;">&nbsp;</div>
						<!-- 여기 ADD TO 'LIKE' 버튼 누르면 관심 상품에 등록될 수 있도록..? -->
						<%-- <form name="form" method="POST"
							action="<c:url value='/product' />">
							<input type="hidden" name="pId" value="${phoneDetail.productId}" />
							<input type="hidden" name="kind" value="0" />
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
										onClick="detailAction('<c:url value='/phone_update'/>')">UPDATE
									</button>
									<button
										class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer"
										onClick="detailAction('<c:url value='/deleteProduct'/>')">DELETE
									</button>
								</div>
							</c:if>
						</form> --%>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	</section>

	<!-- 리뷰 창 -->
	<div class="bor10 m-t-50 p-t-43 p-b-40">
		<div class="tab01">
			<!-- Reviews 내용 -->
			<div class="tab-pane fade" id="reviews">
				<div class=""src/controller/product/DeleteProductController.java"row">
					<div class="col-sm-10 col-md-8 col-lg-6 m-lr-auto">
						<div class="p-b-30 m-lr-15-sm">
							<!-- db에 있는 comment내용과 userid를 getCommentList로 보여주기  -->
							<span class="container">
							<span class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50">
						<h5 class="mtext-108 cl2 p-b-30">Comment List</h5>
                       	 <div class="flex-w flex-t bor12 p-b-13">
           <form  name="form1" method="POST" action="">
                       	<!--  <input type="hidden" name = "kind2" value = "100"/>
                       	 <input type="hidden" name = "pId2" value = "100"/> -->
									<%-- <c:param name="kind2" value="0"/>
                                    <c:param name="pId2" value="${phoneDetail.productId}"/> --%>
                        <table>
                           <thead>
                              <tr>
                               <th><div class="flex-w flex-t p-t-27 p-b-33"><div class="size-400">User</div></div></th>   
                               <th><div class="flex-w flex-t p-t-27 p-b-33"><div class="size-400">Review</div></div></th>
                               <th><div class="flex-w flex-t p-t-27 p-b-33"><div class="size-400">Regist_Date</div></div></th>
                               <th><div class="flex-w flex-t p-t-27 p-b-33"><div class="size-400"></div></div></th>
                              </tr>
                           </thead>
                        <tbody>
                        		<c:forEach var="comment" items="${plist}" varStatus = "status">
									<c:if test="${comment.productId == phoneDetail.productId}">
									<tr>
										<div class="flex-w flex-t bor12 p-b-13">
											<td><div class="size-208">
												<div class="size-400">
													<c:out value="${comment.userId}" />
												</div>
											</div></td>
											<td><div class="flex-w flex-t p-t-27 p-b-33">
												<div class="size-400">
													<c:out value="${comment.review}" />
												</div>
											</div></td>
											<td><div class="flex-w flex-t p-t-27 p-b-33">
												<div class="size-400">
													<c:out value="${comment.registDate}" />
												</div>
											</div></td>
 											<td><div class="flex-w flex-t p-t-27 p-b-33"><div class="size-400">
                                             	
                                             	<button class="flex-c-m stext-101 cl0 size-50 bg7 bor11 hov-btn3 p-lr-15 trans-04 m-b-10" 
                                             	type="button" onClick="deleteAction('<c:url value='/deleteProduct'><c:param name="reviewId" value="${comment.commentId}"/>
                                             	<c:param name="kind2" value="0"/><c:param name="pId2" value="${phoneDetail.productId}"/>
                                             	</c:url>')">
                                                      Delete
                                                </button>
                                              </div></div>
                                             </td>

										</div>
									</tr></c:if>
								</c:forEach>
                        			 <tr>
                                       <td colspan="4" value = "댓글 추가">
                                                   <h5 class="mtext-108 cl2 p-b-7">Add a review</h5>
                                                   <p class="stext-102 cl6">You can write comments by logging in.</p>
                                                   
                                                   <div class="row p-b-25">
                                                   <div class="col-12 p-b-5">
                                                      <textarea class="size-110 bor8 stext-102 cl2 p-lr-20 p-tb-10" id="review" name="review"></textarea>
                                                   </div>
                                                   </div>
                                                
                                                   <button class="flex-c-m stext-101 cl0 size-112 bg7 bor11 hov-btn3 p-lr-15 trans-04 m-b-10" 
                                                   type="button" onClick="myCheck('<c:url value='/productDetail'>
                                             	<c:param name="pId" value="${phoneDetail.productId}"/>
                                             	<c:param name="kind" value="0"/>
                                             	</c:url>')">
                                                      Submit
                                                   </button>
                                    
                                       </td></tr>
                    
						</tbody>
						</table></form></div>
	
								<br>
								<br>
							</span>
							</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>