<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import = "java.util.List" %>
<%@page import = "model.dao.*" %>
<%@page import = "model.dto.*" %>

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
               <a href="<c:url value='/main'/>"class="logo">
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


   <%
   		TabletDAO tabletDAO = new TabletDAO();
   		Tablet tablet = new Tablet();
   		
   		List<Tablet> tabletList = tabletDAO.getTabletList();
   		System.out.println(tabletList.get(0).getName());
   		List<Tablet> tabletList2 = tabletDAO.getTabletByName("탭");
   		System.out.println(tabletList2.get(0).getName());
   		Tablet tabletList3 = tabletDAO.getTabletById("A");
   		System.out.println(tabletList3.getName());
   %>
   
   <!-- Title page -->
   <section class="bg-img1 txt-center p-lr-15 p-tb-92" style="background-image: url('images/product_bg.jpg');">
      <h2 class="ltext-105 cl0 txt-center">
         Tablet
      </h2>
   </section>  
   
 
   
   <form class="bg0 p-t-75 p-b-85" style="position:absolute;left:50%;margin:0 0 0 -510px;">
      <div class="container" >
         <div class="row"  style="display:inline">
            <div class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50" style="display:inline;float:left;width:1050px">
               <div class="bor10 p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm" style="font-size:20px">
                  <h4 class="mtext-109 cl2 p-b-12">
                     Tablet
                  </h4>
                  <hr>
                    <table>
                     <tr class="table_head">
                        <th class="column-0" >Product Id</th>
                                    <th class="column-1" style="padding-left:50px">Name</th>
                                    <th class="column-2"><p style="padding-left:75px">Brand</p></th>
                                    <th class="column-3"><p style="padding-left:60px">Price</p></th>
                        </tr>
                       
						<c:if test="${tabletList.size() == 0 }">
						<tfoot>
						<tr>
						<td colspan="3">현재 데이터가 없습니다.</td>
						</tr>
						</tfoot>
						</c:if>
						<!-- //request.getAttribute("List") == ${List} -->
						<tbody> 
						<c:forEach var="tablet" items="${tabletList}" varStatus="status">
						<tr class="table_row" style="height:70px;">
						<td class="column-0" style="padding-left:30px">
                                <a href="<c:url value='/productDetail'><c:param name="kind2" value="3"/>
                                										<c:param name="pId" value="${tablet.productId}"/>
                                </c:url>" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                                      <font style="font-size:20px">
                                      	<c:out value="${tablet.productId}"/>
                                      </font>
                                 </a>           
                        </td>
                       <td class="column-1" style="padding-left:50px"><c:out value="${tablet.name}"/></td>
                       <td class="column-2" style="padding-left:87px"><c:out value="${tablet.brand}"/></td>   
                       <td class="column-3" style="padding-left:87px"><c:out value="${tablet.price}"/></td>
                 
						</tr>
						</c:forEach>
						</tbody>
						</table>                 
                  
                  <!-- <table>
                     <tr class="table_head">
                        <th class="column-0" >Name</th>
                                    <th class="column-1" style="padding-left:50px">Product Id</th>
                                    <th class="column-2"><p style="padding-left:75px">Brand</p></th>
                                    <th class="column-3"><p style="padding-left:60px">Price</p></th>
                        </tr>
                        <tr class="table_row" style="height:70px;">
                                       <td class="column-0" style="padding-left:30px">
                                          <a href="product-detail.jsp" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                                             <font style="font-size:20px">iPhone 11</font>
                                          </a>
                           </td>
                                    <td class="column-1" style="padding-left:50px">A2221</td>
                                    <td class="column-2" style="padding-left:87px">APPLE</td>   
                                    <td class="column-3" style="padding-left:87px">990, 000부터</td>   
                              </tr>
                              <tr class="table_row" style="height:70px;">
                                       <td class="column-0" style="padding-left:30px">
                                          <a href="product-detail.jsp" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                                             <font style="font-size:20px">iPhone 11 pro</font>
                                          </a>
                           </td>
                                    <td class="column-1" style="padding-left:50px">A2215</td>
                                    <td class="column-2" style="padding-left:87px">APPLE</td>   
                                    <td class="column-3" style="padding-left:87px">1, 390, 000부터</td>
                              </tr>
                              <tr class="table_row" style="height:70px;">
                                       <td class="column-0" style="padding-left:30px">
                                          <a href="product-detail.jsp" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                                             <font style="font-size:20px">iPhone 11 pro MAX</font>
                                          </a>
                           </td>
                                    <td class="column-1" style="padding-left:50px">A2218</td>
                                    <td class="column-2" style="padding-left:87px">APPLE</td>   
                                    <td class="column-3" style="padding-left:87px">1, 550, 000부터</td>
                              </tr>
                              <tr class="table_row" style="height:70px;">
                                       <td class="column-0" style="padding-left:30px">
                                          <a href="product-detail.jsp" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                                             <font style="font-size:20px">iPhone XS</font>
                                          </a>
                           </td>
                                    <td class="column-1" style="padding-left:50px">A2097</td>
                                    <td class="column-2" style="padding-left:87px">APPLE</td>   
                                    <td class="column-3" style="padding-left:87px">단종</td>   
                              </tr>
                              <tr class="table_row" style="height:70px;">
                                       <td class="column-0" style="padding-left:30px">
                                          <a href="product-detail.jsp" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                                             <font style="font-size:20px">iPhone XS MAX</font>
                                          </a>
                           </td>
                                    <td class="column-1" style="padding-left:50px">A2101</td>
                                    <td class="column-2" style="padding-left:87px">APPLE</td>   
                                    <td class="column-3" style="padding-left:87px">단종</td>
                              </tr>
                              <tr class="table_row" style="height:70px;">
                                       <td class="column-0" style="padding-left:30px">
                                          <a href="product-detail.jsp" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                                             <font style="font-size:20px">iPhone 11 pro MAX</font>
                                          </a>
                           </td>
                                    <td class="column-1" style="padding-left:50px">A2218</td>
                                    <td class="column-2" style="padding-left:87px">APPLE</td>   
                                    <td class="column-3" style="padding-left:87px">1, 550, 000부터</td>
                              </tr>
                              <tr class="table_row" style="height:70px;">
                                       <td class="column-0" style="padding-left:30px">
                                          <a href="product-detail.jsp" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                                             <font style="font-size:20px">Galaxy S10e</font>
                                          </a>
                           </td>
                                    <td class="column-1" style="padding-left:50px">SMG970</td>
                                    <td class="column-2" style="padding-left:87px">SAMSUNG</td>   
                                    <td class="column-3" style="padding-left:87px">899, 800부터</td>   
                              </tr>
                              <tr class="table_row" style="height:70px;">
                                       <td class="column-0" style="padding-left:30px">
                                          <a href="product-detail.jsp" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                                             <font style="font-size:20px">Galaxy S10</font>
                                          </a>
                           </td>
                                    <td class="column-1" style="padding-left:50px">SMG973</td>
                                    <td class="column-2" style="padding-left:87px">SAMSUNG</td>   
                                    <td class="column-3" style="padding-left:87px">1, 056, 000부터</td>
                              </tr>
                              <tr class="table_row" style="height:70px;">
                                       <td class="column-0" style="padding-left:30px">
                                          <a href="product-detail.jsp" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                                             <font style="font-size:20px">Galaxy S10+</font>
                                          </a>
                           </td>
                                    <td class="column-1" style="padding-left:50px">SMG975</td>
                                    <td class="column-2" style="padding-left:87px">SAMSUNG</td>   
                                    <td class="column-3" style="padding-left:87px">1, 749, 000부터</td>
                              </tr>
                              <tr class="table_row" style="height:70px;">
                                       <td class="column-0" style="padding-left:30px">
                                          <a href="product-detail.jsp" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                                             <font style="font-size:20px">Mi 9</font>
                                          </a>
                           </td>
                                    <td class="column-1" style="padding-left:50px">M1902F1G</td>
                                    <td class="column-2" style="padding-left:87px">XIAOMI</td>   
                                    <td class="column-3" style="padding-left:87px">599, 000부터</td>   
                              </tr>
                              <tr class="table_row" style="height:70px;">
                                       <td class="column-0" style="padding-left:30px">
                                          <a href="product-detail.jsp" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                                             <font style="font-size:20px">V50 ThinQ</font>
                                          </a>
                           </td>
                                    <td class="column-1" style="padding-left:50px">LMV500</td>
                                    <td class="column-2" style="padding-left:87px">LG</td>   
                                    <td class="column-3" style="padding-left:87px">1, 199, 000부터</td>
                              </tr>
                              <tr class="table_row" style="height:70px;">
                                       <td class="column-0" style="padding-left:30px">
                                          <a href="product-detail.jsp" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                                             <font style="font-size:20px">iPhone 11 pro MAX</font>
                                          </a>
                           </td>
                                    <td class="column-1" style="padding-left:50px">LMV510</td>
                                    <td class="column-2" style="padding-left:87px">LG</td>   
                                    <td class="column-3" style="padding-left:87px">1, 199, 000부터</td>
                              </tr>
                  </table> -->
               </div>
            </div>
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

         </div>
      </div>
   </div>
</body>
</html>