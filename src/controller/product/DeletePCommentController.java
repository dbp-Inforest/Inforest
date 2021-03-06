package controller.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.dao.*;

//delete는 관리자가 하는 것임  이번 중간구현 이후에 할 것 
public class DeletePCommentController implements Controller {

   private static final Logger log = LoggerFactory.getLogger(DeleteProductController.class);
   
   private PCommentDAO pcommentDAO = new PCommentDAO();
   
   @Override
   public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      // TODO Auto-generated method stub

      String kind = null;
      String pId = null;
      String reviewId = null;
      
       if(request.getMethod().equals("POST")) { //DELETE COMMENT
          reviewId = (String)request.getParameter("reviewId");
          kind = (String)request.getParameter("kind2");        
          pId = (String)request.getParameter("pId2");
          System.out.println("댓삭시작->"+ reviewId + "커멘트 아이디" + kind + "  "+ pId);
          pcommentDAO.deletePComment(reviewId);
          return "redirect:/productDetail?kind2=" + kind + "&pId=" + pId; 
       }
       return "redirect:/productList?kind3=" + kind;
   }

}