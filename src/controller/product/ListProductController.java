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
	// private static final int countPerPage = 100;	// 한 화면에 출력할 사용자 수
	
	private PhoneDAO phoneDAO = new PhoneDAO(); 
	private LaptopDAO laptopDAO = new LaptopDAO();
	private CameraDAO cameraDAO = new CameraDAO();
	private TabletDAO tabletDAO = new TabletDAO();
	
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
       
    	if(request.getMethod().equals("GET")) {
    		String kind = request.getParameter("kind3"); //0,1,2,3
    		System.out.print(kind + "debugtest");
    		
    		if(kind.equals("0")) { //phone
    			System.out.println(kind + " debugtest in kind = 0");
    			List<Phone> phoneList = phoneDAO.getPhoneList();
    			request.setAttribute("phoneList", phoneList);
    			return "/phone.jsp";	
    		}else if(kind.equals("1")){ //laptop
    			List<Laptop> laptopList = laptopDAO.getLaptopList();
    			request.setAttribute("laptopList", laptopList);
    			return "/laptop.jsp";	
    		}else if(kind.equals("2")) { //camera
    			List<Camera> cameraList = cameraDAO.getCameraList();
    			request.setAttribute("cameraList", cameraList);
    			return "/camera.jsp";	
    		}else if(kind.equals("3")) { //tablet
    			List<Tablet> tabletList = tabletDAO.getTabletList();
    			request.setAttribute("tabletList", tabletList);
    			return "/tablet.jsp";	
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
