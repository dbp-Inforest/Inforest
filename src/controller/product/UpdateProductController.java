package controller.product;
import java.sql.Date;
//update는 관리자가 하는 것임  이번 중간구현 이후에 할 것 
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UpdateUserController;
import model.dao.CameraDAO;
import model.dao.InforestUserDAO;
import model.dao.LaptopDAO;
import model.dao.PhoneDAO;
import model.dto.Camera;
import model.dto.InforestUser;
import model.dto.Laptop;
import model.dto.Phone;
import model.dto.Tablet;


public class UpdateProductController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UpdateProductController.class);
    
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	int kind = Integer.valueOf(request.getParameter("kind"));
    	
		try {
			System.out.println("자 여기까지 왔다 switch문 시작");
	    	switch(kind) {
		    	case 0:
		    		Phone phone = new Phone();
		    		PhoneDAO phoneDAO = new PhoneDAO();
		    		
		    		System.out.println("자 여기까지 왔다 객체만듬");
		    		phone.setpBattery(request.getParameter("pBattery"));
		    		phone.setpMemory(request.getParameter("pMemory"));
		    		phone.setpDisplay(request.getParameter("pDisplay"));
		    		phone.setpRAM(request.getParameter("pRam"));
		    		phone.setpSize(Double.valueOf(request.getParameter("pSize")));
		    		phone.setpCamera(request.getParameter("pCamera"));
		    		phone.setProductId(request.getParameter("productId"));
		    		phone.setpOS(request.getParameter("pOS"));
		            //--
		    		phone.setName(request.getParameter("pName"));
		    		phone.setColor(request.getParameter("pColor"));
		    		phone.setPrice(request.getParameter("pPrice"));
		    		phone.setBrand(request.getParameter("pBrand"));
		    		phone.setReleased_date(Date.valueOf(request.getParameter("pDate")));
		    		phone.setWeight(Double.valueOf(request.getParameter("pWeight")));
		    		phone.setpKind(Integer.valueOf(request.getParameter("pKind")));
		    		
		    		phoneDAO.updatePhone(phone);
		        	log.debug("Update Phone : {}", phone.getName());
		    		System.out.println("폰 수정 완료!");
		    		break;
		    	case 1:
		    		Laptop laptop = new Laptop(); 
		    		
		    		LaptopDAO laptopDAO = new LaptopDAO();
		    		laptopDAO.insertLaptop(laptop);
		            log.debug("Create Product : {}", laptop);
		    		break;
		    	case 2:
		    		Camera camera = new Camera();
		    		
		    		CameraDAO cameraDAO = new CameraDAO();
		    		cameraDAO.insertCamera(camera);
		            log.debug("Create Product : {}", camera);
		    		break;
		    	case 3:
		    		Tablet tablet = new Tablet();
		            log.debug("Create Product : {}", tablet);
		    		break;
		    	default:
		    		System.out.println("자 여기까지 왔다 default문");
	    	}
	        return "redirect:/product";		// 성공 시 관리 화면으로 redirect
		} catch (Exception e) {		// 예외 발생 시 회원가입 form으로 forwarding
            request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			e.printStackTrace();
			System.out.println("Error in insertProduct");
			return "redirect:/main";
		}
	}

}
