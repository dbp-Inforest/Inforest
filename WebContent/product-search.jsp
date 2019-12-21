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
function likeSearch(productId, num, targetUri, thisKind, thisPage, searchWord) {
   var image = document.getElementById(num);
   
   if (image.src.match("icon-heart-01.png")) {
      alert("add this to 'like'?");
      searchForm.action = targetUri;
      searchForm.likeProduct.value = productId;
      searchForm.division.value = "true";
      searchForm.kind.value = thisKind;
      searchForm.page.value = thisPage;
      searchForm.searchWord.value = searchWord;
      searchForm.submit();
      image.src = "<c:url value='/images/icons/icon-heart-02.png' />";
   }
   else{
      alert("cancel this to 'like'?");
      searchForm.action = targetUri;
      searchForm.likeProduct.value = productId;
      searchForm.division.value = "false";
      searchForm.kind.value = thisKind;
      searchForm.page.value = thisPage;
      searchForm.searchWord.value = searchWord;
      searchForm.submit();
      image.src = "<c:url value='/images/icons/icon-heart-01.png' />";
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
         Product Search
      </h2>
   </section>   


   <form name="searchForm" class="bg0 p-t-40 p-b-85" style="position:absolute;left:50%;margin:0 0 0 -510px;">
   
   <input type="hidden" name="likeProduct" value="200"/>
   <input type="hidden" name="division" value="200"/>
   <input type="hidden" name="kind" value="200"/>
   <input type="hidden" name="page" value="search"/>
   <input type="hidden" name="searchWord" value="what"/>
   
   
      
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
                                    <th class="column-4"><p style="padding-left:80px">&nbsp;</p></th>
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
                  
                  
                  
                  <!-- phone.jsp || laptop.jsp || camera.jsp || tablet.jsp 에서 검색했을 때 -->
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
                       <td class="column-2" style="padding-left:85px"><c:out value="${product.brand}"/></td>   
                       <td class="column-3" style="padding-left:70px"><c:out value="${product.price}"/></td>
                       <td class="column-4 btn-addwish-b2" style="padding-left:90px">
                             <a href="#" onClick="likeSearch( '${product.productId}', ${status.index}, '<c:url value='/productLike'/>', '${kind}', 'search', '${searchWord}')">
                             <c:set var="a" value="${product.productId}"/>
                             <c:if test="${myProdList.indexOf(a) != -1}">
                                   <img id="${status.index}" src="<c:url value='/images/icons/icon-heart-02.png' />" >
                             </c:if>
                             <c:if test="${myProdList.indexOf(a) == -1}">
                                   <img id="${status.index}" src="<c:url value='/images/icons/icon-heart-01.png' />" >
                             </c:if>
                             </a>
                  </td>
                  </tr>
                  </c:forEach>
                  </c:if>
                  
                  
                  
                  
                  
                  
                  <!-- product-search.jsp 에서 검색했을 때 -->
                  <c:if test="${kind == 4 }">
                  
                  <c:set var="idx" value="0"/>
                  
                  <!-- 검색된 phone 리스트 -->
                  <c:forEach var="product_p" items="${PhoneListByName}" varStatus="status_p">
                  <tr class="table_row" style="height:70px;">
                  <td class="column-0" style="padding-left:30px">
                                <a href="<c:url value='/productDetail'/>" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                                      <font style="font-size:20px">
                                         <c:out value="${product_p.productId}"/>
                                      </font>
                                 </a>
                        </td>
                       <td class="column-1" style="padding-left:50px"><c:out value="${product_p.name}"/></td>
                       <td class="column-2" style="padding-left:87px"><c:out value="${product_p.brand}"/></td>   
                       <td class="column-3" style="padding-left:87px"><c:out value="${product_p.price}"/></td>
                       <td class="column-4 btn-addwish-b2" style="padding-left:90px">
                             <a href="#" onClick="likeSearch( '${product_p.productId}', ${idx}, '<c:url value='/productLike'/>', '${kind}', 'search', '${searchWord}')">
                             <c:set var="a" value="${product_p.productId}"/>
                             <c:if test="${myProdList_p.indexOf(a) != -1}">
                                   <img id="${idx}" src="<c:url value='/images/icons/icon-heart-02.png' />" >
                             </c:if>
                             <c:if test="${myProdList_p.indexOf(a) == -1}">
                                   <img id="${idx}" src="<c:url value='/images/icons/icon-heart-01.png' />" >
                             </c:if>
                             </a>
                  </td>
                  </tr>
                  <c:set var="idx" value="${idx + 1}"/>
                  </c:forEach>
                  
                  
                  
                  <!-- 검색된 laptop 리스트 -->
                  <c:forEach var="product_l" items="${LaptopListByName}" varStatus="status_l">
                  <tr class="table_row" style="height:70px;">
                  <td class="column-0" style="padding-left:30px">
                                <a href="<c:url value='/productDetail'/>" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                                      <font style="font-size:20px">
                                         <c:out value="${product_l.productId}"/>
                                      </font>
                                 </a>
                        </td>
                       <td class="column-1" style="padding-left:50px"><c:out value="${product_l.name}"/></td>
                       <td class="column-2" style="padding-left:87px"><c:out value="${product_l.brand}"/></td>   
                       <td class="column-3" style="padding-left:87px"><c:out value="${product_l.price}"/></td>
                       <td class="column-4 btn-addwish-b2" style="padding-left:90px">
                             <a href="#" onClick="likeSearch( '${product_l.productId}', ${idx}, '<c:url value='/productLike'/>', '${kind}', 'search', '${searchWord}')">
                             <c:set var="a" value="${product_l.productId}"/>
                             <c:if test="${myProdList_l.indexOf(a) != -1}">
                                   <img id="${idx}" src="<c:url value='/images/icons/icon-heart-02.png' />" >
                             </c:if>
                             <c:if test="${myProdList_l.indexOf(a) == -1}">
                                   <img id="${idx}" src="<c:url value='/images/icons/icon-heart-01.png' />" >
                             </c:if>
                             </a>
                  </td>
                  </tr>
                  <c:set var="idx" value="${idx + 1}"/>
                  </c:forEach>
                  
                  
                  
                  
                  <!-- 검색된 camera 리스트 -->
                  <c:forEach var="product_c" items="${CameraListByName}" varStatus="status_c">
                  <tr class="table_row" style="height:70px;">
                  <td class="column-0" style="padding-left:30px">
                                <a href="<c:url value='/productDetail'/>" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                                      <font style="font-size:20px">
                                         <c:out value="${product_c.productId}"/>
                                      </font>
                                 </a>
                        </td>
                       <td class="column-1" style="padding-left:50px"><c:out value="${product_c.name}"/></td>
                       <td class="column-2" style="padding-left:87px"><c:out value="${product_c.brand}"/></td>   
                       <td class="column-3" style="padding-left:87px"><c:out value="${product_c.price}"/></td>
                       <td class="column-4 btn-addwish-b2" style="padding-left:90px">
                             <a href="#" onClick="likeSearch( '${product_c.productId}', ${idx}, '<c:url value='/productLike'/>', '${kind}', 'search', '${searchWord}')">
                             <c:set var="a" value="${product_c.productId}"/>
                             <c:if test="${myProdList_c.indexOf(a) != -1}">
                                   <img id="${idx}" src="<c:url value='/images/icons/icon-heart-02.png' />" >
                             </c:if>
                             <c:if test="${myProdList_c.indexOf(a) == -1}">
                                   <img id="${idx}" src="<c:url value='/images/icons/icon-heart-01.png' />" >
                             </c:if>
                             </a>
                  </td>
                  </tr>
                  <c:set var="idx" value="${idx + 1}"/>
                  </c:forEach>
                  
                  
                  <!-- 검색된 tablet 리스트 -->
                  <c:forEach var="product_t" items="${TabletListByName}" varStatus="status_t">
                  <tr class="table_row" style="height:70px;">
                  <td class="column-0" style="padding-left:30px">
                                <a href="<c:url value='/productDetail'/>" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                                      <font style="font-size:20px">
                                         <c:out value="${product_t.productId}"/>
                                      </font>
                                 </a>
                        </td>
                       <td class="column-1" style="padding-left:50px"><c:out value="${product_t.name}"/></td>
                       <td class="column-2" style="padding-left:87px"><c:out value="${product_t.brand}"/></td>   
                       <td class="column-3" style="padding-left:87px"><c:out value="${product_t.price}"/></td>
                       <td class="column-4 btn-addwish-b2" style="padding-left:90px">
                             <a href="#" onClick="likeSearch( '${product_t.productId}', ${idx}, '<c:url value='/productLike'/>', '${kind}', 'search', '${searchWord}')">
                             <c:set var="a" value="${product_t.productId}"/>
                             <c:if test="${myProdList_t.indexOf(a) != -1}">
                                   <img id="${idx}" src="<c:url value='/images/icons/icon-heart-02.png' />" >
                             </c:if>
                             <c:if test="${myProdList_t.indexOf(a) == -1}">
                                   <img id="${idx}" src="<c:url value='/images/icons/icon-heart-01.png' />" >
                             </c:if>
                             </a>
                  </td>
                  </tr>
                  <c:set var="idx" value="${idx + 1}"/>
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
