package controller.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.dao.*;
import model.dto.*;

//delete는 관리자가 하는 것임  이번 중간구현 이후에 할 것 
public class DeleteProductController implements Controller {

	private static final Logger log = LoggerFactory.getLogger(DeleteProductController.class);
	
	private PhoneDAO phoneDAO = new PhoneDAO(); 
	private LaptopDAO laptopDAO = new LaptopDAO();
	private CameraDAO cameraDAO = new CameraDAO();
	private TabletDAO tabletDAO = new TabletDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		String kind = null;
		String pId = null;
		
    	if(request.getMethod().equals("POST")) {
    		kind = (String)request.getParameter("kind"); //0,1,2,3
    		pId = (String)request.getParameter("pId");
    		System.out.println("kind: "+ kind + " pid: " + pId);
    		
    		if(kind.equals("0")) { //phone
    			phoneDAO.deletePhone(pId);
    			System.out.println("Phone 삭제 완료 : " + pId);
    	        return "/product";	
    	        
    		}else if(kind.equals("1")){ //laptop
    			laptopDAO.deleteLaptop(pId);
    			System.out.println("Laptop 삭제 완료 : " + pId);
    	        return "/product";
   
    	    }else if(kind.equals("2")) { //camera
    			cameraDAO.deleteCamera(pId);
    			System.out.println("Camera 삭제 완료 : " + pId);
    	        return "/product";	
    	        
    		}else if(kind.equals("3")) { //tablet
    			tabletDAO.deleteTablet(pId);
    			System.out.println("Tablet 삭제 완료 : " + pId);
    	        return "/product";	
    		}		
		
    		log.debug("Delete Product : {}", pId);
    		System.out.println("메인으로 이동");
    		return "redirect:/main";
		
    	}
    	return "redirect:/productList?kind3=" + kind;
	}

}
