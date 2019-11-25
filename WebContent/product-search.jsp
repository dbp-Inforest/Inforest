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
               <div class="menu-desktop" style="float:left">
                  <ul class="main-menu">
                     <li>
                         <a href="<c:url value='/main'/>" style="text-decoration:none">HOME</a>
                     </li>

                     <li>
                      <a href="<c:url value='/rankCont'/>" style="text-decoration:none">RANK</a>
                     </li>

                     <li>
                      <a href="<c:url value='/product'/>" style="text-decoration:none">PRODUCT</a>
                     </li>

                     <li>
                       <a href="<c:url value='/mypage'/>" style="text-decoration:none">MY PAGE</a>
                     </li>
                     <%
                     	if(session.getAttribute("userId") == null) { %>
	                     <li>
	                        <a href="<c:url value='/signIn'/>" style="text-decoration:none">LOGIN</a>
	                     </li> 
	                     </ul> 
                     <% } else {
	                	 	if((int)session.getAttribute("position") == 0) { %>
	                     <li>
	                        <a href="<c:url value='/management'/>" style="text-decoration:none">MANAGEMENT</a>
	                     </li> 
                     	<% } %>	
	                     </ul>
	                     <div class="menu-desktop" style="float:right">
	                     	<font style="color:white"><%= session.getAttribute("userId") %> 님 안녕하세요. </font> &nbsp;
	                     	<a href="<c:url value='/logout'/>" style="text-decoration:none">LOGOUT</a>
	                     </div>
					 <% } %>		 
               </div>   
            </nav>
         </div>   
      </div>
   </header>        <li>
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


   <form class="bg0 p-t-40 p-b-85" style="position:absolute;left:50%;margin:0 0 0 -510px;">
      <div class="container" >
         <div class="row"  style="display:inline">
            <div class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50" style="display:inline;float:left;width:1050px">
               <div class="bor10 p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm" style="font-size:20px">
                  <h4 class="mtext-109 cl2 p-b-12">
                     Search with  ' <c:out value="${searchWord}"/> '
                  </h4>
                  <hr>
                  <table>
                     <tr class="table_head">
                        <th class="column-0" >Product Id</th>
                                    <th class="column-1" style="padding-left:50px">Name</th>
                                    <th class="column-2"><p style="padding-left:75px">Brand</p></th>
                                    <th class="column-3"><p style="padding-left:60px">Price</p></th>
                        </tr>
                       
						<c:if test="${ListByName.size() == 0 }">
						<tfoot>
						<tr>
						<td colspan="3">현재 데이터가 없습니다.</td>
						</tr>
						</tfoot>
						</c:if>
						<!-- //request.getAttribute("List") == ${List} -->
						<tbody> 
						
						
						<c:if test="${kind < 4 }">
						<c:forEach var="product" items="${ListByName}" varStatus="status">
						<tr class="table_row" style="height:70px;">
						<td class="column-0" style="padding-left:30px">
                                <a href="<c:url value='/productDetail'/>" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                                      <font style="font-size:20px">
                                      	<c:out value="${product.productId}"/>
                                      </font>
                                 </a>
                        </td>
                       <td class="column-1" style="padding-left:50px"><c:out value="${product.name}"/></td>
                       <td class="column-2" style="padding-left:87px"><c:out value="${product.brand}"/></td>   
                       <td class="column-3" style="padding-left:87px"><c:out value="${product.price}"/></td>
                 
						</tr>
						</c:forEach>
						</c:if>
						
						
						
						
						
						
						
						<c:if test="${kind == 4 }">
						<c:forEach var="product" items="${PhoneListByName}" varStatus="status">
						<tr class="table_row" style="height:70px;">
						<td class="column-0" style="padding-left:30px">
                                <a href="<c:url value='/productDetail'/>" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                                      <font style="font-size:20px">
                                      	<c:out value="${product.productId}"/>
                                      </font>
                                 </a>
                        </td>
                       <td class="column-1" style="padding-left:50px"><c:out value="${product.name}"/></td>
                       <td class="column-2" style="padding-left:87px"><c:out value="${product.brand}"/></td>   
                       <td class="column-3" style="padding-left:87px"><c:out value="${product.price}"/></td>
                 
						</tr>
						</c:forEach>
						<c:forEach var="product" items="${LaptopListByName}" varStatus="status">
						<tr class="table_row" style="height:70px;">
						<td class="column-0" style="padding-left:30px">
                                <a href="<c:url value='/productDetail'/>" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                                      <font style="font-size:20px">
                                      	<c:out value="${product.productId}"/>
                                      </font>
                                 </a>
                        </td>
                       <td class="column-1" style="padding-left:50px"><c:out value="${product.name}"/></td>
                       <td class="column-2" style="padding-left:87px"><c:out value="${product.brand}"/></td>   
                       <td class="column-3" style="padding-left:87px"><c:out value="${product.price}"/></td>
                 
						</tr>
						</c:forEach>
						<c:forEach var="product" items="${CameraListByName}" varStatus="status">
						<tr class="table_row" style="height:70px;">
						<td class="column-0" style="padding-left:30px">
                                <a href="<c:url value='/productDetail'/>" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                                      <font style="font-size:20px">
                                      	<c:out value="${product.productId}"/>
                                      </font>
                                 </a>
                        </td>
                       <td class="column-1" style="padding-left:50px"><c:out value="${product.name}"/></td>
                       <td class="column-2" style="padding-left:87px"><c:out value="${product.brand}"/></td>   
                       <td class="column-3" style="padding-left:87px"><c:out value="${product.price}"/></td>
                 
						</tr>
						</c:forEach>
						<c:forEach var="product" items="${TabletListByName}" varStatus="status">
						<tr class="table_row" style="height:70px;">
						<td class="column-0" style="padding-left:30px">
                                <a href="<c:url value='/productDetail'/>" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                                      <font style="font-size:20px">
                                      	<c:out value="${product.productId}"/>
                                      </font>
                                 </a>
                        </td>
                       <td class="column-1" style="padding-left:50px"><c:out value="${product.name}"/></td>
                       <td class="column-2" style="padding-left:87px"><c:out value="${product.brand}"/></td>   
                       <td class="column-3" style="padding-left:87px"><c:out value="${product.price}"/></td>
                 
						</tr>
						</c:forEach>
						</c:if>
						
						</tbody>
						</table>
               </div>
            </div>
         </div>
      </div>
   </form>               
               
</body>
</html>