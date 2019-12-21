package controller.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.dao.*;
import model.dto.*;

public class ListProductController implements Controller {
   // private static final int countPerPage = 100;   // 한 화면에 출력할 사용자 수
   
   private PhoneDAO phoneDAO = new PhoneDAO(); 
   private LaptopDAO laptopDAO = new LaptopDAO();
   private CameraDAO cameraDAO = new CameraDAO();
   private TabletDAO tabletDAO = new TabletDAO();
   private ProductLikeDAO manager = new ProductLikeDAO();
   
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)   throws Exception {
        HttpSession session = request.getSession();
       String userId = (String)session.getAttribute("userId");
       
       System.out.println("폰 리스트 컨트롤러로 넘어왔음.");
        String kind = request.getParameter("kind3"); //0,1,2,3
        String page = request.getParameter("page");
        String searchWord = request.getParameter("searchWord");
       
        System.out.print(kind + "debugtest\n");
        System.out.println("어디서 옴? : " + page + "\n");
       System.out.println("검색어는? : " + searchWord + "\n");
       
       if(request.getMethod().equals("GET")) {

          
          if(kind.equals("0")) { //phone
             System.out.println(kind + " debugtest in kind = 0");
             List<Phone> phoneList = phoneDAO.getPhoneList();
            List<String> myProdList_p = manager.getPhoneLikeListById(userId);
             request.setAttribute("phoneList", phoneList);
             request.setAttribute("myProdList", myProdList_p);
             
             if(page == null) {
                return "/phone.jsp";   
             }
             else {
                return "redirect:/productSearch?kind2=" + kind + "&page=" + page + "&searchWord=" + searchWord;
             }
          }else if(kind.equals("1")){ //laptop
             System.out.println(kind + " debugtest in kind = 1");
             List<Laptop> laptopList = laptopDAO.getLaptopList();
             List<String> myProdList_l = manager.getLaptopLikeListById(userId);
             request.setAttribute("laptopList", laptopList);
             request.setAttribute("myProdList", myProdList_l);
             
             if(page == null) {
                return "/laptop.jsp";   
             }
             else {
                return "redirect:/productSearch?kind2=" + kind + "&page=" + page + "&searchWord=" + searchWord;
             }
          }else if(kind.equals("2")) { //camera
             System.out.println(kind + " debugtest in kind = 2");
             List<Camera> cameraList = cameraDAO.getCameraList();
             List<String> myProdList_c = manager.getCameraLikeListById(userId);
             request.setAttribute("cameraList", cameraList);
             request.setAttribute("myProdList", myProdList_c);
             
             if(page == null) {
                return "/camera.jsp";   
             }
             else {
                return "redirect:/productSearch?kind2=" + kind + "&page=" + page + "&searchWord=" + searchWord;
             }
          }else if(kind.equals("3")) { //tablet
             System.out.println(kind + " debugtest in kind = 3");
             List<Tablet> tabletList = tabletDAO.getTabletList();
             List<String> myProdList_t = manager.getTabletLikeListById(userId);
             request.setAttribute("tabletList", tabletList);
             request.setAttribute("myProdList", myProdList_t);
             
             if(page == null) {
                return "/tablet.jsp";   
             }
             else {
                return "redirect:/productSearch?kind2=" + kind + "&page=" + page + "&searchWord=" + searchWord;
             }   
          }else if(kind.equals("4")) { //product
             System.out.println(kind + " debugtest in kind = 4");
             
             //phone list와 phone like list
             List<Phone> phoneList = phoneDAO.getPhoneList();
            List<String> myProdList_p = manager.getPhoneLikeListById(userId);
             request.setAttribute("phoneList", phoneList);
             request.setAttribute("myProdList_p", myProdList_p);
             
             //laptop list와 laptop like list
             List<Laptop> laptopList = laptopDAO.getLaptopList();
             List<String> myProdList_l = manager.getLaptopLikeListById(userId);
             request.setAttribute("laptopList", laptopList);
             request.setAttribute("myProdList_l", myProdList_l);
             
             //camera list와  camera like list
             List<Camera> cameraList = cameraDAO.getCameraList();
             List<String> myProdList_c = manager.getCameraLikeListById(userId);
             request.setAttribute("cameraList", cameraList);
             request.setAttribute("myProdList_c", myProdList_c);
             
             //tablet list와 tablet like list
             List<Tablet> tabletList = tabletDAO.getTabletList();
             List<String> myProdList_t = manager.getTabletLikeListById(userId);
             request.setAttribute("tabletList", tabletList);
             request.setAttribute("myProdList_t", myProdList_t);
             
             if(page == null) {
                return "/product.jsp";   
             }
             else {
                return "redirect:/productSearch?kind2=" + kind + "&page=" + page + "&searchWord=" + searchWord;
             }   
          }
          
          List<Phone> phone = phoneDAO.getPhoneList();
          request.setAttribute("phone", phone);
                 
             System.out.print("GET-PHONE으로");
             return "redirect:/main";
          }
       System.out.print("phone으로");
       return "redirect:/main";
       
          
    }
}