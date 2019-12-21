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
               return "redirect:/signIn";      // login form ��û���� redirect
        }
       
       String productId = request.getParameter("likeProduct"); //productId �޾ƿ���
       String userId = (String)session.getAttribute("userId");
       String division = request.getParameter("division"); // ���ƿ� �������� ����Ѱ��� ���� true, false��
       String kind = request.getParameter("kind"); //4�� ī�װ� �� ����
       String page = request.getParameter("page");
       String searchWord = request.getParameter("searchWord");
       
       ProductLike like = new ProductLike(productId, userId);
       System.out.println("�˻���� �̰�  : " + searchWord);
       
       try { //productLike ���̺� insert�ϰų� delete
          ProductLikeDAO manager = new ProductLikeDAO();
          System.out.println("1");
          if (division.equals("true")) {
             manager.insertProductLike(like); //db�� �߰�
             System.out.println("2");
          }
          else if (division.equals("false")){
             manager.deleteProductLike(like); //db���� ����
             System.out.println("3");
          }
          
          if (page == null) { 
             return "redirect:/productList?kind3=" + kind;
          }
          else {//product-search.jsp���� ��Ʈ ������ ��
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