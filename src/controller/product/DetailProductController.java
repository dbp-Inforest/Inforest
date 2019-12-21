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

import model.service.UserAnalysis;

public class DetailProductController implements Controller{
   
   private PhoneDAO phoneDAO = new PhoneDAO(); 
   private LaptopDAO laptopDAO = new LaptopDAO();
   private CameraDAO cameraDAO = new CameraDAO();
   private TabletDAO tabletDAO = new TabletDAO();
   private PCommentDAO pcommentDAO = new PCommentDAO();
   
   @Override
   public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      /* phone.jsp, camera.jsp��� �Ķ���ͷ� kind2�� pId�� ���޹޾Ƽ�
        kind�� ������ ���� �׾��� �Լ��� �̿��Ͽ� pid�� ã�� setAttribute�� �ش�pid�� ���������� �Ѱ��ְ�
       product-detail.jsp���� ��Ʈ�ѷ����� �Ѱܹ��� pid���������� �÷� �ϳ��ϳ��� ������ �־��ش�. */
	   
      String kind = request.getParameter("kind2"); //0,1,2,3
      String pid = request.getParameter("pId");

      List<PComment> plist = pcommentDAO.getPCommentList();
      UserAnalysis userAnalysis;
      
      if(request.getMethod().equals("GET")) { // GET request ó��          
          if(kind.equals("0")) { //phone
        	 userAnalysis = new UserAnalysis(pid, kind);
             Phone phoneDetail = phoneDAO.getPhoneById(pid);
             request.setAttribute("phoneDetail", phoneDetail);
             request.setAttribute("plist", plist);
               return "/phone-detail.jsp";   
             
          }else if(kind.equals("1")){ //laptop
        	 userAnalysis = new UserAnalysis(pid, kind);
             Laptop laptopDetail = laptopDAO.getLaptopById(pid);
             request.setAttribute("laptopDetail", laptopDetail);   
               return "/laptop-detail.jsp";   
               
           }else if(kind.equals("2")) { //camera
        	 userAnalysis = new UserAnalysis(pid, kind);  
             Camera cameraDetail = cameraDAO.getCameraById(pid);
             request.setAttribute("cameraDetail", cameraDetail);   
               return "/camera-detail.jsp";   
               
          }else if(kind.equals("3")) { //tablet
        	 userAnalysis = new UserAnalysis(pid, kind);
             Tablet tabletDetail = tabletDAO.getTabletById(pid);
             request.setAttribute("tabletDetail", tabletDetail);   
               return "/tablet-detail.jsp";   
          }      
      
          System.out.println("�������� �̵�");
          return "redirect:/main";
       }
       else if(request.getMethod().equals("POST")) { //POST request ó�� (��� ó��)
          System.out.println("DetailProductController: POST REQUEST ó�� ���� ");
          String review = request.getParameter("review");          
          
          HttpSession session = request.getSession();
          String id = UserSessionUtils.getLoginUserId(session);
         
          PComment pcm = new PComment(review, id, pid);
          int num = pcommentDAO.insertPComment(pcm);
      
          if(kind.equals("0")) { //phone
        	 
             Phone phoneDetail = phoneDAO.getPhoneById(pid);
             List<PComment> plist2 = pcommentDAO.getPCommentList();
             System.out.println(num + "��ŭ �μ�Ʈ�ߴ�.");
             request.setAttribute("phoneDetail", phoneDetail);   
             request.setAttribute("plist", plist2);
             return "/phone-detail.jsp";   
             
          }else if(kind.equals("1")){ 
             Laptop laptopDetail = laptopDAO.getLaptopById(pid);
             request.setAttribute("laptopDetail", laptopDetail);   
               return "/laptop-detail.jsp";   
               
           }else if(kind.equals("2")) { //camera
             Camera cameraDetail = cameraDAO.getCameraById(pid);
             request.setAttribute("cameraDetail", cameraDetail);   
               return "/camera-detail.jsp";   
               
          }else if(kind.equals("3")) { //tablet
             Tablet tabletDetail = tabletDAO.getTabletById(pid);
             request.setAttribute("tabletDetail", tabletDetail);   
               return "/tablet-detail.jsp";   
          }      
      
          System.out.println("�������� �̵�");
          return "redirect:/main";
       }
       System.out.println("DetailProductController: ����");
       return "redirect:/main";
   }
}