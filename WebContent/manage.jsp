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
	function manageView(targetUri) {
	    alert(targetUri);   
	    form.action = targetUri;
	    form.submit();
	 }
</script>
</head>

<body>
	<!-- Header import -->
	<jsp:include page="/WEB-INF/views/header.jsp"/>
	
   <!-- Title page -->
   <section class="bg-img1 txt-center p-lr-15 p-tb-92" style="background-image: url('images/sign_bg.png');">
      <h2 class="ltext-105 cl0 txt-center">
        Management
      </h2>
   </section>   


<div style="height:50px;">&nbsp;</div>

<form name="form" action="<c:url value='/signUpdate'/>">
	<div class="flex-w flex-c-m m-tb-10">	
		<!-- Product Insert -->
		<div class="flex-c-m stext-106 cl6 size-104 bor4 pointer hov-btn3 trans-04 m-r-8 m-tb-4" style="width:300px;height:200px">
			<input type="button" onClick="manageView('<c:url value='/phone_insert'/>')" style="font-size:25pt; background-color:transparent;" value="Insert Phone"/>
		</div>
		<div class="flex-c-m stext-106 cl6 size-104 bor4 pointer hov-btn3 trans-04 m-r-8 m-tb-4" style="width:300px;height:200px">
			<input type="button" onClick="manageView('<c:url value='/laptop_insert'/>')" style="font-size:25pt; background-color:transparent;" value="Insert Laptop"/>
		</div>
		<div class="flex-c-m stext-106 cl6 size-104 bor4 pointer hov-btn3 trans-04 m-r-8 m-tb-4" style="width:300px;height:200px">
			<input type="button" onClick="manageView('<c:url value='/camera_insert'/>')" style="font-size:25pt; background-color:transparent;" value="Insert Camera"/>
		</div>
		<div class="flex-c-m stext-106 cl6 size-104 bor4 pointer hov-btn3 trans-04 m-r-8 m-tb-4" style="width:300px;height:200px">
			<input type="button" onClick="manageView('<c:url value='/tablet_insert'/>')" style="font-size:25pt; background-color:transparent;" value="Insert Tablet"/>
		</div>
	</div>
</form>