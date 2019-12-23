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
      /* phone.jsp, camera.jsp��� �Ķ���ͷ� kind2�� pId�� ���޹޾Ƽ�
        kind�� ������ ���� �׾��� �Լ��� �̿��Ͽ� pid�� ã�� setAttribute�� �ش�pid�� ���������� �Ѱ��ְ�
       product-detail.jsp���� ��Ʈ�ѷ����� �Ѱܹ��� pid���������� �÷� �ϳ��ϳ��� ������ �־��ش�. */
      
      String kind = request.getParameter("kind2"); //0,1,2,3
      String pid = request.getParameter("pId"); //list���� �ѱ�� 
     // String plist2 = request.getParameter("plist");
      
      System.out.println("detail��Ʈ�ѷ�--"+kind+"�׸���"+pid);
      Recommendation userAnalysis;
  
      if(request.getMethod().equals("GET")) { // GET request ó��          
          if(kind.equals("0")) { //phone
            System.out.println("����� ���ٰٰ���" + pid);
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
      
          System.out.println("�������� �̵�");
          return "redirect:/main";
       }
       else if(request.getMethod().equals("POST")) { //POST request ó�� (���)
          System.out.println("DetailProductController: POST REQUEST ó�� ���� ");
          String review = request.getParameter("review");  
          String kind2 = request.getParameter("kind");   
          String pId = request.getParameter("pId"); 
          
          HttpSession session = request.getSession();
          String id = UserSessionUtils.getLoginUserId(session);
         
          System.out.println(review+ "--" + id + "--" + pId);
          PComment pcm = new PComment(review, id, pId);
          int num = pcommentDAO.insertPComment(pcm);
      
          if(kind2.equals("0")) { //phone
            System.out.println("����� ����Ʈ����Ʈ��Ʈ��������");
             Phone phoneDetail = phoneDAO.getPhoneById(pId);
             List<PComment> plist = pcommentDAO.getPCommentList();
             System.out.println(num + "��ŭ �μ�Ʈ�ߴ�.");
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
      
          System.out.println("�������� �̵�");
          return "redirect:/main";
       }
       System.out.println("DetailProductController: ����");
       return "redirect:/main";
   }
}