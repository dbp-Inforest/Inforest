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
	

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

    	if(request.getMethod().equals("GET")) {
    		String kind = request.getParameter("kind2"); //0,1,2,3
    		System.out.print(kind + "debugtest");
    		
    		if(kind.equals("0")) { //phone
    			System.out.println(kind + " debugtest in kind = 0");
    			List<Phone> phoneList = phoneDAO.getPhoneList();
    			request.setAttribute("phoneList", phoneList);
    			return "/product-search.jsp";	
    		}else if(kind.equals("1")){ //laptop
    			List<Laptop> laptopList = laptopDAO.getLaptopList();
    			request.setAttribute("laptopList", laptopList);
    			return "/product-search.jsp";	
    		}else if(kind.equals("2")) { //camera
    			List<Camera> cameraList = cameraDAO.getCameraList();
    			request.setAttribute("cameraList", cameraList);
    			return "/product-search.jsp";		
    		}else if(kind.equals("3")) { //tablet
    			List<Tablet> tabletList = tabletDAO.getTabletList();
    			request.setAttribute("tabletList", tabletList);
    			return "/product-search.jsp";	
    		}
    		
    		List<Phone> phone = phoneDAO.getPhoneList();
    		request.setAttribute("phone", phone);
    		 		
       		return "redirect:/product";
       	}
		
    	return "redirect:/product";
	}
}
