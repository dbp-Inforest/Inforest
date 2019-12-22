<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

	function UpdateAction(targetUri) {
	    form.action = targetUri;
	    alert(targetUri);
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
         Tablet Update
      </h2>
   </section> 
   
 <div style="height:50px;">&nbsp;</div>

<%
	TabletDAO tabletDAO = new TabletDAO();
	Tablet tablet = tabletDAO.getTabletById((String)request.getParameter("pId"));
	System.out.println("안녕 여기는 product-update : " + tablet.getProductId());
%>

<!-- 제품 추가 테이블 -->
<div class="container">
<div class="row">
<form class="bg0 p-t-75 p-b-85" name="form" method="POST" action="<c:url value='/insertProduct' />" style="position:absolute;left:50%;margin:0 0 0 -310px;">
   <input class="stext-104 cl2 plh4 size-sign bor13 p-lr-20 m-r-10 m-tb-5" type="hidden" name="pKind" value="3">
   <table style="width:750px; margin-left: auto; margin-right: auto;" class="table-sign">
   <tr class="table_row">
      <td class="column-1" >
         PRODUCT_ID
      </td>
      <td class="column-2">
         <input class="stext-104 cl2 plh4 size-sign bor13 p-lr-20 m-r-10 m-tb-5" value="<%= tablet.getProductId() %>" type="text" name="productId" placeholder="핸드폰 모델명을 입력해주세요">
      </td>
   </tr>

   <tr class="table_row">
      <td class="column-1">
         NAME
      </td>
      <td class="column-2">
         <input class="stext-104 cl2 plh4 size-sign bor13 p-lr-20 m-r-10 m-tb-5" value="<%= tablet.getName() %>" type="text" name="pName" placeholder="핸드폰 기종을 입력해주세요">
      </td>
   </tr>
   
   <tr class="table_row">
      <td class="column-1">
         P_COLOR
      </td>
      <td class="column-2">
         <input class="stext-104 cl2 plh4 size-sign bor13 p-lr-20 m-r-10 m-tb-5" value="<%= tablet.getColor() %>" type="text" name="pColor" placeholder="핸드폰 색깔을 입력하세요">
      </td>
   </tr>
   
   <tr class="table_row">
      <td class="column-1">
         PRICE
      </td>
      <td class="column-2">
         <input class="stext-104 cl2 plh4 size-sign bor13 p-lr-20 m-r-10 m-tb-5" value="<%= tablet.getPrice() %>" type="text" name="pPrice" placeholder="가격을 입력해주세요">
      </td>
   </tr>
   
   <tr class="table_row">
      <td class="column-1">
         BRAND
      </td>
      <td class="column-2">
         <input class="stext-104 cl2 plh4 size-sign bor13 p-lr-20 m-r-10 m-tb-5" type="text" name="pBrand" value="<%= tablet.getBrand() %>" placeholder="핸드폰 브랜드를 입력해주세요">
      </td>
   </tr>
   
   <tr class="table_row">
      <td class="column-1">
         RELEASED_DATE
      </td>
      <td class="column-2">
         <input class="stext-104 cl2 plh4 size-sign bor13 p-lr-20 m-r-10 m-tb-5" value="<%= tablet.getReleased_date() %>" type="date" name="pDate" placeholder="출시일자  ex ) YY/MM/DD">
      </td>
   </tr>
   
   <tr class="table_row">
      <td class="column-1">
         WEIGHT (g)
      </td>
      <td class="column-2">
         <input class="stext-104 cl2 plh4 size-sign bor13 p-lr-20 m-r-10 m-tb-5" value="<%= tablet.getWeight() %>" type="text" name="pWeight" placeholder="핸드폰 무게는 몇 g인가요?">
      </td>
   </tr>
   
   <tr class="table_row">
      <td class="column-1">
		  T_BATTERY
      </td>
      <td class="column-2">
         <input class="stext-104 cl2 plh4 size-sign bor13 p-lr-20 m-r-10 m-tb-5" type="text" name="tBattery" value="<%= tablet.gettBattery() %>" placeholder="배터리는 몇 mAh인가요?">
      </td>
   </tr>
   
   <tr class="table_row">
      <td class="column-1">
         T_MEMORY (GB)
      </td>
      <td class="column-2">
         <input class="stext-104 cl2 plh4 size-sign bor13 p-lr-20 m-r-10 m-tb-5" type="text" name="tMemory" value="<%= tablet.gettMemory() %>" placeholder="메모리 용량은 몇인가요?">
      </td>
   </tr>
   
   <tr class="table_row">
      <td class="column-1">
         T_SIZE
      </td>
      <td class="column-2">
         <input class="stext-104 cl2 plh4 size-sign bor13 p-lr-20 m-r-10 m-tb-5" type="text" name="tSize" value="<%= tablet.gettSize() %>" placeholder="사이즈는 몇인가요?">
      </td>
   </tr>

   <tr class="table_row">
      <td class="column-1">
         T_OS
      </td>
      <td class="column-2">
         <input class="stext-104 cl2 plh4 size-sign bor13 p-lr-20 m-r-10 m-tb-5" type="text" name="tOS" value="<%= tablet.gettOS() %>" placeholder="운영체제에 대해 입력하세요">
      </td>
   </tr>
   <input class="stext-104 cl2 plh4 size-sign bor13 p-lr-20 m-r-10 m-tb-5" type="hidden" name="kind" value="3">
               
   <tr class="table_row">
      <td class="column-1">
         <div class="flex-c-m stext-101 cl2 size-submit bg8 bor13 hov-btn3  trans-04 pointer m-tb-5">
         	<button type="button" onClick="UpdateAction('<c:url value='/product'/>')">BACK</button>
         </div>
      </td>
      <td class="column-2">
         <div class="flex-c-m stext-101 cl2 size-submit bg8 bor13 hov-btn3  trans-04 pointer m-tb-10">
            <button type="button" value="insert" onClick="UpdateAction('<c:url value='/updateProduct'/>')">UPDATE</button>
         </div>
      </td>
   </tr>
   </table>
   </form>
   </div>
   </div>
</body>
</html>