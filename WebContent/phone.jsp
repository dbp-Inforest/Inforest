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
<script>
function search(kind, targetUri) {
   if (form.phoneSearch.value == "") {
         alert("검색어를 입력해주세요");
         form.phoneSearch.focus();
         return false;
   } 
   form.action = targetUri;
   form.kind2.value = kind;
   form.submit();
}
function like(productId, num, targetUri) {
   var image = document.getElementById(num);
   
   if (image.src.match("icon-heart-01.png")) {
	   alert("'좋아요'목록에 추가하시겠습니까?");
	   alert("'좋아요' 추가!");
	   
      pform.action = targetUri;
      pform.likeProduct.value = productId;
      pform.division.value = "true";
      pform.kind.value = "0";
      pform.submit();
      image.src = "<c:url value='/images/icons/icon-heart-02.png' />";
   }
   else {
	   alert("'좋아요'를 취소하시겠습니까?")
       alert("'좋아요' 취소!");
	   
      pform.action = targetUri;
      pform.likeProduct.value = productId;
      pform.division.value = "false";
      pform.kind.value = "0";
      pform.submit();
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
         Phone
      </h2>
   </section>      
   
<!-- search form -->
<form class="p-t-20" style="margin:0 0 0 450px;" name="form"  action="<c:url value='/productSearch' />" >
<input type="hidden" name="kind2" value="10"/>
   <div class="flex-w flex-m m-r-20 m-tb-5">
       <input class="stext-104 cl2 plh4 size-search bor13 p-lr-20 m-r-10 m-tb-5" type="text" name="phoneSearch" placeholder="Please write down the search term.">
      <div class="flex-c-m stext-101 cl2 size-118 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-5">
      <button type="button"  onClick="search(0, '<c:url value='/productSearch'/>')">Search</button>
      </div>
   </div>
</form>  
   
   <form name = "pform" action="" class="bg0 p-t-30 p-b-85" style="position:absolute;left:50%;margin:0 0 0 -510px;">
   
   <input type="hidden" name="likeNum" value="200"/>
   <input type="hidden" name="likeProduct" value="200"/>
   <input type="hidden" name="division" value="200"/>
   <input type="hidden" name="kind" value="0"/>
   
      <div class="container" >
         <div class="row"  style="display:inline">
            <div class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50" style="display:inline;float:left;width:1050px">
               <div class="bor10 p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm" style="font-size:20px">
                  <h4 class="mtext-109 cl2 p-b-12">
                     Phone
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
                       
                  <c:if test="${phoneList.size() == 0 }">
                  <tfoot>
                  <tr>
                  <td colspan="3">현재 데이터가 없습니다.</td>
                  </tr>
                  </tfoot>
                  </c:if>
                  <!-- //request.getAttribute("List") == ${List} -->
                  <tbody> 
                  <c:forEach var="phone" items="${phoneList}" varStatus="status">
                  <tr class="table_row" style="height:70px;">
                  <td class="column-0" style="padding-left:30px">
                                <a href="<c:url value='/productDetail'><c:param name="kind2" value="0"/>
                                                              <c:param name="pId" value="${phone.productId}"/>
                                       </c:url>" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                                      <font style="font-size:20px">
                                         <c:out value="${phone.productId}"/>
                                      </font>
                                 </a>
                
                        </td>
                       <td class="column-1" style="padding-left:50px"><c:out value="${phone.name }"/></td>
                       <td class="column-2" style="padding-left:85px"><c:out value="${phone.brand}"/></td>   
                       <td class="column-3" style="padding-left:70px"><c:out value="${phone.price}"/></td>
                       <td class="column-4 btn-addwish-b2" style="padding-left:90px">
                             <a href="#" onClick="like( '${phone.productId}', ${status.index}, '<c:url value='/productLike'/>' )">
                             <c:set var="a" value="${phone.productId}"/>
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
                  </tbody>
                  </table>
               </div>
            </div>
         </div>
      </div>
   </form>
</body>
</html>