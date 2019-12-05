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

<style>
	#account-table {
	  font-size: 30px;
	  text-align : center;
	  width : 400px;
	  height : 50px;
	}
</style>

<script>
	function manageView(userId, targetUri) {
	    alert(targetUri);   
	    form.action = targetUri;

	    form.userId.value = userId;
	    alert(form.userId.value);
	
	    form.submit();
	 }
</script>

</head>
<body class="animsition">
	<!-- Header import -->
	<jsp:include page="/WEB-INF/views/header.jsp"/>


   <!-- Title page -->
   <section class="bg-img1 txt-center p-lr-15 p-tb-92" style="background-image: url('images/mypage_bg.png');">
      <h2 class="ltext-105 cl0 txt-center">
         My page
      </h2>
   </section>  
    
	<br><br>
 	<div class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50">
       <div class="p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm">
       		<h4 class="mtext-109 cl2 p-b-30">
                My Accounts
            </h4>
            <%
            	InforestUserDAO userDAO = new InforestUserDAO();
            	InforestUser user = userDAO.getInforestUserById((String)session.getAttribute("userId"));
            	String pos = "user";
            	if(user.getPosition() == 0) {
            		pos = "admin";
            	}
            %>
            <table id="account-table">
	          <div class="flex-w flex-t bor12 p-b-13">
	            <div class="size-210">
	            	<tr id="account-table" ><th id="account-table">
	            	<div class="stext-117 cl2">
	                           ID  :
	                </div></th>
	               <td id="account-table">
	               <div class="stext-117 cl2">
                         		<%= user.getUserId() %>
	                </div></td></tr>
	                </div>
	                <tr></tr>
	                <div class="size-210">
	            	<tr id="account-table"><th id="account-table">
	            	<div class="stext-117 cl2">
	                           NAME  :
	                 </div></th>
	                 <td id="account-table">
	                 <div class="stext-117 cl2">
	                           <%= user.getName() %>
	                 </div></td></tr></div>
	                 <tr></tr>
	                 <div>
	            	<tr id="account-table"><th id="account-table">
	            	<div class="stext-117 cl2">
	                           POSITION  :
	                 </div></th>
	                 <td id="account-table"><div class="stext-117 cl2">
	                        	<%= pos %>
	                 </div></td></tr>                               
	              </div>
	            </div>
           </table>
           <br><br>
           <input type="hidden" name="userId" value="<%= session.getAttribute("userId") %>"/>
	       <form name="form" action="<c:url value='/signUpdate'/>">	
	       		<div class="size-204 flex-w flex-m respon6-next">
				<!-- User Update -->	
				<button class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer" onClick="manageView(<%= session.getAttribute("userId") %>, '<c:url value='/user_update'/>')">
					        User Update </button>
				</div>
			</form>
			<br>
				<!-- User Delete -->
			<form name="form" action="<c:url value='/user_delete'/>">
				<div class="size-204 flex-w flex-m respon6-next">
				<button class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer" onClick="manageView(<%= session.getAttribute("userId") %>, '<c:url value='/user_delete'/>')">
					User Delete </button>
				</div>
			</form>  
			</div>        
         </div>
       </div>
   <br><br>
   <!-- ProductLike -->
   <form class="bg0 p-t-75 p-b-85">
      <span class="container">
         <span class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50">
            <div class="bor10 p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm">
                 <h4 class="mtext-109 cl2 p-b-30">
                     My Products
                  </h4>
                  <div class="wrap-table-shopping-cart">
                     <table class="table-shopping-cart">
                        <tr class="table_head">
                           <th class="column-1">Kind</th>
                           <th class="column-2">Product</th>
                           <th class="column-3"></th>
                           <th class="column-4">Brand</th>
                           <th class="column-5">Price</th>
                        </tr>

                        <tr class="table_row">
                           <td class="column-1">
								Phone
                           </td>
                           <td class="column-2">
                              <div class="how-itemcart1">
                                 <img src="images/phone_img.jpg" alt="IMG"> 
                              </div>
                           </td>
                           <td class="column-3">IPhone 11</td>
                           <td class="column-4">APPLE</td>
                           <td class="column-5">990, 000부터</td>
                        </tr>

                        <tr class="table_row">
                              <td class="column-1">
								Laptop
                           </td>
                           <td class="column-2">
                              <div class="how-itemcart1">
                                 <img src="images/laptop_img.jpg" alt="IMG"> 
                              </div>
                           </td>
                           <td class="column-3">맥북에어</td>
                           <td class="column-4">APPLE</td>
                           <td class="column-5">1,890,000</td>
                        </tr>
                     </table>
                  </div>
					<br>
					<center>
	                  	<button class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer">
                     	GO TO CLICK "LIKE"
                  		</button>				
					</center>
                 </div>
            </span>
		</span>
		<br><br>
		<span class="container">
            <span class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50">
               <div class="bor10 p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm">
                  <h4 class="mtext-109 cl2 p-b-30">
                     My Comments
                  </h4>

                  <div class="flex-w flex-t bor12 p-b-13">
                     <div class="size-208">
                        <span class="stext-110 cl2">
                           Comment List: user
                        </span>
                     </div>
                  <div class="flex-w flex-t p-t-27 p-b-33">
                     <div class="size-400">
                           	아이폰 11 너무 좋아요!!! 인덕션 최고 내용
                     </div>
                  </div>
                   </div>
                  <br><br>
					<center>
	                  <button class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer">
	                     GO TO WRITE COMMENT
	                  </button>
                  </center>
              
	      </span>
	    </span>
	   </form>
      
   <!-- Back to top -->
   <div class="btn-back-to-top" id="myBtn">
      <span class="symbol-btn-back-to-top">
         <i class="zmdi zmdi-chevron-up"></i>
      </span>
   </div>
</body>
</html>