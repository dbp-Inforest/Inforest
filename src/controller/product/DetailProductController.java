package controller.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.dao.CameraDAO;
import model.dao.LaptopDAO;
import model.dao.PCommentDAO;
import model.dao.PhoneDAO;
import model.dao.TabletDAO;
import model.dto.Camera;
import model.dto.Laptop;
import model.dto.PComment;
import model.dto.Phone;
import model.dto.Tablet;

import model.service.Recommendation;

public class DetailProductController implements Controller{
   
   private PhoneDAO phoneDAO = new PhoneDAO(); 
   private LaptopDAO laptopDAO = new LaptopDAO();
   private CameraDAO cameraDAO = new CameraDAO();
   private TabletDAO tabletDAO = new TabletDAO();
   private PCommentDAO pcommentDAO = new PCommentDAO();
   
   @Override
   public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      /* phone.jsp, camera.jsp등에서 파라미터로 kind2와 pId를 전달받아서
        kind의 종류에 따라서 그안의 함수를 이용하여 pid를 찾고 setAttribute로 해당pid의 세부정보를 넘겨주고
       product-detail.jsp에서 컨트롤러부터 넘겨받은 pid세부정보의 컬럼 하나하나를 값으로 넣어준다. */
      
      String kind = request.getParameter("kind2"); //0,1,2,3
      String pid = request.getParameter("pId"); //list에서 넘긴거 
     // String plist2 = request.getParameter("plist");
      
      System.out.println("detail컨트롤러--"+kind+"그리고"+pid);
      Recommendation userAnalysis;
  
      if(request.getMethod().equals("GET")) { // GET request 처리          
          if(kind.equals("0")) { //phone
            System.out.println("여기는 갯겟겟겟임" + pid);
            userAnalysis = new Recommendation(pid, kind);
             Phone phoneDetail = phoneDAO.getPhoneById(pid);
           
             List<PComment> plist = pcommentDAO.getPCommentList();
             request.setAttribute("plist", plist);
             request.setAttribute("phoneDetail", phoneDetail);
             request.setAttribute("pRecomm", userAnalysis.getList());
             return "/phone-detail.jsp";   
             
          }else if(kind.equals("1")){ //laptop
            userAnalysis = new Recommendation(pid, kind);
             Laptop laptopDetail = laptopDAO.getLaptopById(pid);
             List<PComment> plist = pcommentDAO.getPCommentList();
             request.setAttribute("laptopDetail", laptopDetail);   
             request.setAttribute("plist", plist);
             request.setAttribute("lRecomm", userAnalysis.getList());
               return "/laptop-detail.jsp";   
               
           }else if(kind.equals("2")) { //camera
            userAnalysis = new Recommendation(pid, kind);  
             Camera cameraDetail = cameraDAO.getCameraById(pid);
             List<PComment> plist = pcommentDAO.getPCommentList();
             request.setAttribute("cameraDetail", cameraDetail); 
             request.setAttribute("plist", plist);
             request.setAttribute("cRecomm", userAnalysis.getList());
               return "/camera-detail.jsp";   
               
          }else if(kind.equals("3")) { //tablet
            userAnalysis = new Recommendation(pid, kind);
             Tablet tabletDetail = tabletDAO.getTabletById(pid);
             List<PComment> plist = pcommentDAO.getPCommentList();
             request.setAttribute("tabletDetail", tabletDetail);  
             request.setAttribute("plist", plist);
             request.setAttribute("tRecomm", userAnalysis.getList());
               return "/tablet-detail.jsp";   
          }      
      
          System.out.println("메인으로 이동");
          return "redirect:/main";
       }
       else if(request.getMethod().equals("POST")) { //POST request 처리 (댓글)
          System.out.println("DetailProductController: POST REQUEST 처리 시작 ");
          String review = request.getParameter("review");  
          String kind2 = request.getParameter("kind");   
          String pId = request.getParameter("pId"); 
          
          HttpSession session = request.getSession();
          String id = UserSessionUtils.getLoginUserId(session);
         
          System.out.println(review+ "--" + id + "--" + pId);
          PComment pcm = new PComment(review, id, pId);
          int num = pcommentDAO.insertPComment(pcm);
      
          if(kind2.equals("0")) { //phone
            System.out.println("여기는 포스트포스트폿트으으으임");
             Phone phoneDetail = phoneDAO.getPhoneById(pId);
             List<PComment> plist = pcommentDAO.getPCommentList();
             System.out.println(num + "만큼 인서트했다.");
             request.setAttribute("plist", plist);
             request.setAttribute("phoneDetail", phoneDetail);
             return "/phone-detail.jsp"; 
             //return "redirect:/productDetail?kind2=" + kind + "&pId=" + pid;   
             
          }else if(kind2.equals("1")){ 
             Laptop laptopDetail = laptopDAO.getLaptopById(pId);
             List<PComment> plist = pcommentDAO.getPCommentList();
             request.setAttribute("laptopDetail", laptopDetail);   
             request.setAttribute("plist", plist);
               return "/laptop-detail.jsp";   
               
           }else if(kind2.equals("2")) { //camera
             Camera cameraDetail = cameraDAO.getCameraById(pId);
             List<PComment> plist = pcommentDAO.getPCommentList();
             request.setAttribute("cameraDetail", cameraDetail);   
             request.setAttribute("plist", plist);
               return "/camera-detail.jsp";   
               
          }else if(kind2.equals("3")) { //tablet
             Tablet tabletDetail = tabletDAO.getTabletById(pId);
             List<PComment> plist = pcommentDAO.getPCommentList();
             request.setAttribute("tabletDetail", tabletDetail);  
             request.setAttribute("plist", plist);
               return "/tablet-detail.jsp";   
          }      
      
          System.out.println("메인으로 이동");
          return "redirect:/main";
       }
       System.out.println("DetailProductController: 실패");
       return "redirect:/main";
   }
}