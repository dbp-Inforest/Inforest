package controller.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.dao.*;
import model.dto.*;

public class LikeProductController implements Controller{
    
    public String execute(HttpServletRequest request, HttpServletResponse response)   throws Exception {
       HttpSession session = request.getSession();
       
       if (!UserSessionUtils.hasLogined(session)) {
               return "redirect:/signIn";      // login form 요청으로 redirect
        }
       
       String productId = request.getParameter("likeProduct"); //productId 받아오기
       String userId = (String)session.getAttribute("userId");
       String division = request.getParameter("division"); // 좋아요 누른건지 취소한건지 구분 true, false로
       String kind = request.getParameter("kind"); //4개 카테고리 중 뭔지
       String page = request.getParameter("page");
       String searchWord = request.getParameter("searchWord");
       
       ProductLike like = new ProductLike(productId, userId);
       System.out.println("검색어는 이것  : " + searchWord);
       
       try { //productLike 테이블에 insert하거나 delete
          ProductLikeDAO manager = new ProductLikeDAO();
          System.out.println("1");
          if (division.equals("true")) {
             manager.insertProductLike(like); //db에 추가
             System.out.println("2");
          }
          else if (division.equals("false")){
             manager.deleteProductLike(like); //db에서 삭제
             System.out.println("3");
          }
          
          if (page == null) { 
             return "redirect:/productList?kind3=" + kind;
          }
          else {//product-search.jsp에서 하트 눌렀을 때
             return "redirect:/productList?kind3=" + kind + "&page=" + page + "&searchWord=" + searchWord;
          }
       }catch(Exception e) {
             request.setAttribute("ProductLikeFailed", true);
            request.setAttribute("exception", e);
            System.out.println("Error in ProductLike");
            return "/Inforest/phone.jsp";
       }
    }
}