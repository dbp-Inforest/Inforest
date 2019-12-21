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
function search(kind, targetUri) {
   if (form.allSearch.value == "") {
         alert("검색어를 입력해주세요");
         form.allSearch.focus();
         return false;
   } 
   form.action = targetUri;
   
    form.kind2.value = kind;
   form.submit();
}

   function p_view(kind, targetUri) { 
       form1.action = targetUri;
       form1.kind3.value = kind;
       form1.submit();
    }
</script>

</head>
<body class="animsition">
	<!-- Header import -->
	<jsp:include page="/WEB-INF/views/header.jsp" />

   <!-- Title page -->
   <section class="bg-img1 txt-center p-lr-15 p-tb-92" style="background-image: url('images/product_bg.jpg');">
      <h2 class="ltext-105 cl0 txt-center">
         Product
      </h2>
   </section>   

   
      <!-- search form -->
	<form class="p-t-20" style="margin:0 0 0 450px;" name="form"  action="<c:url value='/productSearch' />" >
	<input type="hidden" name="kind2" value="10"/>
	   <div class="flex-w flex-m m-r-20 m-tb-5">
	       <input class="stext-104 cl2 plh4 size-search bor13 p-lr-20 m-r-10 m-tb-5" type="text" name="allSearch" placeholder="Please write down the search term.">
	      <div class="flex-c-m stext-101 cl2 size-118 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-5">
	      <button type="button"  onClick="search(4, '<c:url value='/productSearch'/>')">Search</button>
	      </div>
	   </div>
	</form> 
   
   
   <form name="form1" action="<c:url value='/productList'/>">
   <input type="hidden" name="kind3" value="10"/>
   <div class="flex-w flex-c-m m-tb-10"> <!-- a태그로 하거나  -->
      <div class="flex-c-m stext-106 cl6 size-104 bor4 pointer hov-btn3 trans-04 m-r-8 m-tb-4" style="width:300px;height:200px">
         <input type="button" onClick="p_view(0, '<c:url value='/productList'/>')" style="font-size:25pt; background-color:transparent;" value="Phone"/>
      </div>
      <div class="flex-c-m stext-106 cl6 size-105 bor4 pointer hov-btn3 trans-04 m-r-8 m-tb-4" style="width:300px;height:200px">
         <input type="button" onClick="p_view(1, '<c:url value='/productList'/>')" style="font-size:25pt; background-color:transparent;" value="Laptop"/>
      </div>
      <div class="flex-c-m stext-106 cl6 size-104 bor4 pointer hov-btn3 trans-04 m-r-8 m-tb-4" style="width:300px;height:200px">
         <input type="button" onClick="p_view(2, '<c:url value='/productList'/>')" style="font-size:25pt; background-color:transparent;" value="Camera"/>
      </div>
      <div class="flex-c-m stext-106 cl6 size-105 bor4 pointer hov-btn3 trans-04 m-r-8 m-tb-4" style="width:300px;height:200px">
         <input type="button" onClick="p_view(3, '<c:url value='/productList'/>')" style="font-size:25pt; background-color:transparent;" value='Tablet'/>
      </div>
   </div>
   </form>
</body>
</html>