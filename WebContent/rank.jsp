<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "java.util.*" %>
<%@ page import = "model.dao.*" %>
<%@ page import = "model.dto.*" %>

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
   
   <!-- Header import -->
   <jsp:include page="/WEB-INF/views/header.jsp" />
  
   <!-- Title page -->
   <section class="bg-img1 txt-center p-lr-15 p-tb-92" style="background-image: url('images/rank_bg.png'); " >
      <h2 class="ltext-105 cl0 txt-center">
         Rank
      </h2>
   </section>   
 
<!-- Rank -->
<!-- 이미지 크기는 80*80 픽셀 (.jpg) -->
<form class="bg0 p-t-75 p-b-85" style="position:absolute;left:50%;margin:0 0 0 -515px;">
      <div class="container" >
         <div class="row"  style="display:inline">
            <div class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50" style="display:inline;float:left;width:500px">
               <div class="bor10 p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm">
                  <h4 class="mtext-109 cl2 p-b-12">
                    <center> Phone </center>
                  </h4>
                  <hr>
                 <table width="350">
                     <tr class="table_head" height="30">
                        <th class="column-0" style="color:black">Rank</th>
                        <th class="column-1" style="padding-left:20px; color:black"><p style="padding-left:10px">Name</p></th>
                        <th class="column-2"><p style="padding-left:40px">
                       
                        <font color="black">Like</font>
                        <font color="red">♡</font>
                        </p></th>
                      </tr>
                      
                       <% 
                             List<Ranking> Topten_p = (List<Ranking>)request.getAttribute("phoneR");
                       
                             for(int i = 0; i < 10; i++) {   //top10 출력
                       %>
                             
                              <tr class="table_row" height="35" >
                                       <td class="column-0" style="color:black"><b><center>
                       <%= 
                              i+1            
                       %>
                       <!-- 랭킹 순위 [1-10] -->      
                                 
                                      </center> </b></td>
                                    <td class="column-1" style="padding-left:20px"><center>
                                   <a href="<c:url value='/productDetail'><c:param name="kind2" value="0"/>
                                   <c:param name="pId" value="<%= Topten_p.get(i).getProductId() %>"/></c:url>" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                                        <%=
                                              Topten_p.get(i).getName()
                                    %>  
                                </a> </center>  </td>
                                    <td class="column-2" style="padding-left:40px"><center>
                                         <%= Topten_p.get(i).getLikeCount() %>
                                   </center></td>
                               </tr>
                       <%
                          } 
                       %>
                       
                  </table>
               </div>
            </div>
            
            
          <div class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50" style="display:inline;float:left;width:500px">
               <div class="bor10 p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm">
                  <h4 class="mtext-109 cl2 p-b-12">
                     <center>LapTop</center>
                  </h4>
                  <hr>
                   <table width="350">
                     <tr class="table_head" height="30">
                        <th class="column-0" style="color:black">Rank</th>
                        <th class="column-1" style="padding-left:20px; color:black"><p style="padding-left:10px">Name</p></th>
                        <th class="column-2"><p style="padding-left:40px">
                       
                        <font color="black">Like</font>
                         <font color="red">♡</font>
                        </p></th>
                      </tr>
                      
                       <% 
                             List<Ranking> Topten_l = (List<Ranking>)request.getAttribute("laptopR");
                       
                             for(int i = 0; i < 10; i++) {   //top10 출력
                       %>
                             
                              <tr class="table_row" height="35" >
                                       <td class="column-0" style="color:black"><b><center>
                       <%= 
                              i+1            
                       %>
                       <!-- 랭킹 순위 [1-10] -->      
                                 
                                      </center> </b></td>
                                    <td class="column-1" style="padding-left:20px"><center>
                                   <a href="<c:url value='/productDetail'><c:param name="kind2" value="1"/>
                                   <c:param name="pId" value="<%= Topten_l.get(i).getProductId() %>"/></c:url>" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                                        <%=
                                              Topten_l.get(i).getName()
                                    %>  
                                </a> </center>  </td>
                                    <td class="column-2" style="padding-left:40px"><center>
                                         <%= Topten_l.get(i).getLikeCount() %>
                                   </center></td>
                               </tr>
                       <%
                          } 
                       %>
                       
                  </table>
               </div>
            </div>
         </div>
      </div>
      <div class="container">
         <div class="row" style="display:inline">
            <div class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50" style="display:inline;float:left;width:500px">
               <div class="bor10 p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm">
                  <h4 class="mtext-109 cl2 p-b-12">
                    <center> Camera </center>
                  </h4>
                  <hr>
                    <table width="350">
                     <tr class="table_head" height="30">
                        <th class="column-0" style="color:black">Rank</th>
                        <th class="column-1" style="padding-left:20px; color:black"><p style="padding-left:10px">Name</p></th>
                        <th class="column-2"><p style="padding-left:40px">
                       
                        <font color="black">Like</font>
                        <font color="red">♡</font>
                        </p></th>
                      </tr>
                      
                       <% 
                             List<Ranking> Topten_c = (List<Ranking>)request.getAttribute("cameraR");
                       
                             for(int i = 0; i < 10; i++) {   //top10 출력
                       %>
                             
                              <tr class="table_row" height="35" >
                                       <td class="column-0" style="color:black"><b><center>
                       <%= 
                              i+1            
                       %>
                       <!-- 랭킹 순위 [1-10] -->      
                                 
                                      </center> </b></td>
                                    <td class="column-1" style="padding-left:20px"><center>
                                   <a href="<c:url value='/productDetail'><c:param name="kind2" value="2"/>
                                   <c:param name="pId" value="<%= Topten_c.get(i).getProductId() %>"/></c:url>" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                                        <%=
                                              Topten_c.get(i).getName()
                                    %>  
                                </a> </center>  </td>
                                    <td class="column-2" style="padding-left:40px"><center>
                                         <%= Topten_c.get(i).getLikeCount() %>
                                   </center></td>
                               </tr>
                       <%
                          } 
                       %>
                       
                  </table>
               </div>
            </div>
            <div class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50"  style="display:inline;float:left;width:500px">
               <div class="bor10 p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm">
                  <h4 class="mtext-109 cl2 p-b-12">
                    <center> Tablet </center>
                  </h4>
                  <hr>
                 <table width="350">
                     <tr class="table_head" height="30">
                        <th class="column-0" style="color:black">Rank</th>
                        <th class="column-1" style="padding-left:20px; color:black"><p style="padding-left:10px">Name</p></th>
                        <th class="column-2"><p style="padding-left:40px">
                        
                        <font color="black">Like</font>
                        <font color="red">♡</font>
                        </p></th>
                      </tr>
                      
                       <% 
                             List<Ranking> Topten_t = (List<Ranking>)request.getAttribute("tabletR");
                       
                             for(int i = 0; i < 10; i++) {   //top10 출력
                       %>
                             
                              <tr class="table_row" height="35" >
                                       <td class="column-0" style="color:black"><b><center>
                       <%= 
                              i+1            
                       %>
                       <!-- 랭킹 순위 [1-10] -->      
                                 
                                      </center> </b></td>
                                    <td class="column-1" style="padding-left:20px"><center>
                                   <a href="<c:url value='/productDetail'><c:param name="kind2" value="3"/>
                                   <c:param name="pId" value="<%= Topten_t.get(i).getProductId() %>"/></c:url>" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                                        <%=
                                              Topten_t.get(i).getName()
                                    %>  
                                </a> </center>  </td>
                                    <td class="column-2" style="padding-left:40px"><center>
                                         <%= Topten_t.get(i).getLikeCount() %>
                                   </center></td>
                               </tr>
                       <%
                          } 
                       %>
                       
                  </table>
               </div>
            </div>
         </div>
      </div>
   </form>




   <!-- Back to top -->
   <div class="btn-back-to-top" id="myBtn">
      <span class="symbol-btn-back-to-top">
         <i class="zmdi zmdi-chevron-up"></i>
      </span>
   </div>

<!--===============================================================================================-->   
   <script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
   <script src="vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
   <script src="vendor/bootstrap/js/popper.js"></script>
   <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
   <script src="vendor/select2/select2.min.js"></script>
   <script>
      $(".js-select2").each(function(){
         $(this).select2({
            minimumResultsForSearch: 20,
            dropdownParent: $(this).next('.dropDownSelect2')
         });
      })
   </script>
<!--===============================================================================================-->
   <script src="vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
<!--===============================================================================================-->
   <script src="vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
   <script>
      $('.js-pscroll').each(function(){
         $(this).css('position','relative');
         $(this).css('overflow','hidden');
         var ps = new PerfectScrollbar(this, {
            wheelSpeed: 1,
            scrollingThreshold: 1000,
            wheelPropagation: false,
         });

         $(window).on('resize', function(){
            ps.update();
         })
      }); 
   </script>
<!--===============================================================================================-->
   <script src="js/main.js"></script>

</body>
</html>