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
       		
    		String kind = request.getParameter("kind"); //0,1,2,3,4
    		
    		if(kind.equals("0")) { //phone
    			List<Phone> phone = phoneDAO.getPhoneList();
    			request.setAttribute("phone", phone);
    			return "/phone.jsp";	
    		}else if(kind.equals("1")){ //laptop
    			List laptop = laptopDAO.getLaptopList();
    			request.setAttribute("laptop", laptop);
    			return "/laptop.jsp";	
    		}else if(kind.equals("2")) { //camera
    			List camera = cameraDAO.getCameraList();
    			request.setAttribute("camera", camera);
    			return "/camera.jsp";	
    		}else if(kind.equals("3")) { //tablet
    			List tablet = tabletDAO.getTabletList();
    			request.setAttribute("tablet", tablet);
    			return "/tablet.jsp";	
    		}else if(kind.equals("4")) { //all
    			List phone = phoneDAO.getPhoneList();
    			List laptop = laptopDAO.getLaptopList();
    			List camera = cameraDAO.getCameraList();
    			List tablet = tabletDAO.getTabletList();
    			request.setAttribute("phone", phone);
    			request.setAttribute("laptop", laptop);
    			request.setAttribute("camera", camera);
    			request.setAttribute("tablet", tablet);
    			return "/product.jsp";	
    		}	
    		return "/product.jsp";
    }
}
