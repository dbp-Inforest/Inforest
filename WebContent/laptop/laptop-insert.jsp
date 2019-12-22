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
function productCreate() {
	alert("눌렸다");
	form.submit();
}

function productList(targetUri) {
    form.action = targetUri;
    form.submit();
 }
   
</script>

</head>
<body>
	<!-- Header import -->
	<jsp:include page="/WEB-INF/views/header.jsp"/>
	 
   <!-- Title page -->
   <section class="bg-img1 txt-center p-lr-15 p-tb-92" style="background-image: url('images/product_bg.jpg');">
      <h2 class="ltext-105 cl0 txt-center">
         Laptop Insert
      </h2>
   </section> 
   
 <div style="height:50px;">&nbsp;</div>


<!-- 제품 추가 테이블 -->
<div class="container">
<div class="row">
<form class="bg0 p-t-75 p-b-85" name="form" method="POST" action="<c:url value='/insertProduct' />" style="position:absolute;left:50%;margin:0 0 0 -310px;">
   <input class="stext-104 cl2 plh4 size-sign bor13 p-lr-20 m-r-10 m-tb-5" type="hidden" name="pKind" value="1">
   <table style="width:750px; margin-left: auto; margin-right: auto;" class="table-sign">
   <tr class="table_row">
      <td class="column-1" >
         PRODUCT_ID
      </td>
      <td class="column-2">
         <input class="stext-104 cl2 plh4 size-sign bor13 p-lr-20 m-r-10 m-tb-5" type="text" name="productId" placeholder="모델명을 입력해주세요">
      </td>
   </tr>

   <tr class="table_row">
      <td class="column-1">
         NAME
      </td>
      <td class="column-2">
         <input class="stext-104 cl2 plh4 size-sign bor13 p-lr-20 m-r-10 m-tb-5" type="text" name="pName" placeholder="제품명을 입력해주세요">
      </td>
   </tr>
   
   <tr class="table_row">
      <td class="column-1">
         P_COLOR
      </td>
      <td class="column-2">
         <input class="stext-104 cl2 plh4 size-sign bor13 p-lr-20 m-r-10 m-tb-5" type="text" name="pColor" placeholder="색깔을 입력하세요">
      </td>
   </tr>
   
   <tr class="table_row">
      <td class="column-1">
         PRICE
      </td>
      <td class="column-2">
         <input class="stext-104 cl2 plh4 size-sign bor13 p-lr-20 m-r-10 m-tb-5" type="text" name="pPrice" placeholder="가격을 입력해주세요">
      </td>
   </tr>
   
   <tr class="table_row">
      <td class="column-1">
         BRAND
      </td>
      <td class="column-2">
         <input class="stext-104 cl2 plh4 size-sign bor13 p-lr-20 m-r-10 m-tb-5" type="text" name="pBrand" placeholder="브랜드를 입력해주세요">
      </td>
   </tr>
   
   <tr class="table_row">
      <td class="column-1">
         RELEASED_DATE
      </td>
      <td class="column-2">
         <input class="stext-104 cl2 plh4 size-sign bor13 p-lr-20 m-r-10 m-tb-5" type="date" name="pDate" placeholder="출시일자  ex ) YY/MM/DD">
      </td>
   </tr>
   
   <tr class="table_row">
      <td class="column-1">
         WEIGHT (g)
      </td>
      <td class="column-2">
         <input class="stext-104 cl2 plh4 size-sign bor13 p-lr-20 m-r-10 m-tb-5" type="text" name="pWeight" placeholder="무게는 몇 g인가요?">
      </td>
   </tr>
   
   <!-- 여기서부터 개별 컬럼들  -->

   <tr class="table_row">
      <td class="column-1">
		  L_PURPOSE
      </td>
      <td class="column-2">
         <input class="stext-104 cl2 plh4 size-sign bor13 p-lr-20 m-r-10 m-tb-5" type="text" name="lPurpose" placeholder="목적에 대해 입력하세요">
      </td>
   </tr>
   
   <tr class="table_row">
      <td class="column-1">
         L_DISPLAY (GB)
      </td>
      <td class="column-2">
         <input class="stext-104 cl2 plh4 size-sign bor13 p-lr-20 m-r-10 m-tb-5" type="text" name="lDisplay" placeholder="디스플레이 정보에 대해 입력하세요">
      </td>
   </tr>
   
   <tr class="table_row">
      <td class="column-1">
         L_CPU
      </td>
      <td class="column-2">
         <input class="stext-104 cl2 plh4 size-sign bor13 p-lr-20 m-r-10 m-tb-5" type="text" name="lCPU" placeholder="CPU정보에 대해 입력하세요">
      </td>
   </tr>
   
   <tr class="table_row">
      <td class="column-1">
         L_RAM_MEMORY (GB)
      </td>
      <td class="column-2">
         <input class="stext-104 cl2 plh4 size-sign bor13 p-lr-20 m-r-10 m-tb-5" type="text" name="lRAMMemory" placeholder="RAM과  MEMORY에 대해 입력하세요">
      </td>
   </tr>
   
   <tr class="table_row">
      <td class="column-1">
         L_OS
      </td>
      <td class="column-2">
         <input class="stext-104 cl2 plh4 size-sign bor13 p-lr-20 m-r-10 m-tb-5" type="text" name="lOS" placeholder="운영체제에 대해 입력하세요">
      </td>
   </tr>
   
   <tr class="table_row">
      <td class="column-1">
         L_SSD
      </td>
      <td class="column-2">
         <input class="stext-104 cl2 plh4 size-sign bor13 p-lr-20 m-r-10 m-tb-5" type="text" name="lSSD" placeholder="핸드폰 카메라 정보에 대해 입력하세요">
      </td>
   </tr>
               
   <tr class="table_row">
      <td class="column-1">
         <div class="flex-c-m stext-101 cl2 size-submit bg8 bor13 hov-btn3  trans-04 pointer m-tb-5">
        <button type="button" onClick="productList('<c:url value='/management'/>')">BACK</button>
               </div>
      </td>
      <td class="column-2">
         <div class="flex-c-m stext-101 cl2 size-submit bg8 bor13 hov-btn3  trans-04 pointer m-tb-10">
            <button type="button" value="insert" onClick="productCreate()">INSERT</button>
         </div>
      </td>
   </tr>
   </table>
   </form>
   </div>
   </div>
</body>
</html>