package controller.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.dao.*;
import model.dto.*;

//검색창에 입력한 productid로 제품 목록 보여주기 .. 
//입력 할 수 있는 값을 상품명? 브랜드? 등등 가능??
//검색창은 모든 페이지에서 불러올수 있도록 jsp구현??
public class ViewProductController implements Controller {
   private PhoneDAO phoneDAO = new PhoneDAO(); 
   private LaptopDAO laptopDAO = new LaptopDAO();
   private CameraDAO cameraDAO = new CameraDAO();
   private TabletDAO tabletDAO = new TabletDAO();
   private ProductLikeDAO manager = new ProductLikeDAO();

   @Override
   public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      // TODO Auto-generated method stub

      HttpSession session = request.getSession();
       String userId = (String)session.getAttribute("userId");
       
       if(request.getMethod().equals("GET")) {
          String kind = request.getParameter("kind2"); //0,1,2,3,4
          String psearch = request.getParameter("phoneSearch"); //phone.jsp 에서의 검색어
          String lsearch = request.getParameter("laptopSearch"); //laptop.jsp 에서의 검색어
          String csearch = request.getParameter("cameraSearch"); //camera.jsp 에서의 검색어
          String tsearch = request.getParameter("tabletSearch"); //tablet.jsp 에서의 검색어
          String allsearch = request.getParameter("allSearch"); //product.jsp 에서의 검색어
          System.out.print(kind + "debugtest");
          
          String page = request.getParameter("page");
          String searchWord = request.getParameter("searchWord"); //product-search.jsp 에서 받아온 검색어
          
          if(kind.equals("0")) { //phone
             System.out.println(kind + " debugtest in kind = 0");
             List<Phone> phoneList;
             List<String> myProdList_p;
             
             if (page == null) {
                phoneList = phoneDAO.getPhoneByName(psearch);
                myProdList_p = manager.getPhoneLikeListById(userId);
                
                request.setAttribute("searchWord", psearch);
                 request.setAttribute("ListByName", phoneList);
                 request.setAttribute("myProdList", myProdList_p);
                 request.setAttribute("kind", kind);
                 
                 return "/product-search.jsp";   
             }
             else {
                phoneList = phoneDAO.getPhoneByName(searchWord);
                myProdList_p = manager.getPhoneLikeListById(userId);
                
                request.setAttribute("searchWord", searchWord);
                 request.setAttribute("ListByName", phoneList);
                 request.setAttribute("myProdList", myProdList_p);
                 request.setAttribute("kind", kind);
             }
             return "/product-search.jsp";   
          }else if(kind.equals("1")){ //laptop
             List<Laptop> laptopList;
             List<String> myProdList_l;
             
             if (page == null) {
                laptopList = laptopDAO.getLaptopByName(lsearch);
                myProdList_l = manager.getLaptopLikeListById(userId);
                
                request.setAttribute("searchWord", lsearch);
                 request.setAttribute("ListByName", laptopList);
                 request.setAttribute("myProdList", myProdList_l);
                 request.setAttribute("kind", kind);
                 
                 return "/product-search.jsp";   
             }
             else {
                laptopList = laptopDAO.getLaptopByName(searchWord);
                myProdList_l = manager.getLaptopLikeListById(userId);
                
                request.setAttribute("searchWord", searchWord);
                request.setAttribute("ListByName", laptopList);
                 request.setAttribute("myProdList", myProdList_l);
                 request.setAttribute("kind", kind);
             }
             return "/product-search.jsp";      
          }else if(kind.equals("2")) { //camera
             List<Camera> cameraList;
             List<String> myProdList_c;
             
             if (page == null) {
                cameraList = cameraDAO.getCameraByName(csearch);
                myProdList_c = manager.getCameraLikeListById(userId);
                
                request.setAttribute("searchWord", csearch);
                 request.setAttribute("ListByName", cameraList);
                 request.setAttribute("myProdList", myProdList_c);
                 request.setAttribute("kind", kind);
                 
                 return "/product-search.jsp";   
             }
             else {
                cameraList = cameraDAO.getCameraByName(searchWord);
                myProdList_c = manager.getCameraLikeListById(userId);
                
                request.setAttribute("searchWord", searchWord);
                request.setAttribute("ListByName", cameraList);
                 request.setAttribute("myProdList", myProdList_c);
                 request.setAttribute("kind", kind);
             }
             return "/product-search.jsp";         
          }else if(kind.equals("3")) { //tablet
             List<Tablet> tabletList;
             List<String> myProdList_t;
             
             if (page == null) {
                tabletList = tabletDAO.getTabletByName(tsearch);
                myProdList_t = manager.getTabletLikeListById(userId);
                
                request.setAttribute("searchWord", tsearch);
                 request.setAttribute("ListByName", tabletList);
                 request.setAttribute("myProdList", myProdList_t);
                 request.setAttribute("kind", kind);
                 
                 return "/product-search.jsp";   
             }
             else {
                tabletList = tabletDAO.getTabletByName(searchWord);
                myProdList_t = manager.getTabletLikeListById(userId);
                
                request.setAttribute("searchWord", searchWord);
                request.setAttribute("ListByName", tabletList);
                 request.setAttribute("myProdList", myProdList_t);
                 request.setAttribute("kind", kind);
             }             
             return "/product-search.jsp";   
          }else if(kind.equals("4")) { //all product
             List<Phone> phoneList;
             List<Laptop> laptopList;
             List<Camera> cameraList;
             List<Tablet> tabletList;
             
             List<String> myProdList_p;
             List<String> myProdList_l;
             List<String> myProdList_c;
             List<String> myProdList_t;
             
             
             if (page == null) {
                phoneList = phoneDAO.getPhoneByName(allsearch);
                laptopList = laptopDAO.getLaptopByName(allsearch);
                cameraList = cameraDAO.getCameraByName(allsearch);
                tabletList = tabletDAO.getTabletByName(allsearch);
                
                
                myProdList_p = manager.getPhoneLikeListById(userId);
                 myProdList_l = manager.getLaptopLikeListById(userId);
                 myProdList_c = manager.getCameraLikeListById(userId);
                 myProdList_t = manager.getTabletLikeListById(userId);
                 
                 
                
                request.setAttribute("searchWord", allsearch);
                 request.setAttribute("PhoneListByName", phoneList);
                 request.setAttribute("LaptopListByName", laptopList);
                 request.setAttribute("CameraListByName", cameraList);
                 request.setAttribute("TabletListByName", tabletList);
                 request.setAttribute("myProdList_p", myProdList_p);
                 request.setAttribute("myProdList_l", myProdList_l);
                 request.setAttribute("myProdList_c", myProdList_c);
                 request.setAttribute("myProdList_t", myProdList_t);
                 
                 request.setAttribute("kind", kind);
                 
                 return "/product-search.jsp";   
             }
             else {
                phoneList = phoneDAO.getPhoneByName(searchWord);
                laptopList = laptopDAO.getLaptopByName(searchWord);
                cameraList = cameraDAO.getCameraByName(searchWord);
                tabletList = tabletDAO.getTabletByName(searchWord);
                
                
                myProdList_p = manager.getPhoneLikeListById(userId);
                 myProdList_l = manager.getLaptopLikeListById(userId);
                 myProdList_c = manager.getCameraLikeListById(userId);
                 myProdList_t = manager.getTabletLikeListById(userId);
                 
                 
                
                request.setAttribute("searchWord", searchWord);
                 request.setAttribute("PhoneListByName", phoneList);
                 request.setAttribute("LaptopListByName", laptopList);
                 request.setAttribute("CameraListByName", cameraList);
                 request.setAttribute("TabletListByName", tabletList);
                 request.setAttribute("myProdList_p", myProdList_p);
                 request.setAttribute("myProdList_l", myProdList_l);
                 request.setAttribute("myProdList_c", myProdList_c);
                 request.setAttribute("myProdList_t", myProdList_t);
                 
                 request.setAttribute("kind", kind);
             }
             return "/product-search.jsp";   
          }
          
          List<Phone> phone = phoneDAO.getPhoneList();
          request.setAttribute("phone", phone);
                 
             return "redirect:/product";
          }
      
       return "redirect:/product";
   }
}
